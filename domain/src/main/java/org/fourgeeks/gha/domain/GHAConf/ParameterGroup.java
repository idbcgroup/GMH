/**
 * 
 */
package org.fourgeeks.gha.domain.GHAConf;

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
@Table(name = "ParameterGroup", schema = "GHAConf")
public class ParameterGroup extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "bpiFk")
	private Bpi bpi;
}
