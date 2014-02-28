package org.fourgeeks.gha.webclient.client.UI.tabs;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.panels.GHAHeaderOption;

import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;

/**
 * @author alacret
 * 
 */
final class GHATabSet extends HLayout {

	private final List<GHATabHeader> headers = new ArrayList<GHATabHeader>();
	private int optionPos = 1;
	private int tabPos = 0;

	// TODO, see wheter the tabPanel is necesary
	public GHATabSet(GHATabbedPanel tabPanel) {
		super();
		setHeight(30);
		setStyleName("gha-tab-set");
		addMember(new LayoutSpacer());
	}

	/**
	 * @param title
	 * @param tabbedPanel
	 * 
	 */
	public GHATabSet(String title, GHATabbedPanel tabbedPanel) {
		this(tabbedPanel);
		final Label label = new Label();
		label.setContents(title);
		label.setStyleName("tabset-header-title");
		label.setWidth("150px");
		label.setHeight("30px");
		addMember(label, 0);
		tabPos++;
		optionPos++;
	}

	public void add(GHATabHeader header) {
		headers.add(header);
		addMember(header, tabPos++);
	}

	/**
	 * @param text
	 * @param clickHandler
	 * @param imgSrc
	 */
	public void addOption(String text, String imgSrc, ClickHandler clickHandler) {
		final GHAHeaderOption tabSetOption = new GHAHeaderOption(text + "...",
				GHAUiHelper.DEFAULT_HEADER_OPTION_WIDTH, true,
				"../resources/img/" + imgSrc + ".png", "../resources/img/"
						+ imgSrc + "Over.png");
		tabSetOption.setStyleName("tab-header-title-large");
		tabSetOption.addClickHandler(clickHandler);
		tabSetOption.unMarkSelected();
		addMember(tabSetOption, tabPos + optionPos++);
	}
}