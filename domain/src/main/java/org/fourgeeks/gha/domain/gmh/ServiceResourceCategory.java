/**
 * 
 */
package org.fourgeeks.gha.domain.gmh;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.fourgeeks.gha.domain.AbstractCodeEntity;

/**
 * @author emiliot
 * 
 */
@Entity
@NamedQueries(value = {
		@NamedQuery(name = "ServiceResourceCategory.getAll", query = "SELECT category from ServiceResourceCategory category ORDER BY category.code"),
		@NamedQuery(name = "ServiceResourceCategory.findByCode", query = "SELECT category from ServiceResourceCategory category WHERE category.code = :code") })
public class ServiceResourceCategory extends AbstractCodeEntity implements

Comparable<ServiceResourceCategory> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;

	/**
	 * 
	 */
	public ServiceResourceCategory() {
		// TODO Auto-generated constructor stub
	}

	public ServiceResourceCategory(String code) {
		this.code = code;
	}

	@Override
	public int compareTo(ServiceResourceCategory arg0) {
		return code.compareTo(arg0.getCode());
	}

	@Override
	public boolean equals(Object arg0) {
		if (arg0 == null)
			return false;
		if (this == arg0)
			return true;
		if (!(arg0 instanceof ServiceResourceCategory))
			return false;
		final ServiceResourceCategory other = (ServiceResourceCategory) arg0;
		return this.code.equals(other.getCode());
	}

	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		return code.hashCode();
	}

	public void setName(String name) {
		this.name = name;
	}

}
