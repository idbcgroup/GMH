/**
 * 
 */
package org.fourgeeks.gha.webclient.client.materialbrand;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.glm.Material;
import org.fourgeeks.gha.domain.glm.MaterialBrand;
import org.fourgeeks.gha.domain.glm.MaterialCategory;
import org.fourgeeks.gha.domain.glm.MaterialTypeEnum;
import org.fourgeeks.gha.domain.gmh.Brand;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.GHAUtil;
import org.fourgeeks.gha.webclient.client.UI.ResultSetContainerType;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHABrandSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.textitems.GHACodeTextItem;
import org.fourgeeks.gha.webclient.client.UI.icons.GHACancelButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHACleanButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHASearchButton;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHADynamicForm;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHADynamicForm.FormType;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHASearchForm;
import org.fourgeeks.gha.webclient.client.materialcategory.GHAMaterialCategoryPickTreeItem;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author emiliot
 * 
 */
public class MaterialBrandSearchForm extends GHASearchForm<MaterialBrand>
		implements MaterialBrandSelectionListener,
		MaterialBrandSelectionProducer {
	private GHATextItem codeTextItem, nameTextItem, descriptionTextItem,
			modelTextItem, extCodeTextItem;
	private GHABrandSelectItem brandItem;
	private GHAMaterialCategoryPickTreeItem categoryItem;
	protected MaterialTypeEnum fixedMaterial = MaterialTypeEnum.MATERIAL;
	private final MaterialBrandResultSet resultSet = new MaterialBrandResultSet(
			ResultSetContainerType.SEARCH_FORM);
	private final GHADynamicForm form;

	{
		codeTextItem = new GHACodeTextItem();
		nameTextItem = new GHATextItem(GHAStrings.get("name"));
		modelTextItem = new GHATextItem(GHAStrings.get("model"));
		extCodeTextItem = new GHATextItem(GHAStrings.get("external-code"));
		descriptionTextItem = new GHATextItem(GHAStrings.get("description"));
		descriptionTextItem.setColSpan(2);
		brandItem = new GHABrandSelectItem();
		categoryItem = new GHAMaterialCategoryPickTreeItem(
				GHAStrings.get("category"));

		resultSet
				.addMaterialBrandSelectionListener(new MaterialBrandSelectionListener() {

					@Override
					public void select(MaterialBrand entity) {
						hide();
					}
				});
		form = new GHADynamicForm(4, FormType.NORMAL_FORM);
	}

	/**
	 * @param title
	 */
	public MaterialBrandSearchForm(String title) {
		super(title);

		form.setItems(categoryItem, codeTextItem, nameTextItem, modelTextItem,
				brandItem, extCodeTextItem, descriptionTextItem);

		codeTextItem.addKeyUpHandler(searchKeyUpHandler);
		nameTextItem.addKeyUpHandler(searchKeyUpHandler);
		modelTextItem.addKeyUpHandler(searchKeyUpHandler);
		extCodeTextItem.addKeyUpHandler(searchKeyUpHandler);
		descriptionTextItem.addKeyUpHandler(searchKeyUpHandler);
		brandItem.addKeyUpHandler(searchKeyUpHandler);
		categoryItem.addKeyUpHandler(searchKeyUpHandler);

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

		addMembers(formLayout,
				GHAUiHelper
						.verticalGraySeparator(GHAUiHelper.V_SEPARATOR_HEIGHT
								+ "px"), resultSet);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.materialbrand.
	 * MaterialBrandSelectionProducer
	 * #addMaterialBrandSelectionListener(org.fourgeeks
	 * .gha.webclient.client.materialbrand.MaterialBrandSelectionListener)
	 */
	@Override
	public void addMaterialBrandSelectionListener(
			MaterialBrandSelectionListener listener) {
		resultSet.addMaterialBrandSelectionListener(listener);
	}

	public void clean() {
		form.clearValues();
		resultSet.clean();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.materialbrand.
	 * MaterialBrandSelectionProducer
	 * #notifyMaterialBrand(org.fourgeeks.gha.domain.glm.MaterialBrand)
	 */
	@Override
	public void notifyMaterialBrand(MaterialBrand materialBrand) {
	}

	@Override
	public void onResize(ResizeEvent event) {
		super.onResize(event);
		form.resize();

	}

	@Override
	public void open() {
		resultSet.setVisible(true);
		super.open();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.materialbrand.
	 * MaterialBrandSelectionProducer
	 * #removeMaterialBrandSelectionListener(org.fourgeeks
	 * .gha.webclient.client.materialbrand.MaterialBrandSelectionListener)
	 */
	@Override
	public void removeMaterialBrandSelectionListener(
			MaterialBrandSelectionListener listener) {
		resultSet.removeMaterialBrandSelectionListener(listener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.UI.superclasses.GHASearchForm#search()
	 */
	@Override
	public void search() {
		final MaterialBrand materialBrand = new MaterialBrand();
		final Material material = new Material();
		material.setCode(codeTextItem.getValueAsString());
		material.setName(nameTextItem.getValueAsString());
		material.setDescription(descriptionTextItem.getValueAsString());
		material.setModel(modelTextItem.getValueAsString());
		material.setExternalCode(extCodeTextItem.getValueAsString());
		material.setType(fixedMaterial);

		if (brandItem.getValue() != null)
			materialBrand.setBrand(new Brand(Integer.valueOf(brandItem
					.getValueAsString()), null));

		if (categoryItem.getValue() != null) {
			material.setMaterialCategory(new MaterialCategory(categoryItem
					.getValue().toString()));
		}
		materialBrand.setMaterial(material);
		search(materialBrand);
	}

	protected void search(final MaterialBrand materialBrand) {
		MaterialBrandModel.find(materialBrand,
				new GHAAsyncCallback<List<MaterialBrand>>() {

					@Override
					public void onSuccess(List<MaterialBrand> results) {
						List<MaterialBrand> newList = null;
						if (blackList != null) {
							List<AbstractEntity> tmpList = GHAUtil
									.binarySearchFilterEntity(results,
											blackList);
							List<MaterialBrand> newTmpList = new ArrayList<MaterialBrand>();

							for (AbstractEntity entity : tmpList)
								newTmpList.add((MaterialBrand) entity);
							newList = newTmpList;
						} else {
							newList = results;
						}

						resultSet.setRecords(newList, false);
					}
				});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.materialbrand.
	 * MaterialBrandSelectionListener
	 * #select(org.fourgeeks.gha.domain.glm.MaterialBrand)
	 */
	@Override
	public void select(MaterialBrand materialBrand) {
		search(materialBrand);
	}

}
