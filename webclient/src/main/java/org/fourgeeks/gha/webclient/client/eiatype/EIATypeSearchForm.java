package org.fourgeeks.gha.webclient.client.eiatype;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.fourgeeks.gha.domain.AbstractCodeEntity;
import org.fourgeeks.gha.domain.enu.EiaSubTypeEnum;
import org.fourgeeks.gha.domain.enu.EiaTypeEnum;
import org.fourgeeks.gha.domain.gmh.Brand;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHACache;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.GHAUtil;
import org.fourgeeks.gha.webclient.client.UI.ResultSetContainerType;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASpacerItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHABrandSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHAEiaTypeSubTypeSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHAEiaTypeTypeSelectItem;
import org.fourgeeks.gha.webclient.client.UI.imageitems.buttons.GHACancelButton;
import org.fourgeeks.gha.webclient.client.UI.imageitems.buttons.GHACleanButton;
import org.fourgeeks.gha.webclient.client.UI.imageitems.buttons.GHASearchButton;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHADynamicForm;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHADynamicForm.FormType;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHASearchForm;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret, emiliot
 * 
 */
public class EIATypeSearchForm extends GHASearchForm<EiaType> implements
		EIATypeSelectionListener, EiaTypeSelectionProducer,
		EiaTypeListSelectionProducer {

	private GHATextItem nameItem;
	private GHATextItem modelItem;
	private GHABrandSelectItem brandItem;
	private GHAEiaTypeTypeSelectItem typeItem;
	private GHAEiaTypeSubTypeSelectItem subTypeItem;
	private final EiaTypeResultSet resultSet = new EiaTypeResultSet(
			ResultSetContainerType.SEARCH_FORM);
	private final GHADynamicForm form;

	{
		typeItem = new GHAEiaTypeTypeSelectItem();
		subTypeItem = new GHAEiaTypeSubTypeSelectItem();
		nameItem = new GHATextItem(GHAStrings.get("name"));
		nameItem.setColSpan(2);
		brandItem = new GHABrandSelectItem();
		modelItem = new GHATextItem(GHAStrings.get("model"));

		form = new GHADynamicForm(4, FormType.NORMAL_FORM);
		//
		resultSet.addEiaTypeSelectionListener(new EIATypeSelectionListener() {

			@Override
			public void select(EiaType eiaType) {
				hide();
			}
		});
		resultSet
				.addEiaTypeListSelectionListener(new EiaTypeListSelectionListener() {
					@Override
					public void select(List<EiaType> eiaTypes) {
						hide();
					}
				});
	}

	/**
	 * @param title
	 * 
	 */
	public EIATypeSearchForm(String title) {
		super(title);

		form.setItems(typeItem, subTypeItem, brandItem, new GHASpacerItem(),
				nameItem, modelItem);

		nameItem.addKeyUpHandler(searchKeyUpHandler);
		brandItem.addKeyUpHandler(searchKeyUpHandler);
		modelItem.addKeyUpHandler(searchKeyUpHandler);
		typeItem.addKeyUpHandler(searchKeyUpHandler);
		subTypeItem.addKeyUpHandler(searchKeyUpHandler);
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
	public void addEiaTypeSelectionListener(
			EIATypeSelectionListener eIATypeSelectionListener) {
		resultSet.addEiaTypeSelectionListener(eIATypeSelectionListener);

	}

	/**
	 * 
	 */
	public void clean() {
		form.clearValues();
		resultSet.clean();
	}

	@Override
	public void close() {
		destroy();
	}

	private void fill() {
		typeItem.setValueMap(EiaTypeEnum.toValueMap());
		subTypeItem.setValueMap(EiaSubTypeEnum.toValueMap());

		fillBrands(false);
	}

	private void fillBrands(boolean forceFromServer) {
		GHACache.INSTANCE.getBrands(new GHAAsyncCallback<List<Brand>>() {

			@Override
			public void onSuccess(List<Brand> result) {
				final LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
				for (final Brand brand : result)
					valueMap.put(brand.getId() + "", brand.getName());
				brandItem.setValueMap(valueMap);

			}
		}, forceFromServer);
	}

	@Override
	public void hide() {
		super.hide();
	}

	@Override
	public void notifyEiaType(EiaType eiaType) {
	}

	@Override
	public void onResize(ResizeEvent event) {
		// TODO Auto-generated method stub
		super.onResize(event);
		form.resize();
	}

	@Override
	public void open() {
		resultSet.setVisible(true);
		super.open();
	}

	@Override
	public void removeEiaTypeSelectionListener(
			EIATypeSelectionListener eIATypeSelectionListener) {
		resultSet.removeEiaTypeSelectionListener(eIATypeSelectionListener);

	}

	@Override
	public void search() {
		final EiaType eiaType = new EiaType();
		eiaType.setName(nameItem.getValueAsString());
		if (brandItem.getValue() != null)
			eiaType.setBrand(new Brand(Integer.valueOf(brandItem
					.getValueAsString()), null));
		eiaType.setModel(modelItem.getValueAsString());
		if (typeItem.getValue() != null)
			eiaType.setType(EiaTypeEnum.valueOf(typeItem.getValueAsString()));
		if (subTypeItem.getValue() != null)
			eiaType.setSubtype(EiaSubTypeEnum.valueOf(subTypeItem
					.getValueAsString()));

		search(eiaType);
	}

	private void search(final EiaType eiaType) {
		EIATypeModel.find(eiaType, new GHAAsyncCallback<List<EiaType>>() {

			@Override
			public void onSuccess(List<EiaType> results) {
				List<EiaType> newList = null;
				if (blackList != null) {
					final List<AbstractCodeEntity> tmpList = GHAUtil
							.binarySearchFilterCodeEntity(results, blackList);
					final List<EiaType> newTmpList = new ArrayList<EiaType>();
					for (final AbstractCodeEntity abstractCodeEntity : tmpList)
						newTmpList.add((EiaType) abstractCodeEntity);
					newList = newTmpList;
				} else
					newList = results;

				resultSet.setRecords(newList, false);

			}
		});
	}

	@Override
	public void select(EiaType eiaType) {
		search(eiaType);

		// Reload the Brand Select field, to prevent outdated cached list of
		// brands
		fillBrands(true);
	}

	@Override
	public void addEiaTypeListSelectionListener(
			EiaTypeListSelectionListener eiaTypeListSelectionListener) {
		resultSet.addEiaTypeListSelectionListener(eiaTypeListSelectionListener);
	}

	@Override
	public void removeEiaTypeListSelectionListener(
			EiaTypeListSelectionListener eiaTypeListSelectionListener) {
		resultSet
				.removeEiaTypeListSelectionListener(eiaTypeListSelectionListener);
	}

	@Override
	public void notifyEiaTypeList(List<EiaType> eiaTypes) {
	}
}
