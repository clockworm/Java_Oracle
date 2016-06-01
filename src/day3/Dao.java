package day3;

import java.util.ArrayList;

public interface Dao<T> {
	public int add(T t);

	public int delete(T t);

	public int update(T t);

	public T find(int id);

	public ArrayList<T> findMore(T t);

	public ArrayList<T> query();

	public ArrayList<T> pageQuery(int count, int page);
}
