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

/**
 * @author emiliot
 * ManyToMany between Resources and ProtocolActivities
 *
 */
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "protocolActivityFk", "resourceFk" }))
public class ProtocolActivityResource extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "protocolActivityFk", nullable = false)
	private ProtocolActivity protocolActivity;
	
	@ManyToOne
	@JoinColumn(name = "resourceFk", nullable = false)
	private Resource resource;

	/**
	 * 
	 */
	public ProtocolActivityResource() {
		// TODO Auto-generated constructor stub
	}

	public ProtocolActivity getProtocolActivity() {
		return protocolActivity;
	}

	public Resource getResource() {
		return resource;
	}

	public void setProtocolActivity(ProtocolActivity protocolActivity) {
		this.protocolActivity = protocolActivity;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}
	
	
}
