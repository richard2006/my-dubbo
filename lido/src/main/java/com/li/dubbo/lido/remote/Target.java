/**
 * 
 */
package com.li.dubbo.lido.remote;

import java.net.InetSocketAddress;
import java.net.URL;
import java.nio.channels.ReadPendingException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

/**
 * @author lipeng
 *
 * 2016年5月13日
 */

/**
 * 类CommonServer.java的实现描述：TODO 类实现描述
 *
 * @author yp 2015年7月30日 上午11:27:49
 */
public class Target {

	 private String targetHost;
	    private int targetPort;

	    public Target(String targetHost, int targetPort) {
	        this.targetHost = targetHost;
	        this.targetPort = targetPort;
	    }

	    public void run() throws Exception {
	        System.err.println("Target host:" + targetHost + " targetPort:" + targetPort);

	        // Configure the bootstrap.
	        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
	        EventLoopGroup workerGroup = new NioEventLoopGroup();
	        try {
	            ServerBootstrap b = new ServerBootstrap();
	            b.group(bossGroup, workerGroup)
	                    .channel(NioServerSocketChannel.class)
	                    .childHandler(new ChannelInitializer<SocketChannel>() {
	                        @Override
	                        public void initChannel(SocketChannel ch)
	                                throws Exception {
	                            // 注册handler
	                            ch.pipeline().addLast(
	                                    new ObjectEncoder(),
	                                    new ObjectDecoder(ClassResolvers.cacheDisabled(null)),
	                                    new TargetHandler()
	                            );
	                        }
	                    })
	                    .bind(targetPort).sync().channel().closeFuture().sync();
	            //监听本地的一个端口，当有客户端请求时，然后向目标服务器发送请求，获取消息，然后发送给客户端
	        } finally {
	            bossGroup.shutdownGracefully();
	            workerGroup.shutdownGracefully();
	        }
	    }

	    public static void main(String[] args) throws Exception {
	        new Target("127.0.0.1", 12358).run();
	    }
	}