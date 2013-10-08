/**
 * 
 */
package org.fourgeeks.gha.domain.gmh;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.HasKey;

/**
 * @author emiliot, vivi.torresg
 * 
 */

@Entity
@Table(name = "manufacturer", uniqueConstraints = @UniqueConstraint(columnNames = { "name" }))
@NamedQueries(value = {
		@NamedQuery(name = "Manufacturer.getAll", query = "SELECT e from Manufacturer e order by e.name"),
		@NamedQuery(name = "Manufacturer.findByManufacturer", query = "SELECT e from Manufacturer e where e like :manufacturer order by e.id") })
public class Manufacturer extends AbstractEntity implements HasKey {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// @OneToMany(mappedBy = "manufacturer")
	// private Collection <EiaType> eiaTypes;

	@Column(nullable = false)
	private String name;

	/**
	 * 
	 */
	public Manufacturer() {

	}

	/**
	 * @param name
	 */
	public Manufacturer(String name) {
		this.name = name;
	}

	public Manufacturer(int id, String name) {
		this.id = id;
		this.name = name;
	}

	/**
	 * @return the name of the manufacturer
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
}
