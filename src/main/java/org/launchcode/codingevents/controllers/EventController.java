package org.launchcode.codingevents.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping(value="events")
public class EventController {

	@GetMapping
	public String displayAllEvents(Model model) {
		List<String> events = new ArrayList<>(Arrays.asList("Polka Concert", "Pancake Baking with Bertie", "Spinning " +
				"Tops"));
		model.addAttribute("events", events);
		return "events/index";
	}

	@GetMapping("create")
	public String renderCreateEventForm() {
		return "events/create";
	}
}
