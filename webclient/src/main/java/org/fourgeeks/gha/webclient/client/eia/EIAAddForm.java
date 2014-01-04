package org.fourgeeks.gha.webclient.client.eia;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.alerts.GHAAlertManager;
import org.fourgeeks.gha.webclient.client.UI.icons.GHACloseButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHASaveButton;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideCloseAction;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAAddForm;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;

import com.smartgwt.client.widgets.AnimationCallback;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret, emiliot Add Eia Form
 * 
 */
public class EIAAddForm extends GHAAddForm<Eia> implements
		EIATypeSelectionListener, EiaSelectionProducer {

	/**
	 * @param title
	 * 
	 */
	public EIAAddForm(String title) {
		super(title);
		form = new EIAForm();
		VLayout sideButtons = GHAUiHelper.createBar(new GHASaveButton(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						save();
					}
				}), new GHACloseButton(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				cancel();
			}
		}));

		HLayout gridPanel = new HLayout();
		gridPanel.addMembers(form, new LayoutSpacer(), sideButtons);
		addMember(gridPanel);
	}

	@Override
	public void addEiaSelectionListener(
			EIASelectionListener eiaSelectionListener) {
		((EiaSelectionProducer) form)
				.addEiaSelectionListener(eiaSelectionListener);

	}

	@Override
	public boolean canBeClosen(HideCloseAction hideAction) {
		return true;
	}

	@Override
	public boolean canBeHidden(HideCloseAction hideAction) {
		return true;
	}

	@Override
	public void close() {
		hide(new AnimationCallback() {

			@Override
			public void execute(boolean earlyFinish) {
				form.hide();
				destroy();
			}
		});
	}

	@Override
	public void hide() {
		super.hide();
		form.hide();
	}

	@Override
	public void notifyEia(Eia eia) {
		return;
	}

	@Override
	public void open() {
		super.open();
		form.show();
	}

	@Override
	public void removeEiaSelectionListener(
			EIASelectionListener eiaSelectionListener) {
		((EiaSelectionProducer) form)
				.removeEiaSelectionListener(eiaSelectionListener);
	}

	@Override
	protected void save() {
		form.save(new GHAAsyncCallback<Eia>() {

			@Override
			public void onSuccess(Eia result) {
				GHAAlertManager.alert("eia-save-success");
				hide();
			}
		});

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener#select
	 * (org.fourgeeks.gha.domain.gmh.EiaType)
	 */
	@Override
	public void select(EiaType eiaType) {
		((EIATypeSelectionListener) form).select(eiaType);
	}

}
