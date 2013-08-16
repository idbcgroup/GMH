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
 * @author emiliot
 * 
 */

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "name" }))
@NamedQueries(value = {
		@NamedQuery(name = "Brand.getAll", query = "SELECT e from Brand e order by e.name"),
		@NamedQuery(name = "Brand.findByName", query = "SELECT e from Brand e where lower(e.name) like :name order by e.id") })
public class Brand extends AbstractEntity implements HasKey {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// @OneToMany(mappedBy = "brand", fetch=FetchType.EAGER)
	// private ArrayList<EiaType> eiaTypes;

	@Column(nullable = false)
	private String name;

	/**
	 * 
	 */
	public Brand() {

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
}
