package org.fourgeeks.gha.domain.gar;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.fourgeeks.gha.domain.AbstractEntity;

@Entity
public class JobPositionDefinition extends AbstractEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@OneToMany(mappedBy = "jobPositionDef")
	private Collection <JobPosition> jobPosition;
	
}
