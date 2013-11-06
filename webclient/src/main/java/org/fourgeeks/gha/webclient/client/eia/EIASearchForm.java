package org.fourgeeks.gha.webclient.client.eia;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.enu.EiaStateEnum;
import org.fourgeeks.gha.domain.ess.Role;
import org.fourgeeks.gha.domain.ess.WorkingArea;
import org.fourgeeks.gha.domain.gar.Facility;
import org.fourgeeks.gha.domain.gar.Obu;
import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHACache;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.GHAUtil;
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
import com.smartgwt.client.widgets.form.fields.events.KeyUpEvent;
import com.smartgwt.client.widgets.form.fields.events.KeyUpHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret, emiliot
 * 
 */
public class EIASearchForm extends GHASearchForm<Eia> implements
		EIASelectionListener, EiaSelectionProducer {

	private GHATextItem codeItem, fixedAssetIdentifierItem, serialNumberItem;
	private GHASelectItem responsibleRoleItem, eiaTypeItem,
			workingAreaLocationItem, facilityLocationItem, obuItem, stateItem;
	private EiaResultSet resultSet = new EiaResultSet();
	private final DynamicForm form = new DynamicForm();

	{

		responsibleRoleItem = new GHASelectItem("Responsable");
		codeItem = new GHATextItem("Código");
		codeItem.setLength(20);
		codeItem.setMask("####################");
		eiaTypeItem = new GHASelectItem("Tipo de equipo");
		fixedAssetIdentifierItem = new GHATextItem("Identificador");
		fixedAssetIdentifierItem.setLength(20);
		workingAreaLocationItem = new GHASelectItem("Área de Trabajo");
		facilityLocationItem = new GHASelectItem("Servicio/Instalación");
		obuItem = new GHASelectItem("Organización");
		serialNumberItem = new GHATextItem("Serial");
		serialNumberItem.setLength(20);
		serialNumberItem.setMask("AAAAAAAAAAAAAAAAAAAA");
		fixedAssetIdentifierItem.setLength(20);
		fixedAssetIdentifierItem.setMask("###################");
		stateItem = new GHASelectItem("Estado");

		resultSet.addEiaSelectionListener(new EIASelectionListener() {

			@Override
			public void select(Eia eia) {
				hide();
			}
		});
	}

	/**
	 * 
	 */
	public EIASearchForm(String title) {
		super(title);
		setTop(GHAUiHelper.DEFAULT_TOP_SECTION_HEIGHT);
		setHeight(GHAUiHelper.getTabHeight() - 5 + "px");

		GHAUiHelper.addGHAResizeHandler(this);

		form.setTitleOrientation(TitleOrientation.TOP);
		form.setNumCols(5);

		form.setItems(responsibleRoleItem, codeItem, eiaTypeItem,
				fixedAssetIdentifierItem, workingAreaLocationItem,
				facilityLocationItem, obuItem, serialNumberItem, stateItem);

		KeyUpHandler searchKeyUpHandler = new KeyUpHandler() {

			@Override
			public void onKeyUp(KeyUpEvent event) {
				if (event.getKeyName().equals("Enter")) {
					search();
				}
			}
		};
		// actualCostItem.addKeyUpHandler(searchKeyUpHandler);
		responsibleRoleItem.addKeyUpHandler(searchKeyUpHandler);
		codeItem.addKeyUpHandler(searchKeyUpHandler);
		eiaTypeItem.addKeyUpHandler(searchKeyUpHandler);
		fixedAssetIdentifierItem.addKeyUpHandler(searchKeyUpHandler);
		workingAreaLocationItem.addKeyUpHandler(searchKeyUpHandler);
		facilityLocationItem.addKeyUpHandler(searchKeyUpHandler);
		obuItem.addKeyUpHandler(searchKeyUpHandler);
		serialNumberItem.addKeyUpHandler(searchKeyUpHandler);
		stateItem.addKeyUpHandler(searchKeyUpHandler);

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

	@Override
	public void addEiaSelectionListener(
			EIASelectionListener eiaSelectionListener) {
		resultSet.addEiaSelectionListener(eiaSelectionListener);
	}

	/**
	 * 
	 */
	private void clean() {
		form.clearValues();
		resultSet.clean();
	}

	@Override
	public void close() {
		destroy();
	}

	private void fill() {
		stateItem.setValueMap(EiaStateEnum.toValueMap());
		searchForEiaTypes();
		searchForRoleBases();
		searchForLocations();
		searchForObus();
	}

	@Override
	public void hide() {
		super.hide();
	}

	@Override
	public void notifyEia(Eia eia) {
	}

	@Override
	public void onResize(ResizeEvent event) {
		setHeight(GHAUiHelper.getTabHeight() - 5 + "px");
	}

	@Override
	public void open() {
		resultSet.setVisible(true);
		super.open();
	}

	@Override
	public void removeEiaSelectionListener(
			EIASelectionListener eiaSelectionListener) {
		resultSet.removeEiaSelectionListener(eiaSelectionListener);
	}

	@Override
	public void search() {
		Eia eia = new Eia();
		// if (actualCostItem.getValue() != null)
		// eia.setActualCost(new BigDecimal(actualCostItem.getValueAsString()));
		if (responsibleRoleItem.getValue() != null)
			eia.setResponsibleRole(new Role(Long.parseLong(responsibleRoleItem
					.getValueAsString())));
		if (codeItem.getValue() != null)
			eia.setCode(codeItem.getValueAsString());
		if (eiaTypeItem.getValue() != null)
			eia.setEiaType(new EiaType(eiaTypeItem.getValueAsString()));
		if (fixedAssetIdentifierItem.getValue() != null)
			eia.setFixedAssetIdentifier(fixedAssetIdentifierItem
					.getValueAsString());
		if (workingAreaLocationItem.getValue() != null)
			eia.setWorkingArea(new WorkingArea(Integer
					.valueOf(workingAreaLocationItem.getValueAsString())));
		if (facilityLocationItem.getValue() != null)
			eia.setFacility(new Facility(Integer.valueOf(facilityLocationItem
					.getValueAsString())));
		if (obuItem.getValue() != null)
			eia.setObu(new Obu(Long.parseLong(obuItem.getValueAsString())));
		if (serialNumberItem.getValue() != null)
			eia.setSerialNumber(serialNumberItem.getValueAsString());
		if (stateItem.getValue() != null)
			eia.setState(EiaStateEnum.valueOf(stateItem.getValueAsString()));
		else
			eia.setState(null);
		search(eia);
	}

	private void search(final Eia eia) {
		EIAModel.find(eia, new GHAAsyncCallback<List<Eia>>() {

			@Override
			public void onSuccess(List<Eia> result) {
				List<Eia> newList = new ArrayList<Eia>();
				if (blackList != null) {
					List<AbstractEntity> tmpList = GHAUtil
							.binarySearchFilterEntity(result, blackList);
					List<Eia> newTmpList = new ArrayList<Eia>();
					for (AbstractEntity entity : tmpList)
						newTmpList.add((Eia) entity);
					newList = newTmpList;
				} else
					newList = result;
				resultSet.setRecords(newList, false);
			}
		});
	}

	private void searchForEiaTypes() {
		GHACache.INSTANCE.getEiaTypes(new GHAAsyncCallback<List<EiaType>>() {

			@Override
			public void onSuccess(List<EiaType> result) {
				LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
				for (EiaType eiaType : result)
					valueMap.put(eiaType.getCode(), eiaType.getName());
				eiaTypeItem.setValueMap(valueMap);
			}
		}, false);
	}

	private void searchForLocations() {
		GHACache.INSTANCE
				.getWorkingAreas(new GHAAsyncCallback<List<WorkingArea>>() {
					@Override
					public void onSuccess(List<WorkingArea> result) {
						LinkedHashMap<String, String> valueMapWorkingArea = new LinkedHashMap<String, String>();

						for (WorkingArea entity : result) {
							valueMapWorkingArea.put(entity.getId() + "",
									entity.getName());
						}

						workingAreaLocationItem
								.setValueMap(valueMapWorkingArea);
					}
				});
		GHACache.INSTANCE.getFacilities(new GHAAsyncCallback<List<Facility>>() {
			@Override
			public void onSuccess(List<Facility> result) {
				LinkedHashMap<String, String> valueMapFacility = new LinkedHashMap<String, String>();

				for (Facility entity : result) {
					valueMapFacility.put(entity.getId() + "", entity.getName());
				}

				facilityLocationItem.setValueMap(valueMapFacility);
			}
		});
	}

	private void searchForObus() {
		GHACache.INSTANCE.getObus(new GHAAsyncCallback<List<Obu>>() {

			@Override
			public void onSuccess(List<Obu> result) {
				LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
				for (Obu obu : result)
					valueMap.put(String.valueOf(obu.getId()), obu.getName());
				obuItem.setValueMap(valueMap);
			}
		});
	}

	private void searchForRoleBases() {
		GHACache.INSTANCE.getBaseRoles(new GHAAsyncCallback<List<Role>>() {

			@Override
			public void onSuccess(List<Role> result) {
				LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
				for (Role roleBase : result)
					valueMap.put(String.valueOf(roleBase.getId()),
							roleBase.getName());
				responsibleRoleItem.setValueMap(valueMap);
			}
		});
	}

	@Override
	public void select(Eia eia) {
		search(eia);
	}

	/**
	 * @param eiaType
	 */
	public void select(EiaType eiaType) {
		if (eiaType != null) {
			eiaTypeItem.setValue(eiaType.getCode());
			eiaTypeItem.disable();
		}

	}
}
