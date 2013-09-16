package org.fourgeeks.gha.domain.ess;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.fourgeeks.gha.domain.AbstractCodeEntity;

@Entity
public class Function extends AbstractCodeEntity {

	private String name;

	@ManyToOne
	private View view;

	public Function() {
	}

	public Function(View view, String name, String code) {
		this.view = view;
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