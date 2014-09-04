package org.fourgeeks.gha.webclient.client.materialcategory;

import java.util.List;

import org.fourgeeks.gha.domain.glm.ServicesResourceCategory;
import org.fourgeeks.gha.domain.glm.MaterialTypeEnum;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.ResultSetContainerType;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.textitems.GHACodeTextItem;
import org.fourgeeks.gha.webclient.client.UI.imageitems.buttons.GHACancelButton;
import org.fourgeeks.gha.webclient.client.UI.imageitems.buttons.GHACleanButton;
import org.fourgeeks.gha.webclient.client.UI.imageitems.buttons.GHASearchButton;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHASearchForm;

import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.types.VerticalAlignment;
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
@Deprecated
public class MaterialCategorySearchForm extends GHASearchForm<ServicesResourceCategory>
implements MaterialCategorySelectionListener,
MaterialCategorySelectionProducer {

	private GHATextItem codeTextItem, nameTextItem, descriptionTextItem,
	modelTextItem, extCodeTextItem;
	protected GHASelectItem typeSelectItem;
	private final MaterialCategoryResultSet resultSet = new MaterialCategoryResultSet(
			ResultSetContainerType.SEARCH_FORM);
	private final DynamicForm form = new DynamicForm();

	{
		codeTextItem = new GHACodeTextItem(300);
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
			public void select(ServicesResourceCategory material) {
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

		final VLayout sideButtons = GHAUiHelper.createBar(new GHASearchButton(
				searchClickHandler), new GHACleanButton(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						clean();
					}
				}), new GHACancelButton(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						hide();
						clean();
					}
				}));

		final HLayout formLayout = new HLayout();
		formLayout.setPadding(10);
		formLayout.setHeight(GHAUiHelper.DEFAULT_INNER_TOP_SECTION_HEIGHT
				+ "px");
		formLayout.setDefaultLayoutAlign(VerticalAlignment.CENTER);
		formLayout.addMembers(form, new LayoutSpacer(), sideButtons);

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
	public void notifyMaterialCategory(ServicesResourceCategory servicesResourceCategory) {
	}

	@Override
	public void removeMaterialSelectionListener(
			MaterialCategorySelectionListener materialSelectionListener) {
		resultSet.removeMaterialSelectionListener(materialSelectionListener);
	}

	@Override
	public void search() {
		final ServicesResourceCategory material = new ServicesResourceCategory();
		material.setCode(codeTextItem.getValueAsString());
		material.setName(nameTextItem.getValueAsString());
		// material.setDescription(descriptionTextItem.getValueAsString());
		// material.setModel(modelTextItem.getValueAsString());
		// material.setExternalCode(extCodeTextItem.getValueAsString());
		// if (typeSelectItem.getValue() != null)
		// material.setType(MaterialTypeEnum.valueOf(typeSelectItem
		// .getValueAsString()));

		search(material);
	}

	/**
	 * @param materialCateogry
	 */
	protected void search(final ServicesResourceCategory materialCateogry) {
		MaterialCategoryModel.find(materialCateogry,
				new GHAAsyncCallback<List<ServicesResourceCategory>>() {

			@Override
			public void onSuccess(List<ServicesResourceCategory> results) {
				List<ServicesResourceCategory> newList = null;
				// if (blackList != null) {
				// List<AbstractCodeEntity> tmpList = GHAUtil
				// .binarySearchFilterCodeEntity(results,
				// blackList);
				// List<ServicesResourceCategory> newTmpList = new
				// ArrayList<ServicesResourceCategory>();
				// for (AbstractCodeEntity abstractCodeEntity : tmpList)
				// newTmpList
				// .add((ServicesResourceCategory) abstractCodeEntity);
				// newList = newTmpList;
				// } else
				newList = results;

				resultSet.setRecords(newList, false);
			}
		});
	}

	@Override
	public void select(ServicesResourceCategory servicesResourceCategory) {
		search(servicesResourceCategory);
	}

}
