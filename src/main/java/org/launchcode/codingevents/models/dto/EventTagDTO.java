package org.launchcode.codingevents.models.dto;

import org.launchcode.codingevents.models.Event;
import org.launchcode.codingevents.models.EventTag;

import javax.validation.constraints.NotNull;

public class EventTagDTO {

	@NotNull
	private Event event;

	@NotNull
	private EventTag tag;

	public EventTagDTO() {}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public EventTag getTag() {
		return tag;
	}

	public void setTag(EventTag tag) {
		this.tag = tag;
	}
}
