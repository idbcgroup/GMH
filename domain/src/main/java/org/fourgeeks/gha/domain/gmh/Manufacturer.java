/**
 * 
 */
package org.fourgeeks.gha.domain.gmh;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.fourgeeks.gha.domain.AbstractEntity;

/**
 * @author emiliot
 * 
 */

@Entity
@Table(name = "manufacturer", uniqueConstraints = @UniqueConstraint(columnNames = { "name" }))
public class Manufacturer extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// @OneToMany(mappedBy = "manufacturer")
	// private Collection <EiaType> eiaTypes;

	private String name;
	
	public Manufacturer(){
		
	}

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
