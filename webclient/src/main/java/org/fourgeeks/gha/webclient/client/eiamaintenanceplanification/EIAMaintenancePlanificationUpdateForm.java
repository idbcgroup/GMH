package org.fourgeeks.gha.webclient.client.eiamaintenanceplanification;

import org.fourgeeks.gha.domain.gmh.EiaMaintenancePlanification;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.icons.GHACloseButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHASaveButton;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHANotification;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAUpdateForm;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;

import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author nelson
 * 
 */
public class EIAMaintenancePlanificationUpdateForm extends
		GHAUpdateForm<EiaMaintenancePlanification> implements
		EiaMaintenancePlanificationSelectionListener, EIATypeSelectionListener,
		EiaMaintenancePlanificationSelectionProducer {

	{
		form = new EIAMaintenancePlanificationForm();
	}

	/**
	 * Constructor del formulario para planificacion de mantenimiento
	 */
	public EIAMaintenancePlanificationUpdateForm() {
		super(GHAStrings.get("edit-eia-maintenance-planification"));
		VLayout sideButtons = GHAUiHelper.createBar(new GHASaveButton(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						update();
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

	/**
	 * Activate the form
	 */
	public void activate() {
		form.activate();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.eiamaintenanceplanification.
	 * EiaMaintenancePlanificationSelectionProducer
	 * #addEiaMaintenancePlanificationSelectionListener
	 * (org.fourgeeks.gha.webclient.client.eiamaintenanceplanification.
	 * EiaMaintenancePlanificationSelectionListener)
	 */
	@Override
	public void addEiaMaintenancePlanificationSelectionListener(
			EiaMaintenancePlanificationSelectionListener preventivePlanifSelectionListener) {

		((EiaMaintenancePlanificationSelectionProducer) form)
				.addEiaMaintenancePlanificationSelectionListener(preventivePlanifSelectionListener);
	}

	/**
	 * Deactivate the form
	 */
	public void deactivate() {
		form.deactivate();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.UI.superclasses.GHASlideInWindow#hide
	 * ()
	 */
	@Override
	public void hide() {
		super.hide();
		form.hide();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.eiamaintenanceplanification.
	 * EiaMaintenancePlanificationSelectionProducer
	 * #notifyEiaMaintenancePlanification
	 * (org.fourgeeks.gha.domain.gmh.EiaMaintenancePlanification)
	 */
	@Override
	public void notifyEiaMaintenancePlanification(
			EiaMaintenancePlanification entity) {
		return;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.UI.superclasses.GHASlideInWindow#open
	 * ()
	 */
	@Override
	public void open() {
		super.open();
		form.show();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.eiamaintenanceplanification.
	 * EiaMaintenancePlanificationSelectionProducer
	 * #removeEiaMaintenancePlanificationSelectionListener
	 * (org.fourgeeks.gha.webclient.client.eiamaintenanceplanification.
	 * EiaMaintenancePlanificationSelectionListener)
	 */
	@Override
	public void removeEiaMaintenancePlanificationSelectionListener(
			EiaMaintenancePlanificationSelectionListener listener) {

		((EiaMaintenancePlanificationSelectionProducer) form)
				.removeEiaMaintenancePlanificationSelectionListener(listener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.eiamaintenanceplanification.
	 * EiaMaintenancePlanificationSelectionListener
	 * #select(org.fourgeeks.gha.domain.gmh.EiaMaintenancePlanification)
	 */
	@Override
	public void select(EiaMaintenancePlanification entity) {
		((EiaMaintenancePlanificationSelectionListener) form).select(entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener#select
	 * (org.fourgeeks.gha.domain.gmh.EiaType)
	 */
	@Override
	public void select(EiaType eiaType) {
		((EIATypeSelectionListener) form).select(eiaType);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.UI.superclasses.GHAUpdateForm#update()
	 */
	@Override
	protected void update() {
		form.update(new GHAAsyncCallback<EiaMaintenancePlanification>() {
			@Override
			public void onSuccess(EiaMaintenancePlanification result) {
				GHANotification
						.alert("eia-maintenance-planification-update-success");
				hide();
			}
		});
	}

}
