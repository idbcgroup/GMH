package org.fourgeeks.gha.domain.mix;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import org.fourgeeks.gha.domain.AbstractEntity;

@Entity
public class AssociatedCitizenId extends AbstractEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManyToMany(mappedBy="associatedCitizens")
	private Collection <Citizen> citizens;

}
