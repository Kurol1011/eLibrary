package kz.project1.eLibrary.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Book {
	private int id;
	private int user_id;
	@NotEmpty(message="Name should not be empty")
	@Size(min=1,max=200,message="Name should be between 1 and 200 characters")
	private String name;
	@Size(min=3,max=149,message="Name should be between 3 and 149 characters")
	@Pattern(regexp="([A-Z]\\w+\\s[A-Z]\\w+\\s[A-Z]\\w+)|([А-Я][а-я]+\\s[А-Я][а-я]+\\s[А-Я][а-я]+)|([A-Z]\\w+\\s[A-Z]\\w+)|([А-Я][а-я]+\\s[А-Я][а-я]+)|([A-Z]\\w+)|([А-Я][а-я]+)",message = "Имя автора должно начинатся с заглавной буквой и не должен содержать ничего кроме букв")
	private String author;
	private int year;
	
	public Book() {}

	public Book(int id,String name,String author,int year) {
		this.id = id;
		this.name = name;
		this.author = author;
		this.year = year;
	}

	
	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
}
