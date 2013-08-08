/**
 * 
 */
package org.fourgeeks.gha.domain.gmh;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.glm.Material;

/**
 * @author emiliot
 *
 */
@Entity
public class EiaTypeUtility extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "materialFk")
	private Material material;
	
	@ManyToOne
	@JoinColumn(name = "eiaTypeFk")
	private EiaType eiaType;
}
