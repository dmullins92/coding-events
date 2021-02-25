package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.data.EventTagRepository;
import org.launchcode.codingevents.models.EventCategory;
import org.launchcode.codingevents.models.EventTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping(value="eventTags")
public class EventTagsController {

	@Autowired
	private EventTagRepository eventTagRepository;

	@GetMapping
	public String displayAllEventTags(Model model) {
		model.addAttribute("title", "All Tags");
		model.addAttribute("tags", eventTagRepository.findAll());
		return "eventTags/index";
	}

	@GetMapping("create")
	public String renderCreateEventTagForm(Model model) {
		model.addAttribute("title", "Create Tag");
		model.addAttribute("tag", new EventTag());
		return "eventTags/create";
	}

	@PostMapping("create")
	public String processCreateEventCategoryForm(@ModelAttribute(value = "tag") @Valid EventTag newEventTag,
	                                             Errors errors,
	                                             Model model) {
		if(errors.hasErrors()) {
			model.addAttribute("title", "Create Tag");
			model.addAttribute("tags", eventTagRepository.findAll());
			return "eventTags/create";
		} else {
			eventTagRepository.save(newEventTag);
			return "redirect:";
		}
	}
}
