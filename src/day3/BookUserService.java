package day3;

/**
 * 业务层
 */

public class BookUserService implements Service {
	private BookUserDao bookUserDao;

	public BookUserService(BookUserDao bookUserDao) {
		this.bookUserDao = bookUserDao;
	}

	@Override
	public void register(BookUser user) {
		System.out.println("注册");
		bookUserDao.add(user);
	}

	@Override
	public void login(String name, String password) {
		BookUser user = new BookUser();
		user.setUsername(name);
		user.setUserpass(password);
//		bookUserDao.findMore(user);
		System.out.println("登录");
	}
}
