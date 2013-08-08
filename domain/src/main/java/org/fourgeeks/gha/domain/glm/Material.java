/**
 * 
 */
package org.fourgeeks.gha.domain.glm;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.gmh.EiaTypeMaterial;
import org.fourgeeks.gha.domain.gmh.EiaTypeUtility;

/**
 * @author emiliot
 *
 */
@Entity
public class Material extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "materialTypeFk")
	private MaterialType type;
	
	@ManyToOne
	@JoinColumn(name = "eUtilityFk")
	private EiaTypeUtility eUtility;
	
	@ManyToOne
	@JoinColumn(name = "eMaterialFk")
	private EiaTypeMaterial eMaterial;
}
