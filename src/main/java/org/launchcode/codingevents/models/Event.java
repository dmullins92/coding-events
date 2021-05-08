package org.launchcode.codingevents.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Event extends AbstractEntity {

	@NotBlank(message = "Please add a name for your event.")
	@Size(min = 3, max = 50, message = "Name must be between 5 and 50 characters.")
	private String name;

	@ManyToOne
	@NotNull(message = "Category is required.")
	private EventCategory eventCategory;

	@ManyToMany
	private final List<EventTag> tags = new ArrayList<>();

	@Size(max = 500, message = "Description is too long.")
	private String description;

	@Positive(message = "Please set your maximum attendance.")
	private Integer maxNumberAttendees;

	@NotBlank(message = "Please add an email so guests can contact you.")
	@Email(message = "Must be a valid email.")
	private String contactEmail;

	@NotBlank(message = "Please add an address for your event.")
	private String eventAddress;

	private Boolean shouldRegister;

	public Event() {}

	public Event(String name,
	             EventCategory eventCategory) {
		this.name = name;
		this.eventCategory = eventCategory;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public EventCategory getEventCategory() {
		return eventCategory;
	}

	public void setEventCategory(EventCategory eventCategory) {
		this.eventCategory = eventCategory;
	}

	public List<EventTag> getTags() {
		return tags;
	}

	public void addTag(EventTag tag) {
		this.tags.add(tag);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getMaxNumberAttendees() {
		return maxNumberAttendees;
	}

	public void setMaxNumberAttendees(Integer maxNumberAttendees) {
		this.maxNumberAttendees = maxNumberAttendees;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public String getEventAddress() {
		return eventAddress;
	}

	public void setEventAddress(String eventAddress) {
		this.eventAddress = eventAddress;
	}

	public Boolean getShouldRegister() {
		return shouldRegister;
	}

	public void setShouldRegister(Boolean shouldRegister) {
		this.shouldRegister = shouldRegister;
	}

	@Override
	public String toString() {
		return name;
	}

}
