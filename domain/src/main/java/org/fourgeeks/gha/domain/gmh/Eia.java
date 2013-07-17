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
import org.fourgeeks.gha.domain.ess.BaseRole;
import org.fourgeeks.gha.domain.gar.BuildingLocation;
import org.fourgeeks.gha.domain.gar.Facility;
import org.fourgeeks.gha.domain.gar.Obu;
import org.fourgeeks.gha.domain.glm.ExternalProvider;

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

	@ManyToOne
	@JoinColumn(name = "baseRoleFk")
	private BaseRole responsibleRole;

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
	private Date desincorporatedDate;
	/** Fecha de Desincorporación length =22 */
	private String desincorporateReason;

	@ManyToOne
	@JoinColumn(name = "eiaTypeFk")
	private EiaType eiaType;

	/** Número de Serial del Equipo length =60 */
	private String fixedAssetIdentifier;
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

	@ManyToOne
	@JoinColumn(name = "maintenanceLocationFk")
	private BuildingLocation maintenanceLocation;

	@ManyToOne
	@JoinColumn(name = "maintenanceProviderFk")
	private ExternalProvider maintenanceProvider;
	
	@ManyToOne
	@JoinColumn(name = "obuFk", nullable = false)
	private Obu obu;

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
	private EiaStateEnum state = EiaStateEnum.CREATED;

	@ManyToOne
	@JoinColumn(name = "externalProviderFk")
	private ExternalProvider provider;

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
		this.eiaType = eiaType;
		this.warrantySince = warrantySince;
		this.warrantyTimePot = warrantyTimePot;
		this.state = state;
		this.warrantyState = warrantyState;
	}

	public Date getAcceptationDate() {
		return acceptationDate;
	}

	public BigDecimal getActualCost() {
		return actualCost;
	}

	public CurrencyTypeEnum getActualCostCurrency() {
		return actualCostCurrency;
	}

	public BigDecimal getAdquisitionCost() {
		return adquisitionCost;
	}

	public CurrencyTypeEnum getAdquisitionCostCurrency() {
		return adquisitionCostCurrency;
	}

	public CurrencyTypeEnum getAdquisitionCostCurrencyLocal() {
		return adquisitionCostCurrencyLocal;
	}

	public BigDecimal getAdquisitionCostLocal() {
		return adquisitionCostLocal;
	}

	public BaseRole getResponsibleRole() {
		return responsibleRole;
	}

	public String getCode() {
		return code;
	}

	public Date getContabilizationDate() {
		return contabilizationDate;
	}

	public Date getDateLastDepreciation() {
		return dateLastDepreciation;
	}

	public DepreciationMethodEnum getDepreciationMethod() {
		return depreciationMethod;
	}

	public int getDepreciationTime() {
		return depreciationTime;
	}

	public TimePeriodEnum getDepreciationTimePot() {
		return depreciationTimePot;
	}

	public Date getDesincorporatedDate() {
		return desincorporatedDate;
	}

	public String getDesincorporateReason() {
		return desincorporateReason;
	}

	public EiaType getEiaType() {
		return eiaType;
	}

	public Date getInstallationDate() {
		return installationDate;
	}

	public String getInstallationProvider() {
		return installationProvider;
	}

	public int getLifeTime() {
		return lifeTime;
	}

	public TimePeriodEnum getLifeTimePot() {
		return lifeTimePot;
	}

	public BuildingLocation getMaintenanceLocation() {
		return maintenanceLocation;
	}

	public ExternalProvider getMaintenanceProvider() {
		return maintenanceProvider;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public Date getPurchaseInvoiceDate() {
		return purchaseInvoiceDate;
	}

	public String getPurchaseInvoiceNumber() {
		return purchaseInvoiceNumber;
	}

	public Date getPurchaseOrderDate() {
		return purchaseOrderDate;
	}

	public String getPurchaseOrderNumber() {
		return purchaseOrderNumber;
	}

	public Date getReceptionDate() {
		return receptionDate;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public EiaStateEnum getState() {
		return state;
	}

	public ExternalProvider getProvider() {
		return provider;
	}

	public WarrantySinceEnum getWarrantySince() {
		return warrantySince;
	}

	public WarrantyStateEnum getWarrantyState() {
		return warrantyState;
	}

	public int getWarrantyTime() {
		return warrantyTime;
	}

	public TimePeriodEnum getWarrantyTimePot() {
		return warrantyTimePot;
	}

	public void setAcceptationDate(Date acceptationDate) {
		this.acceptationDate = acceptationDate;
	}

	public void setActualCost(BigDecimal actualCost) {
		this.actualCost = actualCost;
	}

	public void setActualCostCurrency(CurrencyTypeEnum actualCostCurrency) {
		this.actualCostCurrency = actualCostCurrency;
	}

	public void setAdquisitionCost(BigDecimal adquisitionCost) {
		this.adquisitionCost = adquisitionCost;
	}

	public void setAdquisitionCostCurrency(
			CurrencyTypeEnum adquisitionCostCurrency) {
		this.adquisitionCostCurrency = adquisitionCostCurrency;
	}

	public void setAdquisitionCostCurrencyLocal(
			CurrencyTypeEnum adquisitionCostCurrencyLocal) {
		this.adquisitionCostCurrencyLocal = adquisitionCostCurrencyLocal;
	}

	public void setAdquisitionCostLocal(BigDecimal adquisitionCostLocal) {
		this.adquisitionCostLocal = adquisitionCostLocal;
	}

	public void setResponsibleRole(BaseRole responsibleRole) {
		this.responsibleRole = responsibleRole;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setContabilizationDate(Date contabilizationDate) {
		this.contabilizationDate = contabilizationDate;
	}

	public void setDateLastDepreciation(Date dateLastDepreciation) {
		this.dateLastDepreciation = dateLastDepreciation;
	}

	public void setDepreciationMethod(DepreciationMethodEnum depreciationMethod) {
		this.depreciationMethod = depreciationMethod;
	}

	public void setDepreciationTime(int depreciationTime) {
		this.depreciationTime = depreciationTime;
	}

	public void setDepreciationTimePot(TimePeriodEnum depreciationTimePot) {
		this.depreciationTimePot = depreciationTimePot;
	}

	public void setDesincorporatedDate(Date desincorporatedDate) {
		this.desincorporatedDate = desincorporatedDate;
	}

	public void setDesincorporateReason(String desincorporateReason) {
		this.desincorporateReason = desincorporateReason;
	}

	public void setEiaType(EiaType eiaType) {
		this.eiaType = eiaType;
	}

	public void setInstallationDate(Date installationDate) {
		this.installationDate = installationDate;
	}

	public void setInstallationProvider(String installationProvider) {
		this.installationProvider = installationProvider;
	}

	public void setLifeTime(int lifeTime) {
		this.lifeTime = lifeTime;
	}

	public void setLifeTimePot(TimePeriodEnum lifeTimePot) {
		this.lifeTimePot = lifeTimePot;
	}

	public void setMaintenanceLocation(BuildingLocation maintenanceLocation) {
		this.maintenanceLocation = maintenanceLocation;
	}

	public void setMaintenanceProvider(ExternalProvider maintenanceProvider) {
		this.maintenanceProvider = maintenanceProvider;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public void setPurchaseInvoiceDate(Date purchaseInvoiceDate) {
		this.purchaseInvoiceDate = purchaseInvoiceDate;
	}

	public void setPurchaseInvoiceNumber(String purchaseInvoiceNumber) {
		this.purchaseInvoiceNumber = purchaseInvoiceNumber;
	}

	public void setPurchaseOrderDate(Date purchaseOrderDate) {
		this.purchaseOrderDate = purchaseOrderDate;
	}

	public void setPurchaseOrderNumber(String purchaseOrderNumber) {
		this.purchaseOrderNumber = purchaseOrderNumber;
	}

	public void setReceptionDate(Date receptionDate) {
		this.receptionDate = receptionDate;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public void setState(EiaStateEnum state) {
		this.state = state;
	}

	public void setProvider(ExternalProvider provider) {
		this.provider = provider;
	}

	public void setWarrantySince(WarrantySinceEnum warrantySince) {
		this.warrantySince = warrantySince;
	}

	public void setWarrantyState(WarrantyStateEnum warrantyState) {
		this.warrantyState = warrantyState;
	}

	public void setWarrantyTime(int warrantyTime) {
		this.warrantyTime = warrantyTime;
	}

	public void setWarrantyTimePot(TimePeriodEnum warrantyTimePot) {
		this.warrantyTimePot = warrantyTimePot;
	}

	public String getFixedAssetIdentifier() {
		return fixedAssetIdentifier;
	}

	public Obu getObu() {
		return obu;
	}

	public void setFixedAssetIdentifier(String fixedAssetIdentifier) {
		this.fixedAssetIdentifier = fixedAssetIdentifier;
	}

	public void setObu(Obu obu) {
		this.obu = obu;
	}

}
