package org.fourgeeks.gha.webclient.client.eiatype;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAImgButton;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHATabSet;

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
public class EIATypeTopSection extends HLayout implements
		EIATypeSelectionListener, ResizeHandler {

	private final EIATypeTab eiaTypeTab;
	private EIATypeSearchForm eiaTypeSearchForm;
	private GHATextItem nameItem, codeItem, brandItem, modelItem, manItem,
			umdnsCodeItem, mobilityItem, typeItem, subTypeItem;
//	private List<EiaTypePicture> listEiaTypePictures;
	int index;
//	private GHAImg photo;
	{
		eiaTypeSearchForm = new EIATypeSearchForm();
		codeItem = new GHATextItem("Código", false);
		nameItem = new GHATextItem("Nombre", false);
		brandItem = new GHATextItem("Marca", false);
		modelItem = new GHATextItem("Modelo", false);
		manItem = new GHATextItem("Fabricante", false);
		umdnsCodeItem = new GHATextItem("EIAUMDNS", false);
		mobilityItem = new GHATextItem("Movilidad", false);
		typeItem = new GHATextItem("Tipo de Equipo", false);
		subTypeItem = new GHATextItem("Subtipo", false);
	}

	/**
	 * @param eiaTypeTab
	 */
	public EIATypeTopSection(EIATypeTab eiaTypeTab) {
		super();
		this.eiaTypeTab = eiaTypeTab;
		eiaTypeSearchForm.addEiaTypeSelectionListener(eiaTypeTab);
		eiaTypeTab.addEiaTypeSelectionListener(this);
		eiaTypeTab.addGHAHideableHandler(eiaTypeSearchForm);
		eiaTypeTab.addGHAClosableHandler(eiaTypeSearchForm);
		GHAUiHelper.addGHAResizeHandler(this);

		setStyleName("sides-padding padding-top");// Esto es VUDU!
		setWidth100();
		setHeight(GHAUiHelper.INNER_TOP_SECTION_HEIGHT + "px");
		setBackgroundColor("#EAEAEA");
		setDefaultLayoutAlign(VerticalAlignment.CENTER);

		DynamicForm form = new DynamicForm();
		form.setTitleOrientation(TitleOrientation.TOP);
		form.setNumCols(5);
		form.setItems(codeItem, nameItem, brandItem, modelItem, manItem,
				umdnsCodeItem, mobilityItem, typeItem, subTypeItem);

		// Panel de la Fotografia
//
//		HLayout photoPanel = new HLayout();
//		photoPanel.setMembersMargin(10);
//		photoPanel.setWidth(130);
//		// photoPanel.setDefaultLayoutAlign(Alignment.CENTER);
//		photo = new GHAImg("../resources/img/Foto.jpg", 80, 80);
//		// photo.setTop(8);
//		photo.setStyleName("top-8");
//
//		VLayout photoBotones = new VLayout();
//		photoBotones.setWidth(30);
//		photoBotones.setLayoutMargin(5);
//		photoBotones.setMembersMargin(10);
//		photoBotones.setDefaultLayoutAlign(Alignment.CENTER);
//
//		GHAImgButton searchPhoto = new GHAImgButton(
//				"../resources/icons/search.png");
//		searchPhoto.addClickHandler(new ClickHandler() {
//
//			@Override
//			public void onClick(ClickEvent event) {
//				EiaTypePicture picture = listEiaTypePictures.get(index);
//				new PopupShowPicture("../webclient/picture/eiaType/"
//						+ picture.getPicture());
//			}
//		});
//		GHAImgButton nextPhoto = new GHAImgButton(
//				"../resources/icons/arrow.png");
//		nextPhoto.addClickHandler(new ClickHandler() {
//			@Override
//			public void onClick(ClickEvent event) {
//				next();
//			}
//		});
//
//		photoBotones.addMembers(searchPhoto);
//		photoBotones.addMembers(nextPhoto);
//		photoPanel.addMembers(photo, photoBotones);
//		// Botones laterales del Panel

		VLayout sideButtons = GHAUiHelper.createBar(
				new GHAImgButton("../resources/icons/search.png", new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						search();
					}
				}),
				new GHAImgButton("../resources/icons/cancel.png",new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						GHATabSet.closeTab(EIATypeTopSection.this.eiaTypeTab);					
					}
				}));

		addMembers(form, /* new LayoutSpacer(), photoPanel,*/ new LayoutSpacer(),
				sideButtons);
	}

//	private void next() {
//		if (index < listEiaTypePictures.size()) {
//			index++;
//			EiaTypePicture picture = listEiaTypePictures.get(index);
//			photo.setSrc("../webclient/picture/eiaType/" + picture.getPicture());
//		} else {
//			index = 0;
//			EiaTypePicture picture = listEiaTypePictures.get(index);
//			photo.setSrc("../webclient/picture/eiaType/" + picture.getPicture());
//		}
//
//	}

	/**
	 * Obtiene las fotografias de un EiaType
	 * 
	 * @param eiaType
	 */
//	private void getEiaTypePicture(EiaType eiaType) {
//		index = 0;
//		listEiaTypePictures = new ArrayList<EiaTypePicture>();
//		EIATypePictureModel.findByEiaType(eiaType,
//				new GHAAsyncCallback<List<EiaTypePicture>>() {
//
//					@Override
//					public void onSuccess(List<EiaTypePicture> result) {
//						listEiaTypePictures = result;
//						EiaTypePicture picture1 = result.get(0);
//						photo.setSrc("../webclient/picture/eiaType/"
//								+ picture1.getPicture());
//					}
//				});
//	}

	@Override
	public void select(EiaType eiaType) {
//		getEiaTypePicture(eiaType);
		codeItem.setValue(eiaType.getCode());
		nameItem.setValue(eiaType.getName());

		if (eiaType.getBrand() != null){
			brandItem.setValue(eiaType.getBrand().getName());
			if (eiaType.getBrand().getManufacturer() != null)
				manItem.setValue(eiaType.getBrand().getManufacturer().getName());
		}

		modelItem.setValue(eiaType.getModel());

		umdnsCodeItem.setValue(eiaType.getEiaUmdns());

		if (eiaType.getMobility() != null)
			mobilityItem.setValue(eiaType.getMobility().toString());

		if (eiaType.getType() != null)
			typeItem.setValue(eiaType.getType().toString());

		if (eiaType.getSubtype() != null)
			subTypeItem.setValue(eiaType.getSubtype().toString());
	}

	/**
	 * Opens the search form
	 */
	public void search() {
		eiaTypeSearchForm.open();
	}

	// @Override
	// public void addEiaTypeSelectionListener(
	// EIATypeSelectionListener selecionListener) {
	// selectionListeners.add(selecionListener);
	// }

	@Override
	public void onResize(ResizeEvent event) {
		setHeight(GHAUiHelper.INNER_TOP_SECTION_HEIGHT + "px");
	}

	// @Override
	// public void removeEiaTypeSelectionListener(
	// EIATypeSelectionListener eIATypeSelectionListener) {
	// selectionListeners.remove(eIATypeSelectionListener);
	//
	// }

}
