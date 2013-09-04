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
import org.fourgeeks.gha.domain.gmh.Brand;

/**
 * @author alacret
 * 
 */
@Entity
@NamedQueries(value = {
		@NamedQuery(name = "Material.getAll", query = "SELECT m FROM Material m ORDER BY m.name"),
		@NamedQuery(name = "Material.getByType", query = "SELECT m FROM Material m JOIN m.materialCategory c WHERE c.type = :materialTypeId ORDER BY c.name"),
		@NamedQuery(name = "Material.findByName", query = "SELECT m FROM Material m JOIN m.materialCategory c WHERE LOWER(c.name) LIKE :name ORDER BY c.id"),
		@NamedQuery(name = "Material.findByBrand", query = "SELECT m FROM Material m WHERE m.brand = :brand ORDER BY m.id")})
public class Material extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "materialCategoryFk")
	private MaterialCategory materialCategory;
	
	@ManyToOne
	@JoinColumn(name = "brandFk")
	private Brand brand;
	
	private int amount;
	private String barCode;
	
	/*ALL THE FIELDS BELOW SHOULD BE ELIMINATED*/

	@NotNull(message = "El tipo de material no puede ser nulo")
	@Column(nullable = false)
	private MaterialTypeEnum type;
	private String description;
	@ManyToOne
	@JoinColumn(name = "providerFk")
	private ExternalProvider externalProvider;
	private String name;
	private String code;
	private String extCode;
	private String model;
	/**
	 * 
	 */
	public Material() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param code
	 * @param name
	 * @param type
	 */
	public Material(String code, String name, MaterialTypeEnum type) {
		this.code = code;
		this.name = name;
		this.type = type;
	}

	/**
	 * @return the type
	 */
	public MaterialTypeEnum getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
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
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the provider
	 */
	public ExternalProvider getExternalProvider() {
		return externalProvider;
	}

	/**
	 * @param externalProvider
	 */
	public void setExternalProvider(ExternalProvider externalProvider) {
		this.externalProvider = externalProvider;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the extCode
	 */
	public String getExtCode() {
		return extCode;
	}

	/**
	 * @param extCode
	 *            the extCode to set
	 */
	public void setExtCode(String extCode) {
		this.extCode = extCode;
	}

	/**
	 * @return the model
	 */
	public String getModel() {
		return model;
	}

	/**
	 * @param model
	 *            the model to set
	 */
	public void setModel(String model) {
		this.model = model;
	}

	public MaterialCategory getMaterialCategory() {
		return materialCategory;
	}

	public void setMaterialCategory(MaterialCategory materialCategory) {
		this.materialCategory = materialCategory;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

}
