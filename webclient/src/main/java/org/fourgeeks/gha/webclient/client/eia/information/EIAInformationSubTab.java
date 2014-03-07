package org.fourgeeks.gha.webclient.client.eia.information;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHASubTab;
import org.fourgeeks.gha.webclient.client.eia.EIAPanel;
import org.fourgeeks.gha.webclient.client.eia.EIASelectionListener;

import com.smartgwt.client.widgets.tab.events.TabDeselectedEvent;
import com.smartgwt.client.widgets.tab.events.TabDeselectedHandler;
import com.smartgwt.client.widgets.tab.events.TabSelectedEvent;
import com.smartgwt.client.widgets.tab.events.TabSelectedHandler;

/**
 * @author alacret, emiliot Equipments sub tab
 */
public class EIAInformationSubTab extends GHASubTab implements
EIASelectionListener {

	private final EIAInformationFormPanel form;

	/**
	 * @param panel
	 * 
	 */
	public EIAInformationSubTab(EIAPanel panel) {
		super(GHAStrings.get("information"));
		panel.addHideableListener(this);
		panel.addClosableListener(this);
		form = new EIAInformationFormPanel();
		addClosableListener(form);
		addHideableListener(form);

		setPane(form);

		form.addEiaSelectionListener(panel);
		panel.addEiaSelectionListener(this);

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
		form.select(eia);
	}

	/**
	 * 
	 */
	public void show() {
		form.show();
	}
}
