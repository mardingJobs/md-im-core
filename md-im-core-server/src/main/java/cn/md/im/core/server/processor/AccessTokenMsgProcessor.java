package cn.md.im.core.server.processor;

import cn.md.im.core.common.cache.DistributedCacheService;
import cn.md.im.core.common.constants.IMConstants;
import cn.md.im.core.common.jwt.JwtUtils;
import cn.md.im.core.common.model.IMMsgType;
import cn.md.im.core.common.model.IMAccessToken;
import cn.md.im.core.common.model.IMUserSession;
import cn.md.im.core.server.cache.UserChannelContextCache;
import cn.md.im.core.server.model.IMMessageHolder;
import com.alibaba.fastjson.JSON;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * * @Author: Martin
 * * @Date    2024/9/3 16:17
 * * @Description
 **/
@Component
public class AccessTokenMsgProcessor implements MessageProcessor<IMAccessToken> {

    private Logger logger = LoggerFactory.getLogger(AccessTokenMsgProcessor.class);

    @Value("${jwt.accessToken.secret}")
    private String accessTokenSecret;

    @Resource
    private DistributedCacheService distributedCache;


    @Value("${server.id}")
    private Long serverId;

    @Override
    public void process(ChannelHandlerContext ctx, IMAccessToken token) {
        String accessToken = token.getAccessToken();
        logger.info("收到登录请求，accessToken:{}", accessToken);

        // 登陆校验
        if (!JwtUtils.checkSign(token.getAccessToken(), accessTokenSecret)) {
            logger.warn("LoginProcessor.process|用户登录信息校验未通过,强制用户下线,token:{}", accessToken);
            ctx.channel().close();
            return;
        }

        String info = JwtUtils.getInfo(accessToken);
        IMUserSession userSession = JSON.parseObject(info, IMUserSession.class);
        if (userSession == null){
            logger.warn("LoginProcessor.process|转化后的SessionInfo为空");
            return;
        }

        Long userId = userSession.getUserId();
        Integer terminal = userSession.getTerminal();
        logger.info("LoginProcessor.process|用户登录, userId:{}", userId);

        // 获取通道信息
        ChannelHandlerContext channelCtx = UserChannelContextCache.getChannelCtx(userId, terminal);
        if (channelCtx == null) {
            // 缓存用户客户端和channel的关系
            UserChannelContextCache.addChannelCtx(userId, terminal, ctx);
        }

        // 缓存用户客户端连接的哪一个服务，如果用户长时间不操作后，缓存过期了，找不到服务，怎么处理？
        // 使用IdleStateHandler，读过期时间要小于缓存过期时间，这样会自动断开链接。
        // 针对断链重试的事情，客户端判断是否是主动断开链接，如果是主动的话，就不需要重新尝试了。
        String userServerKey = String.join(IMConstants.REDIS_KEY_SPLIT,IMConstants.IM_USER_SERVER_ID, userId.toString(), terminal.toString());
        // 设置5分钟超时时间
        distributedCache.set(userServerKey, serverId.toString(), IMConstants.ONLINE_TIMEOUT_SECONDS, TimeUnit.SECONDS);

        // 响应ws
        IMMessageHolder<?> holder = new IMMessageHolder<>();
        holder.setType(IMMsgType.LOGIN.code());
        ctx.channel().writeAndFlush(holder);
    }


    @Override
    public IMAccessToken transForm(IMMessageHolder obj) {
        return JSON.parseObject(JSON.toJSONString(obj.getData()), IMAccessToken.class);
    }
}
