/**
 * 
 */
package org.fourgeeks.gha.domain.glm;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.fourgeeks.gha.domain.AbstractCodeEntity;

/**
 * @author eduardoguerere
 * 
 */

@Entity
@NamedQueries(value = { @NamedQuery(name = "MaterialType.getAll", query = "SELECT e from MaterialType e order by e.name") })
public class MaterialType extends AbstractCodeEntity implements
		Comparable<MaterialType> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public MaterialType() {
	}

	public MaterialType(String code) {
		this.code = code;
	}

	@Override
	public int compareTo(MaterialType arg0) {
		return code.compareTo(arg0.getCode());
	}

	@Override
	public boolean equals(Object arg0) {
		if (arg0 == null)
			return false;
		if (this == arg0)
			return true;
		if (!(arg0 instanceof MaterialType))
			return false;
		MaterialType other = (MaterialType) arg0;
		return this.code.equals(other.getCode());
	}

	@Override
	public int hashCode() {
		return this.code.hashCode();
	}

}