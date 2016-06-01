package day3;

import day2.PageClass;

public class BookUser extends PageClass<BookUser> {
	private int id;
	private String username;
	private String userpass;
	private String email;

	public BookUser() {
	}

	/**
	 * @param id
	 * @param username
	 * @param userpass
	 * @param email
	 */
	public BookUser(int id, String username, String userpass, String email) {
		this.id = id;
		this.username = username;
		this.userpass = userpass;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpass() {
		return userpass;
	}

	public void setUserpass(String userpass) {
		this.userpass = userpass;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "id=" + id + ", username=" + username + ", userpass=" + userpass + ", email=" + email;
	}

}
