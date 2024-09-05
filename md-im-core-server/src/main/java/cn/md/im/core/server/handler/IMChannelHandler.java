package cn.md.im.core.server.handler;


import cn.md.im.core.common.model.IMCmdType;
import cn.md.im.core.server.model.IMMessageHolder;
import cn.md.im.core.server.processor.MessageProcessor;
import cn.md.im.core.server.processor.ProcessorFactory;
import com.alibaba.fastjson.JSON;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelId;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * * @Author: Martin
 * * @Date    2024/9/3 15:23
 * * @Description
 **/

public class IMChannelHandler extends SimpleChannelInboundHandler<IMMessageHolder> {

    private final Logger logger = LoggerFactory.getLogger(IMChannelHandler.class);


    /**
     * Is called for each message of type {@link I}.
     *
     * @param ctx the {@link ChannelHandlerContext} which this {@link SimpleChannelInboundHandler}
     *            belongs to
     * @param msg the message to handle
     * @throws Exception is thrown if an error occurred
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, IMMessageHolder msg) throws Exception {
        logger.info("IMChannelHandler channelRead0: {}" ,JSON.toJSONString(msg));
        MessageProcessor processor = ProcessorFactory.getProcessor(IMCmdType.fromCode(msg.getCmd()));
        if (processor == null) {
            logger.error("IMChannelHandler.channelRead0 processor is null,Params:{}",msg);
            return;
        }
        processor.process(ctx,processor.transForm(msg));
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        ChannelId id = ctx.channel().id();
        logger.info("IMChannelHandler.handlerAdded|{}连接", id.asLongText());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        ChannelId id = ctx.channel().id();
        logger.info("IMChannelHandler.handlerRemoved|{}断开连接", id.asLongText());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ChannelId id = ctx.channel().id();
        logger.info("IMChannelHandler.channelActive|{}连接", id.asLongText());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        ChannelId id = ctx.channel().id();
        logger.info("IMChannelHandler.channelInactive|{}断开连接", id.asLongText());



    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.error("IMChannelHandler.exceptionCaught|异常:{}", cause.getMessage());
    }
}
