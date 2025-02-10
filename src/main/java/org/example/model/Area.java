package org.example.model;

import jakarta.persistence.*;

import java.io.Serializable;

//@Entity
@Table(name = "AREA")
public class Area implements Serializable {

	@Id
	@GeneratedValue
	private long id;

	@Column(name = "NAME", nullable = false)
	private String name;

	@Column(name = "EVENT", nullable = false)
	private Event event;

	@Column(name = "DESCRIPTION", nullable = false)
	private String description;

	public Event getEvent() {
		return this.event;
	}

}