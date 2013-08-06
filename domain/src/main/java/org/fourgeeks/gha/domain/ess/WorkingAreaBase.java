/**
 * 
 */
package org.fourgeeks.gha.domain.ess;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.gar.ObuBase;

/**
 * @author emiliot
 *
 */

@Entity
public class WorkingAreaBase extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@OneToOne
	@JoinColumn(name = "locationTypeFk")
	private LocationType locationType;
	
	@ManyToOne
	@JoinColumn(name = "obuBaseFk")
	private ObuBase obuBase;

	/**
	 * 
	 */
	public WorkingAreaBase() {
		// TODO Auto-generated constructor stub
	}

	public LocationType getLocationType() {
		return locationType;
	}

	public ObuBase getObuBase() {
		return obuBase;
	}

	public void setLocationType(LocationType locationType) {
		this.locationType = locationType;
	}

	public void setObuBase(ObuBase obuBase) {
		this.obuBase = obuBase;
	}
	
	
}
