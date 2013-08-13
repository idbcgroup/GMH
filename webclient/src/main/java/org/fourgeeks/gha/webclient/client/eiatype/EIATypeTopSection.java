package org.fourgeeks.gha.webclient.client.eiatype;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.EiaTypePicture;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAClosable;
import org.fourgeeks.gha.webclient.client.UI.GHAHideable;
import org.fourgeeks.gha.webclient.client.UI.GHAImg;
import org.fourgeeks.gha.webclient.client.UI.GHAImgButton;
import org.fourgeeks.gha.webclient.client.UI.GHATabSet;
import org.fourgeeks.gha.webclient.client.UI.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

public class EIATypeTopSection extends HLayout implements
		EIATypeSelectionListener, ResizeHandler {

	private final EIATypeTab eIATypeTab;
	private List<EIATypeSelectionListener> selectionListeners;
	private EIATypeSearchForm eiaTypeSearchForm;
	private GHATextItem nameItem, codeItem, brandItem, modelItem, manItem,
						umdnsCodeItem, mobilityItem,typeItem, subTypeItem;
	private List<EiaTypePicture> listEiaTypePictures;
	int index;
	private GHAImg photo;
	{
		index = 0;
		listEiaTypePictures = new ArrayList<EiaTypePicture>();
		selectionListeners = new LinkedList<EIATypeSelectionListener>();
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

	public EIATypeTopSection(EIATypeTab eiaTypeTab) {
		super();
		GHAUiHelper.addGHAResizeHandler(this);
		eIATypeTab = eiaTypeTab;
		
		eiaTypeSearchForm.AddEIATypeSelectionListener(eiaTypeTab);

		eiaTypeTab.addGHAHideableHandler(new GHAHideable() {
			@Override
			public void hide() {
				eiaTypeSearchForm.hide();
			}
		});
		eiaTypeTab.addGHAClosableHandler(new GHAClosable() {

			@Override
			public void close() {
				eiaTypeSearchForm.destroy();
			}
		});

		setStyleName("sides-padding padding-top");// Esto es VUDU!
		setWidth100();
		setHeight(GHAUiHelper.INNER_TOP_SECTION_HEIGHT + "px");
		setBackgroundColor("#EAEAEA");
		setDefaultLayoutAlign(VerticalAlignment.CENTER);

		DynamicForm form = new DynamicForm();
		form.setTitleOrientation(TitleOrientation.TOP);
		form.setNumCols(5);
		form.setItems(codeItem, nameItem,  brandItem, modelItem, manItem,
					  umdnsCodeItem, mobilityItem, typeItem, subTypeItem);

		// Panel de la Fotografia

		HLayout photoPanel = new HLayout();
		photoPanel.setMembersMargin(10);
		photoPanel.setWidth(130);
		// photoPanel.setDefaultLayoutAlign(Alignment.CENTER);
		photo = new GHAImg("../resources/img/Foto.jpg", 80, 80);
		// photo.setTop(8);
		photo.setStyleName("top-8");

		VLayout photoBotones = new VLayout();
		photoBotones.setWidth(30);
		photoBotones.setLayoutMargin(5);
		photoBotones.setMembersMargin(10);
		photoBotones.setDefaultLayoutAlign(Alignment.CENTER);

		GHAImgButton searchPhoto = new GHAImgButton("../resources/icons/search.png");
		GHAImgButton nextPhoto = new GHAImgButton("../resources/icons/arrow.png");
		nextPhoto.addClickHandler(new ClickHandler() {		
			@Override
			public void onClick(ClickEvent event) {		
				next();			
			}
		});
		
		photoBotones.addMembers(searchPhoto);
		photoBotones.addMembers(nextPhoto);
		photoPanel.addMembers(photo, photoBotones);

		// Botones laterales del Panel

		VLayout panelBotones = new VLayout();
		panelBotones.setWidth(30);
		panelBotones.setLayoutMargin(5);
		panelBotones.setMembersMargin(10);
		panelBotones.setDefaultLayoutAlign(Alignment.CENTER);

		GHAImgButton cleanImg = new GHAImgButton("../resources/icons/clean.png");
		cleanImg.setSize("20px", "20px");
		cleanImg.setHoverStyle("boxed");
		GHAImgButton searchImg = new GHAImgButton("../resources/icons/search.png");
		searchImg.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				search();
			}
		});
		GHAImgButton cancelButton = new GHAImgButton("../resources/icons/cancel.png");
		cancelButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				GHATabSet.closeTab(eIATypeTab);
				
			}
		});
		
		panelBotones.addMembers(searchImg, cleanImg, cancelButton);

		addMembers(form, new LayoutSpacer(), photoPanel, new LayoutSpacer(), panelBotones);
	}
	
	private void next()
	{
		if(index < listEiaTypePictures.size()){
			index++;
			EiaTypePicture picture = listEiaTypePictures.get(index);
			photo.setSrc("../webclient/picture/eiaType/"
					+ picture.getPicture());
			Window.alert("Imagen1: "+picture.getPicture());
		} else
			{
				index = 0;
				EiaTypePicture picture = listEiaTypePictures.get(index);
				photo.setSrc("../webclient/picture/eiaType/"
						+ picture.getPicture());
				Window.alert("Imagen2: "+picture.getPicture());
			}
				
	}
/**
 * Obtiene las fotografias de un EiaType
 * @param eiaType
 */
	private void getEiaTypePicture(EiaType eiaType){
		EIATypePictureModel.find(eiaType,
				new GHAAsyncCallback<List<EiaTypePicture>>() {

					@Override
					public void onSuccess(List<EiaTypePicture> result) {
						Window.alert("imagenes obtenidas con exito: "+result.size());
						listEiaTypePictures = result;
						EiaTypePicture picture1 = result.get(0);
						photo.setSrc("../webclient/picture/eiaType/"
								+ picture1.getPicture());
						Window.alert("imagen desplegada en el componente");
					}
				});
	}
	@Override
	public void select(EiaType eiaType) {
		// Window.alert("top section select");
		
		selectEiaType(eiaType);
		getEiaTypePicture(eiaType);
		codeItem.setValue(eiaType.getCode());
		nameItem.setValue(eiaType.getName());

		if (eiaType.getBrand() != null)
			brandItem.setValue(eiaType.getBrand().getName());

		modelItem.setValue(eiaType.getModel());

		if (eiaType.getManufacturer() != null)
			manItem.setValue(eiaType.getManufacturer().getName());

		umdnsCodeItem.setValue(eiaType.getEiaUmdns());

		if (eiaType.getMobility() != null)
			mobilityItem.setValue(eiaType.getMobility().toString());

		if (eiaType.getType() != null)
			typeItem.setValue(eiaType.getType().toString());

		if (eiaType.getSubtype() != null)
			subTypeItem.setValue(eiaType.getSubtype().toString());

	}

	public void search() {
		eiaTypeSearchForm.open();
	}

	public void AddEIATypeSelectionListener(
			EIATypeSelectionListener selecionListener) {
		selectionListeners.add(selecionListener);
	}

	private void selectEiaType(EiaType eiaType) {
		for (EIATypeSelectionListener listener : selectionListeners)
			listener.select(eiaType);

	}

	@Override
	public void onResize(ResizeEvent event) {
		setHeight(GHAUiHelper.INNER_TOP_SECTION_HEIGHT + "px");
	}
	
}
