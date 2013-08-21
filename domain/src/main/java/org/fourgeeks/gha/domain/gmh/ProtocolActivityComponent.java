package org.fourgeeks.gha.domain.gmh;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
public class ProtocolActivityComponent extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "protocolActivityFk")
	ProtocolActivity protocolActivity;

	@ManyToOne
	@JoinColumn(name = "parentProtocolActivityFk")
	ProtocolActivity parentProtocolActivity;

	/**
	 * 
	 */
	public ProtocolActivityComponent() {
	}
}