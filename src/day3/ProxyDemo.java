package day3;

public class ProxyDemo {
	public static void main(String[] args) {
		BookUserDao bookUserDao = new BookUserDao();
		BookUserService service = new BookUserService(bookUserDao);
		DynamicProxy dyProxy = new DynamicProxy(service);
		Service serv = (Service) dyProxy.getProxy();
		serv.register(new BookUser());
		serv.login("1", "2");
	}
}
