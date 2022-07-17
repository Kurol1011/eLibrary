package kz.project1.eLibrary.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import kz.project1.eLibrary.models.Book;
import kz.project1.eLibrary.models.Person;

@Component
public class BookDAO {
	private final JdbcTemplate jdbcTemplate;
	
	@Autowired
	public BookDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	
	public List<Book> index(){
		return jdbcTemplate.query("SELECT * FROM book", new BookMapper());
	}
	
	
	public Book show(int id) {
		return jdbcTemplate.query("SELECT * FROM book WHERE book_id = ?", new Object[] {id},new BookMapper()).stream().findAny().orElse(null);
	}
	
	
	public void save(Book book) {
		 jdbcTemplate.update("INSERT INTO book(name,author,year) VALUES(?,?,?)",book.getName(),book.getAuthor(),book.getYear());
		
	}
	
	public void update(int id,Book updatedBook) {
		jdbcTemplate.update("UPDATE book SET name=?, author=?, year=? WHERE book_id = ?",updatedBook.getName(),updatedBook.getAuthor(),updatedBook.getYear(),id);
	}
	
	public void delete(int id) {
		jdbcTemplate.update("DELETE FROM book WHERE book_id=?",id);	
	}
	
	public void appointBook(Person person,int book_id) {
		jdbcTemplate.update("UPDATE book SET user_id=? WHERE book_id = ?",person.getId(),book_id);
	}
	
	public void deleteUser(int book_id) {
		jdbcTemplate.update("UPDATE book SET user_id=null WHERE book_id = ?",book_id);
	}
	
}
