package org.fourgeeks.gha.webclient.client.eiatype.eiadamagereport;

import org.fourgeeks.gha.domain.gmh.EiaDamageReport;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHASectionForm;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHASubTab;
import org.fourgeeks.gha.webclient.client.eiadamagereport.EiaDamageReportSelectionListener;
import org.fourgeeks.gha.webclient.client.eiadamagereport.EiaDamageReportSelectionProducer;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeTab;

import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.events.TabDeselectedEvent;
import com.smartgwt.client.widgets.tab.events.TabDeselectedHandler;
import com.smartgwt.client.widgets.tab.events.TabSelectedEvent;
import com.smartgwt.client.widgets.tab.events.TabSelectedHandler;

public class EIADamageReportSubTab extends GHASubTab implements
		EiaDamageReportSelectionProducer {

	private EiaDamageReportGridPanel damageReportPanel;
	private GHASectionForm sectionForm;
	{
		sectionForm = new GHASectionForm();
		damageReportPanel = new EiaDamageReportGridPanel();
	}

	public EIADamageReportSubTab(EIATypeTab tab) {
		super("Equipos dañados", tab);

		addClosableListener(damageReportPanel);
		addHideableListener(damageReportPanel);

		sectionForm.addSection("Reporte Equipo Dañado", damageReportPanel);
		sectionForm.addSection("Planificación Mantemiento", new VLayout());

		setPane(sectionForm);

		tab.addEiaTypeSelectionListener(damageReportPanel);

		addTabSelectedHandler(new TabSelectedHandler() {
			@Override
			public void onTabSelected(TabSelectedEvent event) {
				sectionForm.show();
			}
		});

		addTabDeselectedHandler(new TabDeselectedHandler() {
			@Override
			public void onTabDeselected(TabDeselectedEvent event) {
				sectionForm.hide();
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
