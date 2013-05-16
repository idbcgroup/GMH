package org.fourgeeks.gha.domain.mix;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Institution {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String description;
	private String healthCode; //String?
	private String institutionLevel; //String?
	private String name;
	
	//TODO: private String principalProduct;
	//TODO: private String productiveSector;
	//TODO: private String productiveSubSector;
	
	private int risk; //int?
	private String sector; //String?
	private String taxCode; //String?
	/**
	 * 
	 */
	public Institution() {
		super();
		// TODO Auto-generated constructor stub
	}
	public long getId() {
		return id;
	}
	public String getDescription() {
		return description;
	}
	public String getHealthCode() {
		return healthCode;
	}
	public String getInstitutionLevel() {
		return institutionLevel;
	}
	public String getName() {
		return name;
	}
	public int getRisk() {
		return risk;
	}
	public String getSector() {
		return sector;
	}
	public String getTaxCode() {
		return taxCode;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setHealthCode(String healthCode) {
		this.healthCode = healthCode;
	}
	public void setInstitutionLevel(String institutionLevel) {
		this.institutionLevel = institutionLevel;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setRisk(int risk) {
		this.risk = risk;
	}
	public void setSector(String sector) {
		this.sector = sector;
	}
	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}
	
}
