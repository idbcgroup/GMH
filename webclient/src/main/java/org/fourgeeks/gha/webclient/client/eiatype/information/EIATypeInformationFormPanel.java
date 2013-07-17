package org.fourgeeks.gha.webclient.client.eiatype.information;

import gwtupload.client.IFileInput.FileInputType;
import gwtupload.client.IUploadStatus.Status;
import gwtupload.client.IUploader;
import gwtupload.client.IUploader.OnFinishUploaderHandler;
import gwtupload.client.IUploader.UploadedInfo;
import gwtupload.client.MultiUploader;
import gwtupload.client.PreloadedImage;
import gwtupload.client.PreloadedImage.OnLoadPreloadedImageHandler;
import gwtupload.client.SingleUploader;

import java.io.InputStream;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.List;

import org.fourgeeks.gha.domain.enu.EiaMobilityEnum;
import org.fourgeeks.gha.domain.enu.EiaSubTypeEnum;
import org.fourgeeks.gha.domain.enu.EiaTypeEnum;
import org.fourgeeks.gha.domain.gmh.Brand;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.Manufacturer;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAButton;
import org.fourgeeks.gha.webclient.client.UI.GHACache;
import org.fourgeeks.gha.webclient.client.UI.GHAClosable;
import org.fourgeeks.gha.webclient.client.UI.GHASelectItem;
import org.fourgeeks.gha.webclient.client.UI.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.GHAUploadPhotographs;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeAddForm;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeModel;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeTab;
import com.google.gwt.user.client.DOM;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.HasMouseOverHandlers;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.CustomButton;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.AnimationEffect;
import com.smartgwt.client.types.ImageStyle;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

public class EIATypeInformationFormPanel extends VLayout implements
		EIATypeSelectionListener, GHAClosable {

	private EIATypeAddForm addForm;
	private GHATextItem codeItem, nameItem, modelItem, descriptionItem,
			useDescriptionItem, eiaUmdnsItem;
	private GHASelectItem brandItem, manItem, mobilityItem, typeItem,
			subTypeItem;
	private EiaType eiaType, orginalEiaType;
	private EIATypeTab tab;
	private GHAUploadPhotographs uploadPhoto1;
	private OnFinishUploaderHandler onFinishUploaderHandler;
	private OnLoadPreloadedImageHandler showImage;
	private FlowPanel panelImages;
	private Img img1, img2, img3;
	{
		addForm = new EIATypeAddForm();
		codeItem = new GHATextItem("Código", 150);
		nameItem = new GHATextItem("Nombre", 150);
		modelItem = new GHATextItem("Modelo", 150);
		descriptionItem = new GHATextItem("Descripción", 480);
		descriptionItem.setColSpan(3);
		useDescriptionItem = new GHATextItem("Uso", 480);
		useDescriptionItem.setColSpan(3);
		eiaUmdnsItem = new GHATextItem("EIAUMDNS", 150);
		manItem = new GHASelectItem("Fabricante", 150);
		brandItem = new GHASelectItem("Marca", 150);
		mobilityItem = new GHASelectItem("Movilidad", 150);
		typeItem = new GHASelectItem("Tipo", 150);
		subTypeItem = new GHASelectItem("Subtipo", 150);
		//uploadPhoto1 = new GHAUploadPhotographs(3, "20px", "subir");
		//URL url = EIATypeInformationFormPanel.class.getResource("/default.png");
//		img1 = new Img("/default.png", 100, 100);  
//		img1.setImageType(ImageStyle.STRETCH);  
//		img1.setBorder("1px solid gray");  
//		img1.setLeft(240); 
		
	}
	
	private class ButtonCustom extends Composite implements HasClickHandlers, HasMouseOverHandlers{
	    
	    public ButtonCustom() {
	      DecoratorPanel widget = new DecoratorPanel();
	      initWidget(widget);
	      widget.setWidget(new GHAButton("../resources/icons/new.png"));
	     // widget.setSize("15px","15px");
	    }
	   
		@Override
		public HandlerRegistration addClickHandler(
				com.google.gwt.event.dom.client.ClickHandler handler) {
			return addDomHandler(handler, com.google.gwt.event.dom.client.ClickEvent.getType());
		}

		@Override
		public HandlerRegistration addMouseOverHandler(MouseOverHandler handler) {
			return addDomHandler(handler, com.google.gwt.event.dom.client.MouseOverEvent.getType());
		}

		
	  }

	public EIATypeInformationFormPanel(EIATypeTab tab) {
		activateForm(false);
		ButtonCustom buttonAddImage = new ButtonCustom(); 
		MultiUploader uploadPhoto1 = new MultiUploader(FileInputType.CUSTOM.with(buttonAddImage));
		uploadPhoto1.setMaximumFiles(1);
		uploadPhoto1.setValidExtensions("jpg", "jpeg", "png", "gif");
		panelImages = new FlowPanel();
		onFinishUploaderHandler = new OnFinishUploaderHandler() {
			@Override
			public void onFinish(IUploader uploader) {
				 if (uploader.getStatus() == Status.SUCCESS) {

				        new PreloadedImage(uploader.fileUrl(), showImage);
			  
				        // The server sends useful information to the client by default
				        UploadedInfo info = uploader.getServerInfo();
				        System.out.println("File name " + info.name);
				        System.out.println("File content-type " + info.ctype);
				        System.out.println("File size " + info.size);

				        // You can send any customized message and parse it 
				        System.out.println("Server message " + info.message);
				        SC.say("File name " + info.name);
				        SC.say("File content-type " + info.ctype);
				        SC.say("File size " + info.size);
				        SC.say("Server message " + info.message);
				      }
				    }
			};
			showImage = new OnLoadPreloadedImageHandler() {
			    public void onLoad(PreloadedImage image) {
			      image.setWidth("150px");
			      image.setHeight("100px");
			    			    	
			    //  img1.setImage(image.getName(), image.getUrl());
			      panelImages.add(image);
			    }
			  };
			  
		this.tab = tab;
		tab.addClosableHandler(this);
		addForm.addEiaTypeSelectionListener(tab);
		setWidth100();
		setBackgroundColor("#E0E0E0");
		setStyleName("sides-padding top-padding");// Esto es VUDU!
		setAlign(Alignment.CENTER);

		Label title = new Label("<h3>Caracteristicas del EIA Type</h3>");
		title.setHeight(30);
		title.setWidth100();
		title.setStyleName("title-label");
		addMember(title);

		DynamicForm form = new DynamicForm();
		form.setTitleOrientation(TitleOrientation.TOP);
		form.setNumCols(4);
		form.setItems(brandItem, manItem, typeItem, subTypeItem,
				descriptionItem, mobilityItem, useDescriptionItem, codeItem,
				nameItem, modelItem, eiaUmdnsItem);
//		img1.setImageType(ImageStyle.STRETCH);  

		VLayout sideButtons = new VLayout();
		sideButtons.setWidth(30);
		sideButtons.setLayoutMargin(5);
		sideButtons.setBackgroundColor("#E0E0E0");
		sideButtons.setMembersMargin(10);
		sideButtons.setDefaultLayoutAlign(Alignment.CENTER);

		GHAButton addButton = new GHAButton("../resources/icons/new.png");
		addButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				addForm.animateShow(AnimationEffect.FLY);
			}
		});

		GHAButton saveButton = new GHAButton("../resources/icons/save.png");
		saveButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				save();
			}
		});
		GHAButton undoButton = new GHAButton("../resources/icons/undo.png");
		undoButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				undo();

			}
		});

		sideButtons.addMembers(saveButton, undoButton);
		sideButtons.addMember(GHAUiHelper.verticalGraySeparator("2px"));
		sideButtons.addMember(addButton);
		HLayout uploadImagenes = new HLayout();
		
		uploadImagenes.addMember(panelImages);
		uploadImagenes.addMember(uploadPhoto1);
		uploadPhoto1.addOnFinishUploadHandler(onFinishUploaderHandler);
		HLayout gridPanel = new HLayout();
		gridPanel.addMembers(form, new LayoutSpacer(), sideButtons);
		
		addMember(gridPanel);
		addMember(uploadImagenes);
		fillBrands();
		fillMans();
		fillExtras();
	}
	
	public void activateForm(boolean activate){
		codeItem.setDisabled(!activate);
		nameItem.setDisabled(!activate);
		modelItem.setDisabled(!activate);
		descriptionItem.setDisabled(!activate);
		descriptionItem.setDisabled(!activate);
		useDescriptionItem.setDisabled(!activate);
		useDescriptionItem.setDisabled(!activate);
		eiaUmdnsItem.setDisabled(!activate);
		manItem.setDisabled(!activate);
		brandItem.setDisabled(!activate);
		mobilityItem.setDisabled(!activate);
		typeItem.setDisabled(!activate);
		subTypeItem .setDisabled(!activate);
	}

	protected void undo() {
		select(this.orginalEiaType);
		save();
	}

	private void fillExtras() {
		// types
		LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
		for (EiaTypeEnum eiaTypeEnum : EiaTypeEnum.values())
			valueMap.put(eiaTypeEnum.name() + "", eiaTypeEnum.toString());
		typeItem.setValueMap(valueMap);
		// subtypes
		valueMap = new LinkedHashMap<String, String>();
		for (EiaSubTypeEnum subtype : EiaSubTypeEnum.values())
			valueMap.put(subtype.name() + "", subtype.toString());
		subTypeItem.setValueMap(valueMap);
		// mobility
		valueMap = new LinkedHashMap<String, String>();
		for (EiaMobilityEnum mobility : EiaMobilityEnum.values())
			valueMap.put(mobility.name() + "", mobility.toString());
		mobilityItem.setValueMap(valueMap);
	}

	private void fillMans() {
		GHACache.INSTANCE
				.getManufacturesrs(new GHAAsyncCallback<List<Manufacturer>>() {

					@Override
					public void onSuccess(List<Manufacturer> result) {
						LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
						for (Manufacturer manufacturer : result)
							valueMap.put(manufacturer.getId() + "",
									manufacturer.getName());
						manItem.setValueMap(valueMap);

					}
				});

	}

	private void fillBrands() {
		GHACache.INSTANCE.getBrands(new GHAAsyncCallback<List<Brand>>() {

			@Override
			public void onSuccess(List<Brand> result) {
				LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
				for (Brand brand : result)
					valueMap.put(brand.getId() + "", brand.getName());
				brandItem.setValueMap(valueMap);

			}
		});

	}

	@Override
	public void select(EiaType eiaType) {
		activateForm(true);
		
		this.eiaType = this.orginalEiaType = eiaType;
		if (eiaType.getBrand() != null)
			brandItem.setValue(eiaType.getBrand().getId());
		if (eiaType.getManufacturer() != null)
			manItem.setValue(eiaType.getManufacturer().getId());
		codeItem.setValue(eiaType.getCode());
		nameItem.setValue(eiaType.getName());
		descriptionItem.setValue(eiaType.getDescription());
		modelItem.setValue(eiaType.getModel());
		useDescriptionItem.setValue(eiaType.getUseDescription());
		eiaUmdnsItem.setValue(eiaType.getEiaUmdns());
		mobilityItem.setValue(eiaType.getMobility().name());
		typeItem.setValue(eiaType.getType().name());
		subTypeItem.setValue(eiaType.getSubtype().name());
	}

	private void save() {
		if (this.eiaType == null)
			return;
		final EiaType eiaType = new EiaType();
		eiaType.setId(this.eiaType.getId());
		if (brandItem.getValue() != null)
			eiaType.setBrand(new Brand(Integer.valueOf(brandItem
					.getValueAsString()), null));
		if (manItem.getValue() != null)
			eiaType.setManufacturer(new Manufacturer(Integer.valueOf(manItem
					.getValueAsString()), null));
		eiaType.setCode(codeItem.getValueAsString());
		eiaType.setName(nameItem.getValueAsString());
		eiaType.setDescription(descriptionItem.getValueAsString());
		eiaType.setModel(modelItem.getValueAsString());
		eiaType.setUseDescription(useDescriptionItem.getValueAsString());
		eiaType.setEiaUmdns(eiaUmdnsItem.getValueAsString());
		if (mobilityItem.getValue() != null)
			eiaType.setMobility(EiaMobilityEnum.valueOf(mobilityItem
					.getValueAsString()));
		if (typeItem.getValue() != null)
			eiaType.setType(EiaTypeEnum.valueOf(typeItem.getValueAsString()));
		if (subTypeItem.getValue() != null)
			eiaType.setSubtype(EiaSubTypeEnum.valueOf(subTypeItem
					.getValueAsString()));

		EIATypeModel.update(eiaType, new GHAAsyncCallback<EiaType>() {

			@Override
			public void onSuccess(EiaType result) {
				tab.select(result);
			}
		});

	}

	@Override
	public void close() {
		addForm.animateHide(AnimationEffect.SLIDE);
		addForm.destroy();
	}
}
