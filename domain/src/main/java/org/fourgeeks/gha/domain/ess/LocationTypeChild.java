/**
 * 
 */
package org.fourgeeks.gha.domain.ess;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.fourgeeks.gha.domain.AbstractEntity;

/**
 * @author emiliot
 *
 */
@Entity
public class LocationTypeChild extends AbstractEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 *  This represents the parent locationType
	 */
	@ManyToOne
	@JoinColumn(name = "")
	private LocationType parentLocationType;
	
	/**
	 * This respresents the locationType associated with this child locationType
	 */
	@OneToOne
	private LocationType locationType;
	
}
