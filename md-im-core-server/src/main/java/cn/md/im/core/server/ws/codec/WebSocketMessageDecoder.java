package cn.md.im.core.server.ws.codec;

import cn.md.im.core.server.model.IMMessageHolder;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSON;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 将TextWebSocketFrame转换为IMMessageHolder
 * * @Author: Martin
 * * @Date    2024/9/3 14:59
 * * @Description
 **/
public class WebSocketMessageDecoder extends MessageToMessageDecoder<TextWebSocketFrame> {

    private Logger logger = LoggerFactory.getLogger(WebSocketMessageDecoder.class);

    /**
     * Decode from one message to an other. This method will be called for each written message that can be handled
     * by this decoder.
     *
     * @param ctx the {@link ChannelHandlerContext} which this {@link MessageToMessageDecoder} belongs to
     * @param msg the message to decode to an other one
     * @param out the {@link List} to which decoded messages should be added
     * @throws Exception is thrown if an error occurs
     */
    @Override
    protected void decode(ChannelHandlerContext ctx, TextWebSocketFrame msg, List<Object> out) throws Exception {
        String text = msg.text();
        logger.info("WebSocketMessageDecoder decode|{}",text);
        IMMessageHolder messageHolder = JSON.parseObject(text, IMMessageHolder.class);
        out.add(messageHolder);
    }
}
