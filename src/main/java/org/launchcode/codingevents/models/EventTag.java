package org.launchcode.codingevents.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class EventTag extends AbstractEntity {

	@NotBlank(message = "Please add a name for your tag.")
	@Size(min = 3, max = 50, message = "Name must be between 5 and 50 characters.")
	private String name;

	@ManyToMany(mappedBy = "tags")
	private final List<Event> events = new ArrayList<>();

	public EventTag(String name) {
		this.name = name;
	}

	public EventTag() {}

	public String getName() {
		return name;
	}

	public String getDisplayName() {
		return "#" + name + " ";
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Event> getEvents() {
		return events;
	}
}
