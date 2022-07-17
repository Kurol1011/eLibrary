package kz.project1.eLibrary.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import kz.project1.eLibrary.models.Book;



public class BookMapper implements RowMapper<Book> {

	@Override
	public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		Book book = new Book();
		book.setId(rs.getInt("book_id"));
		book.setName(rs.getString("name"));
		book.setAuthor(rs.getString("author"));
		book.setYear(rs.getInt("year"));
		book.setUser_id(rs.getInt("user_id"));
		return book;
	}

}
