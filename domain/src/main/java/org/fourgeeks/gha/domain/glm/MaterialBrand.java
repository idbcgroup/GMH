/**
 * 
 */
package org.fourgeeks.gha.domain.glm;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.gmh.Brand;

/**
 * @author emiliot
 * 
 */
@Entity
public class MaterialBrand extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "materialFk")
	private Material material;

	@ManyToOne
	@JoinColumn(name = "brandFk")
	private Brand brand;

	private int amount;

	/**
	 * 
	 */
	public MaterialBrand() {
		// TODO Auto-generated constructor stub
	}

	public int getAmount() {
		return amount;
	}

	public Brand getBrand() {
		return brand;
	}

	public Material getMaterial() {
		return material;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

}
