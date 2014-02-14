/**
 * 
 */
package org.fourgeeks.gha.domain.gmh;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.fourgeeks.gha.domain.AbstractEntity;

/**
 * @author emiliot
 * 
 */
@Entity
@NamedQueries(value = {
		@NamedQuery(name = "EiaTypeCategory.getAll", query = "SELECT category from EiaTypeCategory category ORDER BY category.name"),
		@NamedQuery(name = "EiaTypeCategory.findByCode", query = "SELECT category from EiaTypeCategory category WHERE category.code=:code") })
public class EiaTypeCategory extends AbstractEntity {

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
	public EiaTypeCategory() {
		// TODO Auto-generated constructor stub
	}

	public EiaTypeCategory(String code) {
		this.code = code;
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
