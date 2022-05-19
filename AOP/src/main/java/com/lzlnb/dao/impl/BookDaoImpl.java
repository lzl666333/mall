package com.lzlnb.dao.impl;

import com.lzlnb.dao.BookDao;
import org.springframework.stereotype.Repository;

@Repository
public class BookDaoImpl implements BookDao {

	@Override
	public void save() {
		System.out.println(System.currentTimeMillis());
		System.out.println("Book save...");
	}

	@Override
	public void update() {
		System.out.println("Book update...");
	}
}
