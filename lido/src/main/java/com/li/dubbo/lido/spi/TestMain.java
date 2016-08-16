/**
 * 
 */
package com.li.dubbo.lido.spi;

import java.util.ServiceLoader;

/**
 * @author lipeng
 *
 * 2016年5月17日
 */
public class TestMain {
	public static void main(String[] args) {
		ServiceLoader<IOperation> ops = ServiceLoader.load(IOperation.class);
		for (IOperation iOperation : ops) {
			System.out.println(iOperation.operation(30, 10));
		}
	}
}
