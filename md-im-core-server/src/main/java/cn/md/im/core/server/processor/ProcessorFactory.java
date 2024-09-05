package cn.md.im.core.server.processor;


import cn.md.im.core.server.holder.SpringContextHolder;
import cn.md.im.core.common.model.IMCmdType;

public class ProcessorFactory {

    public static MessageProcessor<?> getProcessor(IMCmdType cmd){
        switch (cmd){
            //登录
            case LOGIN:
                return SpringContextHolder.getApplicationContext().getBean(AccessTokenMsgProcessor.class);
            //心跳
//            case HEART_BEAT:
//                return SpringContextHolder.getApplicationContext().getBean(HeartbeatProcessor.class);
//            //单聊消息
//            case PRIVATE_MESSAGE:
//                return SpringContextHolder.getApplicationContext().getBean(PrivateMessageProcessor.class);
//            //群聊消息
//            case GROUP_MESSAGE:
//                return SpringContextHolder.getApplicationContext().getBean(GroupMessageProcessor.class);
            default:
                return null;

        }
    }
}