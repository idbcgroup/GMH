/**
 * 
 */
package org.fourgeeks.gha.domain.gmh;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.fourgeeks.gha.domain.enu.EiaMobilityEnum;
import org.fourgeeks.gha.domain.enu.EiaSubTypeEnum;
import org.fourgeeks.gha.domain.enu.EiaTypeEnum;

/**
 * @author emiliot
 * 
 */

@Entity
@DiscriminatorValue("eiatype")
@NamedQueries(value = {
		@NamedQuery(name = "EiaType.getAll", query = "SELECT e from EiaType e order by e.code"),
		@NamedQuery(name = "EiaType.findByMaintenancePlan", query = "SELECT etype from EiaTypeMaintenancePlan e JOIN e.eiaType etype WHERE e.maintenancePlan = :maintenancePlan ORDER BY e.id") })
public class EiaType extends ServiceAndResource {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "brandFk")
	private Brand brand;

	// @ManyToOne
	// @JoinColumn(name = "manufacturerFk")
	// private Manufacturer manufacturer;

	@NotNull(message = "name-not-null")
	@Size(min = 1, max = 255, message = "El Nombre debe tener entre 1 y 255 caracteres")
	@Column(nullable = false)
	private String name;
	/** Nombre del Equipo o InstalaciÃ³n length =255 */

	// @Size(min = 1, max = 255, message =
	// "La DescripciÃ³n debe tener entre 1 y 255 caracteres")
	private String description;
	/** DescripciÃ³n detallada Equipo o InstalaciÃ³n length =255 */

	// @Size(min = 1, max = 255, message =
	// "El Modelo debe tener entre 1 y 255 caracteres")
	private String model;
	/** Modelo del Equipo o InstalaciÃ³n length =255 */

	// private String eiaUseInArea; /** Ã�rea de utilizaciÃ³n del equipo length
	// =60 */

	// @Size(min = 1, max = 255, message =
	// "El Uso debe tener entre 1 y 255 caracteres")
	private String useDescription;
	/** DescripciÃ³n del Uso en el Ã¡rea de utilizaciÃ³n length =255 */

	// private String eiaSerialized; /** Equipo es serializado (Si/NO) length =6
	// */

	// @Size(min = 1, max = 16, message =
	// "El CÃ³digo UMDNS debe tener entre 1 y 255 caracteres")
	private String eiaUmdns;
	/** CÃ³digo UMDNS length =16 */

	// @NotNull(message = "mobility-not-null")
	@NotNull(message = "mobility-not-null")
	@Column(nullable = false)
	private EiaMobilityEnum mobility;
	/** Equipo es movilizable length =60 */

	private EiaTypeEnum type;
	/** Tipo de Equipo length =60 */

	// @NotNull(message = "sub-type-not-null")
	// @Column(nullable = false)
	private EiaSubTypeEnum subtype;

	@ManyToOne
	@NotNull(message = "eiatype-category-not-null")
	@JoinColumn(name = "eiaTypeCategoryFk", nullable = false)
	private EiaTypeCategory eiaTypeCategory;

	/**
	 * 
	 */
	public EiaType() {
		super();
	}

	/**
	 * @param code
	 */
	public EiaType(String code) {
		this.code = code;
	}

	/**
	 * @param code
	 * @param brand
	 * @param name
	 * @param mobility
	 * @param type
	 * @param subtype
	 * @param model
	 */
	public EiaType(String code, Brand brand, String name,
			EiaMobilityEnum mobility, EiaTypeEnum type, EiaSubTypeEnum subtype,
			String model) {
		super();
		this.brand = brand;
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

	/**
	 * @return the brand
	 */
	public Brand getBrand() {
		return brand;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	public EiaTypeCategory getEiaTypeCategory() {
		return eiaTypeCategory;
	}

	public String getEiaUmdns() {
		return eiaUmdns;
	}

	public EiaMobilityEnum getMobility() {
		return mobility;
	}

	/**
	 * @return the model
	 */
	public String getModel() {
		return model;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	public EiaSubTypeEnum getSubtype() {
		return subtype;
	}

	public EiaTypeEnum getType() {
		return type;
	}

	public String getUseDescription() {
		return useDescription;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setEiaTypeCategory(EiaTypeCategory eiaTypeCategory) {
		this.eiaTypeCategory = eiaTypeCategory;
	}

	public void setEiaUmdns(String eiaUmdns) {
		this.eiaUmdns = eiaUmdns;
	}

	public void setMobility(EiaMobilityEnum mobility) {
		this.mobility = mobility;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSubtype(EiaSubTypeEnum subtype) {
		this.subtype = subtype;
	}

	public void setType(EiaTypeEnum type) {
		this.type = type;
	}

	public void setUseDescription(String useDescription) {
		this.useDescription = useDescription;
	}
}