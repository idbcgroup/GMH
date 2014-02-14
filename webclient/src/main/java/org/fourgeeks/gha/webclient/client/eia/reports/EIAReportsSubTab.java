package org.fourgeeks.gha.webclient.client.eia.reports;

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
 * @author naramirez
 * 
 */
public class EIAReportsSubTab extends GHASubTab implements EIASelectionListener {
	private EIAReportsFormPanel form;

	/**
	 * @param panel
	 */
	public EIAReportsSubTab(EIAPanel panel) {
		super(GHAStrings.get("reports"));
		panel.addHideableListener(this);
		panel.addClosableListener(this);

		form = new EIAReportsFormPanel();
		addClosableListener(form);
		addHideableListener(form);

		setPane(form);

		panel.addEiaSelectionListener(this);

		// handlers
		addTabSelectedHandler(new TabSelectedHandler() {
			@Override
			public void onTabSelected(TabSelectedEvent event) {
				form.show();
			}
		});

		addTabDeselectedHandler(new TabDeselectedHandler() {
			@Override
			public void onTabDeselected(TabDeselectedEvent event) {
				hide();
			}
		});
	}

	@Override
	public void select(Eia eia) {
	}
}
