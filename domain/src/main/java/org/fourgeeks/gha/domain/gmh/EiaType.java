/**
 * 
 */
package org.fourgeeks.gha.domain.gmh;

import javax.persistence.Entity;

import org.fourgeeks.gha.domain.AbstractEntity;

/**
 * @author emiliot
 *
 */

@Entity
public class EiaType extends AbstractEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String eiaTypeName;
	private String eiaTypeCode;
	private String eiaTypeBrand;
	private String eiaTypeManufacturer;
	private String eiaTypeModel;
	/**
	 * 
	 */
	public EiaType() {
		super();
		// TODO Auto-generated constructor stub
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getEiaTypeName() {
		return eiaTypeName;
	}
	public String getEiaTypeCode() {
		return eiaTypeCode;
	}
	public String getEiaTypeBrand() {
		return eiaTypeBrand;
	}
	public String getEiaTypeManufacturer() {
		return eiaTypeManufacturer;
	}
	public String getEiaTypeModel() {
		return eiaTypeModel;
	}
	public void setEiaTypeName(String eiaTypeName) {
		this.eiaTypeName = eiaTypeName;
	}
	public void setEiaTypeCode(String eiaTypeCode) {
		this.eiaTypeCode = eiaTypeCode;
	}
	public void setEiaTypeBrand(String eiaTypeBrand) {
		this.eiaTypeBrand = eiaTypeBrand;
	}
	public void setEiaTypeManufacturer(String eiaTypeManufacturer) {
		this.eiaTypeManufacturer = eiaTypeManufacturer;
	}
	public void setEiaTypeModel(String eiaTypeModel) {
		this.eiaTypeModel = eiaTypeModel;
	}
	
}
