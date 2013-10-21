package org.fourgeeks.gha.webclient.client.eiatype;

import java.util.List;

import org.fourgeeks.gha.domain.gmh.Brand;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHABrandSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHACodeItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHAEiaTypeSubTypeSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHAEiaTypeTypeSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHAMobilitySelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAImgButton;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
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
public class EIATypeTopForm extends HLayout implements
		EIATypeSelectionListener, ResizeHandler {

	// private final EIATypeTab eiaTypeTab;
	// private EIATypeSearchForm eiaTypeSearchForm;
	private GHATextItem nameItem, codeItem, modelItem;
	private GHAEiaTypeTypeSelectItem typeItem;
	private GHAEiaTypeSubTypeSelectItem subTypeItem;
	private GHABrandSelectItem brandItem;
	private GHAMobilitySelectItem mobilityItem;
	// private List<EiaTypePicture> listEiaTypePictures;
	int index;
	// private GHAImg photo;
	private EiaTypeResultSet resultSet;

	{
		// eiaTypeSearchForm = new EIATypeSearchForm();
		codeItem = new GHACodeItem(230);
		nameItem = new GHATextItem(GHAStrings.get("name"), 460);
		nameItem.setColSpan(2);
		brandItem = new GHABrandSelectItem(200);
		modelItem = new GHATextItem(GHAStrings.get("model"), 230);
		mobilityItem = new GHAMobilitySelectItem(220);
		typeItem = new GHAEiaTypeTypeSelectItem(220);
		subTypeItem = new GHAEiaTypeSubTypeSelectItem(220);

	}

	/**
	 * @param resultSet
	 */
	public EIATypeTopForm(EiaTypeResultSet resultSet) {
		super();
		this.resultSet = resultSet;
		// this.eiaTypeTab = eiaTypeTab;
		// eiaTypeTab.addGHAHideableHandler(eiaTypeSearchForm);
		// eiaTypeTab.addGHAClosableHandler(eiaTypeSearchForm);
		GHAUiHelper.addGHAResizeHandler(this);
		setStyleName("sides-padding padding-top");// Esto es VUDU!
		setWidth100();
		setHeight(GHAUiHelper.DEFAULT_TOP_SECTION_HEIGHT + "px");
		setBackgroundColor(GHAUiHelper.HIGHLIGHTED_BACKGROUND_COLOR);
		setDefaultLayoutAlign(VerticalAlignment.CENTER);
		DynamicForm form = new DynamicForm();
		form.setTitleOrientation(TitleOrientation.TOP);
		form.setNumCols(4);
		form.setItems(codeItem, modelItem, nameItem, brandItem, mobilityItem,
				typeItem, subTypeItem);
		// form.setItems(codeItem, nameItem, brandItem, modelItem, mobilityItem,
		// typeItem, subTypeItem);
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
		VLayout sideButtons = GHAUiHelper.createBar(new GHAImgButton(
				"../resources/icons/search.png", new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						search();
					}
				}));
		addMembers(form, /* new LayoutSpacer(), photoPanel, */
				new LayoutSpacer(), sideButtons);

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
	public void select(EiaType eiaType) {
		// // getEiaTypePicture(eiaType);
		// codeItem.setValue(eiaType.getCode());
		// nameItem.setValue(eiaType.getName());
		//
		// if (eiaType.getBrand() != null) {
		// brandItem.setValue(eiaType.getBrand().getName());
		// if (eiaType.getBrand().getManufacturer() != null)
		// manItem.setValue(eiaType.getBrand().getManufacturer().getName());
		// }
		//
		// modelItem.setValue(eiaType.getModel());
		//
		// if (eiaType.getMobility() != null)
		// mobilityItem.setValue(eiaType.getMobility().toString());
		//
		// if (eiaType.getType() != null)
		// typeItem.setValue(eiaType.getType().toString());
		//
		// if (eiaType.getSubtype() != null)
		// subTypeItem.setValue(eiaType.getSubtype().toString());
	}

	/**
	 * Triggered from the topForm search icon, do the search and fill the result
	 * set
	 */
	public void search() {
		Window.alert("enter search");
		EiaType eiaType = new EiaType();

		Window.alert("1");
		eiaType.setCode(codeItem.getValueAsString());
		Window.alert("2");
		eiaType.setName(nameItem.getValueAsString());
		Window.alert("3");

		if (brandItem.getValue() != null) {
			eiaType.setBrand(new Brand(Integer.valueOf(brandItem
					.getValueAsString()), null));
		}
		Window.alert("4");
		eiaType.setModel(modelItem.getValueAsString());
		Window.alert("5");
		if (mobilityItem.getValue() != null)
			eiaType.setMobility(mobilityItem.getValue());
		Window.alert("6");
		if (typeItem.getValue() != null)
			eiaType.setType(typeItem.getValue());
		Window.alert("7");
		if (subTypeItem.getValue() != null)
			eiaType.setSubtype(subTypeItem.getValue());
		Window.alert("8");

		search(eiaType);
	}

	/**
	 * Make the call to search and fill the resultSet with the result
	 * 
	 * @param eiaType
	 */
	public void search(EiaType eiaType) {
		Window.alert("search");
		EIATypeModel.find(eiaType, new GHAAsyncCallback<List<EiaType>>() {
			@Override
			public void onSuccess(List<EiaType> result) {
				Window.alert("succes search");
				if (result.size() == 1) {
					Window.alert("only one result, go to the internal tabset");
					resultSet.hide();
				} else {
					resultSet.setRecords(result);
					resultSet.show();
				}
			}

		});
	}

	// @Override
	// public void addEiaTypeSelectionListener(
	// EIATypeSelectionListener selecionListener) {
	// selectionListeners.add(selecionListener);
	// }

	@Override
	public void onResize(ResizeEvent event) {
		setHeight(GHAUiHelper.DEFAULT_TOP_SECTION_HEIGHT + "px");
	}

	// @Override
	// public void removeEiaTypeSelectionListener(
	// EIATypeSelectionListener eIATypeSelectionListener) {
	// selectionListeners.remove(eIATypeSelectionListener);
	//
	// }

}
