package com.java.oracle.base;

import java.sql.ResultSet;

public interface RowData<T> {
	public T getRow(ResultSet res);
}
