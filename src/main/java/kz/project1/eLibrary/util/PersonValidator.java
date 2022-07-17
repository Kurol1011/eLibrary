package kz.project1.eLibrary.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import kz.project1.eLibrary.dao.PersonDAO;
import kz.project1.eLibrary.models.Person;



@Component
public class PersonValidator implements Validator {
	
	private final PersonDAO personDAO;
	
	@Autowired
	public PersonValidator(PersonDAO personDAO) {
		this.personDAO = personDAO;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method 
		return Person.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		Person person = (Person) target;
		if(personDAO.show(person.getFullName(),person.getId()).isPresent()) {
			errors.rejectValue("fullName", "","Это имя уже занято!");
		}
	}

}
