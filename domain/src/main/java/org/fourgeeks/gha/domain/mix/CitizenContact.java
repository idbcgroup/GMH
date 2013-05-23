package org.fourgeeks.gha.domain.mix;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import org.fourgeeks.gha.domain.AbstractEntity;

@Entity
public class CitizenContact extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManyToMany(mappedBy="citizenContacts")
	private Collection <Citizen> citizens;
	
	/**
	 * 
	 */
	public CitizenContact() {
		super();
		// TODO Auto-generated constructor stub
	}
}
