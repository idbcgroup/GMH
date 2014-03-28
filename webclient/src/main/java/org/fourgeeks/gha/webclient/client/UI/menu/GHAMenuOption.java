package org.fourgeeks.gha.webclient.client.UI.menu;

import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.icons.GHAImg;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHALabel;

import com.google.gwt.user.client.History;
import com.smartgwt.client.types.Cursor;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.MouseOutEvent;
import com.smartgwt.client.widgets.events.MouseOutHandler;
import com.smartgwt.client.widgets.events.MouseOverEvent;
import com.smartgwt.client.widgets.events.MouseOverHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;

/**
 * @author alacret
 * 
 */
public class GHAMenuOption extends HLayout {

	// private final String token;

	/**
	 * 
	 */
	public GHAMenuOption() {
		setWidth100();
		setHeight("30px");
		setMembersMargin(7);
		setDefaultLayoutAlign(VerticalAlignment.CENTER);
		setCursor(Cursor.HAND);
		setVisible(false);
		setStyleName("menu-option");
		addMember(new LayoutSpacer());
	}

	/**
	 * @param text
	 * @param token
	 * @param imgSrc
	 */
	public GHAMenuOption(final String text, final String token,
			final String imgSrc) {
		this();
		final GHAImg iconButton = new GHAImg(imgSrc);
		addMember(iconButton);
		final GHALabel titulo = new GHALabel(text);
		titulo.setWidth100();
		titulo.setHeight("25px");
		titulo.setStyleName("menu-option-title button-pointer");

		addClickHandler(new ClickHandler() {

			@Override
			public void onClick(final ClickEvent event) {
				if (token != null)
					History.newItem(token);
			}
		});

		addMouseOverHandler(new MouseOverHandler() {

			@Override
			public void onMouseOver(final MouseOverEvent event) {
				setBackgroundColor(GHAUiHelper.DEFAULT_PLACES_BAR_BACKGROUND_COLOR);
			}
		});

		addMouseOutHandler(new MouseOutHandler() {

			@Override
			public void onMouseOut(final MouseOutEvent event) {
				setBackgroundColor("#FFFFFF");
			}
		});

		addMember(titulo);
		addMember(new LayoutSpacer());

	}

}
