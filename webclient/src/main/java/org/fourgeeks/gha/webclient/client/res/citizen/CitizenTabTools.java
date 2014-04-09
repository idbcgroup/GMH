package org.fourgeeks.gha.webclient.client.res.citizen;

import org.fourgeeks.gha.webclient.client.UI.exceptions.UnavailableToCloseException;
import org.fourgeeks.gha.webclient.client.UI.imageitems.buttons.GHACleanButton;
import org.fourgeeks.gha.webclient.client.UI.imageitems.buttons.GHACloseButton;
import org.fourgeeks.gha.webclient.client.UI.imageitems.buttons.GHASaveButton;
import org.fourgeeks.gha.webclient.client.UI.interfaces.ClosableListener;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideCloseAction;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideableListener;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret
 * 
 */
public class CitizenTabTools extends VLayout implements ClosableListener, HideableListener{

	/**
	 * 
	 */
	public CitizenTabTools() {
		setWidth(30);
		setHeight100();
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

		addMember(new GHACleanButton(clickHandler));
		addMember(new GHASaveButton(clickHandler));
		addMember(new GHACloseButton(clickHandler));
		final LayoutSpacer spacer = new LayoutSpacer();
		spacer.setHeight(30);
		addMember(spacer);
		// addMember(new GHADeleteButton(clickHandler));
		// addMember(new GHAEditButton(clickHandler));
		// addMember(new GHANewButton(clickHandler));
		// addMember(spacer);
		//
		// addMember(new GHASearchButton(clickHandler));
		// addMember(new GHAUndoButton(clickHandler));
		// addMember(new GHACancelButton(clickHandler));

	}

	@Override
	public boolean canBeHidden(HideCloseAction closeAction) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean canBeClosen(HideCloseAction closeAction) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void close() throws UnavailableToCloseException {
		// TODO Auto-generated method stub
		destroy();
	}
}
