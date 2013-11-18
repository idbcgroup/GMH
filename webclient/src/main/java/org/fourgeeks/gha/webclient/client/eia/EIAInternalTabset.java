package org.fourgeeks.gha.webclient.client.eia;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAInternalTabSet;
import org.fourgeeks.gha.webclient.client.eia.component.EIAComponentSubTab;
import org.fourgeeks.gha.webclient.client.eia.information.EIAInformationSubTab;

import com.smartgwt.client.types.AnimationEffect;

/**
 * @author alacret, emiliot
 * 
 */
public class EIAInternalTabset extends GHAInternalTabSet implements
		EIASelectionListener {

	private EIAInformationSubTab infoSubTab;
	private EIAComponentSubTab partsSubTab;

	// private EIAReportsSubTab reportsSubTab;

	// private EIAMaintPlanSubTab eiaPlanSubTab;
	// private EIACostSubTab eiaCostsSubTab;
	// private EIAMovementsSubTab eiaMovementsSubTab;

	/**
	 * @param eiaTab
	 */
	public EIAInternalTabset(EIATab eiaTab) {
		super(eiaTab);
		infoSubTab = new EIAInformationSubTab(eiaTab);
		hideables.add(infoSubTab);
		closables.add(infoSubTab);
		partsSubTab = new EIAComponentSubTab(eiaTab);
		hideables.add(partsSubTab);
		closables.add(partsSubTab);
		// reportsSubTab = new EIAReportsSubTab(eiaTab);
		// hideables.add(reportsSubTab);
		// closables.add(reportsSubTab);
		// eiaPlanSubTab = new EIAMaintPlanSubTab(eiaTab);
		// eiaCostsSubTab = new EIACostSubTab(eiaTab);
		// eiaMovementsSubTab = new EIAMovementsSubTab(eiaTab);

		// Agregando las Subtabs
		addTab(infoSubTab);
		addTab(partsSubTab);
		// addTab(reportsSubTab);
		// addTab(eiaPlanSubTab);
		// addTab(eiaCostsSubTab);
		// addTab(eiaMovementsSubTab);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.eia.EIASelectionListener#select(org
	 * .fourgeeks.gha.domain.gmh.Eia)
	 */
	@Override
	public void select(Eia eia) {
		selectTab(infoSubTab);

		// forza al panel del tab a mostrarse sy es el seleccionado
		if (getSelectedTab() == infoSubTab)
			infoSubTab.show();

		animateShow(AnimationEffect.FADE);
	}

	@Override
	public void show() {
		// forza al panel del tab a mostrarse sy es el seleccionado
		if (getSelectedTab() == infoSubTab)
			infoSubTab.show();

		super.show();
	}

}
