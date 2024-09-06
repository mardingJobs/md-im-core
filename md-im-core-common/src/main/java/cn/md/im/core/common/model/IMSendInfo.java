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
package cn.md.im.core.common.model;

/**
 * @author binghe(微信 : hacker_binghe)
 * @version 1.0.0
 * @description 发送信息
 * @github https://github.com/binghe001
 * @copyright 公众号: 冰河技术
 */
public class IMSendInfo<T> {

    /**
     * 命令类型 IMCmdType枚举的值
     */
    private Integer cmd;

    /**
     * 推送消息的数据
     */
    private T data;

    public IMSendInfo() {
    }

    public IMSendInfo(Integer cmd, T data) {
        this.cmd = cmd;
        this.data = data;
    }

    public Integer getCmd() {
        return cmd;
    }

    public void setCmd(Integer cmd) {
        this.cmd = cmd;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
