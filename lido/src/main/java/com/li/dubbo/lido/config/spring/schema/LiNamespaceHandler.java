/**
 * 
 */
package com.li.dubbo.lido.config.spring.schema;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * @author lipeng
 *
 * 2016年5月12日
 */
public class LiNamespaceHandler extends NamespaceHandlerSupport {

	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.xml.NamespaceHandler#init()
	 */
	public void init() {
		// TODO Auto-generated method stub
		registerBeanDefinitionParser("people",new PeopleBeanDefinitionParser());
	}


}
