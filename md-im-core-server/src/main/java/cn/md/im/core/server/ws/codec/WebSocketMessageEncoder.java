package cn.md.im.core.server.ws.codec;

import cn.md.im.core.server.model.IMMessageHolder;
import com.alibaba.fastjson.JSON;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.util.List;

/**
 * 将IMMessageHolder转为TextWebSocketFrame
 * * @Author: Martin
 * * @Date    2024/9/3 14:59
 * * @Description
 **/
public class WebSocketMessageEncoder extends MessageToMessageEncoder<IMMessageHolder> {

    /**
     * Encode from one message to an other. This method will be called for each written message that can be handled
     * by this encoder.
     *
     * @param ctx the {@link ChannelHandlerContext} which this {@link MessageToMessageEncoder} belongs to
     * @param msg the message to encode to an other one
     * @param out the {@link List} into which the encoded msg should be added
     *            needs to do some kind of aggregation
     * @throws Exception is thrown if an error occurs
     */
    @Override
    protected void encode(ChannelHandlerContext ctx, IMMessageHolder msg, List<Object> out) throws Exception {
        String jsonString = JSON.toJSONString(msg);
        TextWebSocketFrame socketFrame = new TextWebSocketFrame(jsonString);
        out.add(socketFrame);
    }
}
