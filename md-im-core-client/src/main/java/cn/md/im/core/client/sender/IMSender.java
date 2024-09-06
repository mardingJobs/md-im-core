/**
 * Copyright 2022-9999 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.md.im.core.client.sender;
import cn.md.im.core.common.enums.IMTerminalType;
import cn.md.im.core.common.model.IMGroupMessage;
import cn.md.im.core.common.model.IMPrivateMessage;

import java.util.List;
import java.util.Map;


public interface IMSender {

    /**
     * 发送私聊消息
     */
    <T> void sendPrivateMessage(IMPrivateMessage<T> message);

    /**
     * 发送群聊消息
     */
    <T> void sendGroupMessage(IMGroupMessage<T> message);

    /**
     * 获取在线终端数据
     * key-用户id，value-当前用户的终端列表
     */
    Map<Long, List<IMTerminalType>> getOnlineTerminal(List<Long> userIds);

    /**
     * 判断用户是否在线
     */
    Boolean isOnline(Long userId);

    /**
     * 筛选在线的用户
     */
    List<Long> getOnlineUser(List<Long> userIds);
}
