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

/**
 * @author emiliot
 * 
 */

@Entity
@Table(name = "manufacturer", uniqueConstraints = @UniqueConstraint(columnNames = { "name" }))
@NamedQueries(value = { 
		@NamedQuery(name = "Manufacturer.getAll", 
				query = "SELECT e from Manufacturer e order by e.name"),
		@NamedQuery(name = "Manufacturer.findByName",
				query = "SELECT e from Manufacturer e where lower(e.name) like :name")
})
public class Manufacturer extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// @OneToMany(mappedBy = "manufacturer")
	// private Collection <EiaType> eiaTypes;

	@Column(nullable = false)
	private String name;

	public Manufacturer() {

	}

	public Manufacturer(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
