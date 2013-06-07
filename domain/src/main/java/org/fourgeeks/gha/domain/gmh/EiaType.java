/**
 * 
 */
package org.fourgeeks.gha.domain.gmh;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.fourgeeks.gha.domain.AbstractEntity;

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
	
	/**Attributes*/
	
	private Long eiaId; /** <PKEY> LO COLOCA AUTO LA BDATO o JAVA length =19 */
	private Long eiaBpiFk; /** <FKEY> ID Institución donde esta adjudicado el equipo length =19 */
	private String eiaBpiCode; /** Código Institución donde esta adjudicado el equipo length =20 */
	private String eiaCode; /** Código asignado al EIA length =20 */
	private String eiaName; /** Nombre del Equipo o Instalación length =255 */
	private String eiaDescription; /** Descripción detallada Equipo o Instalación length =255 */
	private String eiaBrand; /** Marca del Equipo o Instalación length =255 */
	private String eiaManufacturer; /** Fabricante del Equipo o Instalación length =255 */
	private String eiaModel; /** Modelo del Equipo o Instalación length =255 */
	private String eiaUseInArea; /** Área de utilización del equipo length =60 */
	private String eiaUseDescription; /** Descripción del Uso en el área de utilización length =255 */
	private Integer eiaQtyAdjudge; /** Cantidad originalmente adjudicada a la institución length =6 */
	private Short eiaQtyInMaintenance; /** Cantidad de equipos en mantenimiento length =4 */
	private Short eiaQtyDesincorporated; /** Cantidad de equipos desincorporados length =4 */
	private String eiaSerialized; /** Equipo es serializado (Si/NO) length =6 */
	private String eiaUmdns; /** Código UMDNS length =16 */
	private String eiaMovility; /** Equipo es movilizable length =60 */
	private String eiaType; /** Tipo de Equipo length =60 */
	private String eiaSubtype; /** Subtipo de Equipo length =60 */
	private String eiaComposite; /** Tiene partes se identifican en la tabla EIA_COMPONENT length =6 */
	private String eiaHasSpares; /** El Equipo o Instalación tiene Repuestos length =6 */
	private String eiaUseConsumables; /** El Equipo o Instalación usa Consumibles (Materiales) length =6 */
	private String eiaServicesRequired; /** Requiere Servicios (Agua, gases, vacio, etc.) (Si/NO) length =6 */
	private String eiaSpecialMaterial; /** Requiere material Especial (Si/NO) length =6 */
	private String eiaUsedInService; /** Se usa en Servicios (Si/NO) length =6 */
	private String eiaIsService; /** Es un Servicio que se presta length =6 */



	private String name;
	private String code;
	private String model;

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

	public String getName() {
		return name;
	}

	public String getCode() {
		return code;
	}

	public String getModel() {
		return model;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Brand getBrand() {
		return brand;
	}

	public Manufacturer getManufacturer() {
		return manufacturer;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}
}