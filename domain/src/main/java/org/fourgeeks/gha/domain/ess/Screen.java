package org.fourgeeks.gha.domain.ess;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.fourgeeks.gha.domain.AbstractCodeEntity;

@Entity
public class Screen extends AbstractCodeEntity {

	private String name;

	@ManyToOne
	private Module module;

	public Screen() {
	}

	public Screen(Module module, String name, String code) {
		this.module = module;
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