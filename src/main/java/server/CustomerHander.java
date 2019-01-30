/**
 * @author: maxu1
 * @date: 2019/1/30 19:08
 */

package server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

/**
 * 创建自定义助手类
 *
 * @author maxu
 */
public class CustomerHander extends SimpleChannelInboundHandler<HttpObject> {

	@Override
	protected void channelRead0(ChannelHandlerContext channelHandlerContext, HttpObject httpObject) throws Exception {
		// 获取channel
		Channel channel = channelHandlerContext.channel();
		// 显示客户端的远程地址
		if (httpObject instanceof HttpRequest) {
			System.out.println(channel.remoteAddress());
			// 缓冲区，将数据拷贝到缓冲区
			ByteBuf content = Unpooled.copiedBuffer("Hello.netty", CharsetUtil.UTF_8);
			// 构建一个HttpResponse
			FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_0,HttpResponseStatus.OK,content);
			response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
			response.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());
			// 把响应加到客户端
			channelHandlerContext.writeAndFlush(response);
		}
	}

	@Override
	public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
		System.out.println("注册");
		super.channelRegistered(ctx);
	}

	@Override
	public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
		System.out.println("移除");
		super.channelUnregistered(ctx);
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("活跃");
		super.channelActive(ctx);
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("不活");
		super.channelInactive(ctx);
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		System.out.println("读完了");
		super.channelReadComplete(ctx);
	}

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		System.out.println("用户事件");
		super.userEventTriggered(ctx, evt);
	}

	@Override
	public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
		System.out.println("channel可写更改");
		super.channelWritabilityChanged(ctx);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		System.out.println("捕获异常");
		super.exceptionCaught(ctx, cause);
	}


	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		System.out.println("助手类添加");
		super.handlerAdded(ctx);
	}

	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		System.out.println("助手类移除");
		super.handlerRemoved(ctx);
	}
}
