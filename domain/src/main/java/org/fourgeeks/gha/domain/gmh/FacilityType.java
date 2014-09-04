/**
 * 
 */
package org.fourgeeks.gha.domain.gmh;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.fourgeeks.gha.domain.AbstractCodeEntity;

/**
 * @author eduardoguerere
 * 
 */

@Entity
@NamedQueries(value = { @NamedQuery(name = "FacilityType.getAll", query = "SELECT e from FacilityType e order by e.name") })
public class FacilityType extends AbstractCodeEntity implements
		Comparable<FacilityType> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public FacilityType() {
	}

	public FacilityType(String code) {
		this.code = code;
	}

	@Override
	public int compareTo(FacilityType arg0) {
		return code.compareTo(arg0.getCode());
	}

	@Override
	public boolean equals(Object arg0) {
		if (arg0 == null)
			return false;
		if (this == arg0)
			return true;
		if (!(arg0 instanceof FacilityType))
			return false;
		FacilityType other = (FacilityType) arg0;
		return this.code.equals(other.getCode());
	}

	@Override
	public int hashCode() {
		return this.code.hashCode();
	}

}