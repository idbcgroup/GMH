package org.fourgeeks.gha.domain.mix;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SystemInstance {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	//TODO: customPidGenerator
	//TODO: pidGenerationCriteria

	/**
	 * 
	 */
	public SystemInstance() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
