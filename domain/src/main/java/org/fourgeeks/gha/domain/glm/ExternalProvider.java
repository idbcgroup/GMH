/**
 * 
 */
package org.fourgeeks.gha.domain.glm;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.enu.ProviderPreferenceEnum;
import org.fourgeeks.gha.domain.enu.ProviderResourceTypeEnum;
import org.fourgeeks.gha.domain.enu.ProviderServicesEnum;
import org.fourgeeks.gha.domain.enu.ProviderTypeEnum;
import org.fourgeeks.gha.domain.gmh.Brand;
import org.fourgeeks.gha.domain.gmh.Manufacturer;
import org.fourgeeks.gha.domain.mix.Institution;

/**
 * @author alacret
 * 
 */

@Entity
public class ExternalProvider extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@OneToOne
	@JoinColumn(name = "institutionFk", nullable = false)
	private Institution institution;

	@ManyToOne
	@JoinColumn(name = "manufacturerFk")
	private Manufacturer primaryManufacturer;

	@ManyToOne
	@JoinColumn(name = "brandFk")
	private Brand primaryBrand;

	/** ATTRIBUTES */

	private String code;
	/** Nombre Proveedor Externo length =255 */
	private ProviderResourceTypeEnum resourceType;
	/** Tipo de Recurso que suministra el Proveedor length =60 */
	private ProviderTypeEnum type;
	/** Tipo de Proveedor length =60 */
	private ProviderServicesEnum services;
	/** Servicios que ofrece sobre lo que suministra length =60 */
	private ProviderPreferenceEnum preference;
	/** Preferencia sobre el proveedor length =60 */
	private ProviderQualEnum qualification;
	/** Calificacion sobre el Proveedor en base a su cumplimiento length =60 */
	private ProviderRepresentEnum whoRepresent;

	/** Proveedor Representa a un FABRICANTE o a una o varias MARCAS length =60 */

	/**
	 * 
	 */
	public ExternalProvider() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param institution
	 * @param primaryManufacturer
	 * @param primaryBrand
	 * @param code
	 * @param name
	 * @param resourceType
	 * @param type
	 * @param services
	 * @param preference
	 * @param qualification
	 * @param whoRepresent
	 */
	public ExternalProvider(Institution institution,
			Manufacturer primaryManufacturer, Brand primaryBrand, String code,
			String name, ProviderResourceTypeEnum resourceType,
			ProviderTypeEnum type, ProviderServicesEnum services,
			ProviderPreferenceEnum preference, ProviderQualEnum qualification,
			ProviderRepresentEnum whoRepresent) {
		this.institution = institution;
		this.primaryManufacturer = primaryManufacturer;
		this.primaryBrand = primaryBrand;
		this.code = code;
		this.resourceType = resourceType;
		this.type = type;
		this.services = services;
		this.preference = preference;
		this.qualification = qualification;
		this.whoRepresent = whoRepresent;
	}

	/**
	 * @return
	 */
	public Institution getInstitution() {
		return institution;
	}

	/**
	 * @return
	 */
	public Manufacturer getPrimaryManufacturer() {
		return primaryManufacturer;
	}

	/**
	 * @return
	 */
	public Brand getPrimaryBrand() {
		return primaryBrand;
	}

	/**
	 * 
	 * @return
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 
	 * @return
	 */
	public ProviderResourceTypeEnum getResourceType() {
		return resourceType;
	}

	public ProviderTypeEnum getType() {
		return type;
	}

	public ProviderServicesEnum getServices() {
		return services;
	}

	public ProviderPreferenceEnum getPreference() {
		return preference;
	}

	public ProviderQualEnum getQualification() {
		return qualification;
	}

	public ProviderRepresentEnum getWhoRepresent() {
		return whoRepresent;
	}

	public void setInstitution(Institution institution) {
		this.institution = institution;
	}

	public void setPrimaryManufacturer(Manufacturer primaryManufacturer) {
		this.primaryManufacturer = primaryManufacturer;
	}

	public void setPrimaryBrand(Brand primaryBrand) {
		this.primaryBrand = primaryBrand;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setResourceType(ProviderResourceTypeEnum resourceType) {
		this.resourceType = resourceType;
	}

	public void setType(ProviderTypeEnum type) {
		this.type = type;
	}

	public void setServices(ProviderServicesEnum services) {
		this.services = services;
	}

	public void setPreference(ProviderPreferenceEnum preference) {
		this.preference = preference;
	}

	public void setQualification(ProviderQualEnum qualification) {
		this.qualification = qualification;
	}

	public void setWhoRepresent(ProviderRepresentEnum whoRepresent) {
		this.whoRepresent = whoRepresent;
	}

}
