package org.fourgeeks.gha.webclient.client.materialcategory;

import org.fourgeeks.gha.domain.glm.MaterialCategory;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.alerts.GHAAlertManager;
import org.fourgeeks.gha.webclient.client.UI.icons.GHACancelButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHASaveButton;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideCloseAction;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAAddForm;

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
public class MaterialCategoryAddForm extends GHAAddForm<MaterialCategory>
implements MaterialCategorySelectionProducer {

	protected MaterialCategoryForm form;
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
	public boolean canBeClosen(HideCloseAction hideAction) {// TODO
		if (form.hasUnCommittedChanges()) {
			GHAAlertManager.confirm("unsaved-changes", new BooleanCallback() {

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
	public boolean canBeHidden(HideCloseAction hideAction) {// TODO
		if (form.hasUnCommittedChanges()) {
			GHAAlertManager.confirm("unsaved-changes", new BooleanCallback() {

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
			GHAAlertManager.confirm("unsaved-changes", new BooleanCallback() {

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
	public void open() {
		super.open();
		form.show();
	}

	@Override
	public void removeMaterialSelectionListener(
			MaterialCategorySelectionListener materialSelectionListener) {
		form.addMaterialSelectionListener(materialSelectionListener);

	}

	@Override
	protected void save() {
		form.save(new GHAAsyncCallback<MaterialCategory>() {

			@Override
			public void onSuccess(MaterialCategory arg0) {
				GHAAlertManager.alert("material-save-success");
				form.cancel();
				hide();
			}

		});
	}

}
