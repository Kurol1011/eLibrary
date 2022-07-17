package kz.project1.eLibrary.models;


import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.validation.constraints.Max;  
import javax.validation.constraints.Min; 

public class Person {
	private int  id;
	@Size(min=3,max=149,message="Name should be between 3 and 149 characters")
	@Pattern(regexp="([A-Z]\\w+\\s[A-Z]\\w+\\s[A-Z]\\w+)|([А-Я][а-я]+\\s[А-Я][а-я]+\\s[А-Я][а-я]+)|([A-Z]\\w+\\s[A-Z]\\w+)|([А-Я][а-я]+\\s[А-Я][а-я]+)",message = "Имя человека должно начинатся с заглавной буквой и не должен содержать ничего кроме букв")
	private String fullName;
	@Min(value=1905,message = "Минимальное значение: 1905")
	@Max(value=2022,message = "Максимальное значение: 2022")
	private int bornYear;
	public Person() {}
	
	public Person(int id, String fullName,int bornYear) {
		this.id = id;
		this.fullName = fullName;
		this.bornYear = bornYear;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public int getBornYear() {
		return bornYear;
	}

	public void setBornYear(int bornYear) {
		this.bornYear = bornYear;
	}

	
}
