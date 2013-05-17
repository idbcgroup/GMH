package org.fourgeeks.gha.domain.mix;

import java.sql.Timestamp;

import javax.persistence.Entity;

import org.fourgeeks.gha.domain.AbstractEntity;

@Entity
public class LegalEntity extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Timestamp createdDate;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}
}
