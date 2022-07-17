package kz.project1.eLibrary.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import kz.project1.eLibrary.models.Book;
import kz.project1.eLibrary.models.Person;

@Component
public class PersonDAO {
private final JdbcTemplate jdbcTemplate;
	
	
	@Autowired
	public PersonDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	
	public List<Person> index(){
		return jdbcTemplate.query("SELECT * FROM person", new PersonMapper());
	}
	
	public Optional<Person> show(String fullName,int id) {
		return jdbcTemplate.query("SELECT * FROM person WHERE fullName=? AND NOT user_id=?", new Object[] {fullName,id},new PersonMapper()).stream().findAny();
	}
	
	public Person show(int id) {
		return jdbcTemplate.query("SELECT * FROM person WHERE user_id=?", new Object[] {id},new PersonMapper()).stream().findAny().orElse(null);
	}
	
	
	
	public void save(Person person) {
		 jdbcTemplate.update("INSERT INTO person(fullName,bornYear) VALUES(?,?)",person.getFullName(),person.getBornYear());
		
	}
	
	public void update(int id,Person updatedPerson) {
		jdbcTemplate.update("UPDATE person SET fullname=?, bornyear=? WHERE user_id = ?",updatedPerson.getFullName(),updatedPerson.getBornYear(),id);
		System.out.println(id);
	}

	public void delete(int id) {
		jdbcTemplate.update("DELETE FROM person WHERE user_id=?",id);
		
	}
	
	public List<Book> personBooks(int id){
		return jdbcTemplate.query("SELECT * FROM book JOIN person ON book.user_id = person.user_id WHERE person.user_id = ?",new Object[] {id},new BookMapper());
	}
	
	
}
