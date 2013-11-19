package org.fourgeeks.gha.webclient.client.eiatype.eiadamagereport;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaDamageReport;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAAddForm;
import org.fourgeeks.gha.webclient.client.eia.EIASelectionListener;

public class EiaDamageReportAddForm extends GHAAddForm<EiaDamageReport>
		implements EIASelectionListener {

	public EiaDamageReportAddForm() {
		super(GHAStrings.get("new-eiaDamageReport"));
		// TODO Auto-generated constructor stub
	}

	@Override
	public void select(Eia eia) {
		// TODO Auto-generated method stub

	}

}
