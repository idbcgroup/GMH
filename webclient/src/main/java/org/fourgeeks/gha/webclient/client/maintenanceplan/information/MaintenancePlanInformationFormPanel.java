package org.fourgeeks.gha.webclient.client.maintenanceplan.information;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAClosable;
import org.fourgeeks.gha.webclient.client.UI.GHAHideable;
import org.fourgeeks.gha.webclient.client.UI.GHAImgButton;
import org.fourgeeks.gha.webclient.client.UI.GHASelectItem;
import org.fourgeeks.gha.webclient.client.UI.GHASpacerItem;
import org.fourgeeks.gha.webclient.client.UI.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;
import org.fourgeeks.gha.webclient.client.maintenanceplan.MaintenancePlanTab;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.TitleOrientation;
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
public class MaintenancePlanInformationFormPanel extends VLayout implements
		EIATypeSelectionListener, GHAClosable, GHAHideable {
	
	private GHATextItem codeItem, frequencyItem, usesItem, descriptionItem;
	private GHASelectItem periodOfTime;
	private EiaType eiaType, orginalEiaType;
	private MaintenancePlanTab tab;

	{
		codeItem = new GHATextItem("Código", 150);
		frequencyItem = new GHATextItem("Frecuencia", 150);
		periodOfTime = new GHASelectItem("Periodo de Tiempo", 150);
		usesItem = new GHATextItem("Usos", 150);
		descriptionItem = new GHATextItem("Descripción", 620);
		descriptionItem.setColSpan(4);
	}

	public MaintenancePlanInformationFormPanel(MaintenancePlanTab tab) {
		activateForm(false);
		this.tab = tab;
		
		tab.addGHAClosableHandler(this);
		
		setWidth100();
		setBackgroundColor("#E0E0E0");
		setStyleName("sides-padding padding-top");// Esto es VUDU!
		setAlign(Alignment.CENTER);

		DynamicForm form = new DynamicForm();
		form.setTitleOrientation(TitleOrientation.TOP);
		form.setNumCols(4);
		form.setItems(codeItem, usesItem,new GHASpacerItem(2),
				      frequencyItem,periodOfTime,new GHASpacerItem(2), 
				      descriptionItem);

		VLayout sideButtons = GHAUiHelper.createBar(new GHAImgButton(
				"../resources/icons/save.png", new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						save();
					}
				}), new GHAImgButton("../resources/icons/undo.png",
				new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						undo();
					}
				}));

		HLayout gridPanel = new HLayout();
		gridPanel.addMembers(form, new LayoutSpacer(), sideButtons);

		addMember(gridPanel);

//		fillBrands(false);
//		fillMans(false);
//		fillExtras();
	}

	public void activateForm(boolean activate) {
		codeItem.setDisabled(!activate);
		frequencyItem.setDisabled(!activate);
		periodOfTime.setDisabled(!activate);
		usesItem.setDisabled(!activate);
		descriptionItem.setDisabled(!activate);
	}

	protected void undo() {
		select(this.orginalEiaType);
		save();
	}

	private void fillExtras() {
//		// types
//		LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
//		for (EiaTypeEnum eiaTypeEnum : EiaTypeEnum.values())
//			valueMap.put(eiaTypeEnum.name() + "", eiaTypeEnum.toString());
//		typeItem.setValueMap(valueMap);
//		// subtypes
//		valueMap = new LinkedHashMap<String, String>();
//		for (EiaSubTypeEnum subtype : EiaSubTypeEnum.values())
//			valueMap.put(subtype.name() + "", subtype.toString());
//		subTypeItem.setValueMap(valueMap);
//		// mobility
//		valueMap = new LinkedHashMap<String, String>();
//		for (EiaMobilityEnum mobility : EiaMobilityEnum.values())
//			valueMap.put(mobility.name() + "", mobility.toString());
//		mobilityItem.setValueMap(valueMap);
	}

	private void fillMans(boolean forceFromServer) {
//		GHACache.INSTANCE.getManufacturesrs(
//				new GHAAsyncCallback<List<Manufacturer>>() {
//
//					@Override
//					public void onSuccess(List<Manufacturer> result) {
//						LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
//						for (Manufacturer manufacturer : result)
//							valueMap.put(manufacturer.getId() + "",
//									manufacturer.getName());
//						manItem.setValueMap(valueMap);
//
//					}
//				}, forceFromServer);

	}

	private void fillBrands(boolean forceFromServer) {
//		GHACache.INSTANCE.getBrands(new GHAAsyncCallback<List<Brand>>() {
//
//			@Override
//			public void onSuccess(List<Brand> result) {
//				LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
//				for (Brand brand : result)
//					valueMap.put(brand.getId() + "", brand.getName());
//				brandItem.setValueMap(valueMap);
//
//			}
//		}, forceFromServer);

	}

	@Override
	public void select(EiaType eiaType) {
		activateForm(true);
//		
//		//reload brand and manufacturer forms, in order to avoid issues with new brands or manufacturers
//		fillBrands(true);
//		fillMans(true);
//
//		this.eiaType = this.orginalEiaType = eiaType;
//		if (eiaType.getBrand() != null)
//			brandItem.setValue(eiaType.getBrand().getId());
//		if (eiaType.getManufacturer() != null)
//			manItem.setValue(eiaType.getManufacturer().getId());
//		codeItem.setValue(eiaType.getCode());
//		nameItem.setValue(eiaType.getName());
//		descriptionItem.setValue(eiaType.getDescription());
//		modelItem.setValue(eiaType.getModel());
//		useDescriptionItem.setValue(eiaType.getUseDescription());
//		eiaUmdnsItem.setValue(eiaType.getEiaUmdns());
//		mobilityItem.setValue(eiaType.getMobility().name());
//		typeItem.setValue(eiaType.getType().name());
//		if (eiaType.getSubtype() != null)
//			subTypeItem.setValue(eiaType.getSubtype().name());
//		showPhotographics(eiaType);
	}

	private void save() {
//		if (this.eiaType == null)
//			return;
//		final EiaType eiaType = new EiaType();
//		eiaType.setCode(codeItem.getValueAsString());
//
//		if (brandItem.getValue() != null)
//			eiaType.setBrand(new Brand(Integer.valueOf(brandItem
//					.getValueAsString()), null));
//		if (manItem.getValue() != null)
//			eiaType.setManufacturer(new Manufacturer(Integer.valueOf(manItem
//					.getValueAsString()), null));
//
//		eiaType.setName(nameItem.getValueAsString());
//		eiaType.setDescription(descriptionItem.getValueAsString());
//		eiaType.setModel(modelItem.getValueAsString());
//		eiaType.setUseDescription(useDescriptionItem.getValueAsString());
//		eiaType.setEiaUmdns(eiaUmdnsItem.getValueAsString());
//		if (mobilityItem.getValue() != null)
//			eiaType.setMobility(EiaMobilityEnum.valueOf(mobilityItem
//					.getValueAsString()));
//		if (typeItem.getValue() != null)
//			eiaType.setType(EiaTypeEnum.valueOf(typeItem.getValueAsString()));
//		if (subTypeItem.getValue() != null)
//			eiaType.setSubtype(EiaSubTypeEnum.valueOf(subTypeItem
//					.getValueAsString()));
//		Set<ConstraintViolation<EiaType>> violations = validator
//				.validate(eiaType);
//		if (violations.isEmpty())
//			EIATypeModel.update(eiaType, new GHAAsyncCallback<EiaType>() {
//				@Override
//				public void onSuccess(EiaType eiaTyp) {
//					if (imageCount == 0) {
//						EIATypePictureModel.save(eiaTyp,
//								new GHAAsyncCallback<Void>() {
//
//									@Override
//									public void onSuccess(Void result) {
//										showPhotographics(eiaType);
//									}
//
//									@Override
//									public void onFailure(Throwable caught) {
//										Window.alert("Error actualizando el eiaTypePicture: "
//												+ caught.getMessage());
//									};
//								});
//					} else {
//						/**
//						 * En el arreglo noDeletePicture se guardan los id de
//						 * las imagenes que no se desean borrar
//						 */
//						int noDeletePicture[] = new int[3];
//						noDeletePicture[0] = idImg1;
//						noDeletePicture[1] = idImg2;
//						noDeletePicture[2] = idImg3;
//
//						EIATypePictureModel.update(eiaType, noDeletePicture,
//								new GHAAsyncCallback<Boolean>() {
//
//									@Override
//									public void onSuccess(Boolean result) {
//										showPhotographics(eiaType);
//									}
//
//									public void onFailure(Throwable caught) {
//										Window.alert("Error actualizando el eiaTypePicture: "
//												+ caught.getMessage());
//									};
//
//								});
//
//					}
//					tab.select(eiaTyp);
//				}
//
//				@Override
//				public void onFailure(Throwable caught) {
//					Window.alert("Error actualizando el eiaType: "
//							+ caught.getMessage());
//				}
//			});
//		else
//			// Mostrar solo la primera violación para evitar que salgan muchos
//			// pop-ups sucesivos
//			GHANotification.alert(violations.iterator().next().getMessage());

	}

	@Override
	public void close() {

	}

	@Override
	public void hide() {

	}
}
