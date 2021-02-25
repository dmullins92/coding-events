package org.launchcode.codingevents.models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.*;

@Entity
public class Event extends AbstractEntity {

	@NotBlank(message = "Please add a name for your event.")
	@Size(min = 3, max = 50, message = "Name must be between 5 and 50 characters.")
	private String name;

	@Size(max = 500, message = "Description is too long.")
	private String description;

	@Positive(message = "Please set your maximum attendance.")
	private int maxNumberAttendees;

	@ManyToOne
	@NotNull(message = "Category is required.")
	private EventCategory eventCategory;

	@NotBlank(message = "Please add an email so guests can contact you.")
	@Email(message = "Must be a valid email.")
	private String contactEmail;

	@NotBlank(message = "Please add an address for your event.")
	private String eventAddress;

	private boolean shouldRegister;

	public Event() {}

	public Event(String name,
	             String description,
	             int maxNumberAttendees,
	             EventCategory eventCategory,
	             String contactEmail,
	             String eventAddress) {
		this.name = name;
		this.description = description;
		this.maxNumberAttendees = maxNumberAttendees;
		this.eventCategory = eventCategory;
		this.contactEmail = contactEmail;
		this.eventAddress = eventAddress;
		shouldRegister = false;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getMaxNumberAttendees() {
		return maxNumberAttendees;
	}

	public void setMaxNumberAttendees(int maxNumberAttendees) {
		this.maxNumberAttendees = maxNumberAttendees;
	}

	public EventCategory getEventCategory() {
		return eventCategory;
	}

	public void setEventCategory(EventCategory eventCategory) {
		this.eventCategory = eventCategory;
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

	public boolean getShouldRegister() {
		return shouldRegister;
	}

	public void setShouldRegister(boolean shouldRegister) {
		this.shouldRegister = shouldRegister;
	}

	@Override
	public String toString() {
		return name;
	}

}
