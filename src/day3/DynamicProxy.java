package day3;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxy implements InvocationHandler {
	private Object target;// 被代理目标对象

	public DynamicProxy(Object target) {
		this.target = target;
	}

	/**
	 * 拦截该目标类的方法 代理執行
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("开启事务");
		System.out.println(method.getName());
		method.invoke(target, args);
		System.out.println("关闭事务,记录日志");
		return null;
	}

	public Object getProxy() {
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
	}

}
