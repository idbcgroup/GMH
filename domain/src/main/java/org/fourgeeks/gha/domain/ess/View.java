package org.fourgeeks.gha.domain.ess;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.fourgeeks.gha.domain.AbstractCodeEntity;

@Entity
public class View extends AbstractCodeEntity {

	private String name;

	@ManyToOne
	private Screen screen;

	public View() {
	}

	public View(Screen screen, String name, String code) {
		this.screen = screen;
		this.name = name;
		setCode(code);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}