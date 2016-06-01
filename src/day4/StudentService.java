package day4;

import java.util.List;

/**
 * 业务接口
 * 
 * @author Administrator
 *
 * @param <T>
 */
public interface StudentService<T> {
	public int add(T t);

	@TransactionFlag
	public T query(int id);

	public int update(T t);

	public int delete(int id);

	@TransactionFlag
	public List<T> pageQuery(int count, int page);

}
