package cn.md.im.core.server.processor;


import cn.md.im.core.server.model.IMMessageHolder;
import io.netty.channel.ChannelHandlerContext;


public interface MessageProcessor<T> {

    /**
     * 处理数据
     */
    default void process(ChannelHandlerContext ctx, T data){

    }

    /**
     * 转化数据
     */
    default T transForm(IMMessageHolder obj){
        return (T) obj.getData();
    }

}
