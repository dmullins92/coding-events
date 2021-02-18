package org.launchcode.codingevents.models;

import java.util.Objects;

public class Event {

	private final int id;
	private static int nextId = 1;
	private String name;
	private String description;
	private EventType type;

	public Event(String name, String description, EventType type) {
		this.name = name;
		this.description = description;
		this.type = type;
		this.id = nextId;
		nextId++;
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

	public EventType getType() {
		return type;
	}

	public void setType(EventType type) {
		this.type = type;
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
