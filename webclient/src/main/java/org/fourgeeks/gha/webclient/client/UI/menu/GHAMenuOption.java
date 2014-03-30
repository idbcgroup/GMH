package org.fourgeeks.gha.webclient.client.UI.menu;

import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.icons.GHAImg;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHALabel;

import com.smartgwt.client.types.Cursor;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.events.MouseOutEvent;
import com.smartgwt.client.widgets.events.MouseOutHandler;
import com.smartgwt.client.widgets.events.MouseOverEvent;
import com.smartgwt.client.widgets.events.MouseOverHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;

/**
 * @author alacret Class for the representation of an Option in GHAMenu
 */
public class GHAMenuOption extends HLayout {
	private String text;

	/**
	 * 
	 */
	public GHAMenuOption() {
		setWidth(GHAMenu.BAR_WIDTH);
		setHeight("30px");
		setMembersMargin(7);
		setDefaultLayoutAlign(VerticalAlignment.CENTER);
		setCursor(Cursor.HAND);
		setVisible(false);
		setStyleName("menu-option");
		addMember(new LayoutSpacer());
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
	}

	/**
	 * @param text
	 * @param imgSrc
	 */
	public GHAMenuOption(final String text, final String imgSrc) {
		this();
		this.text = text;
		final GHAImg iconButton = new GHAImg(imgSrc);
		addMember(iconButton);
		final GHALabel titulo = new GHALabel(text);
		titulo.setWidth100();
		titulo.setHeight("25px");
		titulo.setStyleName("menu-option-title button-pointer");
		addMember(titulo);
		addMember(new LayoutSpacer());
	}

	/**
	 * @return the Text
	 */
	public String getText() {
		return text;
	}
}