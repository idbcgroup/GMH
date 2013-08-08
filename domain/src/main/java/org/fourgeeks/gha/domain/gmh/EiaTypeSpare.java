/**
 * 
 */
package org.fourgeeks.gha.domain.gmh;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.fourgeeks.gha.domain.AbstractEntity;

/**
 * @author emiliot
 *
 */

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "eiaTypeFk",
		"spareFk" }))
@NamedQueries(value = {
		@NamedQuery(name = "EiaTypeSpare.findByEiaType",
				query = "SELECT ets from EiaTypeSpare ets WHERE ets.eiaType = :eiaType order by ets.id"),
		})
public class EiaTypeSpare extends AbstractEntity {

	private static final long serialVersionUID = 1L;
	
	@OneToOne
	@JoinColumn(name = "eiaTypeFk")
	EiaType eiaType;
	
	@ManyToOne
	@JoinColumn(name = "spareFk")
	EiaType spare;

	public EiaType getEiaType() {
		return eiaType;
	}

	public void setEiaType(EiaType eiaType) {
		this.eiaType = eiaType;
	}

	public EiaType getSpare() {
		return spare;
	}

	public void setSpare(EiaType spare) {
		this.spare = spare;
	}
	
}
