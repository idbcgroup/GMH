/**
 * 
 */
package org.fourgeeks.gha.domain.ess;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.fourgeeks.gha.domain.AbstractEntity;

/**
 * @author emiliot
 *
 */

@Entity
public class LocationType extends AbstractEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@OneToMany(mappedBy = "locationType")
	private Collection <WorkingArea> workingAreas;
	
	/**
	 * This represents the children collection of this locationType
	 */
	@OneToMany(mappedBy = "parentLocationType")
	private Collection <LocationTypeChild> locationTypeChildren;
	
	/**
	 * 
	 * This represents the link relation to my parent (if any),
	 * semantically it says who is my locationType to refer to my locationType parent
	 */
	@OneToOne(mappedBy = "locationType")
	private LocationTypeChild locationTypeChild;

}
