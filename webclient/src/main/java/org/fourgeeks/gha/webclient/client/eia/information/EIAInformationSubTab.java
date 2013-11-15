package org.fourgeeks.gha.webclient.client.eia.information;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHASubTab;
import org.fourgeeks.gha.webclient.client.eia.EIASelectionListener;
import org.fourgeeks.gha.webclient.client.eia.EIATab;

import com.smartgwt.client.widgets.tab.events.TabDeselectedEvent;
import com.smartgwt.client.widgets.tab.events.TabDeselectedHandler;
import com.smartgwt.client.widgets.tab.events.TabSelectedEvent;
import com.smartgwt.client.widgets.tab.events.TabSelectedHandler;

/**
 * @author alacret, emiliot Equipments sub tab
 */
public class EIAInformationSubTab extends GHASubTab implements
		EIASelectionListener {

	private EIAInformationFormPanel form;

	/**
	 * @param tab
	 * 
	 */
	public EIAInformationSubTab(EIATab tab) {
		super(GHAStrings.get("information"), tab);

		form = new EIAInformationFormPanel();
		addClosableListener(form);
		addHideableListener(form);

		setPane(form);

		form.addEiaSelectionListener(tab);
		tab.addEiaSelectionListener(this);

		addTabSelectedHandler(new TabSelectedHandler() {
			@Override
			public void onTabSelected(TabSelectedEvent event) {
				form.show();
			}
		});

		addTabDeselectedHandler(new TabDeselectedHandler() {
			@Override
			public void onTabDeselected(TabDeselectedEvent event) {
				form.hide();
			}
		});
	}

	@Override
	public void hide() {
		form.hide();
		super.hide();
	}

	@Override
	public void select(Eia eia) {
		form.setEia(eia);
	}

	public void show() {
		form.show();
	}
}
