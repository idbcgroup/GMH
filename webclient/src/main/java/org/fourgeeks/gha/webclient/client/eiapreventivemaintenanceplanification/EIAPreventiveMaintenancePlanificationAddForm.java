package org.fourgeeks.gha.webclient.client.eiapreventivemaintenanceplanification;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaPreventiveMaintenancePlanification;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.icons.GHACloseButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHASaveButton;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAAddForm;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHANotification;
import org.fourgeeks.gha.webclient.client.eia.EIASelectionListener;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;

import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

public class EIAPreventiveMaintenancePlanificationAddForm extends
		GHAAddForm<EiaPreventiveMaintenancePlanification> implements
		EIASelectionListener, EIATypeSelectionListener,
		PreventivePlanificationSelectionProducer {

	{
		form = new EIAPreventiveMaintenancePlanificationForm();
	}

	public EIAPreventiveMaintenancePlanificationAddForm() {
		super(GHAStrings.get("new-eia-preventive-maintenance-planification"));
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
	public void addPreventivePlanificationSelectionListener(
			PreventivePlanificationSelectionListener preventivePlanifSelectionListener) {
		((PreventivePlanificationSelectionProducer) form)
				.addPreventivePlanificationSelectionListener(preventivePlanifSelectionListener);

	}

	@Override
	public void hide() {
		super.hide();
		form.hide();
	}

	@Override
	public void notifyPreventiveMaintenancePlanification(
			EiaPreventiveMaintenancePlanification preventivePlanif) {
		return;
	}

	@Override
	public void open() {
		super.open();
		form.show();
	}

	@Override
	public void removePreventivePlanificationSelectionListener(
			PreventivePlanificationSelectionListener eiaDamageReportSelectionListener) {
		((PreventivePlanificationSelectionProducer) form)
				.removePreventivePlanificationSelectionListener(eiaDamageReportSelectionListener);

	}

	private void save() {
		form.save(new GHAAsyncCallback<EiaPreventiveMaintenancePlanification>() {
			@Override
			public void onSuccess(EiaPreventiveMaintenancePlanification result) {
				GHANotification
						.alert("eiaPreventiveMaintenancePlanification-save-success");
				hide();
			}
		});
	}

	@Override
	public void select(Eia eia) {
		((EIASelectionListener) form).select(eia);
	}

	@Override
	public void select(EiaType eiaType) {
		((EIATypeSelectionListener) form).select(eiaType);
	}

}