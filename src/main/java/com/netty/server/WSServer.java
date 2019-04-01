/**
 * @author: maxu1
 * @date: 2019/1/30 20:22
 */

package com.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 *
 * @author maxu
 */
public class WSServer {
	public static void main(String[] args) throws InterruptedException {
		EventLoopGroup mainGroup = new NioEventLoopGroup();
		EventLoopGroup subGroup = new NioEventLoopGroup();

		try{
			ServerBootstrap serverBootstrap = new ServerBootstrap();
			serverBootstrap.group(mainGroup, subGroup)
					.channel(NioServerSocketChannel.class)
					.childHandler(new WSServerInitlialzer());
			ChannelFuture future = serverBootstrap.bind(8088).sync();
			future.channel().closeFuture().sync();
		} finally {
			mainGroup.shutdownGracefully();
			subGroup.shutdownGracefully();
		}
	}
}
