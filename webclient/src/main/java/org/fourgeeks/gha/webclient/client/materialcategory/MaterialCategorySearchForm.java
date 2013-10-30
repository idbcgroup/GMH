package org.fourgeeks.gha.webclient.client.materialcategory;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.fourgeeks.gha.domain.AbstractCodeEntity;
import org.fourgeeks.gha.domain.glm.MaterialCategory;
import org.fourgeeks.gha.domain.glm.MaterialTypeEnum;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.GHAUtil;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHACodeItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.icons.GHACancelButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHACheckButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHACleanButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHASearchButton;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHANotification;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHASearchForm;

import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret Material search form
 * 
 */
public class MaterialCategorySearchForm extends GHASearchForm<MaterialCategory>
		implements MaterialCategorySelectionListener,
		MaterialCategorySelectionProducer {

	private List<MaterialCategorySelectionListener> selectionListeners;
	private GHATextItem codeTextItem, nameTextItem, descriptionTextItem,
			modelTextItem, extCodeTextItem;
	protected GHASelectItem typeSelectItem;
	private MaterialCategoryGrid grid;

	{
		selectionListeners = new LinkedList<MaterialCategorySelectionListener>();
		//
		codeTextItem = new GHACodeItem(300);
		nameTextItem = new GHATextItem(GHAStrings.get("name"), 300);
		modelTextItem = new GHATextItem(GHAStrings.get("model"), 300);
		extCodeTextItem = new GHATextItem(GHAStrings.get("external-code"), 300);
		descriptionTextItem = new GHATextItem(GHAStrings.get("description"),
				300);
		// selects
		typeSelectItem = new GHASelectItem(GHAStrings.get("type"), 300);
	}

	/**
	 * @param title
	 * 
	 */
	public MaterialCategorySearchForm(String title) {
		super(title);
		final DynamicForm form = new DynamicForm();
		form.setTitleOrientation(TitleOrientation.TOP);
		form.setNumCols(3);
		form.setItems(codeTextItem, nameTextItem, modelTextItem,
				typeSelectItem, extCodeTextItem, descriptionTextItem);

		codeTextItem.addKeyUpHandler(searchKeyUpHandler);
		nameTextItem.addKeyUpHandler(searchKeyUpHandler);
		modelTextItem.addKeyUpHandler(searchKeyUpHandler);
		extCodeTextItem.addKeyUpHandler(searchKeyUpHandler);
		descriptionTextItem.addKeyUpHandler(searchKeyUpHandler);
		typeSelectItem.addKeyUpHandler(searchKeyUpHandler);
		// ////////////////////////////

		VLayout sideButtons = GHAUiHelper.createBar(new GHASearchButton(
				searchClickHandler), new GHACleanButton(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				form.clearValues();
				grid.setData(new ListGridRecord[0]);
			}
		}), new GHACancelButton(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				hide();
			}
		}));

		HLayout formLayout = new HLayout();
		formLayout.setPadding(10);
		formLayout.setHeight(GHAUiHelper.DEFAULT_TOP_SECTION_HEIGHT + "px");
		formLayout.addMembers(form, new LayoutSpacer(), sideButtons);

		addMembers(formLayout,
				GHAUiHelper
						.verticalGraySeparator(GHAUiHelper.V_SEPARATOR_HEIGHT
								+ "px"));

		grid = new MaterialCategoryGrid();
		HLayout gridLayout = new HLayout();
		gridLayout.setPadding(10);

		VLayout sideGridButtons = GHAUiHelper.createBar(new GHACheckButton(
				new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						selectMaterialCategory();
					}
				}));

		gridLayout.addMembers(grid, sideGridButtons);

		addMember(gridLayout);
		fillSelects();
	}

	private void fillSelects() {
		typeSelectItem.setValueMap(MaterialTypeEnum.toValueMap());
	}

	private void selectMaterialCategory() {
		MaterialCategory selectedEntity = grid.getSelectedEntity();
		if (selectedEntity == null) {
			GHANotification.alert("record-not-selected");
			return;
		}
		notifyMaterialCategory(selectedEntity);
		hide();
	}

	@Override
	public void select(MaterialCategory materialCategory) {
		search(materialCategory);
	}

	public void search() {
		MaterialCategory material = new MaterialCategory();
		material.setCode(codeTextItem.getValueAsString());
		material.setName(nameTextItem.getValueAsString());
		material.setDescription(descriptionTextItem.getValueAsString());
		material.setModel(modelTextItem.getValueAsString());
		material.setExternalCode(extCodeTextItem.getValueAsString());
		if (typeSelectItem.getValue() != null)
			material.setType(MaterialTypeEnum.valueOf(typeSelectItem
					.getValueAsString()));

		search(material);
	}

	/**
	 * @param materialCateogry
	 */
	protected void search(final MaterialCategory materialCateogry) {
		MaterialCategoryModel.find(materialCateogry,
				new GHAAsyncCallback<List<MaterialCategory>>() {

					@Override
					public void onSuccess(List<MaterialCategory> results) {
						List<MaterialCategory> newList = null;
						if (blackList != null) {
							List<AbstractCodeEntity> tmpList = GHAUtil
									.binarySearchFilterCodeEntity(results,
											blackList);
							List<MaterialCategory> newTmpList = new ArrayList<MaterialCategory>();
							for (AbstractCodeEntity abstractCodeEntity : tmpList)
								newTmpList
										.add((MaterialCategory) abstractCodeEntity);
							newList = newTmpList;
						} else
							newList = results;

						ListGridRecord[] array = MaterialCategoryUtil
								.toGridRecords(newList).toArray(
										new MaterialCategoryRecord[] {});
						grid.setData(array);
						if (materialCateogry != null
								&& materialCateogry.getCode() != null)
							for (ListGridRecord listGridRecord : grid
									.getRecords())
								if (((MaterialCategoryRecord) listGridRecord)
										.toEntity().getCode() == materialCateogry
										.getCode())
									grid.selectRecord(listGridRecord);
					}
				});
	}

	@Override
	public void addMaterialSelectionListener(
			MaterialCategorySelectionListener materialSelectionListener) {
		selectionListeners.add(materialSelectionListener);
	}

	@Override
	public void removeMaterialSelectionListener(
			MaterialCategorySelectionListener materialSelectionListener) {
		selectionListeners.remove(materialSelectionListener);
	}

	@Override
	public boolean canBeClosen() {
		return true;
	}

	@Override
	public boolean canBeHidden() {
		return true;
	}

	@Override
	public void notifyMaterialCategory(MaterialCategory materialCategory) {
		for (MaterialCategorySelectionListener listener : selectionListeners)
			listener.select(materialCategory);
	}

}
