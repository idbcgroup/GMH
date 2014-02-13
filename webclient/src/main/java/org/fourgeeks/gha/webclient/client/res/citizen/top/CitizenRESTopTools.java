package org.fourgeeks.gha.webclient.client.res.citizen.top;

import org.fourgeeks.gha.webclient.client.UI.icons.GHACancelButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHACleanButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHASaveButton;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret
 * 
 */
public class CitizenRESTopTools extends VLayout {

	/**
	 * 
	 */
	public CitizenRESTopTools() {
		setWidth(30);
		setLayoutMargin(5);
		setMembersMargin(10);
		setDefaultLayoutAlign(Alignment.CENTER);
		setBackgroundColor("#C2D1FF");// TODO
		final ClickHandler clickHandler = new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
			}
		};
		addMember(new GHASaveButton(clickHandler));
		addMember(new GHACleanButton(clickHandler));
		addMember(new GHACancelButton(clickHandler));
	}
}
