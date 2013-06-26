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

	/**
	 * @return the acceptationDate
	 */
	public Date getAcceptationDate() {
		return acceptationDate;
	}

	/**
	 * @param acceptationDate
	 *            the acceptationDate to set
	 */
	public void setAcceptationDate(Date acceptationDate) {
		this.acceptationDate = acceptationDate;
	}

	/**
	 * @return the actualCost
	 */
	public BigDecimal getActualCost() {
		return actualCost;
	}

	/**
	 * @param actualCost
	 *            the actualCost to set
	 */
	public void setActualCost(BigDecimal actualCost) {
		this.actualCost = actualCost;
	}

	/**
	 * @return the actualCostCurrency
	 */
	public CurrencyTypeEnum getActualCostCurrency() {
		return actualCostCurrency;
	}

	/**
	 * @param actualCostCurrency
	 *            the actualCostCurrency to set
	 */
	public void setActualCostCurrency(CurrencyTypeEnum actualCostCurrency) {
		this.actualCostCurrency = actualCostCurrency;
	}

	/**
	 * @return the adquisitionCost
	 */
	public BigDecimal getAdquisitionCost() {
		return adquisitionCost;
	}

	/**
	 * @param adquisitionCost
	 *            the adquisitionCost to set
	 */
	public void setAdquisitionCost(BigDecimal adquisitionCost) {
		this.adquisitionCost = adquisitionCost;
	}

	/**
	 * @return the adquisitionCostCurrency
	 */
	public CurrencyTypeEnum getAdquisitionCostCurrency() {
		return adquisitionCostCurrency;
	}

	/**
	 * @param adquisitionCostCurrency
	 *            the adquisitionCostCurrency to set
	 */
	public void setAdquisitionCostCurrency(
			CurrencyTypeEnum adquisitionCostCurrency) {
		this.adquisitionCostCurrency = adquisitionCostCurrency;
	}

	/**
	 * @return the adquisitionCostCurrencyLocal
	 */
	public CurrencyTypeEnum getAdquisitionCostCurrencyLocal() {
		return adquisitionCostCurrencyLocal;
	}

	/**
	 * @param adquisitionCostCurrencyLocal
	 *            the adquisitionCostCurrencyLocal to set
	 */
	public void setAdquisitionCostCurrencyLocal(
			CurrencyTypeEnum adquisitionCostCurrencyLocal) {
		this.adquisitionCostCurrencyLocal = adquisitionCostCurrencyLocal;
	}

	/**
	 * @return the adquisitionCostLocal
	 */
	public BigDecimal getAdquisitionCostLocal() {
		return adquisitionCostLocal;
	}

	/**
	 * @param adquisitionCostLocal
	 *            the adquisitionCostLocal to set
	 */
	public void setAdquisitionCostLocal(BigDecimal adquisitionCostLocal) {
		this.adquisitionCostLocal = adquisitionCostLocal;
	}

	/**
	 * @return the bpu
	 */
	public String getBpu() {
		return bpu;
	}

	/**
	 * @param bpu
	 *            the bpu to set
	 */
	public void setBpu(String bpu) {
		this.bpu = bpu;
	}

	/**
	 * @return the contabilizationDate
	 */
	public Date getContabilizationDate() {
		return contabilizationDate;
	}

	/**
	 * @param contabilizationDate
	 *            the contabilizationDate to set
	 */
	public void setContabilizationDate(Date contabilizationDate) {
		this.contabilizationDate = contabilizationDate;
	}

	/**
	 * @return the dateLastDepreciation
	 */
	public Date getDateLastDepreciation() {
		return dateLastDepreciation;
	}

	/**
	 * @param dateLastDepreciation
	 *            the dateLastDepreciation to set
	 */
	public void setDateLastDepreciation(Date dateLastDepreciation) {
		this.dateLastDepreciation = dateLastDepreciation;
	}

	/**
	 * @return the depreciationMethod
	 */
	public DepreciationMethodEnum getDepreciationMethod() {
		return depreciationMethod;
	}

	/**
	 * @param depreciationMethod
	 *            the depreciationMethod to set
	 */
	public void setDepreciationMethod(DepreciationMethodEnum depreciationMethod) {
		this.depreciationMethod = depreciationMethod;
	}

	/**
	 * @return the depreciationTime
	 */
	public int getDepreciationTime() {
		return depreciationTime;
	}

	/**
	 * @param depreciationTime
	 *            the depreciationTime to set
	 */
	public void setDepreciationTime(int depreciationTime) {
		this.depreciationTime = depreciationTime;
	}

	/**
	 * @return the depreciationTimePot
	 */
	public TimePeriodEnum getDepreciationTimePot() {
		return depreciationTimePot;
	}

	/**
	 * @param depreciationTimePot
	 *            the depreciationTimePot to set
	 */
	public void setDepreciationTimePot(TimePeriodEnum depreciationTimePot) {
		this.depreciationTimePot = depreciationTimePot;
	}

	/**
	 * @return the desincorporatedDate
	 */
	public Date getDesincorporatedDate() {
		return desincorporatedDate;
	}

	/**
	 * @param desincorporatedDate
	 *            the desincorporatedDate to set
	 */
	public void setDesincorporatedDate(Date desincorporatedDate) {
		this.desincorporatedDate = desincorporatedDate;
	}

	/**
	 * @return the desincorporateReason
	 */
	public String getDesincorporateReason() {
		return desincorporateReason;
	}

	/**
	 * @param desincorporateReason
	 *            the desincorporateReason to set
	 */
	public void setDesincorporateReason(String desincorporateReason) {
		this.desincorporateReason = desincorporateReason;
	}

	/**
	 * @return the fixeAssetIdentifier
	 */
	public String getFixeAssetIdentifier() {
		return fixeAssetIdentifier;
	}

	/**
	 * @param fixeAssetIdentifier
	 *            the fixeAssetIdentifier to set
	 */
	public void setFixeAssetIdentifier(String fixeAssetIdentifier) {
		this.fixeAssetIdentifier = fixeAssetIdentifier;
	}

	/**
	 * @return the installationDate
	 */
	public Date getInstallationDate() {
		return installationDate;
	}

	/**
	 * @param installationDate
	 *            the installationDate to set
	 */
	public void setInstallationDate(Date installationDate) {
		this.installationDate = installationDate;
	}

	/**
	 * @return the installationProvider
	 */
	public String getInstallationProvider() {
		return installationProvider;
	}

	/**
	 * @param installationProvider
	 *            the installationProvider to set
	 */
	public void setInstallationProvider(String installationProvider) {
		this.installationProvider = installationProvider;
	}

	/**
	 * @return the lifeTime
	 */
	public int getLifeTime() {
		return lifeTime;
	}

	/**
	 * @param lifeTime
	 *            the lifeTime to set
	 */
	public void setLifeTime(int lifeTime) {
		this.lifeTime = lifeTime;
	}

	/**
	 * @return the lifeTimePot
	 */
	public TimePeriodEnum getLifeTimePot() {
		return lifeTimePot;
	}

	/**
	 * @param lifeTimePot
	 *            the lifeTimePot to set
	 */
	public void setLifeTimePot(TimePeriodEnum lifeTimePot) {
		this.lifeTimePot = lifeTimePot;
	}

	/**
	 * @return the maintenanceLocation
	 */
	public String getMaintenanceLocation() {
		return maintenanceLocation;
	}

	/**
	 * @param maintenanceLocation
	 *            the maintenanceLocation to set
	 */
	public void setMaintenanceLocation(String maintenanceLocation) {
		this.maintenanceLocation = maintenanceLocation;
	}

	/**
	 * @return the maintenanceProvider
	 */
	public String getMaintenanceProvider() {
		return maintenanceProvider;
	}

	/**
	 * @param maintenanceProvider
	 *            the maintenanceProvider to set
	 */
	public void setMaintenanceProvider(String maintenanceProvider) {
		this.maintenanceProvider = maintenanceProvider;
	}

	/**
	 * @return the obu
	 */
	public String getObu() {
		return obu;
	}

	/**
	 * @param obu
	 *            the obu to set
	 */
	public void setObu(String obu) {
		this.obu = obu;
	}

	/**
	 * @return the purchaseDate
	 */
	public Date getPurchaseDate() {
		return purchaseDate;
	}

	/**
	 * @param purchaseDate
	 *            the purchaseDate to set
	 */
	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	/**
	 * @return the purchaseInvoiceDate
	 */
	public Date getPurchaseInvoiceDate() {
		return purchaseInvoiceDate;
	}

	/**
	 * @param purchaseInvoiceDate
	 *            the purchaseInvoiceDate to set
	 */
	public void setPurchaseInvoiceDate(Date purchaseInvoiceDate) {
		this.purchaseInvoiceDate = purchaseInvoiceDate;
	}

	/**
	 * @return the purchaseInvoiceNumber
	 */
	public String getPurchaseInvoiceNumber() {
		return purchaseInvoiceNumber;
	}

	/**
	 * @param purchaseInvoiceNumber
	 *            the purchaseInvoiceNumber to set
	 */
	public void setPurchaseInvoiceNumber(String purchaseInvoiceNumber) {
		this.purchaseInvoiceNumber = purchaseInvoiceNumber;
	}

	/**
	 * @return the purchaseOrderDate
	 */
	public Date getPurchaseOrderDate() {
		return purchaseOrderDate;
	}

	/**
	 * @param purchaseOrderDate
	 *            the purchaseOrderDate to set
	 */
	public void setPurchaseOrderDate(Date purchaseOrderDate) {
		this.purchaseOrderDate = purchaseOrderDate;
	}

	/**
	 * @return the purchaseOrderNumber
	 */
	public String getPurchaseOrderNumber() {
		return purchaseOrderNumber;
	}

	/**
	 * @param purchaseOrderNumber
	 *            the purchaseOrderNumber to set
	 */
	public void setPurchaseOrderNumber(String purchaseOrderNumber) {
		this.purchaseOrderNumber = purchaseOrderNumber;
	}

	/**
	 * @return the receptionDate
	 */
	public Date getReceptionDate() {
		return receptionDate;
	}

	/**
	 * @param receptionDate
	 *            the receptionDate to set
	 */
	public void setReceptionDate(Date receptionDate) {
		this.receptionDate = receptionDate;
	}

	/**
	 * @return the vendor
	 */
	public String getVendor() {
		return vendor;
	}

	/**
	 * @param vendor
	 *            the vendor to set
	 */
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	/**
	 * @return the warrantyTime
	 */
	public int getWarrantyTime() {
		return warrantyTime;
	}

	/**
	 * @param warrantyTime
	 *            the warrantyTime to set
	 */
	public void setWarrantyTime(int warrantyTime) {
		this.warrantyTime = warrantyTime;
	}

	/**
	 * @param eiaType
	 *            the eiaType to set
	 */
	public void setEiaType(EiaType eiaType) {
		this.eiaType = eiaType;
	}

}
