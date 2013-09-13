package org.fourgeeks.gha.domain.gar;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.ess.Role;
import org.fourgeeks.gha.domain.gmh.ServiceResource;

@Entity
public class Job extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "jobCategoryFk")
	private JobCategory jobCategory;
	
	@ManyToOne
	@JoinColumn(name = "serviceResourceFk")
	private ServiceResource serviceResource;
	
	@ManyToOne
	@JoinColumn(name = "roleFk")
	private Role role;
	
	@ManyToOne
	@JoinColumn(name = "obuFk")
	private Obu obu;

	/**
	 * 
	 */
	public Job() {
		// TODO Auto-generated constructor stub
	}


	public JobCategory getJobCategory() {
		return jobCategory;
	}

	public void setJobCategory(JobCategory jobCategory) {
		this.jobCategory = jobCategory;
	}

	public ServiceResource getServiceResource() {
		return serviceResource;
	}

	public void setServiceResource(ServiceResource serviceResource) {
		this.serviceResource = serviceResource;
	}


	public Role getRole() {
		return role;
	}


	public void setRole(Role role) {
		this.role = role;
	}


	public Obu getObu() {
		return obu;
	}


	public void setObu(Obu obu) {
		this.obu = obu;
	}

}
