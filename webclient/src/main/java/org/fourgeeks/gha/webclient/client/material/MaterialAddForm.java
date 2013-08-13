package org.fourgeeks.gha.webclient.client.material;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.fourgeeks.gha.domain.glm.ExternalProvider;
import org.fourgeeks.gha.domain.glm.Material;
import org.fourgeeks.gha.domain.glm.MaterialTypeEnum;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHACache;
import org.fourgeeks.gha.webclient.client.UI.GHAImgButton;
import org.fourgeeks.gha.webclient.client.UI.GHASelectItem;
import org.fourgeeks.gha.webclient.client.UI.GHASlideInWindow;
import org.fourgeeks.gha.webclient.client.UI.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

public class MaterialAddForm extends GHASlideInWindow {

	private List<MaterialSelectionListener> listeners;
	private GHATextItem codeItem, extCodeItem, nameItem, descriptionItem,
		modelItem;
	private GHASelectItem typeItem, externalProviderItem;
	
	{
		listeners = new ArrayList<MaterialSelectionListener>();
		codeItem = new GHATextItem("Código", 150);
		extCodeItem = new GHATextItem("Código externo", 150);
		nameItem = new GHATextItem("Nombre", 150);
		descriptionItem = new GHATextItem("Descripción", 480);
		descriptionItem.setColSpan(3);
		typeItem = new GHASelectItem("Tipo", 150);
		modelItem = new GHATextItem("Modelo", 150);
		externalProviderItem = new GHASelectItem("Proveedor externo", 150);
	}
	
	public MaterialAddForm() {
		setHeight(GHAUiHelper.getBottomSectionHeight());
		setTop(240);
		
		Label title = new Label("<h3>Agregar un Material</h3>");
		title.setHeight(30);
		title.setWidth100();
		title.setStyleName("title-label");
		addMember(title);
		
		final DynamicForm form = new DynamicForm();
		form.setTitleOrientation(TitleOrientation.TOP);
		form.setNumCols(4);
		form.setItems(codeItem, extCodeItem, nameItem, descriptionItem,
				typeItem, modelItem, externalProviderItem);
		
		VLayout sideButtons = GHAUiHelper.createBar(
				new GHAImgButton("../resources/icons/save.png", new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						save();
					}
				}),
				new GHAImgButton("../resources/icons/cancel.png",new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						cancel();
					}
				}));
		
		HLayout gridPanel = new HLayout();
		gridPanel.addMembers(form, new LayoutSpacer(), sideButtons);
		addMember(gridPanel);
		fillExternalProviders();
		fillExtras();
	}
	
	private void save() {
		final Material material = new Material();
		material.setCode(codeItem.getValueAsString());
		material.setExtCode(extCodeItem.getValueAsString());
		material.setName(nameItem.getValueAsString());
		material.setDescription(descriptionItem.getValueAsString());
		if (typeItem.getValue() != null)
			material.setType(
					MaterialTypeEnum.valueOf(typeItem.getValueAsString()));
		material.setModel(modelItem.getValueAsString());
		if (externalProviderItem.getValue() != null)
			material.setExternalProvider(new ExternalProvider(
					Integer.parseInt(externalProviderItem.getValueAsString()))
			);
		MaterialModel.save(material, new GHAAsyncCallback<Material>() {

			@Override
			public void onSuccess(Material result) {
				select(result);
				cancel();
			}
		});
	}

	protected void cancel() {
		hide();
		codeItem.clearValue();
		extCodeItem.clearValue();
		nameItem.clearValue();
		descriptionItem.clearValue();
		modelItem.clearValue();
	}
	
	private void fillExternalProviders() {
		GHACache.INSTANCE.getExternalProviders(new GHAAsyncCallback<List<ExternalProvider>>() {
			
			@Override
			public void onSuccess(List<ExternalProvider> result) {
				LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
				for (ExternalProvider externalProvider : result)
					valueMap.put(String.valueOf(externalProvider.getId()),
							externalProvider.getInstitution().getName());
				externalProviderItem.setValueMap(valueMap);
				
			}
		});
	}
	
	private void fillExtras() {
		// types
		typeItem.setValueMap(MaterialTypeEnum.toValueMap());
	}
	
	protected void select(Material material) {
		for (MaterialSelectionListener listener : listeners)
			listener.select(material);
	}
	
	public void addMaterialSelectionListener(MaterialSelectionListener listener) {
		listeners.add(listener);
	}
	
	@Override
	public void onResize(ResizeEvent event) {
		setHeight(GHAUiHelper.getBottomSectionHeight());
	}

}
