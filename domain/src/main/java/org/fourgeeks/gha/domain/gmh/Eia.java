package org.fourgeeks.gha.domain.gmh;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.enu.CurrencyTypeEnum;
import org.fourgeeks.gha.domain.enu.DepreciationMethodEnum;
import org.fourgeeks.gha.domain.enu.EiaStateEnum;
import org.fourgeeks.gha.domain.enu.TimePeriodEnum;
import org.fourgeeks.gha.domain.enu.WarrantySinceEnum;
import org.fourgeeks.gha.domain.enu.WarrantyStateEnum;
import org.fourgeeks.gha.domain.gar.Facility;

@Entity
public class Eia extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	// @OneToOne(mappedBy = "eia")
	// private Terminal terminal;

	@ManyToOne
	@JoinColumn(name = "facilityFk")
	private Facility facility;

	@ManyToOne
	@JoinColumn(name = "eiaTypeFk")
	private EiaType eiaType;

//	@ManyToOne
//	@JoinColumn(name = "buildingFk", nullable = false)
//	private BuildingLocation buildingLocation;

	// @OneToOne(mappedBy = "eia")
	// private SystemPeripheral systemPeripheral;

	/** Attributes */
	private String code;
	/** Código asignado al Equipo o Instalación length =20 */
	private String serialNumber;
	/** Número de Serial del Equipo length =60 */
	private String fixeAssetIdentifier;
	/** Identificación de Activo Fijo length =60 */

	@Column(nullable = false)
	private WarrantySinceEnum warrantySince;
	/** Desde que fecha se considera la garantía del fabricante length =60 */

	@Column(nullable = true)
	private int warrantyTime;
	/** Tiempo de duración de la garantía length =4 */

	private TimePeriodEnum warrantyTimePot;
	/** Periodo de Tiempo (PDT) para la duración de la garantía length =60 */

	private Date purchaseDate;
	/** Fecha de Compra del Equipo length =22 */
	private Date receptionDate;
	/** Fecha de Recepción del Equipo length =22 */
	private Date installationDate;
	/** Fecha de Instalación del Equipo length =22 */
	private Date acceptationDate;
	/** Fecha de Aceptación del Equipo length =22 */
	private Date intermediateWarrantyStartDate;
	/** Fecha inicio de garantía Intermedia length =22 */
	private String locationCode;
	/** Código de Ubicación del Equipo length =20 */
	private String locationName;
	/** Nombre de Ubicación del Equipo length =255 */
	private String obuResponsibleCode;
	/** Código Departamento donde esta adjudicado el equipo length =20 */
	private String obuResponsibleName;
	/** Nombre Departamento donde esta adjudicado el equipo length =255 */

	@Column(nullable = false)
	private EiaStateEnum state;
	/** Estado del Equipo length =60 */

	@Column(nullable = false)
	private WarrantyStateEnum warrantyState;
	/**
	 * Estado de la garantía - Tiempo remanente de garantía sobre el Equipo o
	 * Instalacion length =6
	 */
	private String bpuResponsibleCredential;
	/** Credencial Persona Responsable del equipo length =255 */
	private String bpuResponsibleName;
	/** Nombre Completo Persona Responsable del equipo length =255 */

	private String primaryAreaAttendedCode;
	/** Código de Ubicación del Equipo length =20 */
	private String primaryAreaAttendedName;
	/** Nombre de Ubicación del Equipo length =255 */
	private String purchaseOrderNumber;
	/** Número de la Orden de Compra length =30 */
	private Date purchaseOrderDate;
	/** Fecha de la Orden de Compra length =22 */
	private String vendorCode;
	/** Código Proveedor del Equipo (O/Compra) length =255 */
	private String vendorName;
	/** Nombre Proveedor del Equipo (O/Compra) length =255 */
	private String purchaseInvoiceNumber;
	/** Número de la Factura de Compra length =30 */
	private Date purchaseInvoiceDate;
	/** Fecha de la Factura de Compra length =22 */
	private String installationProvider;
	/** Nombre del proveedor de la instalación length =255 */
	private String installationProviderCode;
	/**
	 * Código del Proveedor de Instalación solo si es un proveedor regular
	 * length =255
	 */
	private String maintenanceLocationCode;
	/** Código de Ubicación del Equipo mientras está en Mantenimiento length =20 */
	private String maintenanceLocationName;
	/**
	 * Nombre de Ubicación del Equipo mientras está en Mantenimiento length =255
	 */
	private String maintenanceProviderCode;
	/** Código Proveedor de Mantenimiento del Equipo length =255 */
	private String maintenanceProviderName;
	/** Nombre Proveedor de Mantenimiento del Equipo length =255 */
	private BigDecimal adquisitionCost;
	/** Costo Adquisición moneda Indicada length =16dec =5 */

	private CurrencyTypeEnum adquisitionCostCurrency;
	/** Denominación Moneda del Costo de Adquisición del equipo length =60 */

	private Date contabilizationDate;
	/** Fecha de Contabilización length =22 */
	private BigDecimal adquisitionCostLocal;
	/** Costo Adquisición moneda Local length =16dec =5 */

	private CurrencyTypeEnum adquisitionCostCurrencyLocal;
	/** Denominación Moneda Local para Costo Contabilizado del equipo length =60 */

	private DepreciationMethodEnum depreciationMethod;
	/** Método de Depreciación length =60 */

	@Column(nullable = true)
	private int depreciationTime;
	/** Tiempo de Depreciación length =4 */
	private TimePeriodEnum depreciationTimePot;
	/** Periodo de Tiempo (PDT) para el Tiempo de Depreciación length =60 */

	@Column(nullable = true)
	private int lifeTime;
	/** Tiempo de Vida Equipo length =4 */
	private TimePeriodEnum lifeTimePot;
	/** Periodo de Tiempo (PDT) para el Tiempo de Vida Equipo length =60 */
	private BigDecimal actualCost;
	/** Costo Actual en Libros length =16dec =5 */
	private CurrencyTypeEnum actualCostCurrency;
	/** Denominación Moneda Local para Costo Contabilizado del equipo length =60 */

	// private EquipmentTypeEnum type; /** Tipo Equipo IT length =60 */

	private Date desincorporatedDate;
	/** Fecha de Desincorporación length =22 */
	private String desincorporateReason;
	/** Motivo de Desincorporación length =255 */
	private Date dateLastDepreciation;

	/** Fecha de última Depreciación length =22 */

	/**
	 * 
	 */
	public Eia() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param facility
	 * @param eiaType
	 * @param warrantySince
	 * @param warrantyTimePot
	 * @param state
	 * @param warrantyState
	 */
	public Eia(Facility facility, EiaType eiaType,
			WarrantySinceEnum warrantySince,
			TimePeriodEnum warrantyTimePot, EiaStateEnum state,
			WarrantyStateEnum warrantyState) {
		super();
		this.facility = facility;
		this.eiaType = eiaType;
		this.warrantySince = warrantySince;
		this.warrantyTimePot = warrantyTimePot;
		this.state = state;
		this.warrantyState = warrantyState;
	}

	public Facility getFacility() {
		return facility;
	}

	public void setFacility(Facility facility) {
		this.facility = facility;
	}

	public EiaType getEiaType() {
		return eiaType;
	}

	public void setEiatype(EiaType eiaType) {
		this.eiaType = eiaType;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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

	/**
	 * @return the serialNumber
	 */
	public String getSerialNumber() {
		return serialNumber;
	}

	/**
	 * @param serialNumber
	 *            the serialNumber to set
	 */
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public TimePeriodEnum getWarrantyTimePot() {
		return warrantyTimePot;
	}

	public EiaStateEnum getState() {
		return state;
	}

	public WarrantyStateEnum getWarrantyState() {
		return warrantyState;
	}

	public void setWarrantyTimePot(TimePeriodEnum warrantyTimePot) {
		this.warrantyTimePot = warrantyTimePot;
	}

	public void setState(EiaStateEnum state) {
		this.state = state;
	}

	public void setWarrantyState(WarrantyStateEnum warrantyState) {
		this.warrantyState = warrantyState;
	}

}
