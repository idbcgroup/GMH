package org.fourgeeks.gha.webclient.client.eia;

import java.sql.Date;
import java.util.List;

import org.fourgeeks.gha.domain.enu.EiaStateEnum;
import org.fourgeeks.gha.domain.ess.Role;
import org.fourgeeks.gha.domain.ess.WorkingArea;
import org.fourgeeks.gha.domain.gar.Facility;
import org.fourgeeks.gha.domain.gar.Obu;
import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHACodeItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHADateItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHAEiaStateSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHAEiaTypeSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHAFacilitySelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHAObuSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHARoleSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASpacerItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHAWorkingAreaSelectItem;
import org.fourgeeks.gha.webclient.client.UI.icons.GHACleanButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHADeleteButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHAImgButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHASearchButton;
import org.fourgeeks.gha.webclient.client.UI.interfaces.GHAClosable;
import org.fourgeeks.gha.webclient.client.UI.interfaces.GHAHideable;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHANotification;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.util.BooleanCallback;
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
	private long selectedEiaId;

	private GHATextItem serialNumber, fixedAssetIdentifier;
	private GHACodeItem codeItem;
	private GHAEiaStateSelectItem stateSelectItem;
	private GHAObuSelectItem obuSelectItem;
	private GHARoleSelectItem baseRoleSelectItem;
	private GHAWorkingAreaSelectItem workingAreaLocationSelectItem;
	private GHAFacilitySelectItem facilityLocationSelectItem;
	private GHAEiaTypeSelectItem eiaTypeSelectItem;
	private GHADateItem acceptationDateItem, installationDateItem;
	private GHAImgButton searchImgButton, deleteImgButton, cleanImgButton;
	private VLayout sideButtons;

	private EiaResultSet resultSet;
	private boolean activated = false;
	private EIATab eiaTab;

	{
		eiaTypeSelectItem = new GHAEiaTypeSelectItem(
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		eiaTypeSelectItem.disable();

		codeItem = new GHACodeItem(120);
		codeItem.disable();

		serialNumber = new GHATextItem(GHAStrings.get("serialNumber-item"),
				120, false);
		fixedAssetIdentifier = new GHATextItem(
				GHAStrings.get("fixedAssetIdentifier-item"), 120, false);

		stateSelectItem = new GHAEiaStateSelectItem(
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		stateSelectItem.disable();

		workingAreaLocationSelectItem = new GHAWorkingAreaSelectItem(
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		workingAreaLocationSelectItem.disable();

		facilityLocationSelectItem = new GHAFacilitySelectItem(
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		facilityLocationSelectItem.disable();

		obuSelectItem = new GHAObuSelectItem(
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		obuSelectItem.disable();

		baseRoleSelectItem = new GHARoleSelectItem(
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		baseRoleSelectItem.disable();

		installationDateItem = new GHADateItem("Instalación",
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE, false);

		acceptationDateItem = new GHADateItem("Fecha de Aceptación",
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE, false);

	}

	/**
	 * @param eiaTab
	 */
	public EIATopForm(EiaResultSet resultSet, EIATab eiaTab) {
		super();
		this.resultSet = resultSet;
		this.eiaTab = eiaTab;

		GHAUiHelper.addGHAResizeHandler(this);
		setStyleName("sides-padding padding-top");// Esto es VUDU!
		setWidth100();
		setHeight(GHAUiHelper.DEFAULT_INNER_TOP_SECTION_HEIGHT + "px");
		setDefaultLayoutAlign(VerticalAlignment.CENTER);
		setBackgroundColor("#EAEAEA");

		DynamicForm form = new DynamicForm();
		// form.setWidth("100px");
		form.setTitleOrientation(TitleOrientation.TOP);
		form.setNumCols(6);
		form.setItems(eiaTypeSelectItem, codeItem, serialNumber,
				fixedAssetIdentifier, stateSelectItem, new GHASpacerItem(),
				workingAreaLocationSelectItem, facilityLocationSelectItem,
				obuSelectItem, baseRoleSelectItem, acceptationDateItem,
				installationDateItem);

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

		searchImgButton = new GHASearchButton(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				search();
			}
		});
		cleanImgButton = new GHACleanButton(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				clearFields();
			}
		});
		deleteImgButton = new GHADeleteButton(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				delete();

			}
		});
		sideButtons = GHAUiHelper.createBar(searchImgButton, cleanImgButton);

		addMembers(form, /* photoPanel, */new LayoutSpacer(), sideButtons);
		deactivate();
	}

	public void activate() {
		eiaTypeSelectItem.enable();
		codeItem.enable();
		serialNumber.enable();
		fixedAssetIdentifier.enable();
		stateSelectItem.enable();
		workingAreaLocationSelectItem.enable();
		facilityLocationSelectItem.enable();
		obuSelectItem.enable();
		baseRoleSelectItem.enable();
		installationDateItem.enable();
		acceptationDateItem.enable();
		cleanImgButton.enable();
		sideButtons.removeMember(deleteImgButton);
		sideButtons.addMember(searchImgButton, 0);

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

	public void clearFields() {
		// first check if the topform is active for search
		if (!this.activated)
			return;

		eiaTypeSelectItem.clearValue();
		codeItem.clearValue();
		serialNumber.clearValue();
		fixedAssetIdentifier.clearValue();
		stateSelectItem.clearValue();
		workingAreaLocationSelectItem.clearValue();
		facilityLocationSelectItem.clearValue();
		obuSelectItem.clearValue();
		baseRoleSelectItem.clearValue();
		installationDateItem.clearValue();
		acceptationDateItem.clearValue();
	}

	@Override
	public void close() {
		destroy();
	}

	public void deactivate() {
		eiaTypeSelectItem.disable();
		codeItem.disable();
		serialNumber.disable();
		fixedAssetIdentifier.disable();
		stateSelectItem.disable();
		workingAreaLocationSelectItem.disable();
		facilityLocationSelectItem.disable();
		obuSelectItem.disable();
		baseRoleSelectItem.disable();
		installationDateItem.disable();
		acceptationDateItem.disable();
		cleanImgButton.disable();
		sideButtons.removeMember(searchImgButton);
		sideButtons.addMember(deleteImgButton, 0);
		activated = false;
	}

	private void delete() {
		GHANotification.confirm(GHAStrings.get("eia"),
				GHAStrings.get("eia-delete-confirm"), new BooleanCallback() {
					@Override
					public void execute(Boolean value) {
						if (value) {
							EIAModel.delete(selectedEiaId,
									new GHAAsyncCallback<Boolean>() {
										@Override
										public void onSuccess(Boolean result) {
											eiaTab.search();
											clearFields();
											GHANotification
													.alert("eia-delete-success");
										}
									});
						}
					}
				});
	}

	public boolean isActivated() {
		return activated;
	}

	@Override
	public void onResize(ResizeEvent event) {
		setHeight(GHAUiHelper.DEFAULT_INNER_TOP_SECTION_HEIGHT + "px");
	}

	/**
	 * 
	 */
	public void search() {
		Eia eia = new Eia();
		eia.setState(null);

		if (eiaTypeSelectItem.getValue() != null)
			eia.setEiaType(new EiaType(eiaTypeSelectItem.getValueAsString()));
		if (codeItem.getValue() != null)
			eia.setCode(codeItem.getValueAsString());
		if (serialNumber.getValue() != null)
			eia.setSerialNumber(serialNumber.getValueAsString());
		if (fixedAssetIdentifier != null)
			eia.setFixedAssetIdentifier(fixedAssetIdentifier.getValueAsString());
		if (obuSelectItem.getValue() != null) {
			Obu obu = new Obu();
			obu.setId(Integer.valueOf(obuSelectItem.getValueAsString()));
			eia.setObu(obu);
		}
		if (baseRoleSelectItem.getValue() != null) {
			Role baseRole = new Role();
			baseRole.setId(Integer.valueOf(baseRoleSelectItem
					.getValueAsString()));
			eia.setResponsibleRole(baseRole);
		}
		if (stateSelectItem.getValue() != null) {
			eia.setState(EiaStateEnum.valueOf(stateSelectItem
					.getValueAsString()));
		}
		if (acceptationDateItem.getValue() != null)
			eia.setAcceptationDate(new Date(acceptationDateItem
					.getValueAsDate().getTime()));
		if (installationDateItem.getValue() != null)
			eia.setInstallationDate(new Date(installationDateItem
					.getValueAsDate().getTime()));
		if (facilityLocationSelectItem.getValue() != null) {
			eia.setWorkingArea(null);
			eia.setFacility(new Facility(Integer
					.valueOf(facilityLocationSelectItem.getValueAsString())));
		}
		if (workingAreaLocationSelectItem.getValue() != null) {
			eia.setFacility(null);
			eia.setWorkingArea(new WorkingArea(Integer
					.valueOf(workingAreaLocationSelectItem.getValueAsString())));
		}
		search(eia);

	}

	public void search(Eia eia) {
		EIAModel.find(eia, new GHAAsyncCallback<List<Eia>>() {

			@Override
			public void onSuccess(List<Eia> result) {
				// Window.alert("Search eia: " + result.size() + "");
				resultSet.setRecords(result);
			}
		});
	}

	@Override
	public void select(Eia eia) {
		this.selectedEiaId = eia.getId();

		if (eia.getEiaType() != null)
			eiaTypeSelectItem.setValue(eia.getEiaType().getCode());
		if (eia.getCode() != null)
			codeItem.setValue(eia.getCode());
		if (eia.getSerialNumber() != null)
			serialNumber.setValue(eia.getSerialNumber());
		if (eia.getFixedAssetIdentifier() != null)
			fixedAssetIdentifier.setValue(eia.getFixedAssetIdentifier());
		if (eia.getObu() != null)
			obuSelectItem.setValue(eia.getObu().getId());
		if (eia.getResponsibleRole() != null)
			baseRoleSelectItem.setValue(eia.getResponsibleRole().getId());
		if (eia.getState() != null)
			stateSelectItem.setValue(eia.getState().name());
		if (eia.getAcceptationDate() != null)
			acceptationDateItem.setValue(eia.getAcceptationDate());
		if (eia.getInstallationDate() != null)
			installationDateItem.setValue(eia.getInstallationDate());
		if (eia.getWorkingArea() != null) {
			workingAreaLocationSelectItem
					.setValue(eia.getWorkingArea().getId());
		}
		if (eia.getFacility() != null) {
			facilityLocationSelectItem.setValue(eia.getFacility().getId());
		}

		// lock fields of the topform
		deactivate();
	}
}