package org.fourgeeks.gha.webclient.client.materialcategory;

import java.util.ArrayList;
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
import org.fourgeeks.gha.webclient.client.UI.icons.GHACleanButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHASearchButton;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHASearchForm;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
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

	private GHATextItem codeTextItem, nameTextItem, descriptionTextItem,
			modelTextItem, extCodeTextItem;
	protected GHASelectItem typeSelectItem;
	private final MaterialCategoryResultSet resultSet = new MaterialCategoryResultSet();
	private final DynamicForm form = new DynamicForm();

	{
		codeTextItem = new GHACodeItem(300);
		nameTextItem = new GHATextItem(GHAStrings.get("name"), 300);
		modelTextItem = new GHATextItem(GHAStrings.get("model"), 300);
		extCodeTextItem = new GHATextItem(GHAStrings.get("external-code"), 300);
		descriptionTextItem = new GHATextItem(GHAStrings.get("description"),
				300);
		// selects
		typeSelectItem = new GHASelectItem(GHAStrings.get("type"), 300);
		//
		resultSet
				.addMaterialSelectionListener(new MaterialCategorySelectionListener() {
					@Override
					public void select(MaterialCategory material) {
						notifyMaterialCategory(material);
						hide();
					}
				});
	}

	/**
	 * @param title
	 * 
	 */
	public MaterialCategorySearchForm(String title) {
		super(title);

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
				clean();
			}
		}), new GHACancelButton(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				hide();
			}
		}));

		HLayout formLayout = new HLayout();
		formLayout.setPadding(10);
		formLayout.setHeight(GHAUiHelper.DEFAULT_INNER_TOP_SECTION_HEIGHT
				+ "px");
		formLayout.addMembers(form, new LayoutSpacer(), sideButtons);

		resultSet.setHeight(resultSet.getHeight() - 35);

		addMembers(formLayout,
				GHAUiHelper
						.verticalGraySeparator(GHAUiHelper.V_SEPARATOR_HEIGHT
								+ "px"), resultSet);
		fill();
	}

	@Override
	public void addMaterialSelectionListener(
			MaterialCategorySelectionListener materialSelectionListener) {
		resultSet.addMaterialSelectionListener(materialSelectionListener);
	}

	/**
	 * 
	 */
	public void clean() {
		form.clearValues();
		resultSet.clean();
	}

	private void fill() {
		typeSelectItem.setValueMap(MaterialTypeEnum.toValueMap());
	}

	@Override
	public void notifyMaterialCategory(MaterialCategory materialCategory) {
	}

	@Override
	public void removeMaterialSelectionListener(
			MaterialCategorySelectionListener materialSelectionListener) {
		resultSet.removeMaterialSelectionListener(materialSelectionListener);
	}

	@Override
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

						resultSet.setRecords(newList, false);
					}
				});
	}

	@Override
	public void select(MaterialCategory materialCategory) {
		search(materialCategory);
	}

	@Override
	public void onResize(ResizeEvent event) {
		super.onResize(event);
		resultSet.setHeight(resultSet.getHeight() - 35);
	}
}
