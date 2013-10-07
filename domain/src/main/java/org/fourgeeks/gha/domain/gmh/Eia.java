package org.fourgeeks.gha.domain.gmh;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.enu.CurrencyTypeEnum;
import org.fourgeeks.gha.domain.enu.DepreciationMethodEnum;
import org.fourgeeks.gha.domain.enu.EiaStateEnum;
import org.fourgeeks.gha.domain.enu.TimePeriodEnum;
import org.fourgeeks.gha.domain.enu.WarrantySinceEnum;
import org.fourgeeks.gha.domain.ess.Role;
import org.fourgeeks.gha.domain.ess.WorkingArea;
import org.fourgeeks.gha.domain.gar.Facility;
import org.fourgeeks.gha.domain.gar.Job;
import org.fourgeeks.gha.domain.gar.Obu;
import org.fourgeeks.gha.domain.glm.ExternalProvider;

/**
 * @author alacret
 * 
 */
@Entity
@NamedQueries(value = {
		@NamedQuery(name = "Eia.getAll", query = "SELECT e from Eia e order by e.id"),
		@NamedQuery(name = "Eia.findByEiaType", query = "SELECT e from Eia e WHERE e.eiaType = :eiaType order by e.id") })
public class Eia extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne
	private EiaMaintenancePlanification eMaintenancePlan;

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

	@NotNull(message = "base-role-not-null")
	@ManyToOne
	@JoinColumn(name = "baseRoleFk", nullable = false)
	private Role responsibleRole;

	@Size(max = 20)
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
	private TimePeriodEnum depreciationTimePoT;
	// private Date desincorporatedDate;

	// @Size(max = 30, message = "El campo debe ser menor a 30 caracteres")
	// private String desincorporateReason;
	@NotNull(message = "type-not-null")
	@ManyToOne
	@JoinColumn(name = "eiaTypeFk", nullable = false)
	private EiaType eiaType;

	@NotNull(message = "asset-id-not-null")
	private String fixedAssetIdentifier;

	/** Fecha de Recepción del Equipo length =22 */
	private Date installationDate;
	/** Fecha de la Factura de Compra length =22 */

	@ManyToOne
	@JoinColumn(name = "installationProviderFk")
	private ExternalProvider installationProvider;

	@Column(nullable = true)
	private int lifeTime;
	/** Tiempo de Vida Equipo length =4 */
	private TimePeriodEnum lifeTimePoT;

	/**
	 * Working Area and Facility where the EIA is located
	 */
	@ManyToOne
	@JoinColumn(name = "workingAreaFk")
	private WorkingArea workingArea;

	@ManyToOne
	@JoinColumn(name = "facilityFk")
	private Facility facility;

	@ManyToOne
	@JoinColumn(name = "jobFk")
	private Job job;
	//
	@NotNull(message = "maintenance-provider-not-null")
	@ManyToOne
	@JoinColumn(name = "maintenanceProviderFk")
	private ExternalProvider maintenanceProvider;

	/**
	 * Responsible Obu for the EIA
	 */
	@NotNull(message = "obu-not-null")
	@ManyToOne
	@JoinColumn(name = "obuFk", nullable = false)
	private Obu obu;

	private Date purchaseDate;
	/** Número de la Factura de Compra length =30 */
	private Date purchaseInvoiceDate;

	private String purchaseInvoiceNumber;
	/** Número de la Orden de Compra length =30 */
	private Date purchaseOrderDate;
	/** Nombre de Ubicación del Equipo length =255 */
	private String purchaseOrderNumber;
	/** Fecha de Compra del Equipo length =22 */
	private Date receptionDate;
	/** Código asignado al Equipo o Instalación length =20 */
	@NotNull(message = "serial-not-null")
	private String serialNumber;
	/** Nombre Departamento donde esta adjudicado el equipo length =255 */

	@NotNull(message = "state-not-null")
	@Column(nullable = false)
	private EiaStateEnum state = EiaStateEnum.CREATED;

	@NotNull(message = "external-provider-not-null")
	@ManyToOne
	@JoinColumn(name = "externalProviderFk", nullable = false)
	private ExternalProvider provider;

	// WARRANTIES Section
	// TODO: CHECK FOR NULL VALUES FROM HERE

	@Column(nullable = true)
	// false)
	private WarrantySinceEnum realWarrantySince;
	/** Estado del Equipo length =60 */

	@Column(nullable = true)
	// false)
	private int realWarrantyTime;

	@Column(nullable = true)
	// false)
	private TimePeriodEnum realWarrantyPoT;

	@Column(nullable = true)
	// false)
	private Date realWarrantyBegin;

	@Column(nullable = true)
	// false)
	private WarrantySinceEnum intWarrantySince;
	/** Estado del Equipo length =60 */

	@Column(nullable = true)
	// false)
	private int intWarrantyTime;

	@Column(nullable = true)
	// false)
	private TimePeriodEnum intWarrantyPoT;

	@Column(nullable = true)
	// false)
	private Date intWarrantyBegin;

	/**
	 * @param responsibleRole
	 * @param eiaType
	 * @param obu
	 * @param state
	 * @param fixedAssetIdentifier
	 * @param maintenanceProvider
	 * @param serialNumber
	 */
	public Eia(Role responsibleRole, EiaType eiaType, Obu obu,
			EiaStateEnum state, String fixedAssetIdentifier,
			ExternalProvider maintenanceProvider, String serialNumber) {
		this.responsibleRole = responsibleRole;
		this.eiaType = eiaType;
		this.obu = obu;
		this.state = state;
		this.fixedAssetIdentifier = fixedAssetIdentifier;
		this.maintenanceProvider = maintenanceProvider;
		this.serialNumber = serialNumber;
	}

	/**
	 * 
	 */
	public Eia() {
		super();
	}

	/**
	 * @return the acceptation Date
	 */
	public Date getAcceptationDate() {
		return acceptationDate;
	}

	/**
	 * @return the ACtual cost
	 */
	public BigDecimal getActualCost() {
		return actualCost;
	}

	/**
	 * @return
	 */
	public CurrencyTypeEnum getActualCostCurrency() {
		return actualCostCurrency;
	}

	/**
	 * @return
	 */
	public BigDecimal getAdquisitionCost() {
		return adquisitionCost;
	}

	/**
	 * @return
	 */
	public CurrencyTypeEnum getAdquisitionCostCurrency() {
		return adquisitionCostCurrency;
	}

	public CurrencyTypeEnum getAdquisitionCostCurrencyLocal() {
		return adquisitionCostCurrencyLocal;
	}

	public BigDecimal getAdquisitionCostLocal() {
		return adquisitionCostLocal;
	}

	public Role getResponsibleRole() {
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

	public TimePeriodEnum getDepreciationTimePoT() {
		return depreciationTimePoT;
	}

	// public Date getDesincorporatedDate() {
	// return desincorporatedDate;
	// }
	//
	// public String getDesincorporateReason() {
	// return desincorporateReason;
	// }

	public EiaType getEiaType() {
		return eiaType;
	}

	public String getFixedAssetIdentifier() {
		return fixedAssetIdentifier;
	}

	public Date getInstallationDate() {
		return installationDate;
	}

	public ExternalProvider getInstallationProvider() {
		return installationProvider;
	}

	public int getLifeTime() {
		return lifeTime;
	}

	public TimePeriodEnum getLifeTimePoT() {
		return lifeTimePoT;
	}

	// public BuildingLocation getMaintenanceLocation() {
	// return maintenanceLocation;
	// }
	//
	public ExternalProvider getMaintenanceProvider() {
		return maintenanceProvider;
	}

	public Obu getObu() {
		return obu;
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

	public WarrantySinceEnum getRealWarrantySince() {
		return realWarrantySince;
	}

	public int getRealWarrantyTime() {
		return realWarrantyTime;
	}

	public TimePeriodEnum getRealWarrantyPoT() {
		return realWarrantyPoT;
	}

	public Date getRealWarrantyBegin() {
		return realWarrantyBegin;
	}

	public WarrantySinceEnum getIntWarrantySince() {
		return intWarrantySince;
	}

	public int getIntWarrantyTime() {
		return intWarrantyTime;
	}

	public TimePeriodEnum getIntWarrantyPoT() {
		return intWarrantyPoT;
	}

	public Date getIntWarrantyBegin() {
		return intWarrantyBegin;
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

	public void setResponsibleRole(Role responsibleRole) {
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

	public void setDepreciationTimePoT(TimePeriodEnum depreciationTimePoT) {
		this.depreciationTimePoT = depreciationTimePoT;
	}

	// public void setDesincorporatedDate(Date desincorporatedDate) {
	// this.desincorporatedDate = desincorporatedDate;
	// }
	//
	// public void setDesincorporateReason(String desincorporateReason) {
	// this.desincorporateReason = desincorporateReason;
	// }

	public void setEiaType(EiaType eiaType) {
		this.eiaType = eiaType;
	}

	public void setFixedAssetIdentifier(String fixedAssetIdentifier) {
		this.fixedAssetIdentifier = fixedAssetIdentifier;
	}

	public void setInstallationDate(Date installationDate) {
		this.installationDate = installationDate;
	}

	public void setInstallationProvider(ExternalProvider installationProvider) {
		this.installationProvider = installationProvider;
	}

	public void setLifeTime(int lifeTime) {
		this.lifeTime = lifeTime;
	}

	public void setLifeTimePoT(TimePeriodEnum lifeTimePoT) {
		this.lifeTimePoT = lifeTimePoT;
	}

	// public void setMaintenanceLocation(BuildingLocation maintenanceLocation)
	// {
	// this.maintenanceLocation = maintenanceLocation;
	// }

	public void setMaintenanceProvider(ExternalProvider maintenanceProvider) {
		this.maintenanceProvider = maintenanceProvider;
	}

	public void setObu(Obu obu) {
		this.obu = obu;
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

	public void setRealWarrantySince(WarrantySinceEnum realWarrantySince) {
		this.realWarrantySince = realWarrantySince;
	}

	public void setRealWarrantyTime(int realWarrantyTime) {
		this.realWarrantyTime = realWarrantyTime;
	}

	public void setRealWarrantyPoT(TimePeriodEnum realWarrantyPoT) {
		this.realWarrantyPoT = realWarrantyPoT;
	}

	public void setRealWarrantyBegin(Date realWarrantyBegin) {
		this.realWarrantyBegin = realWarrantyBegin;
	}

	public void setIntWarrantySince(WarrantySinceEnum intWarrantySince) {
		this.intWarrantySince = intWarrantySince;
	}

	public void setIntWarrantyTime(int intWarrantyTime) {
		this.intWarrantyTime = intWarrantyTime;
	}

	public void setIntWarrantyPoT(TimePeriodEnum intWarrantyPoT) {
		this.intWarrantyPoT = intWarrantyPoT;
	}

	public void setIntWarrantyBegin(Date intWarrantyBegin) {
		this.intWarrantyBegin = intWarrantyBegin;
	}

	// public EiaMaintenancePlan geteMaintenancePlan() {
	// return eMaintenancePlan;
	// }

	public WorkingArea getWorkingArea() {
		return workingArea;
	}

	public Facility getFacility() {
		return facility;
	}

	// public void seteMaintenancePlan(EiaMaintenancePlan eMaintenancePlan) {
	// this.eMaintenancePlan = eMaintenancePlan;
	// }

	public void setWorkingArea(WorkingArea workingArea) {
		this.workingArea = workingArea;
	}

	public void setFacility(Facility facility) {
		this.facility = facility;
	}

}
