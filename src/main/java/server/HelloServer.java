/**
 * @author: maxu1
 * @date: 2019/1/30 16:51
 */

package server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author maxu
 */
public class HelloServer {
	public static void main(String[] args) throws InterruptedException {

		// 定义一对线程组,用于接受客户端的连接,但是不做任何处理
		// channel去注册
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		// 从线程组，老板线程组把任务丢给他，让手下去干活
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			// 服务端的启动类 ，netty服务器的创建
			ServerBootstrap serverBootstrap = new ServerBootstrap();
			// 设置主从线程组
			serverBootstrap.group(bossGroup, workerGroup)
					// 设置nio双向通道
					.channel(NioServerSocketChannel.class)
					// 子处理器，用于处理workerGroup
					.childHandler(new ServerInitializer());
			// 启动server,并且设置8088为启动端口号，同时启动为同步方式
			ChannelFuture future = serverBootstrap.bind(8088).sync();
			// 监听关闭的channel,设置为同步方式
			future.channel().closeFuture().sync();
		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}
}
