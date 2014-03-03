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

import org.fourgeeks.gha.domain.gmh.Brand;
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
		@NamedQuery(name = "Material.findByName", query = "SELECT m FROM Material m WHERE LOWER(m.name) LIKE :name ORDER BY m.code"),
		@NamedQuery(name = "Material.findByBrand", query = "SELECT m FROM Material m WHERE m.brand = :brand ORDER BY m.name") })
public class Material extends ServiceAndResource {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull(message = "material-type-not-null")
	@Column(nullable = false)
	private MaterialTypeEnum type;

	// @ManyToOne
	// @NotNull(message = "material-category-not-null")
	// @JoinColumn(name = "materialCategoryFk"/* , nullable = false */)
	// private MaterialCategory materialCategory;

	private String description;
	private String name;
	private String externalCode;
	private String model;

	@ManyToOne
	@JoinColumn(name = "brandFk")
	private Brand brand;
	private int amount;
	private String barCode;

	/**
	 * 
	 */
	public Material() {
		super();
	}

	/**
	 * @return the amount
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * @return the barCode
	 */
	public String getBarCode() {
		return barCode;
	}

	/**
	 * @return the brand
	 */
	public Brand getBrand() {
		return brand;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return the externalCode
	 */
	public String getExternalCode() {
		return externalCode;
	}

	// public MaterialCategory getMaterialCategory() {
	// return materialCategory;
	// }

	/**
	 * @return the model
	 */
	public String getModel() {
		return model;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the type
	 */
	public MaterialTypeEnum getType() {
		return type;
	}

	/**
	 * @param amount
	 *            the amount to set
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}

	/**
	 * @param barCode
	 *            the barCode to set
	 */
	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	/**
	 * @param brand
	 *            the brand to set
	 */
	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @param externalCode
	 *            the externalCode to set
	 */
	public void setExternalCode(String externalCode) {
		this.externalCode = externalCode;
	}

	// public void setMaterialCategory(MaterialCategory materialCategory) {
	// this.materialCategory = materialCategory;
	// }

	/**
	 * @param model
	 *            the model to set
	 */
	public void setModel(String model) {
		this.model = model;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(MaterialTypeEnum type) {
		this.type = type;
	}

}
