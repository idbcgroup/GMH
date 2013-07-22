package org.fourgeeks.gha.webclient.client.eiatype;

import gwtupload.client.IFileInput.FileInputType;
import gwtupload.client.IUploadStatus.Status;
import gwtupload.client.IUploader;
import gwtupload.client.SingleUploader;
import gwtupload.client.IUploader.OnFinishUploaderHandler;
import gwtupload.client.IUploader.UploadedInfo;
import gwtupload.client.PreloadedImage;
import gwtupload.client.PreloadedImage.OnLoadPreloadedImageHandler;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.fourgeeks.gha.domain.enu.EiaMobilityEnum;
import org.fourgeeks.gha.domain.enu.EiaSubTypeEnum;
import org.fourgeeks.gha.domain.enu.EiaTypeEnum;
import org.fourgeeks.gha.domain.gmh.Brand;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.Manufacturer;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAButton;
import org.fourgeeks.gha.webclient.client.UI.GHACache;
import org.fourgeeks.gha.webclient.client.UI.GHACustomButton;
import org.fourgeeks.gha.webclient.client.UI.GHASelectItem;
import org.fourgeeks.gha.webclient.client.UI.GHASlideInWindow;
import org.fourgeeks.gha.webclient.client.UI.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.GHAUploadPhotographs;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.Window;
import com.google.gwt.validation.client.impl.Validation;
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

public class EIATypeAddForm extends GHASlideInWindow {

	private List<EIATypeSelectionListener> listeners;
	private GHATextItem codeItem, nameItem, modelItem, descriptionItem,
			useDescriptionItem, eiaUmdnsItem;
	
	private OnFinishUploaderHandler onFinishUploaderHandler1, onFinishUploaderHandler2, onFinishUploaderHandler3;
	private OnLoadPreloadedImageHandler showImage1, showImage2, showImage3;
	private Img img1, img2, img3;
	private GHASelectItem brandItem, manItem, mobilityItem, typeItem,
			subTypeItem;
	private Validator validator;
	
	{
		listeners = new ArrayList<EIATypeSelectionListener>();
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
		
		img1 = new Img("../resources/img/default.png", 150, 100);  
		img1.setImageType(ImageStyle.STRETCH);  
		img1.setBorder("1px solid gray");  
		img1.setSize("150px", "130px");
		img1.setLeft(240); 
		
		img2 = new Img("../resources/img/default.png", 150, 100);  
		img2.setImageType(ImageStyle.STRETCH);  
		img2.setBorder("1px solid gray");  
		img2.setSize("150px", "130px");
		img2.setLeft(240); 
		
		img3 = new Img("../resources/img/default.png", 150, 100);  
		img3.setImageType(ImageStyle.STRETCH);  
		img3.setBorder("1px solid gray");  
		img3.setSize("150px", "130px");
		img3.setLeft(240);
		
		validator = Validation.buildDefaultValidatorFactory().getValidator();
	}

	private void setOnFinishUploaderHandler()
	{
		onFinishUploaderHandler1 = new OnFinishUploaderHandler() {
			@Override
			public void onFinish(IUploader uploader) {
				 if (uploader.getStatus() == Status.SUCCESS) {
				        new PreloadedImage(uploader.fileUrl(), showImage1);
				        // The server sends useful information to the client by default
				        UploadedInfo info = uploader.getServerInfo();
				        System.out.println("File name " + info.name);
				        System.out.println("File content-type " + info.ctype);
				        System.out.println("File size " + info.size);
				        // You can send any customized message and parse it 
				        System.out.println("Server message " + info.message);
				        SC.say("Server message " + info.message);
				        SC.say("File name " + info.name);
				      } else {
				    	  	UploadedInfo info = uploader.getServerInfo();
				    	  	SC.say("No se carga la foto");
				      }
				    }
			};
			showImage1 = new OnLoadPreloadedImageHandler() {
			    public void onLoad(PreloadedImage image) {
			     image.setWidth("150px");
			     image.setHeight("100px");
			     img1.addChild(image);
			    }
			  };
			  
		onFinishUploaderHandler2 = new OnFinishUploaderHandler() {
					@Override
					public void onFinish(IUploader uploader) {
						 if (uploader.getStatus() == Status.SUCCESS) {
						        new PreloadedImage(uploader.fileUrl(), showImage2);
						        // The server sends useful information to the client by default
						        UploadedInfo info = uploader.getServerInfo();
						        System.out.println("File name " + info.name);
						        SC.say("File name " + info.name);
						        System.out.println("File content-type " + info.ctype);
						        System.out.println("File size " + info.size);
						        // You can send any customized message and parse it 
						        System.out.println("Server message " + info.message);
						      }
						    }
					};
					showImage2 = new OnLoadPreloadedImageHandler() {
					    public void onLoad(PreloadedImage image) {
					     image.setWidth("150px");
					     image.setHeight("100px");
					     img2.addChild(image);
					    }
					  };
			
		onFinishUploaderHandler3 = new OnFinishUploaderHandler() {
							@Override
							public void onFinish(IUploader uploader) {
								 if (uploader.getStatus() == Status.SUCCESS) {
								        new PreloadedImage(uploader.fileUrl(), showImage3);
								        // The server sends useful information to the client by default
								        UploadedInfo info = uploader.getServerInfo();
								       
								        System.out.println("File name " + info.name);
								        System.out.println("File content-type " + info.ctype);
								        System.out.println("File size " + info.size);
								        // You can send any customized message and parse it 
								        System.out.println("Server message " + info.message);
								      }
								    }
							};
							showImage3 = new OnLoadPreloadedImageHandler() {
							    public void onLoad(PreloadedImage image) {
							     image.setWidth("150px");
							     image.setHeight("100px");
							     img3.addChild(image);
							    }
							  };
	}
	
	public EIATypeAddForm() {
		setHeight(GHAUiHelper.getBottomSectionHeight());
		setTop(240);
		
		GHACustomButton buttonAddImage1= new GHACustomButton();
		GHACustomButton buttonAddImage2= new GHACustomButton(); 
		GHACustomButton buttonAddImage3= new GHACustomButton(); 
		
		SingleUploader uploadPhoto1 = new SingleUploader(FileInputType.CUSTOM.with(buttonAddImage1));
		uploadPhoto1.setValidExtensions("jpg", "jpeg", "png", "gif");
		uploadPhoto1.setAutoSubmit(true);
		
		SingleUploader uploadPhoto2 = new SingleUploader(FileInputType.CUSTOM.with(buttonAddImage2));
		uploadPhoto2.setValidExtensions("jpg", "jpeg", "png", "gif");
		uploadPhoto2.setAutoSubmit(true);
		
		SingleUploader uploadPhoto3 = new SingleUploader(FileInputType.CUSTOM.with(buttonAddImage3));
		uploadPhoto3.setValidExtensions("jpg", "jpeg", "png", "gif");
		uploadPhoto3.setAutoSubmit(true);
		
		setOnFinishUploaderHandler();	  
		
		Label title = new Label("<h3>Agregar un EIA Type</h3>");
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

		VLayout sideButtons = new VLayout();
		sideButtons.setWidth(30);
		sideButtons.setLayoutMargin(5);
		sideButtons.setBackgroundColor("#E0E0E0");
		sideButtons.setMembersMargin(10);
		sideButtons.setDefaultLayoutAlign(Alignment.CENTER);

		GHAButton saveButton = new GHAButton("../resources/icons/save.png");
		saveButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				save();
			}
		});

		GHAButton cancelButton = new GHAButton("../resources/icons/cancel.png");
		cancelButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				EIATypeAddForm.this.animateHide(AnimationEffect.FLY);
				cancel();
				;

			}
		});

		sideButtons.addMembers(saveButton, cancelButton);
		
		HLayout uploadImagenes = new HLayout();
		uploadImagenes.addMember(img1);
		uploadImagenes.addMember(uploadPhoto1);
		uploadImagenes.addMember(img2);
		uploadImagenes.addMember(uploadPhoto2);
		uploadImagenes.addMember(img3);
		uploadImagenes.addMember(uploadPhoto3);
		
		uploadPhoto1.addOnFinishUploadHandler(onFinishUploaderHandler1);
		uploadPhoto2.addOnFinishUploadHandler(onFinishUploaderHandler2);
		uploadPhoto3.addOnFinishUploadHandler(onFinishUploaderHandler3);
		
		HLayout gridPanel = new HLayout();
		gridPanel.addMembers(form, new LayoutSpacer(), sideButtons);
		addMember(gridPanel);
		addMember(uploadImagenes);
		fillBrands();
		fillMans();
		fillExtras();
	}

	// protected void undo() {
	// select(this.orginalEiaType);
	// save();
	// }

	protected void cancel() {
		brandItem.clearValue();
		manItem.setValue("");
		codeItem.clearValue();
		nameItem.clearValue();
		descriptionItem.clearValue();
		modelItem.clearValue();
		useDescriptionItem.clearValue();
		eiaUmdnsItem.clearValue();
		mobilityItem.clearValue();
		typeItem.clearValue();
		subTypeItem.clearValue();
		EIATypeAddForm.this.animateHide(AnimationEffect.SLIDE);
	}

	private void fillExtras() {
		// types
		typeItem.setValueMap(EiaTypeEnum.toValueMap());
		// subtypes
		subTypeItem.setValueMap(EiaSubTypeEnum.toValueMap());
		// mobility
		mobilityItem.setValueMap(EiaMobilityEnum.toValueMap());
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

	// @Override
	// public void select(EiaType eiaType) {
	// this.eiaType = this.orginalEiaType = eiaType;
	// if (eiaType.getBrand() != null)
	// brandItem.setValue(eiaType.getBrand().getId());
	// if (eiaType.getManufacturer() != null)
	// manItem.setValue(eiaType.getManufacturer().getId());
	// codeItem.setValue(eiaType.getCode());
	// nameItem.setValue(eiaType.getName());
	// descriptionItem.setValue(eiaType.getDescription());
	// modelItem.setValue(eiaType.getModel());
	// useDescriptionItem.setValue(eiaType.getUseDescription());
	// eiaUmdnsItem.setValue(eiaType.getEiaUmdns());
	// mobilityItem.setValue(eiaType.getMobility().name());
	// typeItem.setValue(eiaType.getType().name());
	// subTypeItem.setValue(eiaType.getSubtype().name());
	// }

	private void save() {
		final EiaType eiaType = new EiaType();
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
		Set<ConstraintViolation<EiaType>> violations = validator.validate(eiaType);
		if (violations.isEmpty())
			EIATypeModel.save(eiaType, new GHAAsyncCallback<EiaType>() {
	
				@Override
				public void onSuccess(EiaType result) {
					select(result);
					cancel();
				}
			});
		else
			// Mostrar solo la primera violación para evitar que salgan muchos pop-ups sucesivos
			Window.alert(violations.iterator().next().getMessage());
	}

	protected void select(EiaType eiaType) {
		for (EIATypeSelectionListener listener : listeners)
			listener.select(eiaType);
	}

	public void addEiaTypeSelectionListener(EIATypeSelectionListener listener) {
		listeners.add(listener);
	}

	@Override
	public void onResize(ResizeEvent event) {
		setHeight(GHAUiHelper.getBottomSectionHeight());
	}

	@Override
	public void close() {
		destroy();
	}
}
