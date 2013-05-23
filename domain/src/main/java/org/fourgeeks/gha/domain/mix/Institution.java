package org.fourgeeks.gha.domain.mix;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.fourgeeks.gha.domain.AbstractEntity;

@Entity
public class Institution extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@OneToMany(mappedBy = "institution")
	private Collection<InstitutionContact> institutionContacts;
	
	/**
	 * 
	 */
	public Institution() {
		super();
		// TODO Auto-generated constructor stub
	}
}
