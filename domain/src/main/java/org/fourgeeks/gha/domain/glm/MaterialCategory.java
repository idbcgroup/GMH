/**
 * 
 */
package org.fourgeeks.gha.domain.glm;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.gmh.ServiceResourceCategory;

/**
 * @author emiliot, vivi.torresg
 * 
 */

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "code" }))
@NamedQueries(value = {
		@NamedQuery(name = "MaterialCategory.getAll", query = "SELECT e from MaterialCategory e order by e.name"),
		@NamedQuery(name = "MaterialCategory.findByCode", query = "SELECT category from MaterialCategory category WHERE category.code=:code") })
public class MaterialCategory extends AbstractEntity implements
		Comparable<MaterialCategory> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "serviceResourceCategoryFk")
	private ServiceResourceCategory sRCategory;

	private String name;
	private String code;

	/**
	 * 
	 */
	public MaterialCategory() {
	}

	@Override
	public int compareTo(MaterialCategory arg0) {
		return code.compareTo(arg0.getCode());
	}

	@Override
	public boolean equals(Object arg0) {
		if (arg0 == null)
			return false;
		if (this == arg0)
			return true;
		if (!(arg0 instanceof MaterialCategory))
			return false;
		MaterialCategory other = (MaterialCategory) arg0;
		return this.code.equals(other.getCode());
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public ServiceResourceCategory getsRCategory() {
		return sRCategory;
	}

	@Override
	public int hashCode() {
		return this.code.hashCode();
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setsRCategory(ServiceResourceCategory sRCategory) {
		this.sRCategory = sRCategory;
	}

}