package org.fourgeeks.gha.webclient.client.eiadamagereport;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaDamageReport;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.alerts.GHAAlertManager;
import org.fourgeeks.gha.webclient.client.UI.icons.GHACloseButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHASaveButton;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAAddForm;
import org.fourgeeks.gha.webclient.client.eia.EIASelectionListener;

import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author naramirez
 * 
 */
public class EIADamageReportAddForm extends GHAAddForm<EiaDamageReport>
		implements EIASelectionListener, EiaDamageReportSelectionProducer {

	{
		form = new EIADamageReportForm();
	}

	/**
	 * Constructor
	 */
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
		((EiaDamageReportSelectionProducer) form)
				.addEiaDamageReportSelectionListener(eiaDamageReportSelectionListener);

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
	 * @see org.fourgeeks.gha.webclient.client.eiadamagereport.
	 * EiaDamageReportSelectionProducer
	 * #notifyEiaDamageReport(org.fourgeeks.gha.domain.gmh.EiaDamageReport)
	 */
	@Override
	public void notifyEiaDamageReport(EiaDamageReport eiaDamageReport) {
		((EiaDamageReportSelectionProducer) form)
				.notifyEiaDamageReport(eiaDamageReport);
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
	 * @see org.fourgeeks.gha.webclient.client.eiadamagereport.
	 * EiaDamageReportSelectionProducer
	 * #removeEiaDamageReportSelectionListener(org
	 * .fourgeeks.gha.webclient.client
	 * .eiadamagereport.EiaDamageReportSelectionListener)
	 */
	@Override
	public void removeEiaDamageReportSelectionListener(
			EiaDamageReportSelectionListener eiaDamageReportSelectionListener) {
		((EiaDamageReportSelectionProducer) form)
				.removeEiaDamageReportSelectionListener(eiaDamageReportSelectionListener);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.UI.superclasses.GHAAddForm#save()
	 */
	@Override
	protected void save() {
		form.save(new GHAAsyncCallback<EiaDamageReport>() {
			@Override
			public void onSuccess(EiaDamageReport result) {
				GHAAlertManager.alert("eiaDamageReport-save-success");
				hide();
				notifyEiaDamageReport(result);
			}
		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.eia.EIASelectionListener#select(org
	 * .fourgeeks.gha.domain.gmh.Eia)
	 */
	@Override
	public void select(Eia eia) {
		((EIASelectionListener) form).select(eia);

	}

}
