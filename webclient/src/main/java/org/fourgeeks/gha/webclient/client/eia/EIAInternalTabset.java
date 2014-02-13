package org.fourgeeks.gha.webclient.client.eia;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAInternalTabSet;
import org.fourgeeks.gha.webclient.client.eia.component.EIAComponentSubTab;
import org.fourgeeks.gha.webclient.client.eia.information.EIAInformationSubTab;
import org.fourgeeks.gha.webclient.client.eia.reports.EIAReportsSubTab;

import com.smartgwt.client.widgets.tab.Tab;

/**
 * @author alacret, emiliot
 * 
 */
public class EIAInternalTabset extends GHAInternalTabSet implements
		EIASelectionListener {

	private final EIAInformationSubTab infoSubTab;
	private final EIAComponentSubTab partsSubTab;

	private EIAReportsSubTab reportsSubTab;

	// private EIAMaintPlanSubTab eiaPlanSubTab;
	// private EIACostSubTab eiaCostsSubTab;
	// private EIAMovementsSubTab eiaMovementsSubTab;

	/**
	 * @param eiaTab
	 */
	public EIAInternalTabset(EIAPanel eiaTab) {
		super(eiaTab);
		infoSubTab = new EIAInformationSubTab(eiaTab);
		hideables.add(infoSubTab);
		closables.add(infoSubTab);
		partsSubTab = new EIAComponentSubTab(eiaTab);
		hideables.add(partsSubTab);
		closables.add(partsSubTab);
		reportsSubTab = new EIAReportsSubTab(eiaTab);
		hideables.add(reportsSubTab);
		closables.add(reportsSubTab);
		// eiaPlanSubTab = new EIAMaintPlanSubTab(eiaTab);
		// eiaCostsSubTab = new EIACostSubTab(eiaTab);
		// eiaMovementsSubTab = new EIAMovementsSubTab(eiaTab);

		// Agregando las Subtabs
		addTab(infoSubTab);
		addTab(partsSubTab);
		addTab(reportsSubTab);
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
		// selectTab(infoSubTab);
		//
		// // forza al panel del tab a mostrarse sy es el seleccionado
		// if (getSelectedTab() == infoSubTab)
		// infoSubTab.show();
		// El subtab esta configurado para autoguardarse si hay cambios sin
		// guardar y el usuario cambia de tab. una vez que sucede esto el tab es
		// notificado de que el usuario se actualizo, y vuelve a notificar a
		// todos los Listeners que hay una entidad, es decir, este metodo se
		// vuelve a ejecutar. Con tus cambios, cuando yo cambio algo y no lo
		// guardo, y luego paso a otro tab, pasa esto, y el tab de informatcion
		// se vuelve a seleccionar
		show();
	}

	@Override
	public void show() {
		Tab selectedTab = getSelectedTab();
		if (selectedTab == infoSubTab)
			infoSubTab.show();
		if (selectedTab == reportsSubTab)
			reportsSubTab.getPane().show();

		super.show();
	}

}
