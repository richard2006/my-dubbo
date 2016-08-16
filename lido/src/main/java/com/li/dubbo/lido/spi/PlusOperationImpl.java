/**
 * 
 */
package com.li.dubbo.lido.spi;

/**
 * @author lipeng
 *
 * 2016年5月17日
 */
public class PlusOperationImpl implements IOperation {

	/* (non-Javadoc)
	 * @see com.li.dubbo.lido.spi.IOperation#operation(int, int)
	 */
	@Override
	public int operation(int numberA, int numberB) {
		// TODO Auto-generated method stub
		System.out.println("加法：");
		return numberA + numberB;
	}

}
