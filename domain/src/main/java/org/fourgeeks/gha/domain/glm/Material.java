/**
 * 
 */
package org.fourgeeks.gha.domain.glm;

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
		@NamedQuery(name = "Material.getAll", query = "SELECT m FROM Material m ORDER BY m.id"),
		@NamedQuery(name = "Material.getByType", query = "SELECT m FROM Material m JOIN m.materialCategory c WHERE c.type = :materialTypeId ORDER BY c.name"),
		@NamedQuery(name = "Material.findByName", query = "SELECT m FROM Material m JOIN m.materialCategory c WHERE LOWER(c.name) LIKE :name ORDER BY c.id"),
		@NamedQuery(name = "Material.findByBrand", query = "SELECT m FROM Material m WHERE m.brand = :brand ORDER BY m.id") })
public class Material extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "materialCategoryFk", nullable = false)
	private MaterialCategory materialCategory;

	@NotNull(message = "brand-not-null")
	@ManyToOne
	@JoinColumn(name = "brandFk", nullable = false)
	private Brand brand;

	//
	// @ManyToOne
	// @JoinColumn(name = "serviceResourceFk")
	// private ServiceResource serviceResource;

	private int amount;
	private String barCode;

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
