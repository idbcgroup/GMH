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
	@NotNull(message = "name-not-null")
	@Column(nullable = false)
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

	/**
	 * @return the type
	 */
	public MaterialTypeEnum getType() {
		return type;
	}

	/**
	 * @param type
	 */
	public void setType(MaterialTypeEnum type) {
		this.type = type;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the external code
	 */
	public String getExternalCode() {
		return externalCode;
	}

	/**
	 * @param extCode
	 */
	public void setExternalCode(String extCode) {
		this.externalCode = extCode;
	}

	/**
	 * @return the model
	 */
	public String getModel() {
		return model;
	}

	/**
	 * @param model
	 */
	public void setModel(String model) {
		this.model = model;
	}
}