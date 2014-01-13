package org.fourgeeks.gha.webclient.client.maintenanceactivity.information;

import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHASectionForm;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAVerticalLayout;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHASubTab;
import org.fourgeeks.gha.webclient.client.maintenanceplan.MaintenancePlanPanel;

import com.smartgwt.client.widgets.tab.events.TabDeselectedEvent;
import com.smartgwt.client.widgets.tab.events.TabDeselectedHandler;
import com.smartgwt.client.widgets.tab.events.TabSelectedEvent;
import com.smartgwt.client.widgets.tab.events.TabSelectedHandler;

/**
 * @author naramirez
 */
public class MaintenanceActivitySubTab extends GHASubTab {

	private final MaintenanceActivityDefinitionFormPanel activityDefinitionForm;
	private final GHASectionForm sectionForm;

	{
		activityDefinitionForm = new MaintenanceActivityDefinitionFormPanel();
		sectionForm = new GHASectionForm();
	}

	/**
	 * @param panel
	 */
	public MaintenanceActivitySubTab(MaintenancePlanPanel panel) {
		super(GHAStrings.get("maintenance-activity"), panel);

		addClosableListener(activityDefinitionForm);
		addHideableListener(activityDefinitionForm);

		sectionForm.addSection(GHAStrings.get("activity-definition"),
				activityDefinitionForm);

		GHAVerticalLayout mainLayout = new GHAVerticalLayout() {
		};

		mainLayout.addMember(sectionForm);
		setPane(mainLayout);

		// handlers
		addTabSelectedHandler(new TabSelectedHandler() {
			@Override
			public void onTabSelected(TabSelectedEvent event) {
				show();
			}
		});

		addTabDeselectedHandler(new TabDeselectedHandler() {
			@Override
			public void onTabDeselected(TabDeselectedEvent event) {
				hide();
			}
		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.UI.tabs.GHASubTab#hide()
	 */
	@Override
	public void hide() {
		super.hide();
		sectionForm.hide();
	}

	/**
	 * 
	 */
	public void openFirstSection() {
		sectionForm.openFirst();
		show();
	}

	/**
	 * 
	 */
	public void show() {
		sectionForm.show();
		activityDefinitionForm.show();
	}
}
