package org.fourgeeks.gha.webclient.client.eia;

import org.fourgeeks.gha.domain.gmh.Brand;
import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.Manufacturer;
import org.fourgeeks.gha.webclient.client.UI.GHAGridRecord;

/**
 * @author alacret
 * 
 */
public class EIARecord extends GHAGridRecord<Eia> {

	private Eia eia;

	/**
	 * @param eia
	 */
	public EIARecord(Eia eia) {
		this.eia = eia;

		setAttribute("code", eia.getCode());
		setAttribute("serialNumber", eia.getSerialNumber());

		EiaType eiaType = eia.getEiaType();
		if (eiaType != null) {
			setAttribute("eiaTypeCode", eiaType.getCode());
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

	}

	@Override
	public Eia toEntity() {
		return this.eia;
	}

}