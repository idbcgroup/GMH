package org.fourgeeks.gha.webclient.client.eiatype;

import java.util.List;

import org.fourgeeks.gha.domain.enu.EiaSubTypeEnum;
import org.fourgeeks.gha.domain.enu.EiaTypeEnum;
import org.fourgeeks.gha.domain.gmh.Brand;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHATopForm;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHABrandSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHAEiaTypeSubTypeSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHAEiaTypeTypeSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.icons.GHACleanButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHADeleteButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHAImgButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHASearchButton;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHANotification;

import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret, emiliot
 * 
 */
public class EIATypeTopForm extends GHATopForm<EiaTypeResultSet, EiaType>
		implements EIATypeSelectionListener {
	private EiaType selectedEiaType;

	private GHATextItem nameItem;
	private GHATextItem modelItem;
	private GHABrandSelectItem brandItem;
	private GHAEiaTypeTypeSelectItem typeItem;
	private GHAEiaTypeSubTypeSelectItem subTypeItem;
	// private List<EiaTypePicture> listEiaTypePictures;
	// int index;
	// private GHAImg photo;
	private boolean activated = false;
	private GHAImgButton deleteButton, cleanButton, searchButton;
	private VLayout sideButtons;

	{
		typeItem = new GHAEiaTypeTypeSelectItem(230);
		subTypeItem = new GHAEiaTypeSubTypeSelectItem(230);
		nameItem = new GHATextItem(GHAStrings.get("name"), 460);
		nameItem.setColSpan(2);
		brandItem = new GHABrandSelectItem(230);
		modelItem = new GHATextItem(GHAStrings.get("model"), 230);
	}

	/**
	 * @param resultSet
	 * @param eiaTypeTab
	 */
	public EIATypeTopForm(EiaTypeResultSet resultSet, EIATypeTab eiaTypeTab) {
		super(resultSet, eiaTypeTab);
		DynamicForm form = new DynamicForm();
		form.setTitleOrientation(TitleOrientation.TOP);
		form.setNumCols(3);
		form.setItems(typeItem, subTypeItem, brandItem, nameItem, modelItem);
		// Panel de la Fotografia
		//
		// HLayout photoPanel = new HLayout();
		// photoPanel.setMembersMargin(10);
		// photoPanel.setWidth(130);
		// // photoPanel.setDefaultLayoutAlign(Alignment.CENTER);
		// photo = new GHAImg("../resources/img/Foto.jpg", 80, 80);
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
		// searchPhoto.addClickHandler(new ClickHandler() {
		//
		// @Override
		// public void onClick(ClickEvent event) {
		// EiaTypePicture picture = listEiaTypePictures.get(index);
		// new PopupShowPicture("../webclient/picture/eiaType/"
		// + picture.getPicture());
		// }
		// });
		// GHAImgButton nextPhoto = new GHAImgButton(
		// "../resources/icons/arrow.png");
		// nextPhoto.addClickHandler(new ClickHandler() {
		// @Override
		// public void onClick(ClickEvent event) {
		// next();
		// }
		// });
		//
		// photoBotones.addMembers(searchPhoto);
		// photoBotones.addMembers(nextPhoto);
		// photoPanel.addMembers(photo, photoBotones);
		// // Botones laterales del Panel

		modelItem.addKeyUpHandler(searchKeyUpHandler);
		nameItem.addKeyUpHandler(searchKeyUpHandler);
		brandItem.addKeyUpHandler(searchKeyUpHandler);
		typeItem.addKeyUpHandler(searchKeyUpHandler);
		subTypeItem.addKeyUpHandler(searchKeyUpHandler);

		searchButton = new GHASearchButton(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				search();
			}
		});
		cleanButton = new GHACleanButton(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				clear();
			}
		});
		deleteButton = new GHADeleteButton(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				delete();
			}
		});

		sideButtons = GHAUiHelper.createBar(searchButton, cleanButton);
		addMembers(form, /* new LayoutSpacer(), photoPanel, */
				new LayoutSpacer(), sideButtons);
		deactivate();
	}

	// private void next() {
	// if (index < listEiaTypePictures.size()) {
	// index++;
	// EiaTypePicture picture = listEiaTypePictures.get(index);
	// photo.setSrc("../webclient/picture/eiaType/" + picture.getPicture());
	// } else {
	// index = 0;
	// EiaTypePicture picture = listEiaTypePictures.get(index);
	// photo.setSrc("../webclient/picture/eiaType/" + picture.getPicture());
	// }
	//
	// }

	/**
	 * Obtiene las fotografias de un EiaType
	 * 
	 * @param eiaType
	 */
	// private void getEiaTypePicture(EiaType eiaType) {
	// index = 0;
	// listEiaTypePictures = new ArrayList<EiaTypePicture>();
	// EIATypePictureModel.findByEiaType(eiaType,
	// new GHAAsyncCallback<List<EiaTypePicture>>() {
	//
	// @Override
	// public void onSuccess(List<EiaTypePicture> result) {
	// listEiaTypePictures = result;
	// EiaTypePicture picture1 = result.get(0);
	// photo.setSrc("../webclient/picture/eiaType/"
	// + picture1.getPicture());
	// }
	// });
	// }
	@Override
	public void activate() {
		nameItem.enable();
		brandItem.enable();
		modelItem.enable();
		typeItem.enable();
		subTypeItem.enable();
		cleanButton.enable();
		sideButtons.removeMember(deleteButton);
		sideButtons.addMember(searchButton, 0);

		activated = true;

	}

	@Override
	public void clear() {
		// first check if the topform is active for search
		if (!this.activated)
			return;
		nameItem.clearValue();
		brandItem.clearValue();
		modelItem.clearValue();
		typeItem.clearValue();
		subTypeItem.clearValue();
	}

	@Override
	public void deactivate() {
		nameItem.disable();
		brandItem.disable();
		modelItem.disable();
		typeItem.disable();
		subTypeItem.disable();
		cleanButton.disable();
		sideButtons.removeMember(searchButton);
		sideButtons.addMember(deleteButton, 0);
		activated = false;
	}

	protected void delete() {
		GHANotification.confirm(GHAStrings.get("eiatype"),
				GHAStrings.get("eiatype-delete-confirm"),
				new BooleanCallback() {
					@Override
					public void execute(Boolean value) {
						if (value) {
							EIATypeModel.delete(selectedEiaType.getCode(),
									new GHAAsyncCallback<Void>() {
										@Override
										public void onSuccess(Void result) {
											containerTab.search();
											clear();
											GHANotification
													.alert("eiatype-delete-success");
										}
									});
						}
					}
				});
	}

	@Override
	public void search() {
		super.search();
		EiaType eiaType = new EiaType();
		eiaType.setName(nameItem.getValueAsString());
		if (brandItem.getValue() != null)
			eiaType.setBrand(new Brand(Integer.valueOf(brandItem
					.getValueAsString()), null));
		eiaType.setModel(modelItem.getValueAsString());
		if (typeItem.getValue() != null)
			eiaType.setType(EiaTypeEnum.valueOf(typeItem.getValueAsString()));
		if (subTypeItem.getValue() != null)
			eiaType.setSubtype(EiaSubTypeEnum.valueOf(subTypeItem
					.getValueAsString()));
		search(eiaType);
	}

	@Override
	public void search(EiaType eiaType) {
		super.search();
		EIATypeModel.find(eiaType, new GHAAsyncCallback<List<EiaType>>() {
			@Override
			public void onSuccess(List<EiaType> result) {
				resultSet.setRecords(result, true);
			}

		});
	}

	@Override
	public void select(EiaType eiaType) {
		// getEiaTypePicture(eiaType);
		selectedEiaType = eiaType;

		nameItem.setValue(eiaType.getName());
		modelItem.setValue(eiaType.getModel());

		if (eiaType.getBrand() != null)
			brandItem.setValue(eiaType.getBrand().getId());

		if (eiaType.getType() != null)
			typeItem.setValue(eiaType.getType().name());

		if (eiaType.getSubtype() != null)
			subTypeItem.setValue(eiaType.getSubtype().name());

		// lock fields of the topform
		deactivate();
	}

}