package org.fourgeeks.gha.webclient.client.maintenanceactivity;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHATab;
import org.fourgeeks.gha.webclient.client.eia.EIASelectionListener;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;

import com.smartgwt.client.widgets.layout.VLayout;

public class MaintenanceActivityTab extends GHATab implements EIATypeSelectionListener,
		EIASelectionListener {

	public static final String ID = "mact";
	private static final String TITLE = "Actividades De Mant.";
	private MaintenanceActivityTopSection topSection;
	private MaintenanceActivityInternalTabset internalTabset;

	private EiaType eiaType;

	public MaintenanceActivityTab() {
		super();
		getHeader().setTitle(TITLE);

		topSection = new MaintenanceActivityTopSection(this);
		topSection.AddEIATypeSelectionListener(this);
		
		internalTabset = new MaintenanceActivityInternalTabset(this);

		// Creacion de la tab de EIA
		VLayout verticalPanel = new VLayout();
		verticalPanel.setBackgroundColor("#E0E0E0");

		verticalPanel.addMember(topSection);
		verticalPanel.addMember(GHAUiHelper
				.verticalGraySeparator(GHAUiHelper.V_SEPARATOR_HEIGHT + "px"));
		verticalPanel.addMember(internalTabset);
		addMember(verticalPanel);

	}

	@Override
	protected void onDraw() {
		if (eiaType == null)
			topSection.search();
	}

	@Override
	public String getId() {
		return ID;
	}

	@Override
	public void select(Eia eia) {
		topSection.select(eia);
		internalTabset.select(eia);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener#select(org.fourgeeks.gha.domain.gmh.EiaType)
	 */
	@Override
	public void select(EiaType eiaType) {
		this.eiaType = eiaType;
		internalTabset.select(eiaType);
	}

}