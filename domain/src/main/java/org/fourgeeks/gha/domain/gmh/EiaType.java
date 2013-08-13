/**
 * 
 */
package org.fourgeeks.gha.domain.gmh;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.enu.EiaMobilityEnum;
import org.fourgeeks.gha.domain.enu.EiaSubTypeEnum;
import org.fourgeeks.gha.domain.enu.EiaTypeEnum;

/**
 * @author emiliot
 * 
 */

@Entity
@NamedQueries(value = { @NamedQuery(name = "EiaType.getAll", query = "SELECT e from EiaType e order by e.id") })
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

	@NotNull(message = "El Nombre no puede estar vacío")
	@Size(min = 1, max = 255, message = "El Nombre debe tener entre 1 y 255 caracteres")
	@Column(nullable = false)
	private String name;
	/** Nombre del Equipo o Instalación length =255 */

	@Size(min = 1, max = 255, message = "La Descripción debe tener entre 1 y 255 caracteres")
	private String description;
	/** Descripción detallada Equipo o Instalación length =255 */

	@Size(min = 1, max = 255, message = "El Modelo debe tener entre 1 y 255 caracteres")
	private String model;
	/** Modelo del Equipo o Instalación length =255 */

	// private String eiaUseInArea; /** Área de utilización del equipo length
	// =60 */

	@Size(min = 1, max = 255, message = "El Uso debe tener entre 1 y 255 caracteres")
	private String useDescription;
	/** Descripción del Uso en el área de utilización length =255 */

	// private String eiaSerialized; /** Equipo es serializado (Si/NO) length =6
	// */

	@Size(min = 1, max = 16, message = "El Código UMDNS debe tener entre 1 y 255 caracteres")
	private String eiaUmdns;
	/** Código UMDNS length =16 */

	@NotNull(message = "La Movilidad no puede estar vacía")
	@Column(nullable = false)
	private EiaMobilityEnum mobility;
	/** Equipo es movilizable length =60 */

	@NotNull(message = "El Tipo no puede estar vacío")
	@Column(nullable = false)
	private EiaTypeEnum type;
	/** Tipo de Equipo length =60 */

	private EiaSubTypeEnum subtype;

	// @Transient
	// private List<EiaTypePicture> listEiaTypePicture;

	/**
	 * 
	 */
	public EiaType() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public EiaType(long id) {
		setId(id);
	}

	/**
	 * @param brand
	 * @param manufacturer
	 * @param name
	 * @param mobility
	 * @param type
	 * @param subtype
	 * @param model
	 * @param code
	 */
	public EiaType(Brand brand, Manufacturer manufacturer, String name,
			EiaMobilityEnum mobility, EiaTypeEnum type, EiaSubTypeEnum subtype,
			String model, String code) {
		super();
		this.brand = brand;
		this.manufacturer = manufacturer;
		this.name = name;
		this.mobility = mobility;
		this.type = type;
		this.subtype = subtype;
		this.model = model;
		this.code = code;
	}

	// public List<EiaTypePicture> getListEiaTypePicture() {
	// return listEiaTypePicture;
	// }
	//
	// public void setListEiaTypePicture(List<EiaTypePicture>
	// listEiaTypePicture) {
	// this.listEiaTypePicture = listEiaTypePicture;
	// }

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

	public EiaMobilityEnum getMobility() {
		return mobility;
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

	public void setMobility(EiaMobilityEnum mobility) {
		this.mobility = mobility;
	}

	public void setType(EiaTypeEnum type) {
		this.type = type;
	}

	public void setSubtype(EiaSubTypeEnum subtype) {
		this.subtype = subtype;
	}

}