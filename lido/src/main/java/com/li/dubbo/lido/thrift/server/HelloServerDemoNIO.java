/**
 * 
 */
package com.li.dubbo.lido.thrift.server;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TServerSocket;

import com.li.dubbo.lido.thrift.service.HelloServiceImpl;
import com.li.dubbo.lido.thrift.service.HelloWorldService;

/**
 * @author lipeng
 *
 * 2016年6月15日
 */
public class HelloServerDemoNIO {
	public static final int SERVER_PORT = 8090;

	public void startServer() {
		try {
			System.out.println("HelloWorld NIOServer start ....");

			TProcessor tprocessor = new HelloWorldService.Processor<HelloWorldService.Iface>(new HelloServiceImpl());
			// HelloWorldService.Processor&lt;HelloWorldService.Iface&gt; tprocessor =
			// new HelloWorldService.Processor&lt;HelloWorldService.Iface&gt;(
			// new HelloWorldImpl());

			// 使用非阻塞式IO
			TNonblockingServerSocket serverTransport = new TNonblockingServerSocket(SERVER_PORT);
			TNonblockingServer.Args tArgs = new TNonblockingServer.Args(serverTransport);
			tArgs.processor(tprocessor);
			tArgs.protocolFactory(new TBinaryProtocol.Factory());
			// tArgs.protocolFactory(new TCompactProtocol.Factory());
			// tArgs.protocolFactory(new TJSONProtocol.Factory());
			TServer server = new TNonblockingServer(tArgs);
			// 线程池服务模型，使用标准的阻塞式IO，预先创建一组线程处理请求。
			server.serve();

		} catch (Exception e) {
			System.out.println("Server start error!!!");
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		HelloServerDemoNIO server = new HelloServerDemoNIO();
		server.startServer();
	}

}
