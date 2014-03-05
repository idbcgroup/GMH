package org.fourgeeks.gha.webclient.client.res.citizen.body;

import org.fourgeeks.gha.webclient.client.UI.icons.GHACancelButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHACleanButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHACloseButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHADeleteButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHAEditButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHANewButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHASaveButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHASearchButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHAUndoButton;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret
 * 
 */
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
				//TODO:
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
