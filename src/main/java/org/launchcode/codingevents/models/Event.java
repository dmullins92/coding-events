package org.launchcode.codingevents.models;

import java.util.Objects;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class Event {

	private final int id;
	private static int nextId = 0;

	@NotBlank(message = "Please add a name for your event.")
	@Size(min = 3, max = 50, message = "Name must be between 5 and 50 characters.")
	private String name;

	@Size(max = 500, message = "Description is too long.")
	private String description;

	@Positive(message = "Please set your maximum attendance.")
	private int maxNumberAttendees;

	private EventType type;

	@NotBlank(message = "Please add an email so guests can contact you.")
	@Email(message = "Must be a valid email.")
	private String contactEmail;

	@NotBlank(message = "Please add an address for your event.")
	private String eventAddress;

	private boolean shouldRegister;



	public Event() {
		this.id = nextId;
		nextId++;
	}

	public Event(String name,
	             String description,
	             int maxNumberAttendees,
	             EventType type,
	             String contactEmail,
	             String eventAddress) {
		this();
		this.name = name;
		this.description = description;
		this.maxNumberAttendees = maxNumberAttendees;
		this.type = type;
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

	public EventType getType() {
		return type;
	}

	public void setType(EventType type) {
		this.type = type;
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

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Event event = (Event) o;
		return id == event.id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
