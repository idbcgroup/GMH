package org.fourgeeks.gha.webclient.client.eiatype;

import java.util.List;

import org.fourgeeks.gha.domain.enu.EiaSubTypeEnum;
import org.fourgeeks.gha.domain.gmh.Brand;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.EiaTypeCategory;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.alerts.GHAAlertManager;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASpacerItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHABrandSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHAEiaTypeCategorySelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHAEiaTypeSubTypeSelectItem;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHADynamicForm;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHATopForm;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHADynamicForm.FormType;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.widgets.layout.LayoutSpacer;

/**
 * @author alacret, emiliot
 * 
 */
public class EIATypeTopForm extends GHATopForm<EiaTypeResultSet, EiaType>
		implements EIATypeSelectionListener {
	private EiaType selectedEiaType;

	private GHATextItem nameItem;
	private GHATextItem modelItem;
	private GHABrandSelectItem brandItem;
	private GHAEiaTypeCategorySelectItem categoryItem;
	private GHAEiaTypeSubTypeSelectItem subTypeItem;
	private GHADynamicForm form;
	{
		categoryItem = new GHAEiaTypeCategorySelectItem(
				GHAStrings.get("category"));
		subTypeItem = new GHAEiaTypeSubTypeSelectItem();
		nameItem = new GHATextItem(GHAStrings.get("eiatype-name"));
		nameItem.setColSpan(2);
		brandItem = new GHABrandSelectItem();
		modelItem = new GHATextItem(GHAStrings.get("model"));

		modelItem.addKeyUpHandler(searchKeyUpHandler);
		nameItem.addKeyUpHandler(searchKeyUpHandler);
		brandItem.addKeyUpHandler(searchKeyUpHandler);
		categoryItem.addKeyUpHandler(searchKeyUpHandler);
		subTypeItem.addKeyUpHandler(searchKeyUpHandler);

		form = new GHADynamicForm(4, FormType.NORMAL_FORM);
	}

	/**
	 * @param resultSet
	 * @param eiaTypeTab
	 */
	public EIATypeTopForm(EiaTypeResultSet resultSet, EIATypePanel eiaTypeTab) {
		super(resultSet, eiaTypeTab);

		form.setItems(categoryItem, subTypeItem, brandItem,
				new GHASpacerItem(), nameItem, modelItem);
		addMembers(form, new LayoutSpacer(), sideButtons);
	}

	@Override
	public void activate() {
		brandItem.fill(true);
		toggleForm(false);
		super.activate();

	}

	@Override
	public void clear() {
		nameItem.clearValue();
		brandItem.clearValue();
		modelItem.clearValue();
		categoryItem.clearValue();
		subTypeItem.clearValue();
		selectedEiaType = null;
	}

	@Override
	public void deactivate() {
		toggleForm(true);
		sideButtons.removeMembers(searchButton, cleanButton, deleteButton);
	}

	@Override
	protected void delete() {
		GHAAlertManager.confirm("eiatype-delete-confirm",
				new BooleanCallback() {
					@Override
					public void execute(Boolean value) {
						if (value) {
							EIATypeModel.delete(selectedEiaType.getCode(),
									new GHAAsyncCallback<Void>() {
										@Override
										public void onSuccess(Void result) {
											containerTab.search();
											clear();
											GHAAlertManager
													.alert("eiatype-delete-success");
										}
									});
						}
					}
				});
	}

	@Override
	public void onResize(ResizeEvent event) {
		super.onResize(event);
		form.resize();
	}

	@Override
	public void search() {
		super.search();
		EiaType eiaType = new EiaType();
		eiaType.setName(nameItem.getValueAsString());
		if (brandItem.getValue() != null)
			eiaType.setBrand(new Brand(Integer.valueOf(brandItem
					.getValueAsString()), null));
		eiaType.setModel(modelItem.getValueAsString());
		if (categoryItem.getValue() != null)
			eiaType.setEiaTypeCategory(new EiaTypeCategory(categoryItem
					.getValueAsString()));
		if (subTypeItem.getValue() != null)
			eiaType.setSubtype(EiaSubTypeEnum.valueOf(subTypeItem
					.getValueAsString()));
		search(eiaType);
	}

	@Override
	public void search(EiaType eiaType) {
		super.search();
		EIATypeModel.find(eiaType, new GHAAsyncCallback<List<EiaType>>() {
			@Override
			public void onSuccess(List<EiaType> result) {
				resultSet.setRecords(result, true);
			}

		});
	}

	@Override
	public void select(EiaType eiaType) {
		selectedEiaType = eiaType;

		nameItem.setValue(eiaType.getName());
		modelItem.setValue(eiaType.getModel());

		if (eiaType.getBrand() != null) {
			brandItem.fill(true);
			brandItem.setValue(eiaType.getBrand().getId());
		}

		if (eiaType.getType() != null)
			categoryItem.setValue(eiaType.getEiaTypeCategory().getName());

		if (eiaType.getSubtype() != null)
			subTypeItem.setValue(eiaType.getSubtype().name());

		deactivate();
		sideButtons.addMember(deleteButton, 0);
	}

	private void toggleForm(boolean disabled) {
		nameItem.setDisabled(disabled);
		brandItem.setDisabled(disabled);
		modelItem.setDisabled(disabled);
		categoryItem.setDisabled(disabled);
		subTypeItem.setDisabled(disabled);
		cleanButton.setDisabled(disabled);
		activated = !disabled;
	}
}