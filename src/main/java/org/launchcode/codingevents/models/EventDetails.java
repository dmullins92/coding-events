package org.launchcode.codingevents.models;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Entity
public class EventDetails extends AbstractEntity {
	@Size(max = 500, message = "Description is too long.")
	private String description;

	@Positive(message = "Please set your maximum attendance.")
	private Integer maxNumberAttendees;

	@NotBlank(message = "Please add an email so guests can contact you.")
	@Email(message = "Must be a valid email.")
	private String contactEmail;

	@NotBlank(message = "Please add an address for your event.")
	private String eventAddress;

	private boolean shouldRegister;

	public EventDetails(
			String description,
			Integer maxNumberAttendees,
			String contactEmail,
			String eventAddress) {
		this.description = description;
		this.maxNumberAttendees = maxNumberAttendees;
		this.contactEmail = contactEmail;
		this.eventAddress = eventAddress;
		this.shouldRegister = false;
	}

	public EventDetails () {}

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

	public boolean isShouldRegister() {
		return shouldRegister;
	}

	public void setShouldRegister(boolean shouldRegister) {
		this.shouldRegister = shouldRegister;
	}
}
