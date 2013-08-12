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
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "materialFk",
		"eiaTypeFk" }))
@NamedQueries(value = { @NamedQuery(name = "EiaTypeUtility.findByEiaTypeId", query = "SELECT e from EiaTypeUtility e WHERE e.eiaType = :eiaTypeId order by e.id") })
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
