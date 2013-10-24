package org.fourgeeks.gha.webclient.client.eia;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASpacerItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.icons.GHAImgButton;
import org.fourgeeks.gha.webclient.client.UI.interfaces.GHAClosable;
import org.fourgeeks.gha.webclient.client.UI.interfaces.GHAHideable;

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
 * @author alacret, emiliot
 * 
 */
public class EIATopForm extends HLayout implements EIASelectionListener,
		GHAClosable, GHAHideable, ResizeHandler {

	private GHATextItem eiaType, code, serialNumber, state,
			fixedAssetIdentifier, workingArea, facility, responsibleRole, obu,
			acceptationDate, installationDate;

	private EiaResultSet resultSet;
	private boolean activated = false;

	{
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
	public EIATopForm(EiaResultSet resultSet) {
		super();
		this.resultSet = resultSet;

		GHAUiHelper.addGHAResizeHandler(this);
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
				}));

		addMembers(form, /* photoPanel, */new LayoutSpacer(), sideButtons);

	}

	public void activate() {
		activated = true;
	}

	@Override
	public boolean canBeClosen() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.UI.interfaces.GHAHideable#canBeHidden
	 * ()
	 */
	@Override
	public boolean canBeHidden() {
		return true;
	}

	@Override
	public void close() {
		destroy();
	}

	public void deactivate() {
		activated = false;
	}

	public void disableFields() {
		// TODO: Disable fields in the topform
	}

	public void enableFields() {
		// TODO: Enable topform fields
	}

	public boolean isActivated() {
		return activated;
	}

	@Override
	public void onResize(ResizeEvent event) {
		setHeight(GHAUiHelper.DEFAULT_TOP_SECTION_HEIGHT + "px");
	}

	/**
	 * 
	 */
	public void search() {
		Eia eia = new Eia();
		if (eiaType.getValue() != null)
			eia.setEiaType(new EiaType(eiaType.getValueAsString()));
		if (code.getValue() != null)
			eia.setCode(code.getValueAsString());
		if (serialNumber.getValue() != null)
			eia.setSerialNumber(serialNumber.getValueAsString());
		if (fixedAssetIdentifier != null)
			eia.setFixedAssetIdentifier(fixedAssetIdentifier.getValueAsString());

		// // if (actualCostItem.getValue() != null)
		// // eia.setActualCost(new
		// BigDecimal(actualCostItem.getValueAsString()));
		// if (responsibleRoleItem.getValue() != null)
		// eia.setResponsibleRole(new Role(Long.parseLong(responsibleRoleItem
		// .getValueAsString())));
		// if (fixedAssetIdentifierItem.getValue() != null)
		// eia.setFixedAssetIdentifier(fixedAssetIdentifierItem
		// .getValueAsString());
		// if (workingAreaLocationItem.getValue() != null)
		// eia.setWorkingArea(new WorkingArea(Integer
		// .valueOf(workingAreaLocationItem.getValueAsString())));
		// if (facilityLocationItem.getValue() != null)
		// eia.setFacility(new Facility(Integer.valueOf(facilityLocationItem
		// .getValueAsString())));
		// if (obuItem.getValue() != null)
		// eia.setObu(new Obu(Long.parseLong(obuItem.getValueAsString())));
		// if (stateItem.getValue() != null)
		// eia.setState(EiaStateEnum.valueOf(stateItem.getValueAsString()));
		// else
		// eia.setState(null);
		// search(eia);

		// state = new GHATextItem("Estado", 120, false);
		// workingArea = new GHATextItem("Area de trabajo", 120, false);
		// facility = new GHATextItem("Facilidad", 120, false);
		// obu = new GHATextItem("Organización", 120, false);
		// responsibleRole = new GHATextItem("Responsable", 120, false);
		// installationDate = new GHATextItem("Instalación", 120, false);
		// acceptationDate = new GHATextItem("Recibido en", 120, false);

	}

	public void search(Eia eia) {
		// TODO
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

		// lock fields of the topform
		disableFields();
	}
}