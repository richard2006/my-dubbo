/**
 * 
 */
package com.li.dubbo.lido.thrift.service;

import org.apache.thrift.TException;

/**
 * @author lipeng
 *
 * 2016年6月15日
 */
public class HelloServiceImpl implements HelloWorldService.Iface{

	/* (non-Javadoc)
	 * @see com.li.dubbo.lido.thrift.service.HelloWorldService.Iface#sayHello(java.lang.String)
	 */
	@Override
	public String sayHello(String username) throws TException {
		// TODO Auto-generated method stub
		return username + " hello";
	}

}
