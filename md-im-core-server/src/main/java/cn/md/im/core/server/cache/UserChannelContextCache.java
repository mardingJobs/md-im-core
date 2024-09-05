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
package cn.md.im.core.server.cache;

import io.netty.channel.ChannelHandlerContext;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author binghe(微信 : hacker_binghe)
 * @version 1.0.0
 * @description 用户Chanel连接上下文缓存
 * @github https://github.com/binghe001
 * @copyright 公众号: 冰河技术
 */
public class UserChannelContextCache {

    /**
     * 缓存userId和ChannelHandlerContext的关系
     * 主要格式：Map<userId, Map<terminal, ctx>>
     */
    private static Map<Long, Map<Integer, ChannelHandlerContext>> channelMap = new ConcurrentHashMap<>();

    public static void addChannelCtx(Long userId, Integer channel, ChannelHandlerContext ctx){
        channelMap.computeIfAbsent(userId, key -> new ConcurrentHashMap<>()).put(channel, ctx);
    }

    public static void removeChannelCtx(Long userId, Integer terminal){
        if (userId != null && terminal != null && channelMap.containsKey(userId)){
            Map<Integer, ChannelHandlerContext> userChannelMap = channelMap.get(userId);
            if (userChannelMap.containsKey(terminal)){
                userChannelMap.remove(terminal);
            }
        }
    }

    public static ChannelHandlerContext getChannelCtx(Long userId, Integer terminal){
        if (userId != null && terminal != null && channelMap.containsKey(userId)){
            Map<Integer, ChannelHandlerContext> userChannelMap = channelMap.get(userId);
            if (userChannelMap.containsKey(terminal)){
                return userChannelMap.get(terminal);
            }
        }
        return null;
    }

    public static Map<Integer, ChannelHandlerContext> getChannelCtx(Long userId){
        if (userId == null){
            return null;
        }
        return channelMap.get(userId);
    }

}
