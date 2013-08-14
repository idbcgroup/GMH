package org.fourgeeks.gha.webclient.client.UI;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

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
	}
	//TextItem Sizes
	public static final int THREE_COLUMN_FORMITEM_SIZE = 150;
	public static final int FOUR_COLUMN_FORMITEM_SIZE = 135;
	
	// NO ESTAN TOTALMENTE MEDIDAS CON LA INTERFAZ
	private static final int MIN_TAB_HEIGHT = 400;
	private static final int MIN_TOP_SECTION_HEIGHT = 120;
	private static final int MIN_BOTTOM_SECTION_HEIGHT = 260;
	// NO ESTAN TOTALMENTE MEDIDAS CON LA INTERFAZ
	
	public static final int MIN_GRID_SIZE = 120;

	public static final int INNER_TOP_SECTION_HEIGHT = 120;
	public static final int V_SEPARATOR_HEIGHT = 10;

	public static VLayout verticalGraySeparator(String height) {
		VLayout separator = new VLayout();
		separator.setWidth100();
		separator.setBackgroundColor("#666666");
		separator.setHeight(height);
		return separator;
	}
	
	public static HLayout horizontalGraySeparator(String width) {
		HLayout separator = new HLayout();
		separator.setHeight100();
		separator.setWidth(width);
		separator.setBackgroundColor("#666666");
		return separator;
	}

	public static int getTabHeight() {
		int rootPanelHeight = Window.getClientHeight();
		int topPartHeight = RootPanel.get("top-bar").getOffsetHeight()
				+ RootPanel.get("header-bar").getOffsetHeight()
				+ RootPanel.get("menu-bar").getOffsetHeight();
		int footerHeight = RootPanel.get("footer-bar").getOffsetHeight();

		int ret = rootPanelHeight - topPartHeight - footerHeight;
		if (ret < MIN_TAB_HEIGHT) {
			return MIN_TAB_HEIGHT;
		} else {
			return ret;
		}
	}

	public static int getBottomSectionHeight() {
		int biggerTabHeight = getTabHeight();
		int innerTopSection = INNER_TOP_SECTION_HEIGHT + V_SEPARATOR_HEIGHT;

		int ret = biggerTabHeight - innerTopSection;
		if (ret < MIN_BOTTOM_SECTION_HEIGHT) {
			return MIN_BOTTOM_SECTION_HEIGHT;
		} else {
			return ret;
		}
	}
	
	public static int getGridSize(int extrasHeight){
		int bottomSectionHeight = getBottomSectionHeight();
		int titleHeight = 30;
		int topExtras = extrasHeight + titleHeight + 35;
		
		int ret= bottomSectionHeight-topExtras;
		if(ret < MIN_GRID_SIZE){
			return MIN_GRID_SIZE;
		}else{
			return ret;
		}
	}
	
	public static int getEDTGridSize(int extrasHeight){
		int tabHeight = getTabHeight();
		int topExtras = extrasHeight + 30 ;
		
		int ret = (tabHeight-topExtras)/2;
		if(ret < MIN_GRID_SIZE){
			return MIN_GRID_SIZE;
		}else{
			return ret;
		}
	}

	public static VLayout createBar(Canvas... buttons) {
		VLayout sideButtons = new VLayout();
		sideButtons.setWidth(30);
		sideButtons.setLayoutMargin(5);
		sideButtons.setMembersMargin(10);
		sideButtons.setDefaultLayoutAlign(Alignment.CENTER);
		sideButtons.addMembers(buttons);
		return sideButtons;
	}

	private static List<ResizeHandler> handlers = new ArrayList<ResizeHandler>();

	public static void addGHAResizeHandler(ResizeHandler handler) {
		handlers.add(handler);
	}

}
