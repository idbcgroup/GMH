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
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHANotification;

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
public class GHAPanelHeader extends HLayout implements ResizeHandler,
		HideableListener, ClosableListener {

	private static final int OPTION_WIDTH = 90;
	private int memberPos = 1;
	private final List<Option> selectables = new LinkedList<Option>();

	/**
	 * @param tab
	 * @param title
	 */
	public GHAPanelHeader(final GHAPanel tab, String title) {
		GHAUiHelper.addGHAResizeHandler(this);
		setWidth100();
		setHeight(30);
		setDefaultLayoutAlign(VerticalAlignment.TOP);
		setMembersMargin(6);

		addMember(new LayoutSpacer());
		Option closeOption = new Option(this, GHAStrings.get("close"),
				OPTION_WIDTH, true, "../resources/img/cerrarButton.png",
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
		addDebugOption("Notif", new ClickHandler() {			
			@Override
			public void onClick(ClickEvent event) {
				GHANotification.testDialog("Mensaje standard de error Código#1238012");
			}
		});
	}
	
	/**
	 * Add the clean optionst
	 * 
	 * @param clickHandler
	 *            the action to be taken when the user clicks
	 * @return the clean option
	 */
	@Deprecated
	public Option addCleanOption(ClickHandler clickHandler) {
		Option cleanOption = new Option(this, GHAStrings.get("clean") + "...",
				OPTION_WIDTH, true, "../resources/img/limpiarButton.png",
				"../resources/img/limpiarButtonOver.png");
		cleanOption.addClickHandler(clickHandler);
		addMember(cleanOption, memberPos++);
		return cleanOption;
	}

	/**
	 * Add a Debug option
	 * 
	 * @param clickHandler
	 *            the action to be taken when the user clicks
	 * @return the Debug Option
	 */
	public Option addDebugOption(String title, ClickHandler clickHandler) {
		Option debugOption = new Option(this, title + "...",
				OPTION_WIDTH, true, "../resources/img/limpiarButton.png",
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
	public Option addAddOption(ClickHandler clickHandler) {
		Option addOption = new Option(this, GHAStrings.get("add") + "...",
				OPTION_WIDTH, true, "../resources/img/agregarButton.png",
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
	public Option addSearchOption(ClickHandler clickHandler) {
		return addOption(GHAStrings.get("search"), "buscarButton", clickHandler);
	}

	/**
	 * @param clickHandler
	 * @param imgSrc
	 * @return
	 */
	private Option addOption(String text, String imgSrc,
			ClickHandler clickHandler) {
		Option searchOption = new Option(this, text + "...", OPTION_WIDTH,
				true, "../resources/img/" + imgSrc + ".png",
				"../resources/img/" + imgSrc + "Over.png");
		searchOption.addClickHandler(clickHandler);
		addMember(searchOption, memberPos++);
		selectables.add(searchOption);
		return searchOption;
	}

	/**
	 * @author alacret
	 * 
	 */
	public static class Option extends Label {
		private final String bgSrc;
		private final String bgSrcOver;
		private boolean selected = false;

		/**
		 * @param tabHeader
		 * @param width
		 * @param hoverable
		 * @param bgSrc
		 * @param bgSrcOver
		 */
		public Option(final GHAPanelHeader tabHeader, int width,
				boolean hoverable, final String bgSrc, final String bgSrcOver) {
			super();
			this.bgSrc = bgSrc;
			this.bgSrcOver = bgSrcOver;
			setStyleName("tab-header-title");
			setWidth(width + "px");
			setHeight("30px");

			// addClickHandler(new ClickHandler() {
			// @Override
			// public void onClick(ClickEvent event) {
			// tabHeader.unMarkAllButtons();
			// markSelected();
			// }
			// });

			if (hoverable) {
				setStyleName(getStyleName() + " button-pointer");
				setBackgroundImage(bgSrc);
				setBackgroundRepeat(BackgroundRepeat.NO_REPEAT);

				addMouseOverHandler(new MouseOverHandler() {
					@Override
					public void onMouseOver(MouseOverEvent event) {
						if (!selected)
							setBackgroundImage(bgSrcOver);
					}
				});
				addMouseOutHandler(new MouseOutHandler() {

					@Override
					public void onMouseOut(MouseOutEvent event) {
						if (!selected)
							setBackgroundImage(bgSrc);
					}
				});
			}
		}

		/**
		 * @param tabHeader
		 * @param text
		 * @param width
		 * @param hoverable
		 * @param bg
		 * @param bgOver
		 */
		public Option(GHAPanelHeader tabHeader, String text, int width,
				boolean hoverable, String bg, String bgOver) {
			this(tabHeader, width, hoverable, bg, bgOver);
			setContents(text);
		}

		/**
		 * 
		 */
		public void unMarkSelected() {
			setBackgroundImage(bgSrc);
			selected = false;
		}

		/**
		 * mark the button as selected
		 */
		public void markSelected() {
			setBackgroundImage(bgSrcOver);
			selected = true;
		}

	}

	@Override
	public void onResize(ResizeEvent event) {
		setWidth(Window.getClientWidth() - 35);
	}

	/**
	 * 
	 */
	public void unMarkAllButtons() {
		for (Option button : selectables)
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
