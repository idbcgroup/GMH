package org.fourgeeks.gha.webclient.client.eia.reports;

import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.interfaces.GHAClosable;
import org.fourgeeks.gha.webclient.client.UI.interfaces.GHAHideable;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAImgButton;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHASectionForm;

import com.google.gwt.safehtml.shared.UriUtils;
import com.google.gwt.user.client.Window;
import com.smartgwt.client.widgets.Canvas;
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
	private final EIAReportEdoUbicForm edoUbicReportForm;
	private final EIAReportDetalleEquiposForm detalleEquiposReportForm;
	private final GHASectionForm secciones;

	/**
	 * Constructor
	 */
	public EIAReportsFormPanel() {
		super();
		setWidth100();
		setBackgroundColor("#E0E0E0");
		setStyleName("sides-padding padding-top");

		// CREO LOS FORMULARIO
		edoUbicReportForm = new EIAReportEdoUbicForm();
		detalleEquiposReportForm = new EIAReportDetalleEquiposForm();

		// CREO EL SECTION FORM DONDE VAN A IR LOS FORMULARIOS
		secciones = new GHASectionForm();
		secciones.addSection("Edo. y Ubic.", edoUbicReportForm);
		secciones.addSection("Detalles de Equipos", detalleEquiposReportForm);
		secciones.openFirst();

		// CREO EL SIDEBAR
		VLayout sideBarLayout = GHAUiHelper.createBar(
		// boton generar reporte
				new GHAImgButton(PATH_IMG_PRINT_BUTTON, new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						Canvas form = secciones.getSelectedOptionForm();
						GHAReportForm reportForm = (GHAReportForm) form;

						String uri = reportForm.getReportURI();
						Window.open(UriUtils.sanitizeUri(uri), "_blank", "");
					}
				}),
				// boton clean
				new GHAImgButton(PATH_IMG_CLEAN_BUTTON, new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						Canvas form = secciones.getSelectedOptionForm();
						GHAReportForm reportForm = (GHAReportForm) form;

						reportForm.cleanItems();
					}
				}));

		HLayout formPanel = new HLayout();
		formPanel.addMembers(secciones, sideBarLayout);

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

	@Override
	public void hide() {
		// super.hide();
		secciones.deactivate();
	}
}
