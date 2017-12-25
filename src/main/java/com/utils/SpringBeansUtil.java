/**
 * 
 */
package com.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author bin
 *获取bean容器中的实例
 */
@Component
public class SpringBeansUtil implements ApplicationContextAware{
    private static ApplicationContext applicationContext;
	 
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)throws BeansException {
		SpringBeansUtil.applicationContext = applicationContext;
	}
    public static Object getBean(String name){
    	try {
			return applicationContext.getBean(name);
		} catch (BeansException e) {
			return null;
		}
    }
}
