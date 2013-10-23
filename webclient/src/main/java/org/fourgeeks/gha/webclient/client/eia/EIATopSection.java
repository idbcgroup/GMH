package org.fourgeeks.gha.webclient.client.eia;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASpacerItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.interfaces.GHAClosable;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAImgButton;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHATabSet;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret
 * 
 */
public class EIATopSection extends HLayout implements EIASelectionListener,
		GHAClosable, ResizeHandler {

	private final EIATab eIATab;
	private EIASearchForm eiaSearchForm;
	private GHATextItem eiaType, code, serialNumber, state,
			fixedAssetIdentifier, workingArea, facility, responsibleRole, obu,
			acceptationDate, installationDate;

	{
		eiaSearchForm = new EIASearchForm();

		eiaType = new GHATextItem("Tipo de equipo", 120, false);
		code = new GHATextItem("Código", 120, false);
		serialNumber = new GHATextItem("Serial", 120, false);
		fixedAssetIdentifier = new GHATextItem("Identificador", 120, false);
		state = new GHATextItem("Estado", 120, false);
		workingArea = new GHATextItem("Area de trabajo", 120, false);
		facility = new GHATextItem("Facilidad", 120, false);
		obu = new GHATextItem("Organización", 120, false);
		responsibleRole = new GHATextItem("Responsable", 120, false);
		installationDate = new GHATextItem("Instalación", 120, false);
		acceptationDate = new GHATextItem("Recibido en", 120, false);

	}

	/**
	 * @param eiaTab
	 */
	public EIATopSection(EIATab eiaTab) {
		super();
		GHAUiHelper.addGHAResizeHandler(this);
		eiaTab.addGHAClosableHandler(this);
		eiaTab.addEiaSelectionListener(this);
		eIATab = eiaTab;

		eiaSearchForm.addEiaSelectionListener(eIATab);
		eIATab.addGHAHideableHandler(eiaSearchForm);
		eIATab.addGHAClosableHandler(eiaSearchForm);

		setStyleName("sides-padding padding-top");// Esto es VUDU!
		setWidth100();
		setHeight(GHAUiHelper.DEFAULT_TOP_SECTION_HEIGHT + "px");
		setDefaultLayoutAlign(VerticalAlignment.CENTER);
		setBackgroundColor("#EAEAEA");

		DynamicForm form = new DynamicForm();
		// form.setWidth("100px");
		form.setTitleOrientation(TitleOrientation.TOP);
		form.setNumCols(6);
		form.setItems(eiaType, code, serialNumber, fixedAssetIdentifier, state,
				new GHASpacerItem(), workingArea, facility, obu,
				responsibleRole, acceptationDate, installationDate);

		// Panel de la Fotografia Equipos

		// HLayout photoPanel = new HLayout();
		// photoPanel.setMembersMargin(10);
		// photoPanel.setWidth(130);
		// // photoPanel.setDefaultLayoutAlign(Alignment.CENTER);
		// GHAImg photo = new GHAImg("../resources/img/Foto.jpg", 80, 80);
		// // photo.setTop(8);
		// photo.setStyleName("top-8");
		//
		// VLayout photoBotones = new VLayout();
		// photoBotones.setWidth(30);
		// photoBotones.setLayoutMargin(5);
		// photoBotones.setMembersMargin(10);
		// photoBotones.setDefaultLayoutAlign(Alignment.CENTER);
		//
		// GHAImgButton searchPhoto = new GHAImgButton(
		// "../resources/icons/search.png");
		// // GHAButton cleanPhoto = new
		// GHAButton("../resources/icons/clean.png");
		// photoBotones.addMembers(searchPhoto);
		//
		// photoPanel.addMembers(photo, photoBotones);

		VLayout sideButtons = GHAUiHelper.createBar(new GHAImgButton(
				"../resources/icons/search.png", new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						search();
					}
				}), new GHAImgButton("../resources/icons/cancel.png",
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						GHATabSet.closeTab(eIATab);
					}
				}));

		addMembers(form, /* photoPanel, */new LayoutSpacer(), sideButtons);

	}

	/**
	 * 
	 */
	public void search() {
		eiaSearchForm.open();
	}

	@Override
	public void close() {

	}

	@Override
	public void onResize(ResizeEvent event) {
		setHeight(GHAUiHelper.DEFAULT_TOP_SECTION_HEIGHT + "px");
	}

	@Override
	public void select(Eia eia) {
		if (eia.getEiaType() != null) {
			eiaType.setValue(eia.getEiaType().getName());
		}

		code.setValue(eia.getCode());
		serialNumber.setValue(eia.getSerialNumber());
		fixedAssetIdentifier.setValue(eia.getFixedAssetIdentifier());
		state.setValue(eia.getState().toString());

		if (eia.getWorkingArea() != null)
			workingArea.setValue(eia.getWorkingArea().getName());

		if (eia.getFacility() != null)
			facility.setValue(eia.getFacility().getName());

		if (eia.getObu() != null)
			obu.setValue(eia.getObu().getName());

		if (eia.getResponsibleRole() != null)
			responsibleRole.setValue(eia.getResponsibleRole().getName());

		installationDate.setValue(eia.getInstallationDate());
		acceptationDate.setValue(eia.getAcceptationDate());

	}

	@Override
	public boolean canBeClosen() {
		// TODO Auto-generated method stub
		return false;
	}
}