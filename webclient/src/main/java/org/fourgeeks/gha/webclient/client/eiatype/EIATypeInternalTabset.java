package org.fourgeeks.gha.webclient.client.eiatype;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.exceptions.UnavailableToCloseException;
import org.fourgeeks.gha.webclient.client.UI.interfaces.GHAClosable;
import org.fourgeeks.gha.webclient.client.UI.interfaces.GHAHideable;
import org.fourgeeks.gha.webclient.client.eiatype.component.EIATypeComponentSubTab;
import org.fourgeeks.gha.webclient.client.eiatype.equipment.EIATypeEquipmentSubTab;
import org.fourgeeks.gha.webclient.client.eiatype.information.EIATypeInformationSubTab;
import org.fourgeeks.gha.webclient.client.eiatype.materialcategory.EIATypeMaterialCategorySubTab;
import org.fourgeeks.gha.webclient.client.eiatype.utility.EIATypeUtilitySubTab;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.smartgwt.client.types.AnimationEffect;
import com.smartgwt.client.widgets.tab.TabSet;

/**
 * @author alacret
 * 
 */
public class EIATypeInternalTabset extends TabSet implements ResizeHandler,
		GHAHideable, GHAClosable, EIATypeSelectionListener {

	private EIATypeInformationSubTab infoSubTab;
	private EIATypeEquipmentSubTab equipementsSubTab;
	private EIATypeComponentSubTab partsSubTab;
	private EIATypeMaterialCategorySubTab materialSubTab;
	private EIATypeUtilitySubTab servicesSubTab;
	// private EIATypeMaintenanceSubTab maintenanceSubTab;
	private List<GHAHideable> hideables = new ArrayList<GHAHideable>();
	private List<GHAClosable> closables = new ArrayList<GHAClosable>();

	/**
	 * @param tab
	 */
	public EIATypeInternalTabset(EIATypeTab tab) {
		super();
		GHAUiHelper.addGHAResizeHandler(this);
		setWidth100();
		setHeight(GHAUiHelper.getBottomSectionHeight());
		setVisible(false);

		infoSubTab = new EIATypeInformationSubTab(tab);
		hideables.add(infoSubTab);
		closables.add(infoSubTab);
		equipementsSubTab = new EIATypeEquipmentSubTab(tab);
		hideables.add(equipementsSubTab);
		closables.add(equipementsSubTab);
		partsSubTab = new EIATypeComponentSubTab(tab);
		hideables.add(partsSubTab);
		closables.add(partsSubTab);
		materialSubTab = new EIATypeMaterialCategorySubTab(tab);
		hideables.add(materialSubTab);
		closables.add(materialSubTab);
		servicesSubTab = new EIATypeUtilitySubTab(tab);
		hideables.add(servicesSubTab);
		closables.add(servicesSubTab);
		// maintenanceSubTab = new EIATypeMaintenanceSubTab(tab);
		// hideables.add(maintenanceSubTab);
		// closables.add(maintenanceSubTab);

		// Agregando las Subtabs
		addTab(infoSubTab);
		addTab(equipementsSubTab);
		addTab(partsSubTab);
		addTab(materialSubTab);
		addTab(servicesSubTab);
		// addTab(maintenanceSubTab);
	}

	@Override
	public boolean canBeClosen() {
		for (GHAClosable closable : closables)
			if (!closable.canBeClosen())
				return false;
		return true;
	}

	@Override
	public boolean canBeHidden() {
		for (GHAHideable hideable : hideables)
			if (!hideable.canBeHidden())
				return false;
		return true;
	}

	@Override
	public void close() throws UnavailableToCloseException {
		for (GHAClosable closable : closables)
			closable.close();
		destroy();
	}

	@Override
	public void hide() {
		for (GHAHideable hideable : hideables)
			hideable.hide();
		super.hide();
	}

	@Override
	public void onResize(ResizeEvent event) {
		setHeight(GHAUiHelper.getBottomSectionHeight());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener#select
	 * (org.fourgeeks.gha.domain.gmh.EiaType)
	 */
	@Override
	public void select(EiaType eiaType) {
		animateShow(AnimationEffect.FADE);
	}

}