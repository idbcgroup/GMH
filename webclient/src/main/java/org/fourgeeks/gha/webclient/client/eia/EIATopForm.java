package org.fourgeeks.gha.webclient.client.eia;

import java.util.List;

import org.fourgeeks.gha.domain.enu.EiaStateEnum;
import org.fourgeeks.gha.domain.ess.Role;
import org.fourgeeks.gha.domain.ess.WorkingArea;
import org.fourgeeks.gha.domain.gar.Facility;
import org.fourgeeks.gha.domain.gar.Obu;
import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.mix.Bpi;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHATopForm;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHABpiSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHAEiaStateSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHAFacilitySelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHAObuSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHARoleSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHAWorkingAreaSelectItem;
import org.fourgeeks.gha.webclient.client.UI.icons.GHACleanButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHADeleteButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHAImgButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHASearchButton;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHADynamicForm;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHANotification;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret, emiliot
 * 
 */
public class EIATopForm extends GHATopForm<EiaResultSet, Eia> implements
		EIASelectionListener {

	private GHATextItem serialNumber;
	private GHATextItem fixedAssetIdentifier;
	private GHAEiaStateSelectItem stateSelectItem;
	private GHAObuSelectItem obuSelectItem;
	private GHABpiSelectItem bpiObuSelectItem;
	private GHARoleSelectItem baseRoleSelectItem;
	private GHAWorkingAreaSelectItem workingAreaLocationSelectItem;
	private GHAFacilitySelectItem facilityLocationSelectItem;

	private GHAImgButton searchImgButton, deleteImgButton, cleanImgButton;
	private VLayout sideButtons;

	private GHADynamicForm form;

	private Eia selectedEia;

	{
		serialNumber = new GHATextItem(GHAStrings.get("serialNumber-item"),
				false);
		fixedAssetIdentifier = new GHATextItem(
				GHAStrings.get("fixedAssetIdentifier-item"), false);

		stateSelectItem = new GHAEiaStateSelectItem();
		stateSelectItem.disable();

		workingAreaLocationSelectItem = new GHAWorkingAreaSelectItem();
		workingAreaLocationSelectItem.disable();

		facilityLocationSelectItem = new GHAFacilitySelectItem();
		facilityLocationSelectItem.disable();

		obuSelectItem = new GHAObuSelectItem();
		obuSelectItem.disable();

		bpiObuSelectItem = new GHABpiSelectItem();
		bpiObuSelectItem.disable();

		baseRoleSelectItem = new GHARoleSelectItem();
		baseRoleSelectItem.disable();

		form = new GHADynamicForm(GHAUiHelper.getNormalFormWidth(30), 4);
	}

	/**
	 * @param resultSet
	 * @param eiaTab
	 */
	public EIATopForm(EiaResultSet resultSet, EIATab eiaTab) {
		super(resultSet, eiaTab);

		form.setItems(serialNumber, fixedAssetIdentifier, stateSelectItem,
				bpiObuSelectItem, workingAreaLocationSelectItem,
				facilityLocationSelectItem, obuSelectItem, baseRoleSelectItem);
		form.setAutoFocus(true);
		serialNumber.setSelectOnFocus(true);

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
				clear();
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
		serialNumber.enable();
		fixedAssetIdentifier.enable();
		stateSelectItem.enable();
		workingAreaLocationSelectItem.enable();
		facilityLocationSelectItem.enable();
		obuSelectItem.enable();
		bpiObuSelectItem.enable();
		baseRoleSelectItem.enable();

		toggleSideBarButtons(false);

		activated = true;
	}

	@Override
	public void close() {
		destroy();
	}

	public void deactivate() {
		serialNumber.disable();
		fixedAssetIdentifier.disable();
		stateSelectItem.disable();
		workingAreaLocationSelectItem.disable();
		facilityLocationSelectItem.disable();
		obuSelectItem.disable();
		bpiObuSelectItem.disable();
		baseRoleSelectItem.disable();

		activated = false;
	}

	private void toggleSideBarButtons(boolean swich) {
		if (swich) {
			cleanImgButton.disable();
			sideButtons.removeMember(searchImgButton);
			sideButtons.addMember(deleteImgButton, 0);
		} else {
			cleanImgButton.enable();
			sideButtons.removeMember(deleteImgButton);
			sideButtons.addMember(searchImgButton, 0);
		}

	}

	protected void delete() {
		GHANotification.confirm(GHAStrings.get("eia"),
				GHAStrings.get("eia-delete-confirm"), new BooleanCallback() {
					@Override
					public void execute(Boolean value) {
						if (value) {
							EIAModel.delete(selectedEia.getId(),
									new GHAAsyncCallback<Boolean>() {
										@Override
										public void onSuccess(Boolean result) {
											containerTab.search();
											clear();
											GHANotification
													.alert("eia-delete-success");
										}
									});
						}
					}
				});
	}

	/**
	 * 
	 */
	@Override
	public void search() {
		super.search();
		Obu obu = new Obu();
		Eia eia = new Eia();
		eia.setState(null);

		if (serialNumber.getValue() != null)
			eia.setSerialNumber(serialNumber.getValueAsString());
		if (fixedAssetIdentifier != null)
			eia.setFixedAssetIdentifier(fixedAssetIdentifier.getValueAsString());
		if (obuSelectItem.getValue() != null) {
			obu.setId(Long.valueOf(obuSelectItem.getValueAsString()));
			eia.setObu(obu);
		}
		if (bpiObuSelectItem.getValue() != null) {
			Bpi bpi = new Bpi(Long.valueOf(bpiObuSelectItem.getValueAsString()));
			obu.setBpi(bpi);
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
				resultSet.setRecords(result, true);
			}
		});
	}

	@Override
	public void select(Eia eia) {
		super.search();
		this.selectedEia = eia;

		if (eia.getSerialNumber() != null)
			serialNumber.setValue(eia.getSerialNumber());
		if (eia.getFixedAssetIdentifier() != null)
			fixedAssetIdentifier.setValue(eia.getFixedAssetIdentifier());
		if (eia.getObu() != null) {
			Obu obu = eia.getObu();
			obuSelectItem.setValue(obu.getId());
			if (obu.getBpi() != null)
				bpiObuSelectItem.setValue(obu.getBpi().getId());
		}
		if (eia.getResponsibleRole() != null)
			baseRoleSelectItem.setValue(eia.getResponsibleRole().getId());
		if (eia.getState() != null)
			stateSelectItem.setValue(eia.getState().name());
		if (eia.getWorkingArea() != null) {
			workingAreaLocationSelectItem
					.setValue(eia.getWorkingArea().getId());
		}
		if (eia.getFacility() != null) {
			facilityLocationSelectItem.setValue(eia.getFacility().getId());
		}

		// lock fields of the topform
		deactivate();
		toggleSideBarButtons(true);
	}

	@Override
	public void clear() {
		// first check if the topform is active for search
		if (!this.activated)
			return;

		serialNumber.clearValue();
		fixedAssetIdentifier.clearValue();
		stateSelectItem.clearValue();
		workingAreaLocationSelectItem.clearValue();
		facilityLocationSelectItem.clearValue();
		obuSelectItem.clearValue();
		bpiObuSelectItem.clearValue();
		baseRoleSelectItem.clearValue();
	}

	@Override
	public void onResize(ResizeEvent event) {
		super.onResize(event);
		form.resize(GHAUiHelper.getNormalFormWidth(30), 4);
	}
}