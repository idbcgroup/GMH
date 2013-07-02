package org.fourgeeks.gha.webclient.client.eia;

import org.fourgeeks.gha.domain.gmh.Brand;
import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.Manufacturer;
import org.fourgeeks.gha.webclient.client.UI.GHAGridRecord;

public class EIARecord extends GHAGridRecord<Eia> {

	public EIARecord() {
	}

	public EIARecord(Eia eia) {
		setId(eia.getId());
		setAttribute("code", eia.getCode());
		setAttribute("serialNumber", eia.getSerialNumber());

		EiaType eiaType = eia.getEiaType();
		if (eiaType != null) {
			setAttribute("eiaTypeId", eiaType.getId());
			setAttribute("eiaTypeName", eiaType.getName());
			setAttribute("eiaTypeModel", eiaType.getModel());
			Brand brand = eiaType.getBrand();
			if (brand != null) {
				setAttribute("brandId", brand.getId());
				setAttribute("brandName", brand.getName());
			}
			Manufacturer man = eiaType.getManufacturer();
			if (man != null) {
				setAttribute("manufacturerId", man.getId());
				setAttribute("manufacturerName", man.getName());
			}
		}

		// TODO : Faltan campos. Hacer solo los que hagan falta
		/*
		 * private Date acceptationDate; private BigDecimal actualCost; private
		 * CurrencyTypeEnum actualCostCurrency; private BigDecimal
		 * adquisitionCost; private CurrencyTypeEnum adquisitionCostCurrency;
		 * private CurrencyTypeEnum adquisitionCostCurrencyLocal; private
		 * BigDecimal adquisitionCostLocal; private String bpu; private String
		 * code; private Date contabilizationDate; private Date
		 * dateLastDepreciation; private DepreciationMethodEnum
		 * depreciationMethod; private int depreciationTime; private
		 * TimePeriodEnum depreciationTimePot; private Date desincorporatedDate;
		 * private String desincorporateReason; private EiaType eiaType; private
		 * Facility facility; private String fixeAssetIdentifier; private Date
		 * installationDate; private String installationProvider; private int
		 * lifeTime; private TimePeriodEnum lifeTimePot; private String
		 * maintenanceLocation; private String maintenanceProvider; private
		 * String obu; private Date purchaseDate; private Date
		 * purchaseInvoiceDate; private String purchaseInvoiceNumber; private
		 * Date purchaseOrderDate; private String purchaseOrderNumber; private
		 * Date receptionDate; private String serialNumber; private EiaStateEnum
		 * state; private String vendor; private WarrantySinceEnum
		 * warrantySince; private WarrantyStateEnum warrantyState; private int
		 * warrantyTime; private TimePeriodEnum warrantyTimePot;
		 */

	}

	public String getCode() {
		return getAttributeAsString("code");
	}

	public Brand getBrand() {
		Long brandId = getBrandId();
		if (brandId != null) {
			Brand brand = new Brand();
			brand.setId(brandId);
			brand.setName(getBrandName());
			return brand;
		}
		return null;
	}

	public Manufacturer getManufacturer() {
		Long manId = getManufacturerId();
		if (manId != null) {
			Manufacturer man = new Manufacturer();
			man.setId(manId);
			man.setName(getManufacturerName());
			return man;
		}
		return null;
	}

	private String getBrandName() {
		return getAttributeAsString("brandName");
	}

	private Long getBrandId() {
		String id = getAttributeAsString("brandId");
		if (id != null)
			return Long.valueOf(id);
		return null;
	}

	public EiaType getEiaType() {
		Long eiaTypeId = getEiaTypeId();
		if (eiaTypeId != null) {
			EiaType eiaType = new EiaType();
			eiaType.setId(getEiaTypeId());
			eiaType.setName(getEiaTypeName());
			eiaType.setBrand(getBrand());
			eiaType.setCode(getCode());
			eiaType.setManufacturer(getManufacturer());
			// eiaType.setDescription()
			// eiaType.setEiaUmdns()
			return eiaType;
		}
		return null;
	}

	public Long getEiaTypeId() {
		String id = getAttributeAsString("eiaTypeId");
		if (id != null)
			return Long.valueOf(id);
		return null;
	}

	public String getEiaTypeName() {
		return getAttributeAsString("eiaTypeName");
	}

	public String getEiaTypeModel() {
		return getAttributeAsString("eiaTypeModel");
	}

	public String getSerialNumber() {
		return getAttributeAsString("serialNumber");
	}

	public Long getManufacturerId() {
		String id = getAttributeAsString("manufacturerId");
		if (id != null)
			return Long.valueOf(id);
		return null;
	}

	public String getManufacturerName() {
		return getAttributeAsString("manufacturerName");
	}

	@Override
	public Eia toEntity() {// TODO : Faltan campos
		Eia eia = new Eia();
		eia.setId(getId());
		eia.setCode(getCode());
		eia.setSerialNumber(getSerialNumber());

		EiaType eiaType = getEiaType();
		if (eiaType != null)
			eia.setEiaType(eiaType);

		return eia;
	}
}