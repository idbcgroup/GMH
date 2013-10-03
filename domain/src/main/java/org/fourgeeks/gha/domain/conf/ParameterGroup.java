/**
 * 
 */
package org.fourgeeks.gha.domain.conf;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.mix.Bpi;

/**
 * @author emiliot
 * 
 */
@Entity
@Table(name = "ParameterGroup", schema = "conf")
public class ParameterGroup extends AbstractEntity {

	/**
	 * @param name
	 */
	public ParameterGroup(String name) {
		super();
		this.setName(name);
	}

	/**
	 * @return the name
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

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "bpiFk")
	private Bpi bpi;

	private String name;
}
