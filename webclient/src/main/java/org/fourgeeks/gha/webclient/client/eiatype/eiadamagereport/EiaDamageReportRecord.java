package org.fourgeeks.gha.webclient.client.eiatype.eiadamagereport;

import org.fourgeeks.gha.domain.ess.Role;
import org.fourgeeks.gha.domain.gar.Obu;
import org.fourgeeks.gha.domain.gmh.Brand;
import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaDamageReport;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.mix.Bpi;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridRecord;

public class EiaDamageReportRecord extends GHAGridRecord<EiaDamageReport> {

	private EiaDamageReport eiaDamageReport;

	public EiaDamageReportRecord(EiaDamageReport eiaDamageReport) {
		this.eiaDamageReport = eiaDamageReport;
		Eia eia = eiaDamageReport.getEia();

		setAttribute("reportId", eiaDamageReport.getId());
		setAttribute("id", eia.getId());
		setAttribute("serialNumber", eia.getSerialNumber());
		setAttribute("fai", eia.getFixedAssetIdentifier());
		setAttribute("state", eia.getState());
		if (eia.getWorkingArea() != null) {
			setAttribute("location", eia.getWorkingArea().getName());
		} else if (eia.getFacility() != null) {
			setAttribute("location", eia.getFacility().getName());
		}

		Role role = eia.getResponsibleRole();
		if (role != null)
			setAttribute("role", role.getName());

		Obu obu = eia.getObu();
		if (obu != null) {
			setAttribute("obu", obu.getName());
			Bpi bpi = obu.getBpi();
			if (bpi != null)
				setAttribute("bpi", bpi.getInstitution().getName());
		}

		EiaType eiaType = eia.getEiaType();
		if (eiaType != null) {
			setAttribute("type", eiaType.getName());
			setAttribute("model", eiaType.getModel());
			Brand brand = eiaType.getBrand();
			if (brand != null)
				setAttribute("brand", brand.getName());
		}
	}

	@Override
	public EiaDamageReport toEntity() {
		return this.eiaDamageReport;
	}

}
