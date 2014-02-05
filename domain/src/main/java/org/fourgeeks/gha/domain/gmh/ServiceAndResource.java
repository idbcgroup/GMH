package org.fourgeeks.gha.domain.gmh;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import org.fourgeeks.gha.domain.AbstractCodeEntity;

/**
 * Entity to represent resources and services
 * 
 * @author emiliot, naramirez
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class ServiceAndResource extends AbstractCodeEntity {
	/** */
	private static final long serialVersionUID = 1L;

}
