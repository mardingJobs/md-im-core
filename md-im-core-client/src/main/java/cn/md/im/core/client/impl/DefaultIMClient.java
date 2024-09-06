package cn.md.im.core.client.impl;

import cn.md.im.core.client.IMClient;
import cn.md.im.core.common.enums.IMTerminalType;
import cn.md.im.core.common.model.IMGroupMessage;
import cn.md.im.core.common.model.IMPrivateMessage;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * * @Author: Martin
 * * @Date    2024/9/6 20:28
 * * @Description
 **/
public class DefaultIMClient implements IMClient {

    /**
     * 发送私聊消息
     *
     * @param message
     */
    @Override
    public <T> void sendPrivateMessage(IMPrivateMessage<T> message) {

    }

    /**
     * 发送群消息
     *
     * @param message
     */
    @Override
    public <T> void sendGroupMessage(IMGroupMessage<T> message) {

    }

    /**
     * 判断用户是否在线
     *
     * @param userId
     */
    @Override
    public Boolean isOnline(Long userId) {
        return null;
    }

    /**
     * 筛选出在线的用户
     *
     * @param userIds
     */
    @Override
    public List<Long> getOnlineUserList(List<Long> userIds) {
        return Collections.emptyList();
    }

    /**
     * 获取用户与其在线的终端列表
     *
     * @param userIds
     */
    @Override
    public Map<Long, List<IMTerminalType>> getOnlineTerminal(List<Long> userIds) {
        return Collections.emptyMap();
    }
}
