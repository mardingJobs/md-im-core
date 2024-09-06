package cn.md.im.core.client;

import cn.md.im.core.common.enums.IMTerminalType;
import cn.md.im.core.common.model.IMGroupMessage;
import cn.md.im.core.common.model.IMPrivateMessage;

import java.util.List;
import java.util.Map;

/**
 * IM核心客户端
 * * @Author: jack
 * * @Date    2024/9/6 17:30
 **/
public interface IMClient {

    /**
     * 发送私聊消息
     */
    <T> void sendPrivateMessage(IMPrivateMessage<T> message);

    /**
     * 发送群消息
     */
    <T> void sendGroupMessage(IMGroupMessage<T> message);

    /**
     * 判断用户是否在线
     */
    Boolean isOnline(Long userId);

    /**
     * 筛选出在线的用户
     */
    List<Long> getOnlineUserList(List<Long> userIds);

    /**
     * 获取用户与其在线的终端列表
     */
    Map<Long,List<IMTerminalType>> getOnlineTerminal(List<Long> userIds);


}
