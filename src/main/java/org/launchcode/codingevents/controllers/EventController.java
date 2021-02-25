package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.data.EventCategoryRepository;
import org.launchcode.codingevents.data.EventRepository;
import org.launchcode.codingevents.models.Event;
import org.launchcode.codingevents.models.EventCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping(value="events")
public class EventController {

	@Autowired
	private EventRepository eventRepository;

	@Autowired
	private EventCategoryRepository eventCategoryRepository;

	@GetMapping
	public String displayEvents(@RequestParam(required = false) Integer categoryId, Model model) {
		if(categoryId == null) {
			model.addAttribute("events", eventRepository.findAll());
			model.addAttribute("title", "Coding Events");
		} else {
			Optional<EventCategory> result = eventCategoryRepository.findById(categoryId);
			if (result.isEmpty()) {
				model.addAttribute("title", "Invalid Category ID: " + categoryId);
			} else {
				EventCategory category = result.get();
				model.addAttribute("title", "Events in category: " + category.getName());
				model.addAttribute("events", category.getEvents());
			}
		}

		return "events/index";
	}

	@GetMapping("create")
	public String renderCreateEventForm(Model model) {
		model.addAttribute("title", "Create Events");
		model.addAttribute("event", new Event());
		model.addAttribute("categories", eventCategoryRepository.findAll());
		return "events/create";
	}

	@PostMapping("create")
	public String processCreateEventForm(@ModelAttribute @Valid Event newEvent,
	                                     Errors errors,
	                                     Model model) {
		if(errors.hasErrors()) {
			model.addAttribute("title", "Create Events");
			model.addAttribute("events", eventRepository.findAll());
			model.addAttribute("categories", eventCategoryRepository.findAll());
			return "events/create";
		}
		eventRepository.save(newEvent);
		return "redirect:";
	}

	@GetMapping("delete")
	public String displayDeleteEventForm(Model model) {
		model.addAttribute("title", "Delete Events");
		model.addAttribute("events", eventRepository.findAll());
		return "events/delete";
	}

	@PostMapping("delete")
	public String processDeleteEventForm(@RequestParam(required = false) int[] eventIds) {
		if(eventIds != null) {
			for (int id : eventIds) {
				eventRepository.deleteById(id);
			}
		}
		return "redirect:";
	}

	@GetMapping("edit/{eventId}")
	public String displayEditEventForm(Model model, @PathVariable Integer eventId) {
		if (eventId == null) {
			model.addAttribute("events", eventRepository.findAll());
			model.addAttribute("title", "Coding Events");
		} else {
			Optional<Event> result = eventRepository.findById(eventId);
			if (result.isEmpty()) {
				model.addAttribute("title", "Invalid Event ID: " + eventId);
			} else {
				Event event = result.get();
				model.addAttribute("title", "Edit " + event.getName() + "(ID: " + eventId + ")");
				model.addAttribute("event", event);
				model.addAttribute("categories", eventCategoryRepository.findAll());
			}
		}

		return "events/edit";
	}

	@PostMapping()
	public String processEditEventForm(Integer eventId,
	                                   String name,
	                                   String description,
	                                   int maxNumberAttendees,
	                                   String contactEmail,
	                                   String eventAddress,
	                                   boolean shouldRegister,
	                                   Model model) {
		if (eventId == null) {
			model.addAttribute("events", eventRepository.findAll());
			model.addAttribute("title", "Coding Events");
		} else {
			Optional<Event> result = eventRepository.findById(eventId);
			if (result.isPresent()) {
				Event event = result.get();
				event.setName(name);
				event.setDescription(description);
				event.setMaxNumberAttendees(maxNumberAttendees);
				event.setContactEmail(contactEmail);
				event.setEventAddress(eventAddress);
				event.setShouldRegister(shouldRegister);
				eventRepository.save(event);
			}
		}
		return "redirect:events";
	}
}
