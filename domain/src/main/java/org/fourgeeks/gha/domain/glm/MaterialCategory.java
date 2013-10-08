/**
 * 
 */
package org.fourgeeks.gha.domain.glm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.gmh.ServiceResourceCategory;

/**
 * @author emiliot, vivi.torresg
 * 
 */

@Entity
@NamedQueries(value = {
		@NamedQuery(name = "MaterialCategory.findByMaterialCategory", query = "SELECT e from MaterialCategory e where e like:materialCategory order by e.name"),
		@NamedQuery(name = "MaterialCategory.getAll", query = "SELECT e from MaterialCategory e order by e.name") })
public class MaterialCategory extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull(message = "El tipo de material no puede ser nulo")
	@Column(nullable = false)
	private MaterialTypeEnum type;

	@ManyToOne
	@JoinColumn(name = "serviceResourceCategoryFk")
	private ServiceResourceCategory sRCategory;

	private String description;
	private String name;
	private String code;
	private String extCode;
	private String model;

	/**
	 * 
	 */
	public MaterialCategory() {
		// TODO Auto-generated constructor stub
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getExtCode() {
		return extCode;
	}

	public void setExtCode(String extCode) {
		this.extCode = extCode;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}
}
