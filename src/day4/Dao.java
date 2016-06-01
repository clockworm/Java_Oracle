package day4;

import java.util.List;

public interface Dao<T> {
	public int add(T t);

	public int delete(int id);

	public int update(T t);

	public T find(int id);

	public List<T> findMore(T t);

	public List<T> query();

	public List<T> pageQuery(int count, int page);
}
