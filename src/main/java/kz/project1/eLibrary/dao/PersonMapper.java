package kz.project1.eLibrary.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import kz.project1.eLibrary.models.Person;



public class PersonMapper implements RowMapper<Person> {
@Override
public Person mapRow(ResultSet resSet,int i) throws SQLException{
	Person person = new Person();
	person.setId(resSet.getInt("user_id"));
	person.setFullName(resSet.getString("fullName"));
	person.setBornYear(resSet.getInt("bornYear"));
	
	return person;
}
}
