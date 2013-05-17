package org.fourgeeks.gha.domain.vcf;

import javax.persistence.Entity;

import org.fourgeeks.gha.domain.AbstractEntity;

@Entity
public class ItSystem extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
