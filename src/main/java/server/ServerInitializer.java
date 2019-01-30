/**
 * @author: maxu1
 * @date: 2019/1/30 17:18
 */

package server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 *  初始化器
 * @author maxu
 */
public class ServerInitializer extends ChannelInitializer<SocketChannel> {

	@Override
	protected void initChannel(SocketChannel channel) throws Exception {
		// 通过SocketChannel获取对应的管道
		ChannelPipeline pipeline = channel.pipeline();
		// 通过管道添加handler
		// HttpServerCodec是netty自己提供的助手类，可以理解为拦截器

		pipeline.addLast("HttpServerCodec", new HttpServerCodec());
		// 添加自定义的助手类，返回hello netty~
		pipeline.addLast("customer", new CustomerHander());

	}
}
