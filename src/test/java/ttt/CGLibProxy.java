package ttt;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CGLibProxy implements MethodInterceptor {

	private static CGLibProxy cGLibProxy = new CGLibProxy();
	
	private CGLibProxy() {
		
	}
	
	public static CGLibProxy getInstance() {
		return cGLibProxy;
	}
	
	public <T> T getProxy(Class<T> cls) {
		return (T) Enhancer.create(cls, this);
	}
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		before();
		Object result = proxy.invokeSuper(obj, args);
		after();
		return result;
	}
	
	public void before() {
		System.out.println("beforeCglib");
	}
	
	public void after() {
		System.out.println("afterCglib");
	}

}
