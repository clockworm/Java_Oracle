package day4;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import jdbc.oracle.util.JDBC;

public class DynamicProxyFactory implements InvocationHandler {
	private Object target;

	/**
	 * 构造代理对象函数
	 * 
	 * @param target
	 */
	public DynamicProxyFactory(Object target) {
		this.target = target;
	}

	/**
	 * 用注解来标识某个方法是否需要事务处理
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) {
		TransactionFlag flag = method.getAnnotation(TransactionFlag.class);
		if (flag != null && flag.value() == false) {
			try {
				proxy = method.invoke(target, args);
				return proxy;
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				JDBC.closeConnection();
			}
		}
		return transationMethod(proxy, method, args);
	}

	/**
	 * 需要事务的方法
	 * 
	 * @param proxy
	 * @param method
	 * @param args
	 * @return
	 */
	private Object transationMethod(Object proxy, Method method, Object[] args) {
		try {
			TransactionManager.start();
			proxy = method.invoke(target, args);
			TransactionManager.commit();
		} catch (Exception e) {
			TransactionManager.rollback();
			e.printStackTrace();
		} finally {
			JDBC.closeConnection();
		}
		return proxy;
	}

	/**
	 * 得到一个代理对象
	 * 
	 * @return
	 */
	public Object getProxy() {
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
	}
}
