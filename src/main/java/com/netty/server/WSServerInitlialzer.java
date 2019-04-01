/**
 * @author: maxu1
 * @date: 2019/1/30 20:27
 */

package com.netty.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 *
 * @author maxu
 */
public class WSServerInitlialzer extends ChannelInitializer<SocketChannel> {

	@Override
	protected void initChannel(SocketChannel channel) throws Exception {
		ChannelPipeline pipeline = channel.pipeline();
		// http编码器
		pipeline.addLast(new HttpServerCodec());
		// 对写大数据流支持
		pipeline.addLast(new ChunkedWriteHandler());
		// 对httpMessage进行聚合，聚合成FullHttpRequest或者FullHttpResponse
		pipeline.addLast(new HttpObjectAggregator(1024*64));
		// 本handler会帮你处理一些繁重的复杂的事情
		// 会帮你处理握手动作，handshaking  ping + pong = 心跳
		pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
		pipeline.addLast(null);

	}
}
