/**
 * 
 */
package org.fourgeeks.gha.domain.gmh;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.enu.EiaMovilityEnum;
import org.fourgeeks.gha.domain.enu.EiaSubTypeEnum;
import org.fourgeeks.gha.domain.enu.EiaTypeEnum;

/**
 * @author emiliot
 * 
 */

@Entity
public class EiaType extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "brandFk")
	private Brand brand;

	@ManyToOne
	@JoinColumn(name = "manufacturerFk")
	private Manufacturer manufacturer;

	/** Attributes */

	private String code;
	/** Código asignado al EIA length =20 */

	@Column(nullable = false)
	private String name;
	/** Nombre del Equipo o Instalación length =255 */
	private String description;
	/** Descripción detallada Equipo o Instalación length =255 */

	private String model;
	/** Modelo del Equipo o Instalación length =255 */
	// private String eiaUseInArea; /** Área de utilización del equipo length
	// =60 */
	private String useDescription;
	/** Descripción del Uso en el área de utilización length =255 */
	// private String eiaSerialized; /** Equipo es serializado (Si/NO) length =6
	// */
	private String eiaUmdns;
	/** Código UMDNS length =16 */

	@Column(nullable = false)
	private EiaMovilityEnum movility;
	/** Equipo es movilizable length =60 */

	@Column(nullable = false)
	private EiaTypeEnum type;
	/** Tipo de Equipo length =60 */
	private EiaSubTypeEnum subtype;

	/** Subtipo de Equipo length =60 */

	/**
	 * 
	 */
	public EiaType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Brand getBrand() {
		return brand;
	}

	public Manufacturer getManufacturer() {
		return manufacturer;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getModel() {
		return model;
	}

	public String getUseDescription() {
		return useDescription;
	}

	public String getEiaUmdns() {
		return eiaUmdns;
	}

	public EiaMovilityEnum getMovility() {
		return movility;
	}

	public EiaTypeEnum getType() {
		return type;
	}

	public EiaSubTypeEnum getSubtype() {
		return subtype;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public void setUseDescription(String useDescription) {
		this.useDescription = useDescription;
	}

	public void setEiaUmdns(String eiaUmdns) {
		this.eiaUmdns = eiaUmdns;
	}

	public void setMovility(EiaMovilityEnum movility) {
		this.movility = movility;
	}

	public void setType(EiaTypeEnum type) {
		this.type = type;
	}

	public void setSubtype(EiaSubTypeEnum subtype) {
		this.subtype = subtype;
	}

}