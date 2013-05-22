package org.fourgeeks.gha.domain.gar;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import org.fourgeeks.gha.domain.AbstractEntity;

@Entity
public class ResourceService extends AbstractEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@OneToOne(mappedBy = "resourceService")
	private Facility facility;
	

}
