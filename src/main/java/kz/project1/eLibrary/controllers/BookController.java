package kz.project1.eLibrary.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kz.project1.eLibrary.models.Person;
import kz.project1.eLibrary.dao.BookDAO;
import kz.project1.eLibrary.dao.PersonDAO;
import kz.project1.eLibrary.models.Book;
import kz.project1.eLibrary.util.BookValidator;

@Controller
@RequestMapping("/books")
public class BookController {
	private BookDAO bookDAO;
	private PersonDAO personDAO;
	private BookValidator bookValidator;
	
	@Autowired
	public BookController(BookDAO bookDAO,PersonDAO personDAO ,BookValidator bookValidator) {
		this.bookDAO = bookDAO;
		this.personDAO = personDAO;
		this.bookValidator = bookValidator;
	}
	
	@GetMapping()
	public String index(Model model) {
		model.addAttribute("books",bookDAO.index());
		return "books/index";
	}
	
	@GetMapping("/{id}")
	public String show(@PathVariable("id") int id,Model model,Model model2,Model model3,@ModelAttribute("person") Person person) {
		model.addAttribute("book",bookDAO.show(id));
		model2.addAttribute("people", personDAO.index());
		model3.addAttribute("personWithBook",personDAO.show(bookDAO.show(id).getUser_id()));
		return "books/show";
	}
	
	@PatchMapping("/{id}")
	public String giveBook(@ModelAttribute("person") Person person,@PathVariable("id") int id) {
		bookDAO.appointBook(person, id);
		return "redirect:/books";
	}
	
	@PostMapping("/{id}")
	public String deleteUser(@PathVariable("id") int id) {
		bookDAO.deleteUser(id);
		return "redirect:/books";
	}
	
	@GetMapping("/new")
	public String create(@ModelAttribute("book") Book book) {
		return "books/new";
	}
	
	@PostMapping()
	public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
		bookValidator.validate(book, bindingResult);
		if(bindingResult.hasErrors()) {
			return "books/new";
		}
		bookDAO.save(book);
		return "redirect:/books";
	}
	
	
	@GetMapping("/{id}/edit")
	public String edit(Model model,@PathVariable("id") int id) {
		model.addAttribute("book", bookDAO.show(id));
		return "books/edit";
	}
	
	@PatchMapping("/{id}/edit")
	public String update(@ModelAttribute("book") @Valid Book book,BindingResult bindingResult,@PathVariable("id") int id) {
		bookValidator.validate(book, bindingResult);
		if(bindingResult.hasErrors())
			return "books/edit";
		bookDAO.update(id,book);
		return "redirect:/books";
	}
	
	@DeleteMapping("/{id}/edit")
	public String delete(@PathVariable("id") int id) {
		bookDAO.delete(id);
		return "redirect:/books";
	}
}
