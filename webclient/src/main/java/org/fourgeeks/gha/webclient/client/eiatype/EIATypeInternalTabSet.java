package org.fourgeeks.gha.webclient.client.eiatype;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAInternalTabSet;
import org.fourgeeks.gha.webclient.client.eiatype.component.EIATypeComponentSubTab;
import org.fourgeeks.gha.webclient.client.eiatype.damageandplanification.EIADamageAndPlanificationSubTab;
import org.fourgeeks.gha.webclient.client.eiatype.equipment.EIATypeEquipmentSubTab;
import org.fourgeeks.gha.webclient.client.eiatype.information.EIATypeInformationSubTab;
import org.fourgeeks.gha.webclient.client.eiatype.maintenance.EIATypeMaintenanceSubTab;
import org.fourgeeks.gha.webclient.client.eiatype.material.EIATypeMaterialSubTab;
import org.fourgeeks.gha.webclient.client.eiatype.utility.EIATypeUtilitySubTab;

import com.smartgwt.client.types.AnimationEffect;

/**
 * @author alacret, emiliot
 * 
 */
public class EIATypeInternalTabSet extends GHAInternalTabSet implements
		EIATypeSelectionListener {

	private final EIATypeInformationSubTab infoSubTab;
	private final EIATypeEquipmentSubTab equipementsSubTab;
	private final EIATypeComponentSubTab partsSubTab;
	private final EIATypeMaterialSubTab materialSubTab;
	private final EIATypeUtilitySubTab servicesSubTab;
	private final EIADamageAndPlanificationSubTab damageAndPlanificationSubTab;

	private final EIATypeMaintenanceSubTab maintenanceSubTab;

	/**
	 * @param tab
	 */
	public EIATypeInternalTabSet(EIATypeTab tab) {
		super(tab);
		infoSubTab = new EIATypeInformationSubTab(tab);
		hideables.add(infoSubTab);
		closables.add(infoSubTab);
		equipementsSubTab = new EIATypeEquipmentSubTab(tab);
		hideables.add(equipementsSubTab);
		closables.add(equipementsSubTab);
		partsSubTab = new EIATypeComponentSubTab(tab);
		hideables.add(partsSubTab);
		closables.add(partsSubTab);
		materialSubTab = new EIATypeMaterialSubTab(tab);
		hideables.add(materialSubTab);
		closables.add(materialSubTab);
		servicesSubTab = new EIATypeUtilitySubTab(tab);
		hideables.add(servicesSubTab);
		closables.add(servicesSubTab);
		damageAndPlanificationSubTab = new EIADamageAndPlanificationSubTab(tab);
		damageAndPlanificationSubTab
				.addEiaDamageReportSelectionListener(equipementsSubTab);
		hideables.add(damageAndPlanificationSubTab);
		closables.add(damageAndPlanificationSubTab);
		maintenanceSubTab = new EIATypeMaintenanceSubTab(tab);
		hideables.add(maintenanceSubTab);
		closables.add(maintenanceSubTab);
		// Agregando las Subtabs
		addTab(infoSubTab);
		addTab(equipementsSubTab);
		addTab(partsSubTab);
		addTab(materialSubTab);
		addTab(servicesSubTab);
		addTab(damageAndPlanificationSubTab);
		addTab(maintenanceSubTab);
	}

	@Override
	public void select(EiaType eiaType) {
		// selectTab(infoSubTab);
		// if (getSelectedTab() == infoSubTab)
		// infoSubTab.show();
		// if (getSelectedTab() == damageAndPlanificationSubTab) {
		// damageAndPlanificationSubTab.show();
		// damageAndPlanificationSubTab.openFirstSection();
		// }
		// El subtab esta configurado para autoguardarse si hay cambios sin
		// guardar y el usuario cambia de tab. una vez que sucede esto el tab es
		// notificado de que el usuario se actualizo, y vuelve a notificar a
		// todos los Listeners que hay una entidad, es decir, este metodo se
		// vuelve a ejecutar. Con tus cambios, cuando yo cambio algo y no lo
		// guardo, y luego paso a otro tab, pasa esto, y el tab de informatcion
		// se vuelve a seleccionar
		if (!isVisible())
			animateShow(AnimationEffect.FADE);
	}

	@Override
	public void show() {
		// forza al panel del subtab a mostrarse sy es el seleccionado
		if (getSelectedTab() == infoSubTab)
			infoSubTab.show();
		if (getSelectedTab() == damageAndPlanificationSubTab)
			damageAndPlanificationSubTab.show();
		super.show();
	}
}