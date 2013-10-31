package org.fourgeeks.gha.webclient.client.materialcategory;

import org.fourgeeks.gha.domain.glm.MaterialCategory;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.icons.GHACancelButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHASaveButton;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAAddForm;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHANotification;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret
 * 
 */
public class MaterialCategoryAddForm extends GHAAddForm implements
		MaterialCategorySelectionProducer {

	private MaterialCategoryForm form;
	{
		form = new MaterialCategoryForm();
	}

	/**
	 * @param title
	 * 
	 */
	public MaterialCategoryAddForm(String title) {
		super(title);
		VLayout sideButtons = GHAUiHelper.createBar(new GHASaveButton(
				new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						save();
					}
				}), new GHACancelButton(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				hide();
			}
		}));
		HLayout gridPanel = new HLayout();
		// gridPanel.setAlign(VerticalAlignment.TOP);
		gridPanel.addMembers(form, new LayoutSpacer(), sideButtons);
		addMember(gridPanel);
	}

	@Override
	public void addMaterialSelectionListener(
			MaterialCategorySelectionListener materialSelectionListener) {
		form.addMaterialSelectionListener(materialSelectionListener);
	}

	@Override
	public boolean canBeClosen() {
		if (form.hasUnCommittedChanges()) {
			GHANotification.confirm(GHAStrings.get("information"),
					GHAStrings.get("unsaved-changes"), new BooleanCallback() {

						@Override
						public void execute(Boolean value) {
							if (value) {
								form.undo();
							}
						}
					});
			return false;
		}
		return true;
	}

	@Override
	public boolean canBeHidden() {
		if (form.hasUnCommittedChanges()) {
			GHANotification.confirm(GHAStrings.get("information"),
					GHAStrings.get("unsaved-changes"), new BooleanCallback() {

						@Override
						public void execute(Boolean value) {
							if (value) {
								form.undo();
							}
						}
					});
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.UI.superclasses.GHASlideInWindow#hide
	 * ()
	 */
	@Override
	public void hide() {
		if (form.hasUnCommittedChanges()) {
			GHANotification.confirm(GHAStrings.get("information"),
					GHAStrings.get("unsaved-changes"), new BooleanCallback() {

						@Override
						public void execute(Boolean value) {
							if (value) {
								form.undo();
								form.hide();
								MaterialCategoryAddForm.super.hide();
							}
						}
					});
			return;
		}
		form.hide();
		super.hide();
	}

	@Override
	public void notifyMaterialCategory(MaterialCategory materialCategory) {
		form.notifyMaterialCategory(materialCategory);
	}

	@Override
	public void onResize(ResizeEvent event) {
		setHeight(GHAUiHelper.getBottomSectionHeight());
	}

	@Override
	public void removeMaterialSelectionListener(
			MaterialCategorySelectionListener materialSelectionListener) {
		form.addMaterialSelectionListener(materialSelectionListener);

	}

	private void save() {
		form.save(new GHAAsyncCallback<MaterialCategory>() {

			@Override
			public void onSuccess(MaterialCategory arg0) {
				GHANotification.alert("material-save-success");
				hide();
			}

		});
	}

}
