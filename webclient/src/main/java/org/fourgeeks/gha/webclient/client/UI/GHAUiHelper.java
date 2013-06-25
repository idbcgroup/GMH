package org.fourgeeks.gha.webclient.client.UI;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;

public abstract class GHAUiHelper{
	
	static{
		Window.addResizeHandler(new ResizeHandler() {
			
			@Override
			public void onResize(ResizeEvent event) {
				for (ResizeHandler handler : handlers) {
					if(handler != null)
						handler.onResize(event);
				}
				
			}
		});
	}

//	NOT TESTED
	private static final int MIN_TAB_HEIGHT = 400;
	private static final int MIN_TOP_SECTION_HEIGHT = 120;
	private static final int MIN_BOTTOM_SECTION_HEIGHT = 260;
//	NOT TESTED
	
	public static final int INNER_TOP_SECTION_HEIGHT = 120;
	public static final int V_SEPARATOR_HEIGHT = 10;
	

	public static HTML verticalGraySeparator(String height) {
		HTML separator = new HTML();
		separator.setStylePrimaryName("vertical-gray-separator");
		separator.setHeight(height);
		return separator;
	}

	public static int calculateTabHeight() {
		int rootPanelHeight = Window.getClientHeight();
		int topPartHeight = RootPanel.get("top-bar").getOffsetHeight()
				+ RootPanel.get("header-bar").getOffsetHeight()
				+ RootPanel.get("menu-bar").getOffsetHeight();
		int footerHeight = RootPanel.get("footer-bar").getOffsetHeight();

		int ret = rootPanelHeight - topPartHeight - footerHeight;
		if(ret < MIN_TAB_HEIGHT)
			return MIN_TAB_HEIGHT;
		else
			return ret;
	}

	public static int getBottomSectionHeight() {
		int biggerTabHeight = calculateTabHeight();
		int innerTopSection = INNER_TOP_SECTION_HEIGHT + V_SEPARATOR_HEIGHT + 34;

		int ret = biggerTabHeight - innerTopSection;
		if(ret < MIN_BOTTOM_SECTION_HEIGHT)
			return MIN_BOTTOM_SECTION_HEIGHT;
		else
			return ret;
	}
	
	private static List<ResizeHandler> handlers = new ArrayList<ResizeHandler>();
	
	public static void addResizeHandler(ResizeHandler handler){
		handlers.add(handler);
	}

	
}
