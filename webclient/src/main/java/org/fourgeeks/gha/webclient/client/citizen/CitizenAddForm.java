/**
 * 
 */
package org.fourgeeks.gha.webclient.client.citizen;

import org.fourgeeks.gha.domain.mix.Citizen;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.imageitems.buttons.GHACancelButton;
import org.fourgeeks.gha.webclient.client.UI.imageitems.buttons.GHASaveButton;
import org.fourgeeks.gha.webclient.client.UI.pmewindows.GHAErrorMessageProcessor;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAAddForm;

import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret
 * 
 */
public class CitizenAddForm extends GHAAddForm<Citizen> implements
		CitizenSelectionProducer {

	{
		form = new CitizenForm();
	}

	/**
	 * @param title
	 */
	public CitizenAddForm(String title) {
		super(title);
		final VLayout sideButtons = GHAUiHelper.createBar(new GHASaveButton(
				new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						save();
					}
				}), new GHACancelButton(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				cancel();
			}
		}));

		final HLayout gridPanel = new HLayout();
		gridPanel.addMembers(form, new LayoutSpacer(), sideButtons);
		addMember(gridPanel);

	}

	@Override
	protected void save() {
		form.save(new GHAAsyncCallback<Citizen>() {

			@Override
			public void onSuccess(Citizen result) {
				GHAErrorMessageProcessor.alert("user-save-success");
				hide();
			}
		});
	}

	@Override
	public void addCitizenSelectionListener(CitizenSelectionListener listener) {
		((CitizenSelectionProducer) form).addCitizenSelectionListener(listener);
	}

	@Override
	public void removeCitizenSelectionListener(CitizenSelectionListener listener) {
		((CitizenSelectionProducer) form)
				.removeCitizenSelectionListener(listener);

	}

	@Override
	public void notifyCitizen(Citizen citizen) {
		// This is left in blank because is the form who notifys
	}

}
