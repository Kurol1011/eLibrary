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


import kz.project1.eLibrary.dao.PersonDAO;
import kz.project1.eLibrary.models.Person;
import kz.project1.eLibrary.util.PersonValidator;
@Controller
@RequestMapping("/people")
public class PeopleController {
	private PersonDAO personDAO;
	private final PersonValidator personValidator;
	@Autowired
	public PeopleController(PersonDAO personDAO , PersonValidator personValidator) {
		this.personDAO = personDAO;
		this.personValidator = personValidator;
	}
	@GetMapping()
	public String index(Model model) {
		model.addAttribute("people",personDAO.index());
		return "people/index";
	}
	
	@GetMapping("/{id}")
	public String show(@PathVariable("id") int id,Model model,Model model2) {
		model.addAttribute("person",personDAO.show(id));
		model2.addAttribute("personBooks", personDAO.personBooks(id));
		return "people/show";
	}
	@GetMapping("/new")
	public String create(@ModelAttribute("person") Person person) {
		return "people/new";
	}
	
	@PostMapping()
	public String create(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
		personValidator.validate(person, bindingResult);
		if(bindingResult.hasErrors()) {
			return "people/new";
		}
		personDAO.save(person);
		return "redirect:/people";
	}
	
	
	@GetMapping("/{id}/edit")
	public String edit(Model model,@PathVariable("id") int id) {
		model.addAttribute("person", personDAO.show(id));
		return "people/edit";
	}
	
	@PatchMapping("/{id}/edit")
	public String update(@ModelAttribute("person") @Valid Person person,BindingResult bindingResult,@PathVariable("id") int id) {
		personValidator.validate(person, bindingResult);
		if(bindingResult.hasErrors())
			return "people/edit";
		personDAO.update(id,person);
		return "redirect:/people";
	}
	
	@DeleteMapping("/{id}/edit")
	public String delete(@PathVariable("id") int id) {
		personDAO.delete(id);
		return "redirect:/people";
	}
	
}
