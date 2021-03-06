package org.smart4j.framework.helper;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.smart4j.framework.util.ReflectionUtil;


public final class BeanHelper {

	//常量池用的引用，可以作为GC ROOT，所以new HashMap()对象不会被回收
	private static final Map<Class<?>,Object> BEAN_MAP = new HashMap<Class<?>,Object>();
	
	static {
		Set<Class<?>> classSet = ClassHelper.getBeanClassSet();
		for(Class<?> cls:classSet) {
			Object instance = ReflectionUtil.newInstance(cls);
			BEAN_MAP.put(cls, instance);
		}
	}
	
	public static Map<Class<?>,Object> getBeanMap(){
		return BEAN_MAP;
	}
	
	public static void setBean(Class<?> cls,Object object) {
		BEAN_MAP.put(cls, object);
	}
	
	
	
	/**
	 * 获取bean实例
	 * @param cls
	 * @return
	 */
	//<T> 不是返回值，表示传入参数有泛型
	public static <T> T getBean(Class<T> cls) {
		if(!BEAN_MAP.containsKey(cls)) {
			throw new RuntimeException("can not get bean by class:"+cls);
		}
		return (T) BEAN_MAP.get(cls);
	}
	
}
