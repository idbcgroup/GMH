package org.fourgeeks.gha.webclient.client.eia.reports;

import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.icons.GHAImgButton;
import org.fourgeeks.gha.webclient.client.UI.interfaces.ClosableListener;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideCloseAction;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideableListener;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHASectionForm;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAVerticalLayout;

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
public class EIAReportsFormPanel extends GHAVerticalLayout implements
		ClosableListener, HideableListener {

	private final String PATH_IMG_PRINT_BUTTON = "../resources/icons/check.png";
	private final String PATH_IMG_CLEAN_BUTTON = "../resources/icons/clean.png";
	private final EIAReportEdoUbicForm edoUbicReportForm;
	private final EIAReportDetalleEquiposForm detalleEquiposReportForm;
	private final EIAReportListEiaTypesEiasForm listEiaTypesEiasReportForm;
	private final EIAReportListEiasForm listEiasReportForm;
	private final EIAReportListEiaTypeCompsForm listEiaTypeCompsReportForm;
	private final GHASectionForm sectionForm;

	/**
	 * Constructor
	 */
	public EIAReportsFormPanel() {
		super();
		setWidth100();
		setMinWidth(1024);
		setBackgroundColor("#E0E0E0");
		setStyleName("sides-padding padding-top");

		// CREO LOS FORMULARIOS
		edoUbicReportForm = new EIAReportEdoUbicForm();
		detalleEquiposReportForm = new EIAReportDetalleEquiposForm();
		listEiaTypesEiasReportForm = new EIAReportListEiaTypesEiasForm();
		listEiasReportForm = new EIAReportListEiasForm();
		listEiaTypeCompsReportForm = new EIAReportListEiaTypeCompsForm();

		// CREO EL SECTION FORM DONDE VAN A IR LOS FORMULARIOS
		sectionForm = new GHASectionForm();
		sectionForm.addSection("Edo. y Ubic.", edoUbicReportForm);
		sectionForm.addSection("Detalles de Equipos", detalleEquiposReportForm);
		sectionForm.addSection("Listado Tipos de Equipo",
				listEiaTypesEiasReportForm);
		sectionForm.addSection("Listado de Equipos", listEiasReportForm);
		sectionForm.addSection("Tipos de Equipo y sus Comp.",
				listEiaTypeCompsReportForm);
		sectionForm.openFirst();

		// CREO EL SIDEBAR
		VLayout sideBarLayout = GHAUiHelper.createBar(
		// boton generar reporte
				new GHAImgButton(PATH_IMG_PRINT_BUTTON, new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						Canvas form = sectionForm.getSelectedOptionForm();
						GHAReportForm reportForm = (GHAReportForm) form;

						String uri = reportForm.getReportURI();
						Window.open(UriUtils.sanitizeUri(uri), "_blank", "");
					}
				}),
				// boton clean
				new GHAImgButton(PATH_IMG_CLEAN_BUTTON, new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						Canvas form = sectionForm.getSelectedOptionForm();
						GHAReportForm reportForm = (GHAReportForm) form;

						reportForm.cleanItems();
					}
				}));

		HLayout formPanel = new HLayout();
		formPanel.addMembers(sectionForm, sideBarLayout);

		addMember(formPanel);
	}

	@Override
	public boolean canBeClosen(HideCloseAction hideAction) {
		return true;
	}

	public boolean canBeHidden(HideCloseAction hideAction) {
		return true;
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
		super.hide();
		sectionForm.deactivate();
	}

	@Override
	public void show() {
		sectionForm.openFirst();
		super.show();
	}

	public void showSelectedSection() {
		sectionForm.openSelectedSection();
	}
}
