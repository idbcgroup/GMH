package org.fourgeeks.gha.webclient.client.UI.panels;

import java.util.LinkedList;
import java.util.List;

import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.exceptions.UnavailableToCloseException;
import org.fourgeeks.gha.webclient.client.UI.interfaces.ClosableListener;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideCloseAction;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideableListener;
import org.fourgeeks.gha.webclient.client.UI.places.GHAPlaceSet;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;

/**
 * @author alacret
 * 
 */
public class GHAPanelHeader extends HLayout implements ResizeHandler,
HideableListener, ClosableListener {

	private int memberPos = 2;
	private final List<GHAHeaderOption> selectables = new LinkedList<GHAHeaderOption>();

	/**
	 * @param tab
	 * @param title
	 */
	public GHAPanelHeader(final GHAPanel tab, String title) {
		GHAUiHelper.addGHAResizeHandler(this);
		setWidth100();
		setMinWidth(GHAUiHelper.MIN_WIDTH);
		setHeight(GHAUiHelper.MENU_BAR_HEIGTH);
		setDefaultLayoutAlign(VerticalAlignment.TOP);
		setBackgroundColor(GHAUiHelper.DEFAULT_PANEL_BAR_BACKGROUND_COLOR);
		setMembersMargin(5);

		final GHAHeaderOption titulo = new GHAHeaderOption(title,
				GHAUiHelper.DEFAULT_TAB_HEADER_WIDTH, false, "", "");

		addMember(titulo);

		addMember(new LayoutSpacer());
		final GHAHeaderOption closeOption = new GHAHeaderOption(
				GHAStrings.get("close"),
				GHAUiHelper.DEFAULT_HEADER_OPTION_WIDTH, true,
				"../resources/img/cerrarButton.png",
				"../resources/img/cerrarButtonOver.png");
		closeOption.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				try {
					GHAPlaceSet.closeCurrentPlace(HideCloseAction.SAVE);
				} catch (final UnavailableToCloseException e) {
					return;
				}
			}
		});

		addMember(closeOption);
	}

	/**
	 * @param clickHandler
	 * @param imgSrc
	 *            Known values: "limpiarButton", "agregarButton", "cerrarButton"
	 * @return
	 */
	private GHAHeaderOption addSelectableOption(String text, String imgSrc,
			ClickHandler clickHandler) {
		final GHAHeaderOption selectableOption = new GHAHeaderOption(text + "...",
				GHAUiHelper.DEFAULT_HEADER_OPTION_WIDTH, true,
				"../resources/img/" + imgSrc + ".png", "../resources/img/"
						+ imgSrc + "Over.png");
		selectableOption.addClickHandler(clickHandler);
		addMember(selectableOption, memberPos++);
		selectables.add(selectableOption);
		return selectableOption;
	}

	/**
	 * @param clickHandler
	 * @param imgSrc
	 *            Known values: "limpiarButton", "agregarButton", "cerrarButton"
	 * @return
	 */
	private GHAHeaderOption addNonselectableOption(String text, String imgSrc,
			ClickHandler clickHandler) {
		final GHAHeaderOption nonSelectableOption = new GHAHeaderOption(text + "...",
				GHAUiHelper.DEFAULT_HEADER_OPTION_WIDTH, true,
				"../resources/img/" + imgSrc + ".png", "../resources/img/"
						+ imgSrc + "Over.png");
		nonSelectableOption.addClickHandler(clickHandler);
		addMember(nonSelectableOption, memberPos++);
		return nonSelectableOption;
	}

	/**
	 * Add the add option
	 * 
	 * @param clickHandler
	 *            the action to be taken when the user clicks
	 * @return the add option
	 */
	public GHAHeaderOption addAddOption(ClickHandler clickHandler) {
		return addSelectableOption(GHAStrings.get("add"), "agregarButton", clickHandler);
	}

	/**
	 * Add the clean optionst
	 * 
	 * @param clickHandler
	 *            the action to be taken when the user clicks
	 * @return the clean option
	 */
	@Deprecated
	public GHAHeaderOption addCleanOption(ClickHandler clickHandler) {
		return addNonselectableOption(GHAStrings.get("clean"), "limpiarButton", clickHandler);
	}

	/**
	 * Add the search option
	 * 
	 * @param clickHandler
	 *            the action to be taken when the user clicks
	 * @return the search option
	 */
	public GHAHeaderOption addSearchOption(ClickHandler clickHandler) {
		return addSelectableOption(GHAStrings.get("search"), "buscarButton", clickHandler);
	}

	/**
	 * Add a Debug option
	 * 
	 * @param title
	 *            *
	 * @param clickHandler
	 *            the action to be taken when the user clicks
	 * @return the Debug Option
	 */
	public GHAHeaderOption addDebugOption(String title,
			ClickHandler clickHandler) {
		return addNonselectableOption(title, "limpiarButton", clickHandler);
	}

	@Override
	public boolean canBeClosen(HideCloseAction closeAction) {
		return true;
	}

	@Override
	public boolean canBeHidden(HideCloseAction closeAction) {
		return true;
	}

	@Override
	public void close() throws UnavailableToCloseException {
		GHAUiHelper.removeGHAResizeHandler(this);
		destroy();
	}

	@Override
	public void onResize(ResizeEvent event) {

	}

	/**
	 * 
	 */
	public void unMarkAllButtons() {
		for (final GHAHeaderOption button : selectables)
			button.unMarkSelected();
	}
}
