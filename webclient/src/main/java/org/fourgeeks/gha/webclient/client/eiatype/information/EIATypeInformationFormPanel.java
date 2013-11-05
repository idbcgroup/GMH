package org.fourgeeks.gha.webclient.client.eiatype.information;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.icons.GHASaveButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHAUndoButton;
import org.fourgeeks.gha.webclient.client.UI.interfaces.GHAClosable;
import org.fourgeeks.gha.webclient.client.UI.interfaces.GHAHideable;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHANotification;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAVerticalLayout;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;
import org.fourgeeks.gha.webclient.client.eiatype.EiaTypeForm;
import org.fourgeeks.gha.webclient.client.eiatype.EiaTypeSelectionProducer;

import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret
 * 
 */
public class EIATypeInformationFormPanel extends GHAVerticalLayout implements
		EIATypeSelectionListener, EiaTypeSelectionProducer, GHAHideable,
		GHAClosable {

	private EiaTypeForm form;
	private List<EIATypeSelectionListener> listeners;

	{
		form = new EiaTypeForm();
		listeners = new ArrayList<EIATypeSelectionListener>();

		// // inicializando componentes de las imagenes
		// img1 = new Img("../resources/img/default.png", 130, 130);
		// img1.setImageType(ImageStyle.STRETCH);
		// img1.setBorder("1px solid gray");
		// // img1.setLeft(240);
		//
		// img2 = new Img("../resources/img/default.png", 130, 130);
		// img2.setImageType(ImageStyle.STRETCH);
		// img2.setBorder("1px solid gray");
		// // img2.setLeft(240);
		//
		// img3 = new Img("../resources/img/default.png", 130, 130);
		// img3.setImageType(ImageStyle.STRETCH);
		// img3.setBorder("1px solid gray");
		// // img3.setLeft(240);
	}

	/**
	 * crea los eventos OnFinishUploaderHandler que se ejecutan cuando termina
	 * la subida de la imagen
	 * 
	 * @param tab
	 */
	// private void setOnFinishUploaderHandler() {
	// onFinishUploaderHandler1 = new OnFinishUploaderHandler() {
	// @Override
	// public void onFinish(IUploader uploader) {
	// if (uploader.getStatus() == Status.SUCCESS) {
	// EIATypePictureModel.deletePictureFromSession(imgName1,
	// new GHAAsyncCallback<Void>() {
	// @Override
	// public void onSuccess(Void result) {
	//
	// }
	// });
	// UploadedInfo info = uploader.getServerInfo();
	// img1.setSrc(uploader.fileUrl());
	// idImg1 = -1;
	// imgName1 = info.message;
	// }
	// }
	// };
	//
	// onFinishUploaderHandler2 = new OnFinishUploaderHandler() {
	// @Override
	// public void onFinish(IUploader uploader) {
	// if (uploader.getStatus() == Status.SUCCESS) {
	// EIATypePictureModel.deletePictureFromSession(imgName2,
	// new GHAAsyncCallback<Void>() {
	// @Override
	// public void onSuccess(Void result) {
	//
	// }
	// });
	// UploadedInfo info = uploader.getServerInfo();
	// img2.setSrc(uploader.fileUrl());
	// idImg2 = -1;
	// imgName2 = info.message;
	// }
	// }
	// };
	//
	// onFinishUploaderHandler3 = new OnFinishUploaderHandler() {
	// @Override
	// public void onFinish(IUploader uploader) {
	// if (uploader.getStatus() == Status.SUCCESS) {
	// EIATypePictureModel.deletePictureFromSession(imgName3,
	// new GHAAsyncCallback<Void>() {
	// @Override
	// public void onSuccess(Void result) {
	//
	// }
	// });
	// UploadedInfo info = uploader.getServerInfo();
	// img3.setSrc(uploader.fileUrl());
	// idImg3 = -1;
	// imgName3 = info.message;
	// }
	// }
	// };
	// }

	public EIATypeInformationFormPanel() {
		super();

		VLayout sideButtons = GHAUiHelper.createBar(new GHASaveButton(
				new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						save();
					}
				}), new GHAUndoButton(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				undo();
			}
		}));

		HLayout gridPanel = new HLayout();
		gridPanel.addMembers(form, new LayoutSpacer(), sideButtons);

		/**************** COMPONENTE PARA SUBIDA DE IMAGEN ****************************************/
		// SingleUploader uploadPhoto1 = new
		// SingleUploader(FileInputType.BUTTON);
		// // uploadPhoto1.setStyleName("GHAupload-button");
		// uploadPhoto1.getWidget().setStylePrimaryName("GHAupload-button");
		// uploadPhoto1.getWidget().setSize("20px", "20px");
		// uploadPhoto1.setValidExtensions("jpg", "jpeg", "png", "gif");
		// uploadPhoto1.setAutoSubmit(true);
		//
		// SingleUploader uploadPhoto2 = new
		// SingleUploader(FileInputType.BUTTON);
		// // uploadPhoto2.setStyleName("GHAupload-button");
		// uploadPhoto2.getWidget().setStylePrimaryName("GHAupload-button");
		// uploadPhoto2.getWidget().setSize("20px", "20px");
		// uploadPhoto2.setValidExtensions("jpg", "jpeg", "png", "gif");
		// uploadPhoto2.setAutoSubmit(true);
		//
		// SingleUploader uploadPhoto3 = new
		// SingleUploader(FileInputType.BUTTON);
		// // uploadPhoto3.setStyleName("GHAupload-button");
		// uploadPhoto3.getWidget().setStylePrimaryName("GHAupload-button");
		// uploadPhoto3.getWidget().setSize("20px", "20px");
		// uploadPhoto3.setValidExtensions("jpg", "jpeg", "png", "gif");
		// uploadPhoto3.setAutoSubmit(true);
		//
		// setOnFinishUploaderHandler();
		//
		// GHAImgButton deleteButton1 = new GHAImgButton(
		// "../resources/icons/delete.png", new ClickHandler() {
		//
		// @Override
		// public void onClick(ClickEvent event) {
		//
		// img1.setSrc("../resources/img/default.png");
		// idImg1 = -1;
		// EIATypePictureModel.deletePictureFromSession(imgName1,
		// new GHAAsyncCallback<Void>() {
		//
		// @Override
		// public void onSuccess(Void result) {
		// imgName1 = "nothing";
		// }
		//
		// });
		// }
		// });
		// GHAImgButton deleteButton2 = new GHAImgButton(
		// "../resources/icons/delete.png", new ClickHandler() {
		//
		// @Override
		// public void onClick(ClickEvent event) {
		//
		// img2.setSrc("../resources/img/default.png");
		// idImg2 = -1;
		//
		// EIATypePictureModel.deletePictureFromSession(imgName2,
		// new GHAAsyncCallback<Void>() {
		//
		// @Override
		// public void onSuccess(Void result) {
		// imgName2 = "nothing";
		// }
		//
		// });
		// }
		// });
		// GHAImgButton deleteButton3 = new GHAImgButton(
		// "../resources/icons/delete.png", new ClickHandler() {
		// @Override
		// public void onClick(ClickEvent event) {
		// img3.setSrc("../resources/img/default.png");
		// idImg3 = -1;
		// EIATypePictureModel.deletePictureFromSession(imgName3,
		// new GHAAsyncCallback<Void>() {
		//
		// @Override
		// public void onSuccess(Void result) {
		// imgName3 = "nothing";
		// }
		// });
		// }
		// });
		//
		// HLayout uploadImagenes = new HLayout();
		//
		// VLayout buttons1 = new VLayout();
		// buttons1.setWidth(30);
		// buttons1.setLayoutMargin(5);
		// buttons1.setMembersMargin(10);
		// // buttons1.setDefaultLayoutAlign(Alignment.CENTER);
		// buttons1.addMember(uploadPhoto1);
		// buttons1.addMember(deleteButton1);
		//
		// VLayout buttons2 = new VLayout();
		// buttons2.setWidth(30);
		// buttons2.setLayoutMargin(3);
		// buttons2.setMembersMargin(10);
		// // buttons2.setDefaultLayoutAlign(Alignment.CENTER);
		// buttons2.addMember(uploadPhoto2);
		// buttons2.addMember(deleteButton2);
		//
		// VLayout buttons3 = new VLayout();
		// buttons3.setWidth(30);
		// buttons3.setLayoutMargin(5);
		// buttons3.setMembersMargin(10);
		// // buttons3.setDefaultLayoutAlign(Alignment.CENTER);
		// buttons3.addMember(uploadPhoto3);
		// buttons3.addMember(deleteButton3);
		//
		// uploadImagenes.addMembers(img1, buttons1, img2, buttons2, img3,
		// buttons3, new LayoutSpacer());
		//
		// uploadPhoto1.addOnFinishUploadHandler(onFinishUploaderHandler1);
		// uploadPhoto2.addOnFinishUploadHandler(onFinishUploaderHandler2);
		// uploadPhoto3.addOnFinishUploadHandler(onFinishUploaderHandler3);
		//
		/****************************************************************************************/

		addMember(gridPanel);
		// addMember(uploadImagenes);

		// register as eiatypeselected listener with eiatypeform
		// form.addEiaTypeSelectionListener(this);
	}

	/**
	 *	
	 */
	public void activate() {
		form.activate();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.eiatype.EiaTypeSelectionProducer#
	 * addEiaTypeSelectionListener
	 * (org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener)
	 */
	@Override
	public void addEiaTypeSelectionListener(
			EIATypeSelectionListener eIATypeSelectionListener) {
		listeners.add(eIATypeSelectionListener);
	}

	@Override
	public boolean canBeClosen() {
		if (form.hasUnCommittedChanges()) {
			GHANotification.confirm(GHAStrings.get("information"),
					GHAStrings.get("unsaved-changes"), new BooleanCallback() {

						@Override
						public void execute(Boolean value) {
							if (value) {
								form.undo();
							}
						}
					});
			return false;
		}
		return true;
	}

	@Override
	public boolean canBeHidden() {
		if (form.hasUnCommittedChanges()) {
			GHANotification.confirm(GHAStrings.get("information"),
					GHAStrings.get("unsaved-changes"), new BooleanCallback() {

						@Override
						public void execute(Boolean value) {
							if (value) {
								form.undo();
							}
						}
					});
			return false;
		}
		return true;
	}

	@Override
	public void close() {
		destroy();
	}

	@Override
	public void hide() {

	}

	@Override
	public void notifyEiaType(EiaType eiaType) {
		for (EIATypeSelectionListener listener : listeners)
			listener.select(eiaType);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.eiatype.EiaTypeSelectionProducer#
	 * removeEiaTypeSelectionListener
	 * (org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener)
	 */
	@Override
	public void removeEiaTypeSelectionListener(
			EIATypeSelectionListener eIATypeSelectionListener) {
		listeners.remove(eIATypeSelectionListener);
	}

	/**
	 * Muestra las fotografias que tiene un EiaType
	 * 
	 * @param eiaType
	 */
	// private void showPhotographics(EiaType eiaType) {
	//
	// EIATypePictureModel.findByEiaType(eiaType,
	// new GHAAsyncCallback<List<EiaTypePicture>>() {
	//
	// @Override
	// public void onSuccess(List<EiaTypePicture> result) {
	// img1.setSrc("../resources/img/default.png");
	// img2.setSrc("../resources/img/default.png");
	// img3.setSrc("../resources/img/default.png");
	// imageCount = result.size();
	// if (imageCount != 0) {
	// if (result.get(0) != null) {
	// EiaTypePicture picture1 = result.get(0);
	// img1.setSrc("../webclient/picture/eiaType/"
	// + picture1.getPicture());
	// idImg1 = (int) picture1.getId();
	// imgName1 = picture1.getPicture();
	// }
	// if (result.get(1) != null) {
	// EiaTypePicture picture2 = result.get(1);
	// img2.setSrc("../webclient/picture/eiaType/"
	// + picture2.getPicture());
	// idImg2 = (int) picture2.getId();
	// imgName2 = picture2.getPicture();
	// }
	// if (result.get(2) != null) {
	// EiaTypePicture picture3 = result.get(2);
	// img3.setSrc("../webclient/picture/eiaType/"
	// + picture3.getPicture());
	// idImg3 = (int) picture3.getId();
	// imgName3 = picture3.getPicture();
	// }
	//
	// } else {
	// img1.setSrc("../resources/img/default.png");
	// img2.setSrc("../resources/img/default.png");
	// img3.setSrc("../resources/img/default.png");
	// idImg1 = -1;
	// idImg2 = -1;
	// idImg3 = -1;
	// imgName1 = "nothing ";
	// imgName2 = "nothing";
	// imgName3 = "nothing";
	// }
	// }
	// });
	//
	// }

	private void save() {
		form.update();
		// if (this.eiaType == null)
		// return;
		// final EiaType eiaType = new EiaType();
		// eiaType.setCode(codeItem.getValueAsString());
		//
		// if (brandItem.getValue() != null)
		// eiaType.setBrand(new Brand(Integer.valueOf(brandItem
		// .getValueAsString()), null));
		// // if (manItem.getValue() != null)
		// // eiaType.setManufacturer(new Manufacturer(Integer.valueOf(manItem
		// // .getValueAsString()), null));
		//
		// eiaType.setName(nameItem.getValueAsString());
		// eiaType.setDescription(descriptionItem.getValueAsString());
		// eiaType.setModel(modelItem.getValueAsString());
		// eiaType.setUseDescription(useDescriptionItem.getValueAsString());
		// eiaType.setEiaUmdns(eiaUmdnsItem.getValueAsString());
		// if (mobilityItem.getValue() != null)
		// eiaType.setMobility(EiaMobilityEnum.valueOf(mobilityItem
		// .getValueAsString()));
		// if (typeItem.getValue() != null)
		// eiaType.setType(EiaTypeEnum.valueOf(typeItem.getValueAsString()));
		// if (subTypeItem.getValue() != null)
		// eiaType.setSubtype(EiaSubTypeEnum.valueOf(subTypeItem
		// .getValueAsString()));
		// Set<ConstraintViolation<EiaType>> violations = validator
		// .validate(eiaType);
		// if (violations.isEmpty())
		// EIATypeModel.update(eiaType, new GHAAsyncCallback<EiaType>() {
		// @Override
		// public void onSuccess(EiaType eiaTyp) {
		// // if (imageCount == 0) {
		// // EIATypePictureModel.save(eiaTyp,
		// // new GHAAsyncCallback<Void>() {
		// //
		// // @Override
		// // public void onSuccess(Void result) {
		// // showPhotographics(eiaType);
		// // }
		// //
		// // @Override
		// // public void onFailure(Throwable caught) {
		// // Window.alert("Error actualizando el eiaTypePicture: "
		// // + caught.getMessage());
		// // };
		// // });
		// // } else {
		// // /**
		// // * En el arreglo noDeletePicture se guardan los id de
		// // * las imagenes que no se desean borrar
		// // */
		// // int noDeletePicture[] = new int[3];
		// // noDeletePicture[0] = idImg1;
		// // noDeletePicture[1] = idImg2;
		// // noDeletePicture[2] = idImg3;
		// //
		// // EIATypePictureModel.update(eiaType, noDeletePicture,
		// // new GHAAsyncCallback<Boolean>() {
		// //
		// // @Override
		// // public void onSuccess(Boolean result) {
		// // showPhotographics(eiaType);
		// // }
		// //
		// // public void onFailure(Throwable caught) {
		// // Window.alert("Error actualizando el eiaTypePicture: "
		// // + caught.getMessage());
		// // };
		// //
		// // });
		// //
		// // }
		// tab.select(eiaTyp);
		// }
		//
		// @Override
		// public void onFailure(Throwable caught) {
		// Window.alert("Error actualizando el eiaType: "
		// + caught.getMessage());
		// }
		// });
		// else
		// // Mostrar solo la primera violación para evitar que salgan muchos
		// // pop-ups sucesivos
		// GHANotification.alert(violations.iterator().next().getMessage());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener#select
	 * (org.fourgeeks.gha.domain.gmh.EiaType)
	 */
	@Override
	public void select(EiaType eiaType) {
		notifyEiaType(eiaType);
	}

	/**
	 * @param eiaType
	 */
	public void setEiaType(EiaType eiaType) {
		form.setEiaType(eiaType);

		activate();
		// showPhotographics(eiaType);
	}

	protected void undo() {
		form.undo();
	}
}
