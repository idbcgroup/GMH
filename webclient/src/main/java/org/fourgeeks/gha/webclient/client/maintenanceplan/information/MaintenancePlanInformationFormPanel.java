package org.fourgeeks.gha.webclient.client.maintenanceplan.information;

import java.util.LinkedHashMap;

import org.fourgeeks.gha.domain.enu.TimePeriodEnum;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASpacerItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.interfaces.GHAClosable;
import org.fourgeeks.gha.webclient.client.UI.interfaces.GHAHideable;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAImgButton;
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

		fillExtras();
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
		// Period of time
		LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
		for (TimePeriodEnum tpEnum : TimePeriodEnum.values())
			valueMap.put(tpEnum.name() + "", tpEnum.toString());
		periodOfTime.setValueMap(valueMap);
	}

	@Override
	public void select(EiaType eiaType) {
		activateForm(true);
//		
//		//reload brand and manufacturer forms, in order to avoid issues with new brands or manufacturers

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
		
	}

	@Override
	public void close() {

	}

	@Override
	public void hide() {

	}
}
