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
@Table(name = "brand", uniqueConstraints = @UniqueConstraint(columnNames = { "name" }))
public class Brand extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// @OneToMany(mappedBy = "brand", fetch=FetchType.EAGER)
	// private ArrayList<EiaType> eiaTypes;

	private String name;

	public Brand(int id, String name) {
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
