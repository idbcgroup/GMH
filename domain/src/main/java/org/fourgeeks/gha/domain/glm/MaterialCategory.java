/**
 * 
 */
package org.fourgeeks.gha.domain.glm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;

import org.fourgeeks.gha.domain.AbstractCodeEntity;

/**
 * @author emiliot, vivi.torresg
 * 
 */

@Entity
@NamedQueries(value = {
		@NamedQuery(name = "MaterialCategory.getByType", query = "SELECT m FROM MaterialCategory m WHERE m.type = :materialTypeId ORDER BY m.name"),
		@NamedQuery(name = "MaterialCategory.getAll", query = "SELECT e from MaterialCategory e order by e.name") })
public class MaterialCategory extends AbstractCodeEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull(message = "material-type-not-null")
	@Column(nullable = false)
	private MaterialTypeEnum type;

	// @ManyToOne
	// @JoinColumn(name = "serviceResourceCategoryFk")
	// private ServiceResourceCategory sRCategory;

	private String description;
	private String name;
	private String externalCode;
	private String model;

	/**
	 * 
	 */
	public MaterialCategory() {
	}

	/**
	 * @param code
	 * @param name
	 * @param type
	 */
	public MaterialCategory(String code, String name, MaterialTypeEnum type) {
		this.type = type;
		this.name = name;
		this.code = code;
	}

	public MaterialTypeEnum getType() {
		return type;
	}

	public void setType(MaterialTypeEnum type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExternalCode() {
		return externalCode;
	}

	public void setExternalCode(String extCode) {
		this.externalCode = extCode;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}
}
