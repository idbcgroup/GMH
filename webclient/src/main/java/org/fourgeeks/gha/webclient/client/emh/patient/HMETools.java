package org.fourgeeks.gha.webclient.client.emh.patient;

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
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret
 * 
 */
public class HMETools extends VLayout {

	/**
	 * 
	 */
	public HMETools() {
		setWidth(30);
		setLayoutMargin(5);
		setMembersMargin(10);
		setDefaultLayoutAlign(Alignment.CENTER);
		ClickHandler clickHandler = new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
			}
		};
		addMember(new GHACancelButton(clickHandler));
		addMember(new GHACleanButton(clickHandler));
		addMember(new GHACloseButton(clickHandler));
		addMember(new GHADeleteButton(clickHandler));
		addMember(new GHAEditButton(clickHandler));
		addMember(new GHANewButton(clickHandler));
		addMember(new GHASaveButton(clickHandler));
		addMember(new GHASearchButton(clickHandler));
		addMember(new GHAUndoButton(clickHandler));

	}
}
