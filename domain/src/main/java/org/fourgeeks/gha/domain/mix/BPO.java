package org.fourgeeks.gha.domain.mix;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class BPO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private String bpoNumber;
	
	private String bpoType; //String?
	private String legalEntityCredential; //String???
	private Date legalEntityCredentialDate; //Date???
	/**
	 * 
	 */
	public BPO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getBpoNumber() {
		return bpoNumber;
	}
	public String getBpoType() {
		return bpoType;
	}
	public String getLegalEntityCredential() {
		return legalEntityCredential;
	}
	public Date getLegalEntityCredentialDate() {
		return legalEntityCredentialDate;
	}
	public void setBpoNumber(String bpoNumber) {
		this.bpoNumber = bpoNumber;
	}
	public void setBpoType(String bpoType) {
		this.bpoType = bpoType;
	}
	public void setLegalEntityCredential(String legalEntityCredential) {
		this.legalEntityCredential = legalEntityCredential;
	}
	public void setLegalEntityCredentialDate(Date legalEntityCredentialDate) {
		this.legalEntityCredentialDate = legalEntityCredentialDate;
	}
	
	
	
}
