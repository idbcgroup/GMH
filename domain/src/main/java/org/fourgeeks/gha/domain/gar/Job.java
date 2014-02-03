package org.fourgeeks.gha.domain.gar;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.ess.Role;
import org.fourgeeks.gha.domain.gmh.ServiceAndResource;

/**
 * @author emiliot, naramirez
 */
@Entity
@NamedQueries(value = { @NamedQuery(name = "Job.getAll", query = "SELECT e from Job e order by e.id") })
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
	private ServiceAndResource serviceResource;

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

	/**
	 * @param obu
	 * @param role
	 */
	public Job(Obu obu, Role role) {
		this.obu = obu;
		this.role = role;
	}

	/**
	 * @return
	 */
	public JobCategory getJobCategory() {
		return jobCategory;
	}

	/**
	 * @param jobCategory
	 */
	public void setJobCategory(JobCategory jobCategory) {
		this.jobCategory = jobCategory;
	}

	/**
	 * @return
	 */
	public ServiceAndResource getServiceResource() {
		return serviceResource;
	}

	/**
	 * @param serviceResource
	 */
	public void setServiceResource(ServiceAndResource serviceResource) {
		this.serviceResource = serviceResource;
	}

	/**
	 * @return
	 */
	public Role getRole() {
		return role;
	}

	/**
	 * @param role
	 */
	public void setRole(Role role) {
		this.role = role;
	}

	/**
	 * @return
	 */
	public Obu getObu() {
		return obu;
	}

	/**
	 * @param obu
	 */
	public void setObu(Obu obu) {
		this.obu = obu;
	}

}
