/**
 * 
 */
package org.fourgeeks.gha.domain.gmh;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.HasKey;
import org.fourgeeks.gha.domain.glm.Material;

/**
 * @author emiliot, vivi.torresg
 * 
 */

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "name" }))
@NamedQueries(value = {
		@NamedQuery(name = "Brand.getAll", query = "SELECT e from Brand e order by e.name"),
		@NamedQuery(name = "Brand.findByManufacturer", query = "SELECT e from Brand e WHERE e.manufacturer = :manufacturer ORDER BY e.name"),
		@NamedQuery(name = "Brand.findByName", query = "SELECT e from Brand e where lower(e.name) like :name order by e.id") })
public class Brand extends AbstractEntity implements HasKey {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "manufacturerFk")
	private Manufacturer manufacturer;

	@Column(nullable = false)
	private String name;

	@ManyToOne
	@NotNull(message = "material-not-null")
	@JoinColumn(name = "materialFk", nullable = false)
	private Material material;

	@ManyToOne
	@JoinColumn(name = "eiaTypeFk", nullable = false, columnDefinition = "varchar(255) REFERENCES eiatype(code) ON UPDATE CASCADE ON DELETE CASCADE")
	private EiaType eiaType;

	/**
	 * @return the material
	 */
	public Material getMaterial() {
		return material;
	}

	/**
	 * @param material
	 *            the material to set
	 */
	public void setMaterial(Material material) {
		this.material = material;
	}

	/**
	 * 
	 */
	public Brand() {

	}

	public Brand(long id) {
		this.id = id;
	}

	/**
	 * @param name
	 */
	public Brand(String name) {
		this.name = name;
	}

	/**
	 * @param id
	 * @param name
	 */
	public Brand(int id, String name) {
		this.id = id;
		this.name = name;
	}

	/**
	 * @return the name of the brand
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

	@Override
	public String getKey() {
		return String.valueOf(getId());
	}

	public Manufacturer getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}
}
