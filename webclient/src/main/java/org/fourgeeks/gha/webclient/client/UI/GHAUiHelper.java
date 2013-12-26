package org.fourgeeks.gha.webclient.client.UI;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.webclient.client.UI.icons.GHAImg;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHADynamicForm.FormType;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHALabel;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret, jfuentes
 * 
 */
public abstract class GHAUiHelper {

	static {
		Window.addResizeHandler(new ResizeHandler() {

			@Override
			public void onResize(ResizeEvent event) {
				for (ResizeHandler handler : handlers) {
					if (handler != null)
						handler.onResize(event);
				}
			}
		});

		Element element = RootPanel.get().getElement();
		DOM.sinkEvents(element, Event.MOUSEEVENTS);
		DOM.setEventListener(element, new EventListener() {

			@Override
			public void onBrowserEvent(Event event) {
				if (event.getType().equals("mousemove"))// mouse move
					for (EventListener handler : mouseOverHandlers) {
						if (handler != null)
							handler.onBrowserEvent(event);
					}
				else if (event.getType().equals("mousedown"))// mouse down
					for (EventListener handler : clickHandlers) {
						if (handler != null)
							handler.onBrowserEvent(event);
					}
			}
		});
		// RootPanel.get().addDomHandler(null, null)
	}
	/**
	 * The Header Part Default Heights
	 */
	private static final int HEADER_HEIGTH = 60;
	/**
	 * 
	 */
	public static final int MENU_BAR_HEIGTH = 30;

	// NO ESTAN TOTALMENTE MEDIDAS CON LA INTERFAZ
	private static final int MIN_TAB_HEIGHT = 678;
	private static final int MIN_BOTTOM_SECTION_HEIGHT = 556;

	// NO ESTAN TOTALMENTE MEDIDAS CON LA INTERFAZ

	/**
	 * 
	 */
	public static final int MIN_GRID_SIZE = 120;

	/**
	 * The default Section Form option width.
	 */
	public static final int SECTION_FORM_OPTION_WIDTH = 150;

	/**
	 * The minimum width of a GHADynamicForm that is on a normal panel (for width 100%).
	 */
	public static final int MIN_NORMAL_FORM_WIDTH = 900;
	/**
	 * The minimum width of a GHADynamicForm that is embedded on a GHASectionForm.
	 */
	public static final int MIN_SECTION_FORM_FORM_WIDTH = 700;
	/**
	 * The default top section height.
	 */
	public static final int DEFAULT_TOP_SECTION_HEIGHT = HEADER_HEIGTH
			+ MENU_BAR_HEIGTH + 1;
	/**
	 *  The default height of the inner part of the top section (TopForms and such).
	 */
	public static final int DEFAULT_INNER_TOP_SECTION_HEIGHT = 110;
	/**
	 * Default height of a vertical separator
	 */
	public static final int V_SEPARATOR_HEIGHT = 10;
	/**
	 * The background color for the tabs and components.
	 */
	public static final String DEFAULT_BACKGROUND_COLOR = "#F4F4F4";
	/**
	 * The size of a form item if none is specify
	 */
	@Deprecated
	public static final int FOUR_COLUMN_FORMITEM_SIZE = 135;
	@Deprecated
	public static final int THREE_COLUMN_FORMITEM_SIZE = 150;
	public static final int DEFAULT_ITEM_SIZE = 150;
	/**
	 * The default length for a code item
	 */
	public static final Integer CODE_ITEM_LENGTH = 20;
	/**
	 * 
	 */
	public static final String HIGHLIGHTED_BACKGROUND_COLOR = "#F4F4F4";
	public static final int DEFAULT_TEXT_AREA_ITEM_HEIGHT = 70;
	public static final int DEFAULT_ANIMATION_TIME = 400;
	/**
	 * width for the place eyelash
	 */
	public static final int DEFAULT_PLACE_EYELASH_WIDTH = 120;
	/**
	 * height for the place eyelash
	 */
	public static final int DEFAULT_PLACE_EYELASH_HEIGHT = 30;
	/**
	 * height for the tab eyelash
	 */
	public static final int DEFAULT_TAB_EYELASH_HEIGHT = 30;
	/**
	 * 
	 */
	public static final String DEFAULT_PLACES_BAR_BACKGROUND_COLOR = "#e0e0df";
	/**
	 * Default widht for the header option
	 */
	public static final int DEFAULT_HEADER_OPTION_WIDTH = 90;
	public static final int DEFAULT_TAB_SET_HEIGHT = 30;
	/**
	 * the default height for the pation top component
	 */
	public static final int DEFAULT_PATIENT_TOP_HEIGHT = 100;
	/**
	 * a blank space
	 */
	public static final String BLANK_SPACE = "&nbsp";

	/**
	 * @return the height that a tab must have
	 */
	public static int getTabHeight() {
		int rootPanelHeight = Window.getClientHeight();
		int ret = rootPanelHeight - HEADER_HEIGTH - MENU_BAR_HEIGTH - 5;
		if (ret < MIN_TAB_HEIGHT) {
			return MIN_TAB_HEIGHT;
		} else {
			return ret;
		}
	}

	/**
	 * @return the height for the bottom section
	 */
	public static int getBottomSectionHeight() {
		int biggerTabHeight = getTabHeight();
		int innerTopSection = DEFAULT_INNER_TOP_SECTION_HEIGHT
				+ V_SEPARATOR_HEIGHT;

		int ret = biggerTabHeight - innerTopSection;
		if (ret < MIN_BOTTOM_SECTION_HEIGHT) {
			return MIN_BOTTOM_SECTION_HEIGHT;
		} else {
			return ret;
		}
	}

	/**
	 * @param type 
	 * @param buttonsSize 
	 * @return the width that a form must have, depending on its type.
	 */
	public static int getFormWidth(FormType type, int buttonsSize) {
		int rootPanelWidth = Window.getClientWidth();
		int ret;
		if(type==FormType.NORMAL_FORM){
			ret = rootPanelWidth - buttonsSize - 80;
			if(ret < MIN_NORMAL_FORM_WIDTH)
				return MIN_NORMAL_FORM_WIDTH;
			else
				return ret;
		}else{
			ret = rootPanelWidth - (SECTION_FORM_OPTION_WIDTH + 50)	- buttonsSize - 80;
			if(ret < MIN_SECTION_FORM_FORM_WIDTH)
				return MIN_SECTION_FORM_FORM_WIDTH;
			else
				return ret;
		}		
	}

	/**
	 * @return the Top space
	 */
	public static int getTopSpace() {
		return HEADER_HEIGTH + MENU_BAR_HEIGTH + 1;
	}

	/**
	 * @param extrasHeight
	 * @return the grid size.
	 */
	public static int getGridSize(int extrasHeight) {
		int tabHeight = getTabHeight();
		int titleHeight = 30;
		int topExtras = extrasHeight + titleHeight + 35;

		int ret = tabHeight - topExtras;
		if (ret < MIN_GRID_SIZE) {
			return MIN_GRID_SIZE;
		} else {
			return ret;
		}
	}

	/**
	 * @param extrasHeight
	 * @return the grid size
	 */
	public static int getSubtabGridSize(int extrasHeight) {
		int bottomSectionHeight = getBottomSectionHeight();
		int titleHeight = 30;
		int topExtras = extrasHeight + titleHeight + 35;

		int ret = bottomSectionHeight - topExtras;
		if (ret < MIN_GRID_SIZE) {
			return MIN_GRID_SIZE;
		} else {
			return ret;
		}
	}

	/**
	 * @param extrasHeight
	 * @return
	 */
	public static int getEDTGridSize(int extrasHeight) {
		int tabHeight = getTabHeight();
		int topExtras = extrasHeight + 30;

		int ret = (tabHeight - topExtras) / 2;
		if (ret < MIN_GRID_SIZE) {
			return MIN_GRID_SIZE;
		} else {
			return ret;
		}
	}

	public static VLayout verticalGraySeparator(String height) {
		VLayout separator = new VLayout();
		separator.setWidth100();
		separator.setMinWidth(1024);
		separator.setBackgroundColor("#666666");
		separator.setHeight(height);
		return separator;
	}

	public static VLayout verticalSeparator(String height) {
		VLayout separator = new VLayout();
		separator.setWidth100();
		separator.setMinWidth(1024);
		separator.setHeight(height);
		return separator;
	}

	/**
	 * @param src
	 * @param imgW
	 * @param imgH
	 * @param height
	 * @return
	 */
	public static VLayout verticalGraySeparatorImgBar(String src, int imgW,
			int imgH, int height) {
		GHAImg imgButton = new GHAImg(src, imgW, imgH);
		// imgButton.setStyleName("iconTopPadding");

		VLayout separator = new VLayout();
		separator.setWidth100();
		separator.setMinWidth(1024);
		separator.setHeight(height);
		separator.setDefaultLayoutAlign(Alignment.CENTER);
		separator.setBackgroundColor("#666666");

		separator.addMember(imgButton);

		return separator;
	}

	public static HLayout verticalGraySeparatorLabel(String height, String text) {
		HLayout separator = new HLayout();
		separator.setWidth100();
		separator.setMinWidth(1024);
		separator.setBackgroundColor("#666666");
		separator.setHeight(height);
		separator.setStyleName("sides-padding");

		GHALabel title = new GHALabel(text);
		title.setHeight(height);
		title.setStyleName("separator-title-label");

		separator.addMember(title);

		return separator;
	}

	/**
	 * @param width
	 * @return an horizontal gray separator
	 */
	public static HLayout horizontalGraySeparator(String width) {
		HLayout separator = new HLayout();
		separator.setHeight100();
		separator.setWidth(width);
		separator.setBackgroundColor("#666666");
		return separator;
	}

	/**
	 * @param buttons
	 * @return the bar with the buttons
	 */
	public static VLayout createBar(Canvas... buttons) {
		VLayout sideButtons = new VLayout();
		sideButtons.setWidth(30);
		sideButtons.setLayoutMargin(5);
		sideButtons.setMembersMargin(10);
		sideButtons.setDefaultLayoutAlign(Alignment.CENTER);
		if (buttons.length > 0)
			sideButtons.addMembers(buttons);
		return sideButtons;
	}

	private static List<ResizeHandler> handlers = new ArrayList<ResizeHandler>();

	/**
	 * @param handler
	 */
	public static void addGHAResizeHandler(ResizeHandler handler) {
		handlers.add(handler);
	}

	/**
	 * @param handler
	 */
	public static void removeGHAResizeHandler(ResizeHandler handler) {
		handlers.remove(handler);
	}

	private static List<EventListener> mouseOverHandlers = new ArrayList<EventListener>();

	/**
	 * Adds a click handler to the document mouse over event
	 * 
	 * @param handler
	 */
	public static void addDocumentMouseOverHandler(EventListener handler) {
		mouseOverHandlers.add(handler);
	}

	/**
	 * removes a click handler from the document mouse over event
	 * 
	 * @param handler
	 */
	public static void removeDocumentMouseOverHandler(EventListener handler) {
		mouseOverHandlers.remove(handler);
	}

	private static List<EventListener> clickHandlers = new ArrayList<EventListener>();
	public static int DEFAULT_TAB_HEADER_WIDTH = 250;

	/**
	 * Adds a click handler to the document click event
	 * 
	 * @param handler
	 */
	public static void addDocumentClickHandler(EventListener handler) {
		clickHandlers.add(handler);
	}

	/**
	 * removes a click handler from the document click event
	 * 
	 * @param handler
	 */
	public static void removeDocumentClickHandler(EventListener handler) {
		clickHandlers.remove(handler);
	}

}
