package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.data.EventData;
import org.launchcode.codingevents.models.Event;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value="events")
public class EventController {

	@GetMapping
	public String displayAllEvents(Model model) {
		model.addAttribute("events", EventData.getAll());
		model.addAttribute("title", "Coding Events");
		return "events/index";
	}

	@GetMapping("create")
	public String renderCreateEventForm(Model model) {
		model.addAttribute("title", "Create Events");
		model.addAttribute("event", new Event());
		return "events/create";
	}

	@PostMapping("create")
	public String processCreateEventForm(@ModelAttribute @Valid Event newEvent,
	                                     Errors errors,
	                                     Model model) {
		if(errors.hasErrors()) {
			model.addAttribute("title", "Create Events");
			model.addAttribute("events", EventData.getAll());
			return "events/create";
		}
		EventData.add(newEvent);
		return "redirect:";
	}

	@GetMapping("delete")
	public String displayDeleteEventForm(Model model) {
		model.addAttribute("title", "Delete Events");
		model.addAttribute("events", EventData.getAll());
		return "events/delete";
	}

	@PostMapping("delete")
	public String processDeleteEventForm(@RequestParam(required = false) int[] eventIds) {
		if(eventIds != null) {
			for (int id : eventIds) {
				EventData.remove(id);
			}
		}
		return "redirect:";
	}

	@GetMapping("edit/{eventId}")
	public String displayEditEventForm(Model model, @PathVariable int eventId) {
		model.addAttribute("title", "Edit " + EventData.getById(eventId).getName() + "(ID: " + eventId + ")");
		model.addAttribute("event", EventData.getById(eventId));
		return "events/edit";
	}

	@PostMapping()
	public String processEditEventForm(int eventId,
	                                   String name,
	                                   String description,
	                                   int maxNumberAttendees,
	                                   String contactEmail,
	                                   String eventAddress,
	                                   boolean shouldRegister) {
		EventData.getById(eventId).setName(name);
		EventData.getById(eventId).setDescription(description);
		EventData.getById(eventId).setMaxNumberAttendees(maxNumberAttendees);
		EventData.getById(eventId).setContactEmail(contactEmail);
		EventData.getById(eventId).setEventAddress(eventAddress);
		EventData.getById(eventId).setShouldRegister(shouldRegister);
		return "redirect:events";
	}
}
