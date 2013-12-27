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
		setMinWidth(1024);
		setHeight(GHAUiHelper.MENU_BAR_HEIGTH);
		setDefaultLayoutAlign(VerticalAlignment.TOP);
		setMembersMargin(6);

		GHAHeaderOption titulo = new GHAHeaderOption(title,
				GHAUiHelper.DEFAULT_TAB_HEADER_WIDTH, false, "", "");

		addMember(titulo);

		addMember(new LayoutSpacer());
		GHAHeaderOption closeOption = new GHAHeaderOption(
				GHAStrings.get("close"),
				GHAUiHelper.DEFAULT_HEADER_OPTION_WIDTH, true,
				"../resources/img/cerrarButton.png",
				"../resources/img/cerrarButtonOver.png");
		closeOption.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				try {
					GHAPlaceSet.closeCurrentPlace(HideCloseAction.SAVE);
				} catch (UnavailableToCloseException e) {
					return;
				}
			}
		});

		addMember(closeOption);
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
		GHAHeaderOption cleanOption = new GHAHeaderOption(
				GHAStrings.get("clean") + "...",
				GHAUiHelper.DEFAULT_HEADER_OPTION_WIDTH, true,
				"../resources/img/limpiarButton.png",
				"../resources/img/limpiarButtonOver.png");
		cleanOption.addClickHandler(clickHandler);
		addMember(cleanOption, memberPos++);
		return cleanOption;
	}

	/**
	 * Add a Debug option
	 * @param title 	 * 
	 * @param clickHandler
	 *            the action to be taken when the user clicks
	 * @return the Debug Option
	 */
	public GHAHeaderOption addDebugOption(String title, ClickHandler clickHandler) {
		GHAHeaderOption debugOption = new GHAHeaderOption(title + "...",
				GHAUiHelper.DEFAULT_HEADER_OPTION_WIDTH,true,
				"../resources/img/limpiarButton.png",
				"../resources/img/limpiarButtonOver.png");
		debugOption.addClickHandler(clickHandler);
		addMember(debugOption, memberPos++);
		return debugOption;
	}	
	
	/**
	 * Add the add option
	 * 
	 * @param clickHandler
	 *            the action to be taken when the user clicks
	 * @return the add option
	 */
	public GHAHeaderOption addAddOption(ClickHandler clickHandler) {
		GHAHeaderOption addOption = new GHAHeaderOption(GHAStrings.get("add")
				+ "...", GHAUiHelper.DEFAULT_HEADER_OPTION_WIDTH, true,
				"../resources/img/agregarButton.png",
				"../resources/img/agregarButtonOver.png");
		addOption.addClickHandler(clickHandler);
		addMember(addOption, memberPos++);
		selectables.add(addOption);
		return addOption;
	}

	/**
	 * Add the search option
	 * 
	 * @param clickHandler
	 *            the action to be taken when the user clicks
	 * @return the search option
	 */
	public GHAHeaderOption addSearchOption(ClickHandler clickHandler) {
		return addOption(GHAStrings.get("search"), "buscarButton", clickHandler);
	}

	/**
	 * @param clickHandler
	 * @param imgSrc
	 * @return
	 */
	private GHAHeaderOption addOption(String text, String imgSrc,
			ClickHandler clickHandler) {
		GHAHeaderOption searchOption = new GHAHeaderOption(text + "...",
				GHAUiHelper.DEFAULT_HEADER_OPTION_WIDTH, true,
				"../resources/img/" + imgSrc + ".png", "../resources/img/"
						+ imgSrc + "Over.png");
		searchOption.addClickHandler(clickHandler);
		addMember(searchOption, memberPos++);
		selectables.add(searchOption);
		return searchOption;
	}

	@Override
	public void onResize(ResizeEvent event) {
		
	}

	/**
	 * 
	 */
	public void unMarkAllButtons() {
		for (GHAHeaderOption button : selectables)
			button.unMarkSelected();
	}

	@Override
	public boolean canBeClosen(HideCloseAction closeAction) {
		return true;
	}

	@Override
	public void close() throws UnavailableToCloseException {
		GHAUiHelper.removeGHAResizeHandler(this);
		destroy();
	}

	@Override
	public boolean canBeHidden(HideCloseAction closeAction) {
		return true;
	}
}
