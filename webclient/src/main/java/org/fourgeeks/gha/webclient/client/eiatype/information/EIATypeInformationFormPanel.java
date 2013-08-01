package org.fourgeeks.gha.webclient.client.eiatype.information;

import gwtupload.client.IFileInput.FileInputType;
import gwtupload.client.IUploadStatus.Status;
import gwtupload.client.IUploader;
import gwtupload.client.IUploader.OnChangeUploaderHandler;
import gwtupload.client.IUploader.OnFinishUploaderHandler;
import gwtupload.client.IUploader.UploadedInfo;
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
import org.fourgeeks.gha.webclient.client.UI.GHACache;
import org.fourgeeks.gha.webclient.client.UI.GHAClosable;
import org.fourgeeks.gha.webclient.client.UI.GHACustomButton;
import org.fourgeeks.gha.webclient.client.UI.GHAImgButton;
import org.fourgeeks.gha.webclient.client.UI.GHANotification;
import org.fourgeeks.gha.webclient.client.UI.GHASelectItem;
import org.fourgeeks.gha.webclient.client.UI.GHATextItem;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeModel;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypePictureModel;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeTab;

import com.google.gwt.user.client.Window;
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

	private OnFinishUploaderHandler onFinishUploaderHandler1,
			onFinishUploaderHandler2, onFinishUploaderHandler3;
	private OnChangeUploaderHandler onChangeUploaderHandler1,
			onChangeUploaderHandler2, onChangeUploaderHandler3;
	private Img img1, img2, img3;
	private int idImg1, idImg2, idImg3;
	private String imgName1, imgName2, imgName3;
	private Validator validator;

	private int imageCount;
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
	}

	private void inicializarComponentesImg() {
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
					// new PreloadedImage( uploader.fileUrl(), showImage1);
					// The server sends useful information to the client by
					// default
					// Borra la imagen anterior de la session
					EIATypePictureModel.deletePictureFromSession(imgName1,
							new GHAAsyncCallback<Void>() {
								@Override
								public void onSuccess(Void result) {

								}
							});
					UploadedInfo info = uploader.getServerInfo();
					// System.out.println("File name " + info.name);
					// Window.alert("url: "+uploader.fileUrl());
					img1.setSrc(uploader.fileUrl());
					idImg1 = -1;
					imgName1 = info.message;
					// Window.alert("1: "+uploader.getFileName());
					// Window.alert("2: "+uploader.getFileInput().getFilename());
					// Window.alert("3: "+uploader.getInputName());
					// Window.alert("4: "+info.message);
					// img1.setID(null);
					// Window.alert("id: "+img1.getID());
					// System.out.println("File content-type " + info.ctype);
					// System.out.println("File size " + info.size);
					// // You can send any customized message and parse it
					// System.out.println("Server message " + info.message);
				}
			}
		};
		// showImage1 = new OnLoadPreloadedImageHandler() {
		// public void onLoad(PreloadedImage image) {
		// image.setWidth("150px");
		// image.setHeight("100px");
		// Window.alert("remover "+image.getName());
		//
		// img1.removeFromParent();
		// Window.alert("img1 removido");
		// img1.addChild(image);
		// child = image;
		// Window.alert("img1 iingresado");
		// img1.setSrc("../resources/img/default.png");
		// img1.setID(image.getName());
		// }
		// };

		onFinishUploaderHandler2 = new OnFinishUploaderHandler() {
			@Override
			public void onFinish(IUploader uploader) {
				if (uploader.getStatus() == Status.SUCCESS) {
					// new PreloadedImage(uploader.fileUrl(), showImage2);
					// The server sends useful information to the client by
					// default
					// Borra la imagen anterior de la session
					EIATypePictureModel.deletePictureFromSession(imgName2,
							new GHAAsyncCallback<Void>() {
								@Override
								public void onSuccess(Void result) {

								}
							});
					UploadedInfo info = uploader.getServerInfo();
					img2.setSrc(uploader.fileUrl());
					idImg2 = -1;
					imgName2 = info.message;

					// img2.setID(null);
					// Window.alert("id: "+img2.getID());
					// System.out.println("File name " + info.name);
					// System.out.println("File content-type " + info.ctype);
					// System.out.println("File size " + info.size);
					// // You can send any customized message and parse it
					// System.out.println("Server message " + info.message);
				}
			}
		};

		onFinishUploaderHandler3 = new OnFinishUploaderHandler() {
			@Override
			public void onFinish(IUploader uploader) {
				if (uploader.getStatus() == Status.SUCCESS) {
					// new PreloadedImage(uploader.fileUrl(), showImage3);
					// The server sends useful information to the client by
					// default
					// Borra la imagen anterior de la session
					EIATypePictureModel.deletePictureFromSession(imgName3,
							new GHAAsyncCallback<Void>() {
								@Override
								public void onSuccess(Void result) {

								}
							});
					UploadedInfo info = uploader.getServerInfo();
					img3.setSrc(uploader.fileUrl());
					idImg3 = -1;
					imgName3 = info.message;
					// img3.setID(null);
					// //Window.alert("id: "+img3.getID());
					// System.out.println("File name " + info.name);
					// System.out.println("File content-type " + info.ctype);
					// System.out.println("File size " + info.size);
					// You can send any customized message and parse it
					// System.out.println("Server message " + info.message);
				}
			}
		};
	}

	private void setOnChangeUploaderHandler() {
		onChangeUploaderHandler1 = new OnChangeUploaderHandler() {

			@Override
			public void onChange(IUploader uploader) {
				// Window.alert("cambio imagen a borrar la anterior: "+imgName1);
				EIATypePictureModel.deletePictureFromSession(imgName1,
						new GHAAsyncCallback<Void>() {

							@Override
							public void onSuccess(Void result) {
								Window.alert("Imagen borrada exitosamente: "
										+ imgName1);
							}

						});
				// imgName1 = uploader.getFileName();
				// Window.alert("nueva imagen a agregada : "+imgName1);
			}

		};

		onChangeUploaderHandler2 = new OnChangeUploaderHandler() {

			@Override
			public void onChange(IUploader uploader) {
				EIATypePictureModel.deletePictureFromSession(imgName2,
						new GHAAsyncCallback<Void>() {
							@Override
							public void onSuccess(Void result) {
								Window.alert("Imagen borrada exitosamente: "
										+ imgName1);
							}
						});
			}
		};

		onChangeUploaderHandler3 = new OnChangeUploaderHandler() {

			@Override
			public void onChange(IUploader uploader) {
				EIATypePictureModel.deletePictureFromSession(imgName3,
						new GHAAsyncCallback<Void>() {
							@Override
							public void onSuccess(Void result) {
								Window.alert("Imagen borrada exitosamente: "
										+ imgName1);
							}

						});
			}

		};
	}

	public EIATypeInformationFormPanel(EIATypeTab tab) {
		activateForm(false);
		inicializarComponentesImg();
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
		setOnChangeUploaderHandler();
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

		GHAImgButton saveButton = new GHAImgButton(
				"../resources/icons/save.png");
		saveButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				save();
			}
		});
		GHAImgButton undoButton = new GHAImgButton(
				"../resources/icons/undo.png");
		undoButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				undo();

			}
		});

		GHAImgButton deleteButton1 = new GHAImgButton(
				"../resources/icons/delete.png");
		deleteButton1.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				img1.setSrc("../resources/img/default.png");
				idImg1 = -1;
				EIATypePictureModel.deletePictureFromSession(imgName1,
						new GHAAsyncCallback<Void>() {

							@Override
							public void onSuccess(Void result) {
								Window.alert("Imagen borrada exitosamente: "
										+ imgName1);
								imgName1 = "nothing";
							}

						});
				// img1.setID(null);
				// Window.alert("id: "+img1.getID());
				// img1.setID("borrada");
				// com.google.gwt.user.client.Window.open("realizarReporteFichas?pozos="
				// + pozoPrincipal.getNombre()
				// + "&formato=pdf", "_this", "");
				// <servlet>
				// <servlet-name>ServicioRealizarReporteHttpServlet</servlet-name>
				// <servlet-class>ve.org.petrociencia.fichapozo.servicios.reportefichas.ServicioRealizarReporteHttpServlet</servlet-class>
				// </servlet>
				// <servlet-mapping>
				// <servlet-name>ServicioRealizarReporteHttpServlet</servlet-name>
				// <url-pattern>/realizarReporteFichas</url-pattern>
				// </servlet-mapping>
				// <url-pattern>/removeImageSessionHttpServlet</url-pattern>

			}
		});
		GHAImgButton deleteButton2 = new GHAImgButton(
				"../resources/icons/delete.png");
		deleteButton2.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				img2.setSrc("../resources/img/default.png");
				idImg2 = -1;

				EIATypePictureModel.deletePictureFromSession(imgName2,
						new GHAAsyncCallback<Void>() {

							@Override
							public void onSuccess(Void result) {
								Window.alert("Imagen borrada exitosamente: "
										+ imgName2);
								imgName2 = "nothing";
							}

						});
			}
		});
		GHAImgButton deleteButton3 = new GHAImgButton(
				"../resources/icons/delete.png");
		deleteButton3.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				img3.setSrc("../resources/img/default.png");
				idImg3 = -1;
				EIATypePictureModel.deletePictureFromSession(imgName3,
						new GHAAsyncCallback<Void>() {

							@Override
							public void onSuccess(Void result) {
								Window.alert("Imagen borrada exitosamente: "
										+ imgName3);
								imgName3 = "nothing";
							}

						});

			}
		});

		sideButtons.addMembers(saveButton, undoButton);

		HLayout uploadImagenes = new HLayout();
		VLayout buttons1 = new VLayout();
		// buttons1.setWidth(30);
		// buttons1.setHeight(10);

		buttons1.setLayoutMargin(2);
		buttons1.setBackgroundColor("#E0E0E0");
		buttons1.setMembersMargin(2);
		// buttons1.setDefaultLayoutAlign(Alignment.CENTER);
		buttons1.setAutoWidth();
		VLayout buttons2 = new VLayout();
		buttons2.setLayoutMargin(2);
		buttons2.setBackgroundColor("#E0E0E0");
		buttons2.setMembersMargin(2);
		buttons2.setAutoWidth();

		VLayout buttons3 = new VLayout();
		buttons3.setLayoutMargin(2);
		buttons3.setBackgroundColor("#E0E0E0");
		buttons3.setMembersMargin(2);
		buttons3.setAutoWidth();

		uploadImagenes.addMember(img1);
		buttons1.addMember(uploadPhoto1);
		buttons1.addMember(deleteButton1);

		uploadImagenes.addMember(buttons1);
		uploadImagenes.addMember(img2);
		buttons2.addMember(uploadPhoto2);
		buttons2.addMember(deleteButton2);
		uploadImagenes.addMember(buttons2);
		uploadImagenes.addMember(img3);
		buttons3.addMember(uploadPhoto3);
		buttons3.addMember(deleteButton3);
		uploadImagenes.addMember(buttons3);

		uploadPhoto1.addOnFinishUploadHandler(onFinishUploaderHandler1);
		// uploadPhoto1.addOnChangeUploadHandler(onChangeUploaderHandler1);

		uploadPhoto2.addOnFinishUploadHandler(onFinishUploaderHandler2);
		// uploadPhoto2.addOnChangeUploadHandler(onChangeUploaderHandler2);

		uploadPhoto3.addOnFinishUploadHandler(onFinishUploaderHandler3);
		// uploadPhoto3.addOnChangeUploadHandler(onChangeUploaderHandler3);

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

	/**
	 * Muestra las fotografias que tiene un EiaType
	 * 
	 * @param eiaType
	 */
	private void showPhotographics(EiaType eiaType) {

		EIATypePictureModel.find(eiaType,
				new GHAAsyncCallback<List<EiaTypePicture>>() {

					@Override
					public void onSuccess(List<EiaTypePicture> result) {
						Window.alert("Num. Imagenes: " + result.size());
						img1.setSrc("../resources/img/default.png");
						img2.setSrc("../resources/img/default.png");
						img3.setSrc("../resources/img/default.png");
						imageCount = result.size();
						if (imageCount != 0) {
							if (result.get(0) != null) {
								EiaTypePicture picture1 = result.get(0);
								img1.setSrc("../webclient/picture/eiaType/"
										+ picture1.getPicture());
								idImg1 = (int) picture1.getId();
								imgName1 = picture1.getPicture();
							}
							if (result.get(1) != null) {
								EiaTypePicture picture2 = result.get(1);
								img2.setSrc("../webclient/picture/eiaType/"
										+ picture2.getPicture());
								idImg2 = (int) picture2.getId();
								imgName2 = picture2.getPicture();
							}
							if (result.get(2) != null) {
								EiaTypePicture picture3 = result.get(2);
								img3.setSrc("../webclient/picture/eiaType/"
										+ picture3.getPicture());
								idImg3 = (int) picture3.getId();
								imgName3 = picture3.getPicture();
							}

						} else {
							img1.setSrc("../resources/img/default.png");
							img2.setSrc("../resources/img/default.png");
							img3.setSrc("../resources/img/default.png");
							idImg1 = -1;
							idImg2 = -1;
							idImg3 = -1;
							imgName1 = "nothing ";
							imgName2 = "nothing";
							imgName3 = "nothing";
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
				public void onSuccess(EiaType eiaTyp) {
					if (imageCount == 0) {
						EIATypePictureModel.save(eiaTyp,
								new GHAAsyncCallback<Void>() {

									@Override
									public void onSuccess(Void result) {
										Window.alert("Imagenes almacenadas satisfactoriamente");
										showPhotographics(eiaType);
									}

									@Override
									public void onFailure(Throwable caught) {
										Window.alert("Error actualizando el eiaTypePicture: "
												+ caught.getMessage());
									};
								});
					} else {
						int noDeletePicture[] = new int[3];
						noDeletePicture[0] = idImg1;
						noDeletePicture[1] = idImg2;
						noDeletePicture[2] = idImg3;
						Window.alert("noDeletePicture[0] = " + idImg1
								+ "\n noDeletePicture[1] = " + idImg2
								+ "\n noDeletePicture[2] = " + idImg3);
						EIATypePictureModel.update(eiaType, noDeletePicture,
								new GHAAsyncCallback<Boolean>() {

									@Override
									public void onSuccess(Boolean result) {
										Window.alert("EiaTypePictures actualizados correctamente");
										showPhotographics(eiaType);
									}

									public void onFailure(Throwable caught) {
										Window.alert("Error actualizando el eiaTypePicture: "
												+ caught.getMessage());
									};

								});

					}
					// tab.select(result)
				}

				@Override
				public void onFailure(Throwable caught) {
					Window.alert("Error actualizando el eiaType: "
							+ caught.getMessage());
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
