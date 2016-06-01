package day3;

/**
 * 静态代理 用於本身業務外的操作 比如寫日誌 事務的操作 AOP 业务本身逻辑和其他的事务相关处理进行分离
 */

public class BookUserServiceProxy implements Service {
	private BookUserService bookUserService;

	public BookUserServiceProxy(BookUserService bookUserService) {
		this.bookUserService = bookUserService;
	}

	@Override
	public void login(String name, String password) {
		System.out.println("开启事务");
		bookUserService.login(name, password);
		System.out.println("关闭事务,记录日志");

	}

	@Override
	public void register(BookUser user) {
		System.out.println("开启事务");
		bookUserService.register(user);
		System.out.println("关闭事务,记录日志");
	}

}
