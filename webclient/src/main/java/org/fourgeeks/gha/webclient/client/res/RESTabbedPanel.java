package org.fourgeeks.gha.webclient.client.res;

import org.fourgeeks.gha.domain.mix.Citizen;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.exceptions.UnavailableToCloseException;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideCloseAction;
import org.fourgeeks.gha.webclient.client.UI.places.GHAPlace;
import org.fourgeeks.gha.webclient.client.UI.places.GHAPlaceSet;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHATabbedPanel;
import org.fourgeeks.gha.webclient.client.citizen.CitizenAddForm;
import org.fourgeeks.gha.webclient.client.citizen.CitizenSearchForm;
import org.fourgeeks.gha.webclient.client.citizen.CitizenSelectionListener;

import com.google.gwt.user.client.History;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;

/**
 * @author jfuentes
 * 
 */
public class RESTabbedPanel extends GHATabbedPanel {

	private final CitizenAddForm citizenAddForm = new CitizenAddForm(
			GHAStrings.get("citizen-new"));
	private final CitizenSearchForm citizenSearchForm = new CitizenSearchForm(
			GHAStrings.get("citizen-search"));

	/**
	 * @param title
	 * @param place
	 *            TODO
	 */
	public RESTabbedPanel(String title, final GHAPlace place) {
		super(title);

		citizenSearchForm
		.addCitizenSelectionListener(new CitizenSelectionListener() {
			@Override
			public void onCitizenSelect(Citizen citizen) {
				History.newItem("res/" +citizen.getId());
			}
		});
		citizenAddForm
		.addCitizenSelectionListener(new CitizenSelectionListener() {
			@Override
			public void onCitizenSelect(Citizen citizen) {
				History.newItem("res/" +citizen.getId());
			}
		});

		addHeaderOption(GHAStrings.get("citizen-search"), "buscarButton",
				new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				citizenSearchForm.open();
			}
		});
		addHeaderOption(GHAStrings.get("citizen-new"), "agregarButton",
				new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				citizenAddForm.open();
				//				History.newItem("res/" + Math.round(Math.random() *10));
			}
		});
		addHeaderOption(GHAStrings.get("close"), "cerrarButton",
				new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				try {
					GHAPlaceSet.closeCurrentPlace(HideCloseAction.SAVE);
				} catch (final UnavailableToCloseException e) {
					return;
				}
			}
		});
	}

}
