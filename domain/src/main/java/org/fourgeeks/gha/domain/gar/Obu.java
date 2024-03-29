package org.fourgeeks.gha.domain.gar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.mix.Bpi;

/**
 * @author alacret
 * 
 *         Organization Bussiness unit
 */
@Entity
@NamedQueries(value = { 
		@NamedQuery(name = "Obu.getAll", 
				query = "SELECT e from Obu e order by e.name")
})
public class Obu extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@ManyToOne
	@JoinColumn(name = "bpiFk")
	private Bpi bpi;
	
	@ManyToOne
	@JoinColumn(name = "parentObu")
	private Obu parentObu;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String code;

	/**
	 * 
	 */
	public Obu() {
		
	}
	
	/**
	 * @param id
	 */
	public Obu(long id) {
		setId(id);
	}
	
	/**
	 * @return the bpi
	 */
	public Bpi getBpi() {
		return bpi;
	}

	/**
	 * @param bpi
	 *            the bpi to set
	 */
	public void setBpi(Bpi bpi) {
		this.bpi = bpi;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	public Obu getParentObu() {
		return parentObu;
	}

	public void setParentObu(Obu parentObu) {
		this.parentObu = parentObu;
	}

}
