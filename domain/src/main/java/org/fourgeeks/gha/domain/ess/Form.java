package org.fourgeeks.gha.domain.ess;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.fourgeeks.gha.domain.AbstractEntity;

@Entity
public class Form extends AbstractEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@OneToMany(mappedBy = "form")
	private Collection<View> views;

}
