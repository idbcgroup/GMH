package org.fourgeeks.gha.webclient.client.eiatype.damageandplanification;

import org.fourgeeks.gha.domain.gmh.EiaDamageReport;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHASectionForm;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAVerticalLayout;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHASubTab;
import org.fourgeeks.gha.webclient.client.eiadamagereport.EiaDamageReportSelectionListener;
import org.fourgeeks.gha.webclient.client.eiadamagereport.EiaDamageReportSelectionProducer;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypePanel;

import com.smartgwt.client.widgets.tab.events.TabDeselectedEvent;
import com.smartgwt.client.widgets.tab.events.TabDeselectedHandler;
import com.smartgwt.client.widgets.tab.events.TabSelectedEvent;
import com.smartgwt.client.widgets.tab.events.TabSelectedHandler;

/**
 * 
 * @author nelson
 * 
 */
public class EIADamageAndPlanificationSubTab extends GHASubTab implements
		EiaDamageReportSelectionProducer {

	private EiaDamageReportGridPanel damageReportPanel;
	private EIAMaintenancePlanificationGridPanel maintenancePlanifPanel;
	private EIAPreventiveMaintenancePlanificationGridPanel preventivePlanifPanel;
	private GHASectionForm sectionForm;

	{
		sectionForm = new GHASectionForm();
		damageReportPanel = new EiaDamageReportGridPanel();
		maintenancePlanifPanel = new EIAMaintenancePlanificationGridPanel();
		preventivePlanifPanel = new EIAPreventiveMaintenancePlanificationGridPanel();
	}

	/**
	 * @param tab
	 */
	public EIADamageAndPlanificationSubTab(EIATypePanel tab) {
		super(GHAStrings.get("report-and-planification"), tab);

		// listeners
		addClosableListener(damageReportPanel);
		addHideableListener(damageReportPanel);
		addClosableListener(maintenancePlanifPanel);
		addHideableListener(maintenancePlanifPanel);
		addClosableListener(preventivePlanifPanel);
		addHideableListener(preventivePlanifPanel);

		tab.addEiaTypeSelectionListener(damageReportPanel);
		tab.addEiaTypeSelectionListener(maintenancePlanifPanel);
		tab.addEiaTypeSelectionListener(preventivePlanifPanel);

		damageReportPanel
				.addEiaDamageReportSelectionListener(maintenancePlanifPanel);
		preventivePlanifPanel
				.addPreventivePlanificationSelectionListener(maintenancePlanifPanel);

		// section form
		sectionForm.addSection("Reporte Equipo Dañado", damageReportPanel);
		sectionForm.addSection("Mantenimiento Realizado",
				maintenancePlanifPanel);
		sectionForm.addSection("Planificación Mantemiento",
				preventivePlanifPanel);

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
	 * @see org.fourgeeks.gha.webclient.client.eiadamagereport.
	 * EiaDamageReportSelectionProducer
	 * #addEiaDamageReportSelectionListener(org.fourgeeks
	 * .gha.webclient.client.eiadamagereport.EiaDamageReportSelectionListener)
	 */
	@Override
	public void addEiaDamageReportSelectionListener(
			EiaDamageReportSelectionListener eiaDamageReportSelectionListener) {
		damageReportPanel
				.addEiaDamageReportSelectionListener(eiaDamageReportSelectionListener);

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.eiadamagereport.
	 * EiaDamageReportSelectionProducer
	 * #notifyEiaDamageReport(org.fourgeeks.gha.domain.gmh.EiaDamageReport)
	 */
	@Override
	public void notifyEiaDamageReport(EiaDamageReport eiaDamageReport) {
	}

	/**
	 * 
	 */
	public void openFirstSection() {
		sectionForm.openFirst();
		show();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.eiadamagereport.
	 * EiaDamageReportSelectionProducer
	 * #removeEiaDamageReportSelectionListener(org
	 * .fourgeeks.gha.webclient.client
	 * .eiadamagereport.EiaDamageReportSelectionListener)
	 */
	@Override
	public void removeEiaDamageReportSelectionListener(
			EiaDamageReportSelectionListener eiaDamageReportSelectionListener) {
		damageReportPanel
				.removeEiaDamageReportSelectionListener(eiaDamageReportSelectionListener);
	}

	/**
	 * 
	 */
	public void show() {
		sectionForm.show();
		damageReportPanel.show();
		maintenancePlanifPanel.show();
		preventivePlanifPanel.show();
	}
}
