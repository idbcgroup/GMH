/**
 * 
 */
package org.fourgeeks.gha.domain.gmh;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.glm.Material;

/**
 * @author emiliot
 * 
 */

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "materialFk",
		"eiaTypeFk" }))
public class EiaTypeMaterial extends AbstractEntity {

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
