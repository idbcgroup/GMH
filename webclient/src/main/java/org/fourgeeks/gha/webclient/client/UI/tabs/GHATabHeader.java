package org.fourgeeks.gha.webclient.client.UI.tabs;

import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.smartgwt.client.types.BackgroundRepeat;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.Label;
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
public class GHATabHeader extends HLayout implements ResizeHandler {

	private Option titulo;
	private int memberPos = 2;

	/**
	 * @param tab
	 * @param title
	 */
	public GHATabHeader(final GHATab tab, String title) {
		GHAUiHelper.addGHAResizeHandler(this);
		setWidth(Window.getClientWidth() - 35);
		setHeight(30);
		setDefaultLayoutAlign(VerticalAlignment.TOP);
		setMembersMargin(6);
		setStyleName("sides-padding tab-header");

		titulo = new Option(title, 150, false, "", "");

		addMember(titulo);

		addMember(new LayoutSpacer());

		Option closeOption = new Option(GHAStrings.get("close"), 90, true,
				"../resources/img/cerrarButton.png",
				"../resources/img/cerrarButtonOver.png");
		closeOption.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				GHATabSet.closeTab(tab);

			}
		});

		addMember(closeOption);
	}

	/**
	 * Add the clean option
	 * 
	 * @param clickHandler
	 *            the action to be taken when the user clicks
	 */
	public void addCleanOption(ClickHandler clickHandler) {
		Option cleanOption = new Option(GHAStrings.get("clean") + "...", 90,
				true, "../resources/img/limpiarButton.png",
				"../resources/img/limpiarButtonOver.png");
		cleanOption.addClickHandler(clickHandler);
		addMember(cleanOption, memberPos++);
	}

	/**
	 * Add the add option
	 * 
	 * @param clickHandler
	 *            the action to be taken when the user clicks
	 */
	public void addAddOption(ClickHandler clickHandler) {
		Option addOption = new Option(GHAStrings.get("add") + "...", 90, true,
				"../resources/img/agregarButton.png",
				"../resources/img/agregarButtonOver.png");
		addOption.addClickHandler(clickHandler);
		addMember(addOption, memberPos++);
	}

	/**
	 * Add the search option
	 * 
	 * @param clickHandler
	 *            the action to be taken when the user clicks
	 */
	public void addSearchOption(ClickHandler clickHandler) {
		Option searchOption = new Option(GHAStrings.get("search") + "...", 90,
				true, "../resources/img/buscarButton.png",
				"../resources/img/buscarButtonOver.png");
		searchOption.addClickHandler(clickHandler);
		addMember(searchOption, memberPos++);
	}

	private static class Option extends Label {
		public Option(int width, boolean hoverable, final String bgSrc,
				final String bgSrcOver) {
			super();
			setStyleName("tab-header-title");
			setWidth(width + "px");
			setHeight("30px");
			if (hoverable) {
				setStyleName(getStyleName() + " button-pointer");
				setBackgroundImage(bgSrc);
				setBackgroundRepeat(BackgroundRepeat.NO_REPEAT);

				addMouseOverHandler(new MouseOverHandler() {
					@Override
					public void onMouseOver(MouseOverEvent event) {
						setBackgroundImage(bgSrcOver);
					}
				});
				addMouseOutHandler(new MouseOutHandler() {

					@Override
					public void onMouseOut(MouseOutEvent event) {
						setBackgroundImage(bgSrc);
					}
				});
			}
		}

		public Option(String text, int width, boolean hoverable, String bg,
				String bgOver) {
			this(width, hoverable, bg, bgOver);
			setContents(text);
		}

	}

	@Override
	public void onResize(ResizeEvent event) {
		setWidth(Window.getClientWidth() - 35);
	}
}
