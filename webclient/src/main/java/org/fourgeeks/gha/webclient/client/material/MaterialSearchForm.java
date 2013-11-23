package org.fourgeeks.gha.webclient.client.material;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.AbstractCodeEntity;
import org.fourgeeks.gha.domain.glm.Material;
import org.fourgeeks.gha.domain.glm.MaterialTypeEnum;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.GHAUtil;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHACodeItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASpacerItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.icons.GHACancelButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHACleanButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHASearchButton;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHADynamicForm;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHASearchForm;

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
public class MaterialSearchForm extends GHASearchForm<Material> implements
		MaterialSelectionListener, MaterialSelectionProducer {

	private GHATextItem codeTextItem, nameTextItem, descriptionTextItem,
			modelTextItem, extCodeTextItem;
	protected MaterialTypeEnum fixedMaterial = MaterialTypeEnum.MATERIAL;
	private final MaterialResultSet resultSet = new MaterialResultSet();
	private final GHADynamicForm form;

	{
		//
		codeTextItem = new GHACodeItem();
		nameTextItem = new GHATextItem(GHAStrings.get("name"));
		modelTextItem = new GHATextItem(GHAStrings.get("model"));
		extCodeTextItem = new GHATextItem(GHAStrings.get("external-code"));
		descriptionTextItem = new GHATextItem(GHAStrings.get("description"));
		descriptionTextItem.setColSpan(2);
		// selects
		// typeSelectItem = new GHASelectItem(GHAStrings.get("type"));
		resultSet.addMaterialSelectionListener(new MaterialSelectionListener() {

			@Override
			public void select(Material material) {
				hide();
			}
		});
		form = new GHADynamicForm(GHAUiHelper.getNormalFormWidth(30), 4);
	}

	/**
	 * @param title
	 */
	public MaterialSearchForm(String title) {
		super(title);

		form.setItems(codeTextItem, nameTextItem, modelTextItem,
				new GHASpacerItem(), extCodeTextItem, descriptionTextItem);

		codeTextItem.addKeyUpHandler(searchKeyUpHandler);
		nameTextItem.addKeyUpHandler(searchKeyUpHandler);
		modelTextItem.addKeyUpHandler(searchKeyUpHandler);
		extCodeTextItem.addKeyUpHandler(searchKeyUpHandler);
		descriptionTextItem.addKeyUpHandler(searchKeyUpHandler);
		// typeSelectItem.addKeyUpHandler(searchKeyUpHandler);
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

		fillSelects();
	}

	@Override
	public void addMaterialSelectionListener(
			MaterialSelectionListener materialSelectionListener) {
		resultSet.addMaterialSelectionListener(materialSelectionListener);
	}

	/**
	 * 
	 */
	public void clean() {
		form.clearValues();
		resultSet.clean();
	}

	private void fillSelects() {
		// typeSelectItem.setValueMap(MaterialTypeEnum.toValueMap());
	}

	@Override
	public void notifyMaterial(Material material) {
	}

	@Override
	public void onResize(ResizeEvent event) {
		super.onResize(event);
		resultSet.setHeight(resultSet.getHeight() - 35);
		form.resize(GHAUiHelper.getNormalFormWidth(30), 4);

	}

	@Override
	public void open() {
		resultSet.setVisible(true);
		super.open();
	}

	@Override
	public void removeMaterialSelectionListener(
			MaterialSelectionListener materialSelectionListener) {
		resultSet.removeMaterialSelectionListener(materialSelectionListener);
	}

	@Override
	public void search() {
		Material material = new Material();
		material.setCode(codeTextItem.getValueAsString());
		material.setName(nameTextItem.getValueAsString());
		material.setDescription(descriptionTextItem.getValueAsString());
		material.setModel(modelTextItem.getValueAsString());
		material.setExternalCode(extCodeTextItem.getValueAsString());
		// if (typeSelectItem.getValue() != null)
		// materialCategory.setType(MaterialTypeEnum.valueOf(typeSelectItem
		// .getValueAsString()));
		material.setType(fixedMaterial);
		search(material);
	}

	/**
	 * @param materialCateogry
	 */
	protected void search(final Material material) {
		MaterialModel.find(material, new GHAAsyncCallback<List<Material>>() {

			@Override
			public void onSuccess(List<Material> results) {
				List<Material> newList = null;
				if (blackList != null) {
					List<AbstractCodeEntity> tmpList = GHAUtil
							.binarySearchFilterCodeEntity(results, blackList);
					List<Material> newTmpList = new ArrayList<Material>();
					for (AbstractCodeEntity abstractCodeEntity : tmpList)
						newTmpList.add((Material) abstractCodeEntity);
					newList = newTmpList;
				} else {
					newList = results;
				}

				resultSet.setRecords(newList, false);
			}
		});
	}

	@Override
	public void select(Material material) {
		search(material);
	}
}
