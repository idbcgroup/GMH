package org.fourgeeks.gha.webclient.client.eiatype.damageandplanification;

import org.fourgeeks.gha.domain.gmh.EiaDamageReport;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHASectionForm;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHASubTab;
import org.fourgeeks.gha.webclient.client.eiadamagereport.EiaDamageReportSelectionListener;
import org.fourgeeks.gha.webclient.client.eiadamagereport.EiaDamageReportSelectionProducer;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeTab;

import com.smartgwt.client.widgets.tab.events.TabDeselectedEvent;
import com.smartgwt.client.widgets.tab.events.TabDeselectedHandler;
import com.smartgwt.client.widgets.tab.events.TabSelectedEvent;
import com.smartgwt.client.widgets.tab.events.TabSelectedHandler;

public class EIADamageAndPlanificationSubTab extends GHASubTab implements
		EiaDamageReportSelectionProducer {

	private EiaDamageReportGridPanel damageReportPanel;
	private EIAPreventiveMaintenancePlanificationGridPanel preventivePlanifPanel;
	private GHASectionForm sectionForm;

	{
		sectionForm = new GHASectionForm();
		damageReportPanel = new EiaDamageReportGridPanel();
		preventivePlanifPanel = new EIAPreventiveMaintenancePlanificationGridPanel();
	}

	public EIADamageAndPlanificationSubTab(EIATypeTab tab) {
		super("Reporte y Planificacion", tab);

		addClosableListener(damageReportPanel);
		addHideableListener(damageReportPanel);
		addClosableListener(preventivePlanifPanel);
		addHideableListener(preventivePlanifPanel);

		sectionForm.addSection("Reporte Equipo Dañado", damageReportPanel);
		sectionForm.addSection("Planificación Mantemiento",
				preventivePlanifPanel);

		setPane(sectionForm);

		tab.addEiaTypeSelectionListener(damageReportPanel);
		tab.addEiaTypeSelectionListener(preventivePlanifPanel);

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

	@Override
	public void hide() {
		super.hide();
		sectionForm.hide();
	}

	public void show() {
		sectionForm.show();
		damageReportPanel.show();
		preventivePlanifPanel.show();
	}

	public void openFirstSection() {
		sectionForm.openFirst();
	}

	@Override
	public void addEiaDamageReportSelectionListener(
			EiaDamageReportSelectionListener eiaDamageReportSelectionListener) {
		damageReportPanel
				.addEiaDamageReportSelectionListener(eiaDamageReportSelectionListener);

	}

	@Override
	public void removeEiaDamageReportSelectionListener(
			EiaDamageReportSelectionListener eiaDamageReportSelectionListener) {
		damageReportPanel
				.removeEiaDamageReportSelectionListener(eiaDamageReportSelectionListener);
	}

	@Override
	public void notifyEiaDamageReport(EiaDamageReport eiaDamageReport) {
	}
}
