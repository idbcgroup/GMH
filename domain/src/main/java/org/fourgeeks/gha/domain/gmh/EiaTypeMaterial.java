/**
 * 
 */
package org.fourgeeks.gha.domain.gmh;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.glm.Material;

/**
 * @author emiliot
 * 
 */

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "eiaTypeFk",
		"materialFk" }))
@NamedQueries(value = {
		@NamedQuery(name = "EiaTypeMaterial.findByEiaType",
				query = "SELECT etm FROM EiaTypeMaterial etm WHERE etm.eiaType = :eiaType ORDER BY etm.id")
	})
public class EiaTypeMaterial extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "eiaTypeFk")
	private EiaType eiaType;

	@ManyToOne
	@JoinColumn(name = "materialFk")
	private Material material;

	public EiaType getEiaType() {
		return eiaType;
	}

	public void setEiaType(EiaType eiaType) {
		this.eiaType = eiaType;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}
	
}
