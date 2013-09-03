package org.fourgeeks.gha.webclient.client.maintenanceprotocol;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Validator;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASpacerItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAImgButton;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHASlideInWindow;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;
import org.fourgeeks.gha.webclient.client.eiatype.EiaTypeSelectionProducer;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.validation.client.impl.Validation;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

public class MaintenanceProtocolAddForm extends GHASlideInWindow implements
		EiaTypeSelectionProducer {

	private List<EIATypeSelectionListener> listeners;
	private GHATextItem nameItem, descriptionItem;
	private Validator validator;

	{
		listeners = new ArrayList<EIATypeSelectionListener>();
		nameItem = new GHATextItem("Código", 150);
		descriptionItem = new GHATextItem("Descripción", 620);
		descriptionItem.setColSpan(4);

		validator = Validation.buildDefaultValidatorFactory().getValidator();
	}

	public MaintenanceProtocolAddForm() {
		super(2);
		setHeight(GHAUiHelper.getBottomSectionHeight());
		setTop(240);

		Label title = new Label("<h3>Nuevo Protocolo de Mantenimiento</h3>");
		title.setHeight(30);
		title.setWidth100();
		title.setStyleName("title-label");
		addMember(title);

		final DynamicForm form = new DynamicForm();
		form.setTitleOrientation(TitleOrientation.TOP);
		form.setNumCols(4);
		form.setItems(nameItem, new GHASpacerItem(3),
			          descriptionItem);

		VLayout sideButtons = GHAUiHelper.createBar(new GHAImgButton(
				"../resources/icons/save.png", new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						save();
					}
				}), new GHAImgButton("../resources/icons/cancel.png",
				new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						cancel();
					}
				}));

		HLayout gridPanel = new HLayout();
		gridPanel.addMembers(form, new LayoutSpacer(), sideButtons);
		addMember(gridPanel);
		
		fillExtras();
	}

	// protected void undo() {
	// select(this.orginalEiaType);
	// save();
	// }

	protected void cancel() {
		hide();
		nameItem.clearValue();
		descriptionItem.clearValue();
	}

	private void fillExtras() {
		//TODO: 
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
//		final EiaType eiaType = new EiaType();
//		if (brandItem.getValue() != null) {
//			if (brandItem.getValueAsString().matches("[1-9]+\\d*")) {
//				eiaType.setBrand(new Brand(Integer.valueOf(brandItem
//						.getValueAsString()), null));
//			} else {
//				eiaType.setBrand(new Brand(brandItem.getValueAsString()));
//			}
//		}
//		if (manItem.getValue() != null) {
//			if (manItem.getValueAsString().matches("[1-9]+\\d*")) {
//				eiaType.setManufacturer(new Manufacturer(Integer
//						.valueOf(manItem.getValueAsString()), null));
//			} else {
//				eiaType.setManufacturer(new Manufacturer(manItem
//						.getValueAsString()));
//			}
//		}
//		eiaType.setCode(codeItem.getValueAsString());
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
//
//		Set<ConstraintViolation<EiaType>> violations = validator
//				.validate(eiaType);
//		if (violations.isEmpty())
//			EIATypeModel.save(eiaType, new GHAAsyncCallback<EiaType>() {
//
//				@Override
//				public void onSuccess(EiaType result) {
//					select(result);
//					cancel();
//				}
//			});
//		else
//			GHANotification.alert(violations.iterator().next().getMessage());
	}

	protected void select(EiaType eiaType) {
		for (EIATypeSelectionListener listener : listeners)
			listener.select(eiaType);
	}

	@Override
	public void addEiaTypeSelectionListener(
			EIATypeSelectionListener eIATypeSelectionListener) {
		listeners.add(eIATypeSelectionListener);
	}

	@Override
	public void onResize(ResizeEvent event) {
		setHeight(GHAUiHelper.getBottomSectionHeight());
	}

	@Override
	public void close() {
		destroy();
	}

	@Override
	public void removeEiaTypeSelectionListener(
			EIATypeSelectionListener eIATypeSelectionListener) {
		listeners.remove(eIATypeSelectionListener);

	}

}
