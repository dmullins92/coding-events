package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.data.EventCategoryRepository;
import org.launchcode.codingevents.data.EventRepository;
import org.launchcode.codingevents.data.EventTagRepository;
import org.launchcode.codingevents.models.Event;
import org.launchcode.codingevents.models.EventCategory;
import org.launchcode.codingevents.models.EventTag;
import org.launchcode.codingevents.models.dto.EventTagDTO;
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

	@Autowired
	private EventTagRepository eventTagRepository;

	@GetMapping
	public String displayEvents(@RequestParam(required = false) Integer categoryId,
	                            @RequestParam(required = false) Integer tagId,
	                            Model model) {
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

		if(tagId == null) {
			model.addAttribute("events", eventRepository.findAll());
			model.addAttribute("title", "Coding Events");
		} else {
			Optional<EventTag> result = eventTagRepository.findById(tagId);
			if (result.isEmpty()) {
				model.addAttribute("title", "Invalid Tag ID: " + tagId);
			} else {
				EventTag tag = result.get();
				model.addAttribute("title", "Events with tag: " + tag.getName());
				model.addAttribute("events", tag.getEvents());
			}
		}

		return "events/index";
	}

	@GetMapping("create")
	public String displayCreateEventForm(Model model) {
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

	@GetMapping("detail")
	public String displayEventDetails(@RequestParam Integer eventId,
	                                  Model model) {
		Optional<Event> result = eventRepository.findById(eventId);

		if (result.isEmpty()) {
			model.addAttribute("title", "Invalid Event ID: " + eventId);
		} else {
			Event event = result.get();
			model.addAttribute("title", event.getName() + " Details");
			model.addAttribute("event", event);
			model.addAttribute("tags", event.getTags());
		}
		return "events/detail";
	}

	@GetMapping("add-tag")
	public String displayAddTagForm(@RequestParam Integer eventId,
	                                Model model) {
		Optional<Event> result = eventRepository.findById(eventId);
		Event event = result.get();
		model.addAttribute("title", "Add Tag to " + event.getName());
		model.addAttribute("tags", eventTagRepository.findAll());
		EventTagDTO eventTag = new EventTagDTO();
		eventTag.setEvent(event);
		model.addAttribute("eventTag", eventTag);
		return "events/add-tag";
	}

	@PostMapping("add-tag")
	public String processAddTagForm(@ModelAttribute @Valid EventTagDTO eventTag,
	                                Errors errors,
	                                Model model) {
		if(!errors.hasErrors()) {
			Event event = eventTag.getEvent();
			EventTag tag = eventTag.getTag();

			if(!event.getTags().contains(tag)) {
				event.addTag(tag);
				eventRepository.save(event);
			}
			return "redirect:detail?eventId=" + event.getId();
		}
		return "redirect:add-tag";
	}

	@GetMapping("edit/{eventId}")
	public String displayEditEventForm(Model model, @PathVariable Integer eventId) {
		if (eventId == null) {
			model.addAttribute("events", eventRepository.findAll());
			model.addAttribute("title", "Coding Events");
			return "redirect:events";
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
	                                   Integer maxNumberAttendees,
	                                   String contactEmail,
	                                   String eventAddress,
	                                   Boolean shouldRegister,
	                                   Model model) {

			Optional<Event> result = eventRepository.findById(eventId);
			if (result.isEmpty()) {
				model.addAttribute("title", "Invalid Event ID: " + eventId);
			} else {
				Event event = result.get();
				event.setName(name);
				event.setDescription(description);
				event.setMaxNumberAttendees(maxNumberAttendees);
				event.setContactEmail(contactEmail);
				event.setEventAddress(eventAddress);
				event.setShouldRegister(shouldRegister);
				if (shouldRegister == null) {
					event.setShouldRegister(false);
				}
				eventRepository.save(event);
			}
		return "redirect:events";
	}
}
