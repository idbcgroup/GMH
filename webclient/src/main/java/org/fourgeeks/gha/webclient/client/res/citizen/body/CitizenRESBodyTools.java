package org.fourgeeks.gha.webclient.client.res.citizen.body;

import org.fourgeeks.gha.webclient.client.UI.imageitems.buttons.GHACancelButton;
import org.fourgeeks.gha.webclient.client.UI.imageitems.buttons.GHACleanButton;
import org.fourgeeks.gha.webclient.client.UI.imageitems.buttons.GHACloseButton;
import org.fourgeeks.gha.webclient.client.UI.imageitems.buttons.GHADeleteButton;
import org.fourgeeks.gha.webclient.client.UI.imageitems.buttons.GHAEditButton;
import org.fourgeeks.gha.webclient.client.UI.imageitems.buttons.GHANewButton;
import org.fourgeeks.gha.webclient.client.UI.imageitems.buttons.GHASaveButton;
import org.fourgeeks.gha.webclient.client.UI.imageitems.buttons.GHASearchButton;
import org.fourgeeks.gha.webclient.client.UI.imageitems.buttons.GHAUndoButton;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret
 * 
 */
@Deprecated
public class CitizenRESBodyTools extends VLayout {

	/**
	 * 
	 */
	public CitizenRESBodyTools() {
		setWidth(30);
		setLayoutMargin(5);
		setMembersMargin(5);
		setDefaultLayoutAlign(Alignment.CENTER);
		setBackgroundColor("#C2D1FF");
		final ClickHandler clickHandler = new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO:
			}
		};
		addMember(new GHACancelButton(clickHandler));
		addMember(new GHACleanButton(clickHandler));
		addMember(new GHACloseButton(clickHandler));
		addMember(new GHADeleteButton(clickHandler));
		addMember(new GHAEditButton(clickHandler));
		addMember(new GHANewButton(clickHandler));
		addMember(new LayoutSpacer());
		addMember(new GHASaveButton(clickHandler));
		addMember(new GHASearchButton(clickHandler));
		addMember(new GHAUndoButton(clickHandler));

	}
}
