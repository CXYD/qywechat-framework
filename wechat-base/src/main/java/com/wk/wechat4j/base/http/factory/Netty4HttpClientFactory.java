package com.wk.wechat4j.base.http.factory;

import com.wk.wechat4j.base.http.HttpClient;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpContentDecompressor;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpResponseDecoder;
import io.netty.handler.stream.ChunkedWriteHandler;


/**
 * ʹ��Netty
 *
 * @className Netty4HttpClientFactory
 * @author jy
 * @date 2015��8��30��
 * @since JDK 1.6
 * @see
 */
public class Netty4HttpClientFactory extends HttpClientFactory {
	/**
	 * worker�߳���,Ĭ������Ϊcpu�ĺ��� * 4
	 */
	private final int workerThreads;

	public Netty4HttpClientFactory() {
		this(Runtime.getRuntime().availableProcessors() * 4);
	}

	public Netty4HttpClientFactory(int workerThreads) {
		this.workerThreads = workerThreads;
	}

	private volatile Bootstrap bootstrap;

	@Override
	public HttpClient newInstance() {
		if (bootstrap == null) {
			bootstrap = new Bootstrap();
			bootstrap.option(ChannelOption.SO_KEEPALIVE, true).option(
					ChannelOption.TCP_NODELAY, true);
			EventLoopGroup workerGroup = new NioEventLoopGroup(workerThreads);
			bootstrap.group(workerGroup).channel(NioSocketChannel.class)
					.handler(new ChannelInitializer<SocketChannel>() {
						@Override
						protected void initChannel(SocketChannel channel)
								throws Exception {
							ChannelPipeline pipeline = channel.pipeline();
							pipeline.addLast(new HttpClientCodec());
							pipeline.addLast(new HttpContentDecompressor());
							pipeline.addLast(new ChunkedWriteHandler());
							pipeline.addLast(new HttpResponseDecoder());
							pipeline.addLast(new HttpObjectAggregator(
									Integer.MAX_VALUE));
						}
					});
		}
		return new Netty4HttpClient(bootstrap);
	}
}
