package org.fourgeeks.gha.webclient.client.eia;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.exceptions.UnavailableToCloseException;
import org.fourgeeks.gha.webclient.client.UI.interfaces.GHAClosable;
import org.fourgeeks.gha.webclient.client.UI.interfaces.GHAHideable;
import org.fourgeeks.gha.webclient.client.eia.component.EIAComponentSubTab;
import org.fourgeeks.gha.webclient.client.eia.information.EIAInformationSubTab;
import org.fourgeeks.gha.webclient.client.eia.reports.EIAReportsSubTab;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.smartgwt.client.types.AnimationEffect;
import com.smartgwt.client.widgets.tab.TabSet;

/**
 * @author alacret, emiliot
 * 
 */
public class EIAInternalTabset extends TabSet implements ResizeHandler,
		GHAHideable, GHAClosable, EIASelectionListener {

	private EIAInformationSubTab infoSubTab;
	private EIAComponentSubTab partsSubTab;
	private EIAReportsSubTab reportsSubTab;

	// private EIAMaintPlanSubTab eiaPlanSubTab;
	// private EIACostSubTab eiaCostsSubTab;
	// private EIAMovementsSubTab eiaMovementsSubTab;

	private List<GHAHideable> hideables = new ArrayList<GHAHideable>();
	private List<GHAClosable> closables = new ArrayList<GHAClosable>();

	/**
	 * @param eiaTab
	 */
	public EIAInternalTabset(EIATab eiaTab) {
		super();
		setWidth100();
		setHeight(GHAUiHelper.getBottomSectionHeight());
		GHAUiHelper.addGHAResizeHandler(this);
		setVisible(false);

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
	 * org.fourgeeks.gha.webclient.client.UI.interfaces.GHAClosable#canBeClosen
	 * ()
	 */
	@Override
	public boolean canBeClosen() {
		for (GHAClosable closable : closables)
			if (!closable.canBeClosen())
				return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.UI.interfaces.GHAHideable#canBeHidden
	 * ()
	 */
	@Override
	public boolean canBeHidden() {
		for (GHAHideable hideable : hideables)
			if (!hideable.canBeHidden())
				return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.UI.interfaces.GHAClosable#close()
	 */
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
	 * org.fourgeeks.gha.webclient.client.eia.EIASelectionListener#select(org
	 * .fourgeeks.gha.domain.gmh.Eia)
	 */
	@Override
	public void select(Eia eia) {
		animateShow(AnimationEffect.FADE);
	}

}
