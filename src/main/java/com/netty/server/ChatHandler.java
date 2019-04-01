package com.netty.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.time.LocalDateTime;

/**
 * 处理消息的handler
 * TextWebsocketFrame：在netty中，是用于为websocket专门处理文本的对像，frame载体
 *
 * @author maxu
 */
public class ChatHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
	// 用于管理所有客户端所有的channel
	private static ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

	@Override
	protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame textWebSocketFrame) throws Exception {
		// 获取客户端传输过来的消息
		String content = textWebSocketFrame.text();
		System.out.println("接受到的数据");
//		for (Channel channel : clients) {
//			channel.writeAndFlush(new TextWebSocketFrame("接受到消息"+ LocalDateTime.now() +content));
//		}
		clients.writeAndFlush(new TextWebSocketFrame("接受到消息"+ LocalDateTime.now() +content));
	}

	/**
	 * 当客户端连接服务端之后，获取客户端channel，并且放到
	 * ChannelGroup中进行管理
	 *
	 * @param ctx
	 * @throws Exception
	 */
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		clients.add(ctx.channel());
		super.handlerAdded(ctx);
	}

	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		// 当触发handlerRemoved，ChannelGroup会自动移除客户端的channel
		// clients.remove(ctx.channel());
		super.handlerRemoved(ctx);
	}
}
