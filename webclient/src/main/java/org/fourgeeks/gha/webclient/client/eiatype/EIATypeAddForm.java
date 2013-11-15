package org.fourgeeks.gha.webclient.client.eiatype;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.icons.GHACloseButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHASaveButton;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAAddForm;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHANotification;

import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret, emiliot
 * 
 */
public class EIATypeAddForm extends GHAAddForm<EiaType> implements
		EiaTypeSelectionProducer {

	{
		form = new EiaTypeForm();
	}

	/**
	 * @param title
	 * 
	 */
	public EIATypeAddForm(String title) {
		super(title);
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
	public void addEiaTypeSelectionListener(
			EIATypeSelectionListener eIATypeSelectionListener) {
		((EiaTypeSelectionProducer) form)
				.addEiaTypeSelectionListener(eIATypeSelectionListener);
	}

	@Override
	public void notifyEiaType(EiaType eiaType) {
		return;
	}

	@Override
	public void open() {
		super.open();
		form.activate();
	}

	@Override
	public void removeEiaTypeSelectionListener(
			EIATypeSelectionListener eIATypeSelectionListener) {
		((EiaTypeSelectionProducer) form)
				.removeEiaTypeSelectionListener(eIATypeSelectionListener);

	}

	private void save() {
		form.save(new GHAAsyncCallback<EiaType>() {

			@Override
			public void onSuccess(EiaType arg0) {
				GHANotification.alert("eiatype-save-success");
				hide();
			}
		});
	}

	@Override
	public void cancel() {
		if (form.hasUnCommittedChanges()) {
			GHANotification.confirm(GHAStrings.get("information"),
					GHAStrings.get("unsaved-changes"), new BooleanCallback() {

						@Override
						public void execute(Boolean value) {
							if (value) {
								form.undo();
								EIATypeAddForm.super.hide();
							}
						}
					});
		} else
			super.hide();
	}
}
