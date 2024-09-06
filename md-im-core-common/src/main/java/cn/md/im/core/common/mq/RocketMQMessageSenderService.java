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
package cn.md.im.core.common.mq;
import cn.md.im.core.common.constants.IMConstants;
import cn.md.im.core.common.model.TopicMessage;
import com.alibaba.fastjson.JSONObject;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;


@Component
@ConditionalOnProperty(name = "message.mq.type", havingValue = "rocketmq")
public class RocketMQMessageSenderService implements MessageSenderService {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Override
    public boolean send(TopicMessage message) {
        try{
            SendResult sendResult = rocketMQTemplate.syncSend(message.getDestination(), this.getMessage(message));
            return SendStatus.SEND_OK.equals(sendResult.getSendStatus());
        }catch (Exception e){
            return false;
        }
    }

//    @Override
//    public TransactionSendResult sendMessageInTransaction(TopicMessage message, Object arg) {
//        return rocketMQTemplate.sendMessageInTransaction(message.getDestination(), this.getMessage(message), arg);
//    }
//
    //构建ROcketMQ发送的消息
    private Message<String> getMessage(TopicMessage message){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(IMConstants.MSG_KEY, message);
        return MessageBuilder.withPayload(jsonObject.toJSONString()).build();
    }
}
