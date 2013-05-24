package org.fourgeeks.gha.domain.ess;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.mix.SystemInstance;

@Entity
public class ItSystem extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@OneToMany(mappedBy = "itSystem")
	private Collection <SystemInstance> systemInstances;
}
