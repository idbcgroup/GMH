package org.fourgeeks.gha.webclient.client.eiatype;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.fourgeeks.gha.domain.AbstractCodeEntity;
import org.fourgeeks.gha.domain.enu.EiaMobilityEnum;
import org.fourgeeks.gha.domain.enu.EiaSubTypeEnum;
import org.fourgeeks.gha.domain.enu.EiaTypeEnum;
import org.fourgeeks.gha.domain.gmh.Brand;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHACache;
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

import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret, emiliot
 * 
 */
public class EIATypeSearchForm extends GHASearchForm<EiaType> implements
		EIATypeSelectionListener, EiaTypeSelectionProducer {

	private GHATextItem codeEIAItem, nameEIAItem, modelItem, umdnsCodeItem;
	private GHASelectItem brandItem, mobilityItem, typeItem, subTypeItem;
	private EiaTypeResultSet resultSet = new EiaTypeResultSet();
	private final DynamicForm form = new DynamicForm();

	{
		codeEIAItem = new GHACodeItem(225);
		codeEIAItem.setMask("AAAAAAAAAAAAAAAAAAAA");
		nameEIAItem = new GHATextItem(GHAStrings.get("name"), 215);
		nameEIAItem.setLength(255);
		brandItem = new GHASelectItem(GHAStrings.get("name"), 255);
		modelItem = new GHATextItem(GHAStrings.get("model"), 225);
		modelItem.setLength(20);
		umdnsCodeItem = new GHATextItem("EIAUMDNS", 225);
		umdnsCodeItem.setLength(16);
		umdnsCodeItem.setMask("AAAAAAAAAAAAAAAAAAAA");
		mobilityItem = new GHASelectItem(GHAStrings.get("mobility"), 225);
		typeItem = new GHASelectItem(GHAStrings.get("eiatype"), 225);
		subTypeItem = new GHASelectItem(GHAStrings.get("subtype"), 225);
		//
		resultSet.addEiaTypeSelectionListener(new EIATypeSelectionListener() {

			@Override
			public void select(EiaType eiaType) {
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
		form.setTitleOrientation(TitleOrientation.TOP);
		form.setNumCols(4);
		form.setItems(codeEIAItem, nameEIAItem, modelItem, umdnsCodeItem,
				mobilityItem, typeItem, subTypeItem, brandItem);

		codeEIAItem.addKeyUpHandler(searchKeyUpHandler);
		nameEIAItem.addKeyUpHandler(searchKeyUpHandler);
		brandItem.addKeyUpHandler(searchKeyUpHandler);
		modelItem.addKeyUpHandler(searchKeyUpHandler);
		umdnsCodeItem.addKeyUpHandler(searchKeyUpHandler);
		mobilityItem.addKeyUpHandler(searchKeyUpHandler);
		typeItem.addKeyUpHandler(searchKeyUpHandler);
		subTypeItem.addKeyUpHandler(searchKeyUpHandler);
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

		addMembers(formLayout,
				GHAUiHelper
						.verticalGraySeparator(GHAUiHelper.V_SEPARATOR_HEIGHT
								+ "px"), resultSet);
		fill();
	}

	private void fill() {
		typeItem.setValueMap(EiaTypeEnum.toValueMap());
		subTypeItem.setValueMap(EiaSubTypeEnum.toValueMap());
		mobilityItem.setValueMap(EiaMobilityEnum.toValueMap());

		fillBrands(false);
	}

	private void fillBrands(boolean forceFromServer) {
		GHACache.INSTANCE.getBrands(new GHAAsyncCallback<List<Brand>>() {

			@Override
			public void onSuccess(List<Brand> result) {
				LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
				for (Brand brand : result)
					valueMap.put(brand.getId() + "", brand.getName());
				brandItem.setValueMap(valueMap);

			}
		}, forceFromServer);
	}

	@Override
	public void notifyEiaType(EiaType eiaType) {
	}

	@Override
	public void select(EiaType eiaType) {
		search(eiaType);

		// Reload the Brand Select field, to prevent outdated cached list of
		// brands
		fillBrands(true);
	}

	@Override
	public void search() {
		EiaType eiaType = new EiaType();
		eiaType.setCode(codeEIAItem.getValueAsString());
		eiaType.setName(nameEIAItem.getValueAsString());
		if (brandItem.getValue() != null)
			eiaType.setBrand(new Brand(Integer.valueOf(brandItem
					.getValueAsString()), null));
		eiaType.setModel(modelItem.getValueAsString());
		if (mobilityItem.getValue() != null)
			eiaType.setMobility(EiaMobilityEnum.valueOf(mobilityItem
					.getValueAsString()));
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
					List<AbstractCodeEntity> tmpList = GHAUtil
							.binarySearchFilterCodeEntity(results, blackList);
					List<EiaType> newTmpList = new ArrayList<EiaType>();
					for (AbstractCodeEntity abstractCodeEntity : tmpList)
						newTmpList.add((EiaType) abstractCodeEntity);
					newList = newTmpList;
				} else
					newList = results;

				resultSet.setRecords(newList);

			}
		});
	}

	@Override
	public void close() {
		destroy();
	}

	@Override
	public void hide() {
		super.hide();
	}

	@Override
	public void addEiaTypeSelectionListener(
			EIATypeSelectionListener eIATypeSelectionListener) {
		resultSet.addEiaTypeSelectionListener(eIATypeSelectionListener);

	}

	@Override
	public void removeEiaTypeSelectionListener(
			EIATypeSelectionListener eIATypeSelectionListener) {
		resultSet.removeEiaTypeSelectionListener(eIATypeSelectionListener);

	}

	/**
	 * 
	 */
	public void clean() {
		form.clearValues();
		resultSet.clean();
	}

	@Override
	public void open() {
		resultSet.setVisible(true);
		super.open();
	}
}
