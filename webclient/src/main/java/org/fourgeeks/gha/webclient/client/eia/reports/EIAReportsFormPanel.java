package org.fourgeeks.gha.webclient.client.eia.reports;

import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.interfaces.GHAClosable;
import org.fourgeeks.gha.webclient.client.UI.interfaces.GHAHideable;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAImgButton;

import com.google.gwt.safehtml.shared.UriUtils;
import com.google.gwt.user.client.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author naramirez
 * 
 */
public class EIAReportsFormPanel extends VLayout implements GHAClosable,
		GHAHideable {

	private final String PATH_IMG_PRINT_BUTTON = "../resources/icons/check.png";
	private final String PATH_IMG_CLEAN_BUTTON = "../resources/icons/clean.png";
	private final EIAReportsForm formReports;

	/**
	 * Constructor
	 */
	public EIAReportsFormPanel() {
		super();
		setWidth100();
		setBackgroundColor("#E0E0E0");
		setStyleName("sides-padding padding-top");

		// CREO EL FORMULARIO
		formReports = new EIAReportsForm();

		// CREO EL SIDEBAR
		VLayout sideBarLayout = GHAUiHelper.createBar(// boton generar reporte
				new GHAImgButton(PATH_IMG_PRINT_BUTTON, new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						String params = formReports.getURLParameters();

						String uri = "webclient/reporteia" + params;
						Window.open(UriUtils.sanitizeUri(uri), "_blank", "");
					}
				}), // boton clean
				new GHAImgButton(PATH_IMG_CLEAN_BUTTON, new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						formReports.cleanItems();
					}
				}));

		HLayout formPanel = new HLayout();
		formPanel.addMembers(formReports, sideBarLayout);

		addMember(formPanel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.UI.interfaces.GHAClosable#close()
	 */
	@Override
	public void close() {
		// TODO Auto-generated method stub

	}
}
