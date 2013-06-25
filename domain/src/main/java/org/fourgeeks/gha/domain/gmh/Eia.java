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

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** Fecha de Instalación del Equipo length =22 */
	private Date acceptationDate;
	/** Periodo de Tiempo (PDT) para el Tiempo de Vida Equipo length =60 */
	private BigDecimal actualCost;
	/** Costo Actual en Libros length =16dec =5 */
	private CurrencyTypeEnum actualCostCurrency;
	/** Nombre Proveedor de Mantenimiento del Equipo length =255 */
	private BigDecimal adquisitionCost;
	/** Costo Adquisición moneda Indicada length =16dec =5 */
	private CurrencyTypeEnum adquisitionCostCurrency;
	/** Costo Adquisición moneda Local length =16dec =5 */
	private CurrencyTypeEnum adquisitionCostCurrencyLocal;
	/** Fecha de Contabilización length =22 */
	private BigDecimal adquisitionCostLocal;
	/* Credencial de la persona responsable */
	// TODO Foreign key con BPU
	private String bpu;

	private String code;
	/** Denominación Moneda del Costo de Adquisición del equipo length =60 */
	private Date contabilizationDate;
	/** Motivo de Desincorporación length =255 */
	private Date dateLastDepreciation;
	/** Denominación Moneda Local para Costo Contabilizado del equipo length =60 */
	private DepreciationMethodEnum depreciationMethod;
	/** Método de Depreciación length =60 */
	@Column(nullable = true)
	private int depreciationTime;
	/** Tiempo de Depreciación length =4 */
	private TimePeriodEnum depreciationTimePot;
	/** Denominación Moneda Local para Costo Contabilizado del equipo length =60 */
	// private EquipmentTypeEnum type; /** Tipo Equipo IT length =60 */
	private Date desincorporatedDate;
	/** Fecha de Desincorporación length =22 */
	private String desincorporateReason;
	@ManyToOne
	@JoinColumn(name = "eiaTypeFk")
	private EiaType eiaType;
	@ManyToOne
	@JoinColumn(name = "facilityFk")
	private Facility facility;
	/** Número de Serial del Equipo length =60 */
	private String fixeAssetIdentifier;
	/** Fecha de Recepción del Equipo length =22 */
	private Date installationDate;
	/** Fecha de la Factura de Compra length =22 */

	// TODO Foreign key con un proveedor?
	private String installationProvider;

	@Column(nullable = true)
	private int lifeTime;
	/** Tiempo de Vida Equipo length =4 */
	private TimePeriodEnum lifeTimePot;
	/**
	 * Código del Proveedor de Instalación solo si es un proveedor regular
	 * length =255
	 */
	// TODO Foreign Key con Facility?
	private String maintenanceLocation;
	// TODO Foreign key con un proveedor?
	private String maintenanceProvider;

	// TODO Foreign key con un OBU?, esto no viene del BPI?
	private String obu;
	/** Nombre Completo Persona Responsable del equipo length =255 */

	private Date purchaseDate;
	/** Número de la Factura de Compra length =30 */
	private Date purchaseInvoiceDate;
	/** Nombre Proveedor del Equipo (O/Compra) length =255 */
	private String purchaseInvoiceNumber;
	/** Número de la Orden de Compra length =30 */
	private Date purchaseOrderDate;
	/** Nombre de Ubicación del Equipo length =255 */
	private String purchaseOrderNumber;
	/** Fecha de Compra del Equipo length =22 */
	private Date receptionDate;
	/** Código asignado al Equipo o Instalación length =20 */
	private String serialNumber;
	/** Nombre Departamento donde esta adjudicado el equipo length =255 */

	@Column(nullable = false)
	private EiaStateEnum state;

	// TODO Foreign key con un vendor?
	private String vendor;

	@Column(nullable = false)
	private WarrantySinceEnum warrantySince;
	/** Estado del Equipo length =60 */

	@Column(nullable = false)
	private WarrantyStateEnum warrantyState;
	/** Desde que fecha se considera la garantía del fabricante length =60 */

	@Column(nullable = true)
	private int warrantyTime;

	/** Fecha de última Depreciación length =22 */

	/** Tiempo de duración de la garantía length =4 */

	private TimePeriodEnum warrantyTimePot;

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
			WarrantySinceEnum warrantySince, TimePeriodEnum warrantyTimePot,
			EiaStateEnum state, WarrantyStateEnum warrantyState) {
		super();
		this.facility = facility;
		this.eiaType = eiaType;
		this.warrantySince = warrantySince;
		this.warrantyTimePot = warrantyTimePot;
		this.state = state;
		this.warrantyState = warrantyState;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	public EiaType getEiaType() {
		return eiaType;
	}

	public Facility getFacility() {
		return facility;
	}

	/**
	 * @return the serialNumber
	 */
	public String getSerialNumber() {
		return serialNumber;
	}

	public EiaStateEnum getState() {
		return state;
	}

	/**
	 * @return the warrantySince
	 */
	public WarrantySinceEnum getWarrantySince() {
		return warrantySince;
	}

	public WarrantyStateEnum getWarrantyState() {
		return warrantyState;
	}

	public TimePeriodEnum getWarrantyTimePot() {
		return warrantyTimePot;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	public void setEiatype(EiaType eiaType) {
		this.eiaType = eiaType;
	}

	public void setFacility(Facility facility) {
		this.facility = facility;
	}

	/**
	 * @param serialNumber
	 *            the serialNumber to set
	 */
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public void setState(EiaStateEnum state) {
		this.state = state;
	}

	/**
	 * @param warrantySince
	 *            the warrantySince to set
	 */
	public void setWarrantySince(WarrantySinceEnum warrantySince) {
		this.warrantySince = warrantySince;
	}

	public void setWarrantyState(WarrantyStateEnum warrantyState) {
		this.warrantyState = warrantyState;
	}

	public void setWarrantyTimePot(TimePeriodEnum warrantyTimePot) {
		this.warrantyTimePot = warrantyTimePot;
	}

}
