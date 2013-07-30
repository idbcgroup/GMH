package org.fourgeeks.gha.webclient.client.eiatype.information;

import gwtupload.client.IFileInput.FileInputType;
import gwtupload.client.IUploadStatus.Status;
import gwtupload.client.IUploader;
import gwtupload.client.IUploader.OnFinishUploaderHandler;
import gwtupload.client.IUploader.UploadedInfo;
import gwtupload.client.PreloadedImage;
import gwtupload.client.PreloadedImage.OnLoadPreloadedImageHandler;
import gwtupload.client.SingleUploader;

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
import org.fourgeeks.gha.domain.gmh.EiaTypePicture;
import org.fourgeeks.gha.domain.gmh.Manufacturer;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAImgButton;
import org.fourgeeks.gha.webclient.client.UI.GHACache;
import org.fourgeeks.gha.webclient.client.UI.GHAClosable;
import org.fourgeeks.gha.webclient.client.UI.GHACustomButton;
import org.fourgeeks.gha.webclient.client.UI.GHANotification;
import org.fourgeeks.gha.webclient.client.UI.GHASelectItem;
import org.fourgeeks.gha.webclient.client.UI.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.GHAUploadPhotographs;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeModel;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypePictureModel;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeTab;
import com.google.gwt.validation.client.impl.Validation;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.ImageStyle;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

public class EIATypeInformationFormPanel extends VLayout implements
		EIATypeSelectionListener, GHAClosable {
	private GHATextItem codeItem, nameItem, modelItem, descriptionItem,
			useDescriptionItem, eiaUmdnsItem;
	private GHASelectItem brandItem, manItem, mobilityItem, typeItem,
			subTypeItem;
	private EiaType eiaType, orginalEiaType;
	private EIATypeTab tab;
	private GHAUploadPhotographs uploadPhoto1;
	private OnFinishUploaderHandler onFinishUploaderHandler1,
			onFinishUploaderHandler2, onFinishUploaderHandler3;
	private OnLoadPreloadedImageHandler showImage1, showImage2, showImage3;
	private Img img1, img2, img3;
	private Validator validator;

	{
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

	private void setOnFinishUploaderHandler() {
		onFinishUploaderHandler1 = new OnFinishUploaderHandler() {
			@Override
			public void onFinish(IUploader uploader) {
				if (uploader.getStatus() == Status.SUCCESS) {
					new PreloadedImage(uploader.fileUrl(), showImage1);
					// The server sends useful information to the client by
					// default
					UploadedInfo info = uploader.getServerInfo();
					System.out.println("File name " + info.name);
					System.out.println("File content-type " + info.ctype);
					System.out.println("File size " + info.size);
					// You can send any customized message and parse it
					System.out.println("Server message " + info.message);
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
					// The server sends useful information to the client by
					// default
					UploadedInfo info = uploader.getServerInfo();
					System.out.println("File name " + info.name);
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
					// The server sends useful information to the client by
					// default
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

	public EIATypeInformationFormPanel(EIATypeTab tab) {
		activateForm(false);
		GHACustomButton buttonAddImage1 = new GHACustomButton();
		GHACustomButton buttonAddImage2 = new GHACustomButton();
		GHACustomButton buttonAddImage3 = new GHACustomButton();

		SingleUploader uploadPhoto1 = new SingleUploader(
				FileInputType.CUSTOM.with(buttonAddImage1));
		uploadPhoto1.setValidExtensions("jpg", "jpeg", "png", "gif");
		uploadPhoto1.setAutoSubmit(true);

		SingleUploader uploadPhoto2 = new SingleUploader(
				FileInputType.CUSTOM.with(buttonAddImage2));
		uploadPhoto2.setValidExtensions("jpg", "jpeg", "png", "gif");
		uploadPhoto2.setAutoSubmit(true);

		SingleUploader uploadPhoto3 = new SingleUploader(
				FileInputType.CUSTOM.with(buttonAddImage3));
		uploadPhoto3.setValidExtensions("jpg", "jpeg", "png", "gif");
		uploadPhoto3.setAutoSubmit(true);

		setOnFinishUploaderHandler();
		this.tab = tab;
		tab.addClosableHandler(this);
		setWidth100();
		setBackgroundColor("#E0E0E0");
		setStyleName("sides-padding top-padding");// Esto es VUDU!
		setAlign(Alignment.CENTER);

		// Label title = new Label("<h3>Caracteristicas del EIA Type</h3>");
		// title.setHeight(30);
		// title.setWidth100();
		// title.setStyleName("title-label");
		// addMember(title);

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

		GHAImgButton saveButton = new GHAImgButton("../resources/icons/save.png");
		saveButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				save();
			}
		});
		GHAImgButton undoButton = new GHAImgButton("../resources/icons/undo.png");
		undoButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				undo();

			}
		});

		sideButtons.addMembers(saveButton, undoButton);

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

	public void activateForm(boolean activate) {
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
		subTypeItem.setDisabled(!activate);
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
		if (eiaType.getSubtype() != null)
			subTypeItem.setValue(eiaType.getSubtype().name());
		showPhotographics(eiaType);
	}

	private void showPhotographics(EiaType eiaType) {
		EIATypePictureModel.find(eiaType,
				new GHAAsyncCallback<List<EiaTypePicture>>() {

					@Override
					public void onSuccess(List<EiaTypePicture> result) {

						if (result.get(0) != null) {
							EiaTypePicture picture1 = result.get(0);
							img1.setImage("1", picture1.getDescription()
									+ picture1.getPicture());
						}
						if (result.get(1) != null) {
							EiaTypePicture picture2 = result.get(1);
							img2.setImage("2", picture2.getDescription()
									+ picture2.getPicture());
						}
						if (result.get(2) != null) {
							EiaTypePicture picture3 = result.get(3);
							img3.setImage("3", picture3.getDescription()
									+ picture3.getPicture());
						}
					}

				});

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

		Set<ConstraintViolation<EiaType>> violations = validator
				.validate(eiaType);
		if (violations.isEmpty())
			EIATypeModel.update(eiaType, new GHAAsyncCallback<EiaType>() {

				@Override
				public void onSuccess(EiaType result) {
					tab.select(result);
				}
			});
		else
			// Mostrar solo la primera violación para evitar que salgan muchos
			// pop-ups sucesivos
			GHANotification.alert(violations.iterator().next().getMessage());

	}

	@Override
	public void close() {
	}
}
