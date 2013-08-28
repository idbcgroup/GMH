package org.fourgeeks.gha.domain.gmh;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.fourgeeks.gha.domain.AbstractEntity;

/**
 * @author alacret
 * 
 */
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {
		"protocolActivityFk", "parentProtocolActivityFk" }))
@NamedQueries(value = {
		@NamedQuery(name = "ProtocolActivityComponent.getAll", query = "SELECT e from ProtocolActivityComponent e order by e.id"),
		@NamedQuery(name = "ProtocolActivityComponent.findByProtocolActivity", query = "SELECT e FROM  ProtocolActivityComponent e WHERE e.parentProtocolActivity = :parentProtocolActivity ORDER BY e.ordinal")})
public class ProtocolActivityComponent extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "protocolActivityFk")
	private ProtocolActivity protocolActivity;

	@ManyToOne
	@JoinColumn(name = "parentProtocolActivityFk")
	private ProtocolActivity parentProtocolActivity;
	
	/**
	 * Number of order for activity
	 */
	private int ordinal;

	/**
	 * 
	 */
	public ProtocolActivityComponent() {
	}

	public ProtocolActivity getProtocolActivity() {
		return protocolActivity;
	}

	public ProtocolActivity getParentProtocolActivity() {
		return parentProtocolActivity;
	}

	public int getOrdinal() {
		return ordinal;
	}

	public void setProtocolActivity(ProtocolActivity protocolActivity) {
		this.protocolActivity = protocolActivity;
	}

	public void setParentProtocolActivity(ProtocolActivity parentProtocolActivity) {
		this.parentProtocolActivity = parentProtocolActivity;
	}

	public void setOrdinal(int ordinal) {
		this.ordinal = ordinal;
	}
	
	
}