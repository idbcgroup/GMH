/**
 * 
 */
package org.fourgeeks.gha.domain.glm;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;

import org.fourgeeks.gha.domain.gmh.ServiceAndResource;

/**
 * @author alacret, emiliot
 * 
 */
@Entity
@DiscriminatorValue("material")
@NamedQueries(value = {
		@NamedQuery(name = "Material.getAll", query = "SELECT m FROM Material m ORDER BY m.name"),
		@NamedQuery(name = "Material.getByType", query = "SELECT m FROM Material m WHERE m.type = :type ORDER BY m.name"),
		@NamedQuery(name = "Material.findByName", query = "SELECT m FROM Material m WHERE LOWER(m.name) LIKE :name ORDER BY m.code") })
public class Material extends ServiceAndResource {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull(message = "material-type-not-null")
	@Column(nullable = false)
	private MaterialTypeEnum type;

	@ManyToOne
	@JoinColumn(name = "materialCategoryFk")
	private MaterialCategory materialCategory;

	private String description;
	private String name;
	private String externalCode;
	private String model;

	private String barCode;

	/**
	 * 
	 */
	public Material() {
		super();
	}

	public String getBarCode() {
		return barCode;
	}

	public String getDescription() {
		return description;
	}

	public String getExternalCode() {
		return externalCode;
	}

	public MaterialCategory getMaterialCategory() {
		return materialCategory;
	}

	public String getModel() {
		return model;
	}

	public String getName() {
		return name;
	}

	public MaterialTypeEnum getType() {
		return type;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setExternalCode(String externalCode) {
		this.externalCode = externalCode;
	}

	public void setMaterialCategory(MaterialCategory materialCategory) {
		this.materialCategory = materialCategory;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setType(MaterialTypeEnum type) {
		this.type = type;
	}
}
