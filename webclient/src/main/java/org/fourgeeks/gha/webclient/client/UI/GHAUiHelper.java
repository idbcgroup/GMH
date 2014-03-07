package org.fourgeeks.gha.webclient.client.UI;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.webclient.client.UI.icons.GHAImg;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHADynamicForm.FormType;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHALabel;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.Window.ScrollEvent;
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
		Window.addWindowScrollHandler(new Window.ScrollHandler() {

			@Override
			public void onWindowScroll(ScrollEvent event) {
				for (final Window.ScrollHandler handler : scrollHandlers) {
					if (handler != null)
						handler.onWindowScroll(event);
				}
			}
		});
		Window.addResizeHandler(new ResizeHandler() {

			@Override
			public void onResize(ResizeEvent event) {
				for (final ResizeHandler handler : resizeHandlers) {
					if (handler != null)
						handler.onResize(event);
				}
			}
		});

		final Element element = RootPanel.get().getElement();
		DOM.sinkEvents(element, Event.MOUSEEVENTS);
		DOM.setEventListener(element, new EventListener() {

			@Override
			public void onBrowserEvent(Event event) {
				if (event.getType().equals("mousemove"))// mouse move
					for (final EventListener handler : mouseOverHandlers) {
						if (handler != null)
							handler.onBrowserEvent(event);
					}
				else if (event.getType().equals("mousedown"))// mouse down
					for (final EventListener handler : clickHandlers) {
						if (handler != null)
							handler.onBrowserEvent(event);
					}
			}
		});
		// RootPanel.get().addDomHandler(null, null)
	}

	/**
	 * 
	 */
	public static final int MIN_WIDTH = 1024;
	/**
	 * 
	 */
	public static final int MIN_HEIGHT = 768;

	/**
	 * The Header Part Default Heights
	 */
	public static final int HEADER_HEIGTH = 60;
	/**
	 * 
	 */
	public static final int MENU_BAR_HEIGTH = 30;

	// NO ESTAN TOTALMENTE MEDIDAS CON LA INTERFAZ
	private static final int MIN_TAB_HEIGHT = 678;
	private static final int MIN_PANEL_HEIGHT = 648;
	private static final int MIN_BOTTOM_SECTION_HEIGHT = 528;
	private static final int MIN_RES_BODY_HEIGHT = 576;
	private static final int MIN_RESULT_SET_HEIGHT = 360;

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
	 * The minimum width of a GHADynamicForm that is on a normal panel (for
	 * width 100%).
	 */
	public static final int MIN_NORMAL_FORM_WIDTH = 900;
	/**
	 * The minimum width of a GHADynamicForm that is embedded on a
	 * GHASectionForm.
	 */
	public static final int MIN_SECTIONFORM_FORM_WIDTH = 700;
	/**
	 * The minimum width of a small-sized GHADynamicForm that is embedded on a
	 * GHASectionForm.
	 */
	public static final int MIN_SECTIONFORM_MINIFORM_WIDTH = 580;
	/**
	 * The default top header height (with the tabs bar).
	 */
	public static final int DEFAULT_TOP_HEADER_TAB_HEIGHT = HEADER_HEIGTH
			+ MENU_BAR_HEIGTH;
	/**
	 * The default top header height (with the tabs and the panel bars).
	 */
	public static final int DEFAULT_TOP_HEADER_PANEL_HEIGHT = HEADER_HEIGTH
			+ MENU_BAR_HEIGTH + MENU_BAR_HEIGTH;
	/**
	 * The default height of the inner part of the top section (TopForms and
	 * such).
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
	/**
	 * 
	 */
	@Deprecated
	public static final int THREE_COLUMN_FORMITEM_SIZE = 150;
	/**
	 * 
	 */
	public static final int DEFAULT_ITEM_SIZE = 150;
	/**
	 * The default length for a code item
	 */
	public static final Integer CODE_ITEM_LENGTH = 20;
	/**
	 * 
	 */
	public static final String HIGHLIGHTED_BACKGROUND_COLOR = "#F4F4F4";
	/**
	 * 
	 */
	public static final int DEFAULT_TEXT_AREA_ITEM_HEIGHT = 70;
	/**
	 * 
	 */
	public static final int DEFAULT_ANIMATION_TIME = 400;
	/**
	 * width for the place eyelash
	 */
	public static final int DEFAULT_PLACE_EYELASH_WIDTH = 150;
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
	public static final String DEFAULT_PLACES_BAR_BACKGROUND_COLOR = "#e0e0e0";
	/**
	 * 
	 */
	public static final String DEFAULT_PANEL_BAR_BACKGROUND_COLOR = "#ECECEC";
	/**
	 * Default widht for the header option
	 */
	public static final int DEFAULT_HEADER_OPTION_WIDTH = 90;
	/**
	 * 
	 */
	public static final int DEFAULT_TAB_SET_HEIGHT = 30;
	/**
	 * the default height for the patient top component.
	 */
	public static final int DEFAULT_PATIENT_TOP_HEIGHT = 72;
	/**
	 * the default height for the patient basic information.
	 */
	public static final int DEFAULT_PATIENT_BASIC_INFO_HEIGHT = 190;
	/**
	 * the default height for the patient extra information.
	 */
	public static final int DEFAULT_PATIENT_MINIGRID_HEIGHT = 120;
	/**
	 * a blank space
	 */
	@Deprecated
	public static final String BLANK_SPACE = "&nbsp";
	/**
	 * 
	 */
	public static final int DEFAULT_HEADER_OPTION_HEIGHT = 30;

	private static List<ResizeHandler> resizeHandlers = new ArrayList<ResizeHandler>();

	private static List<EventListener> mouseOverHandlers = new ArrayList<EventListener>();

	private static List<EventListener> clickHandlers = new ArrayList<EventListener>();

	private static List<Window.ScrollHandler> scrollHandlers = new ArrayList<Window.ScrollHandler>();

	/**
	 * 
	 */
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
	 * Adds a click handler to the document mouse over event
	 * 
	 * @param handler
	 */
	public static void addDocumentMouseOverHandler(EventListener handler) {
		mouseOverHandlers.add(handler);
	}

	/**
	 * @param handler
	 */
	public static void addGHAResizeHandler(ResizeHandler handler) {
		resizeHandlers.add(handler);
	}

	/**
	 * @param scrollHandler
	 */
	public static void addWindowScrollHandler(Window.ScrollHandler scrollHandler) {
		scrollHandlers.add(scrollHandler);
	}

	/**
	 * @param buttons
	 * @return the bar with the buttons
	 */
	public static VLayout createBar(Canvas... buttons) {
		final VLayout sideButtons = new VLayout();
		sideButtons.setWidth(30);
		sideButtons.setLayoutMargin(5);
		sideButtons.setMembersMargin(10);
		sideButtons.setDefaultLayoutAlign(Alignment.CENTER);
		if (buttons.length > 0)
			sideButtons.addMembers(buttons);
		return sideButtons;
	}

	/**
	 * @return the height for the bottom section
	 */
	public static int getBottomSectionHeight() {
		final int biggerTabHeight = getPanelHeight();
		final int innerTopSection = DEFAULT_INNER_TOP_SECTION_HEIGHT
				+ V_SEPARATOR_HEIGHT;

		final int ret = biggerTabHeight - innerTopSection;
		if (ret < MIN_BOTTOM_SECTION_HEIGHT) {
			return MIN_BOTTOM_SECTION_HEIGHT;
		} else {
			return ret;
		}
	}

	/**
	 * @param extrasHeight
	 * @return the height of the grid.
	 */
	public static int getEDTGridSize(int extrasHeight) {
		final int tabHeight = getPanelHeight();
		final int topExtras = extrasHeight + 30;

		final int ret = (tabHeight - topExtras) / 2;
		if (ret < MIN_GRID_SIZE) {
			return MIN_GRID_SIZE;
		} else {
			return ret;
		}
	}

	/**
	 * @param type
	 * @param extraSpace
	 * @return the width that a form must have, depending on its type.
	 */
	public static int getFormWidth(FormType type, int extraSpace) {
		final int rootPanelWidth = Window.getClientWidth();
		int ret;
		if (type == FormType.NORMAL_FORM) {
			ret = rootPanelWidth - extraSpace;
			if (ret < MIN_NORMAL_FORM_WIDTH)
				return MIN_NORMAL_FORM_WIDTH;
			else
				return ret;
		} else if (type == FormType.SECTIONFORM_FORM) {
			ret = rootPanelWidth - (SECTION_FORM_OPTION_WIDTH + 50)
					- extraSpace;
			if (ret < MIN_SECTIONFORM_FORM_WIDTH)
				return MIN_SECTIONFORM_FORM_WIDTH;
			else
				return ret;
		} else {
			ret = rootPanelWidth - (SECTION_FORM_OPTION_WIDTH + 50)
					- extraSpace;
			if (ret < MIN_SECTIONFORM_MINIFORM_WIDTH)
				return MIN_SECTIONFORM_MINIFORM_WIDTH;
			else
				return ret;
		}
	}

	/**
	 * @param extrasHeight
	 * @return the grid size.
	 */
	public static int getGridSize(int extrasHeight) {
		final int tabHeight = getPanelHeight();
		final int titleHeight = 30;
		final int topExtras = extrasHeight + titleHeight + 35;

		final int ret = tabHeight - topExtras;
		if (ret < MIN_GRID_SIZE) {
			return MIN_GRID_SIZE;
		} else {
			return ret;
		}
	}

	/**
	 * @return the height that a Panel must have
	 */
	public static int getPanelHeight() {
		final int tabHeight = getTabHeight();
		final int ret = tabHeight - MENU_BAR_HEIGTH;
		if (ret < MIN_PANEL_HEIGHT) {
			return MIN_PANEL_HEIGHT;
		} else {
			return ret;
		}
	}

	/**
	 * @return the height for the bottom section
	 */
	public static int getRESBodyHeight() {
		final int biggerTabHeight = getPanelHeight();
		final int innerTopSection = DEFAULT_PATIENT_TOP_HEIGHT + 25;

		final int ret = biggerTabHeight - innerTopSection;
		if (ret < MIN_RES_BODY_HEIGHT) {
			return MIN_RES_BODY_HEIGHT;
		} else {
			return ret;
		}
	}

	/**
	 * @param type
	 * @return the grid size
	 */
	public static int getResultSetGridSize(ResultSetContainerType type) {
		final int resultSetHeight = getResultSetHeight(type);
		final int titleHeight = 60;

		final int ret = resultSetHeight - (titleHeight + 20);
		if (ret < MIN_GRID_SIZE) {
			return MIN_GRID_SIZE;
		} else {
			return ret;
		}
	}

	/**
	 * 
	 * @param type
	 * @return the height for the bottom section of a SearchForm (internal
	 *         bottom section)
	 */
	public static int getResultSetHeight(ResultSetContainerType type) {
		int containerHeight;
		int innerTopSection;

		int ret;
		if (type == ResultSetContainerType.SEARCH_FORM) {
			containerHeight = getBottomSectionHeight();
			innerTopSection = DEFAULT_INNER_TOP_SECTION_HEIGHT
					+ V_SEPARATOR_HEIGHT;
			ret = containerHeight - innerTopSection - 42;
			if (ret < MIN_RESULT_SET_HEIGHT) {
				// Window.alert("Searchform result set(minimun):"+MIN_RESULT_SET_HEIGHT);
				return MIN_RESULT_SET_HEIGHT;
			} else {
				// Window.alert("Bottom section height:"+containerHeight+" Searchform result set:"+ret);
				return ret;
			}
		} else {
			ret = getBottomSectionHeight() - 42;
			if (ret < (MIN_BOTTOM_SECTION_HEIGHT - 10)) {
				// Window.alert("Bottomsection result set(minimun):"+(MIN_BOTTOM_SECTION_HEIGHT
				// - 10));
				return (MIN_BOTTOM_SECTION_HEIGHT - 10);
			} else {
				// Window.alert("Bottomsection result set:"+ret);
				return ret;
			}
		}
	}

	/**
	 * @param extrasHeight
	 * @return the grid size
	 */
	public static int getSubtabGridSize(int extrasHeight) {
		final int bottomSectionHeight = getBottomSectionHeight();
		final int titleHeight = 30;
		final int topExtras = extrasHeight + titleHeight + 35;

		final int ret = bottomSectionHeight - topExtras;
		if (ret < MIN_GRID_SIZE) {
			return MIN_GRID_SIZE;
		} else {
			return ret;
		}
	}

	/**
	 * @return the height that a tab must have
	 */
	public static int getTabHeight() {
		final int rootPanelHeight = Window.getClientHeight();
		final int ret = rootPanelHeight - DEFAULT_TOP_HEADER_TAB_HEIGHT;
		if (ret < MIN_TAB_HEIGHT) {
			return MIN_TAB_HEIGHT;
		} else {
			return ret;
		}
	}

	/**
	 * @return the Top space
	 */
	public static int getTopSpace() {
		return (DEFAULT_TOP_HEADER_PANEL_HEIGHT
				+ DEFAULT_INNER_TOP_SECTION_HEIGHT + V_SEPARATOR_HEIGHT);
	}

	/**
	 * @param width
	 * @return an horizontal gray separator
	 */
	public static HLayout horizontalGraySeparator(String width) {
		final HLayout separator = new HLayout();
		separator.setHeight100();
		separator.setWidth(width);
		separator.setBackgroundColor("#666666");
		return separator;
	}

	/**
	 * removes a click handler from the document click event
	 * 
	 * @param handler
	 */
	public static void removeDocumentClickHandler(EventListener handler) {
		clickHandlers.remove(handler);
	}

	/**
	 * removes a click handler from the document mouse over event
	 * 
	 * @param handler
	 */
	public static void removeDocumentMouseOverHandler(EventListener handler) {
		mouseOverHandlers.remove(handler);
	}

	/**
	 * @param handler
	 */
	public static void removeGHAResizeHandler(ResizeHandler handler) {
		resizeHandlers.remove(handler);
	}

	/**
	 * @param scrollHandler
	 */
	public static void removeWindowScrollHandler(
			Window.ScrollHandler scrollHandler) {
		scrollHandlers.remove(scrollHandler);
	}

	/**
	 * @param height
	 * @return the separator
	 */
	public static VLayout verticalGraySeparator(String height) {
		final VLayout separator = new VLayout();
		separator.setWidth100();
		separator.setMinWidth(MIN_WIDTH);
		separator.setBackgroundColor("#666666");
		separator.setHeight(height);
		return separator;
	}

	/**
	 * @param src
	 * @param imgW
	 * @param imgH
	 * @param height
	 * @return the vertical layout
	 */
	public static VLayout verticalGraySeparatorImgBar(String src, int imgW,
			int imgH, int height) {
		final GHAImg imgButton = new GHAImg(src, imgW, imgH);
		// imgButton.setStyleName("iconTopPadding");

		final VLayout separator = new VLayout();
		separator.setWidth100();
		separator.setMinWidth(MIN_WIDTH);
		separator.setHeight(height);
		separator.setDefaultLayoutAlign(Alignment.CENTER);
		separator.setBackgroundColor("#666666");

		separator.addMember(imgButton);

		return separator;
	}

	/**
	 * @param height
	 * @param text
	 * @return the separator label.
	 */
	public static HLayout verticalGraySeparatorLabel(String height, String text) {
		final HLayout separator = new HLayout();
		separator.setWidth100();
		separator.setMinWidth(MIN_WIDTH);
		separator.setBackgroundColor("#666666");
		separator.setHeight(height);
		separator.setStyleName("sides-padding");

		final GHALabel title = new GHALabel(text);
		title.setHeight(height);
		title.setStyleName("separator-title-label");

		separator.addMember(title);

		return separator;
	}

	/**
	 * @param height
	 * @return the separator
	 */
	public static VLayout verticalSeparator(String height) {
		final VLayout separator = new VLayout();
		separator.setWidth100();
		separator.setMinWidth(MIN_WIDTH);
		separator.setHeight(height);
		return separator;
	}
}
