package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.data.EventCategoryRepository;
import org.launchcode.codingevents.models.EventCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(value="eventCategories")
public class EventCategoryController {

	@Autowired
	private EventCategoryRepository eventCategoryRepository;

	@GetMapping
	public String displayAllEventCategories(Model model) {
		model.addAttribute("title", "All Categories");
		model.addAttribute("categories", eventCategoryRepository.findAll());
		return "eventCategories/index";
	}

	@GetMapping("create")
	public String renderCreateEventCategoryForm(Model model) {
		model.addAttribute("title", "Create Category");
		model.addAttribute("category", new EventCategory());
		return "eventCategories/create";
	}

	@PostMapping("create")
	public String processCreateEventCategoryForm(@ModelAttribute(value = "category") @Valid EventCategory newEventCategory,
	                                             Errors errors,
	                                             Model model) {
		if(errors.hasErrors()) {
			model.addAttribute("title", "Create Category");
			model.addAttribute("categories", eventCategoryRepository.findAll());
			return "eventCategories/create";
		} else {
			eventCategoryRepository.save(newEventCategory);
			return "redirect:";
		}
	}
}
