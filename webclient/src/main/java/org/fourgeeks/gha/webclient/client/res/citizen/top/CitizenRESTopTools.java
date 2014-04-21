package org.fourgeeks.gha.webclient.client.res.citizen.top;

import org.fourgeeks.gha.webclient.client.UI.imageitems.buttons.GHACleanButton;
import org.fourgeeks.gha.webclient.client.UI.imageitems.buttons.GHASaveButton;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret
 * 
 */
@Deprecated
public class CitizenRESTopTools extends VLayout {

	/**
	 * 
	 */
	public CitizenRESTopTools() {
		setWidth(30);
		setLayoutMargin(5);
		setMembersMargin(5);
		setDefaultLayoutAlign(Alignment.CENTER);
		setBackgroundColor("#C2D1FF");// TODO
		final ClickHandler clickHandler = new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				// TODO:
			}
		};

		final ClickHandler closeHandler = new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				// TODO: close
			}
		};
		addMember(new GHACleanButton(clickHandler));
		addMember(new GHASaveButton(clickHandler));
		// addMember(new GHACancelButton(closeHandler));
	}
}
