package org.fourgeeks.gha.webclient.client.eiadamagereport;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaDamageReport;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.icons.GHACloseButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHASaveButton;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAAddForm;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHANotification;
import org.fourgeeks.gha.webclient.client.eia.EIASelectionListener;

import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

public class EIADamageReportAddForm extends GHAAddForm<EiaDamageReport>
		implements EIASelectionListener, EiaDamageReportSelectionProducer {

	{
		form = new EIADamageReportForm();
	}

	public EIADamageReportAddForm() {
		super(GHAStrings.get("new-eiaDamageReport"));
		VLayout sideButtons = GHAUiHelper.createBar(new GHASaveButton(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						save();
					}
				}), new GHACloseButton(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				cancel();
			}
		}));

		HLayout gridPanel = new HLayout();
		gridPanel.addMembers(form, new LayoutSpacer(), sideButtons);
		addMember(gridPanel);
	}

	@Override
	public void addEiaDamageReportSelectionListener(
			EiaDamageReportSelectionListener eiaDamageReportSelectionListener) {
		((EiaDamageReportSelectionProducer) form)
				.addEiaDamageReportSelectionListener(eiaDamageReportSelectionListener);

	}

	@Override
	public void hide() {
		super.hide();
		form.hide();
	}

	@Override
	public void notifyEiaDamageReport(EiaDamageReport eiaDamageReport) {
		return;
	}

	@Override
	public void open() {
		super.open();
		form.show();
	}

	@Override
	public void removeEiaDamageReportSelectionListener(
			EiaDamageReportSelectionListener eiaDamageReportSelectionListener) {
		((EiaDamageReportSelectionProducer) form)
				.removeEiaDamageReportSelectionListener(eiaDamageReportSelectionListener);

	}

	@Override
	protected void save() {
		form.save(new GHAAsyncCallback<EiaDamageReport>() {
			@Override
			public void onSuccess(EiaDamageReport result) {
				GHANotification.alert("eiaDamageReport-save-success");
				hide();
			}
		});
	}

	@Override
	public void select(Eia eia) {
		((EIASelectionListener) form).select(eia);

	}

}
