package org.fourgeeks.gha.webclient.client.eiatype;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.icons.GHACloseButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHAImgButton;
import org.fourgeeks.gha.webclient.client.UI.interfaces.GHAClosable;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHALabel;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHASlideInWindow;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.widgets.AnimationCallback;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret, emiliot
 * 
 */
public class EIATypeAddForm extends GHASlideInWindow implements
		EiaTypeSelectionProducer, GHAClosable {

	private EiaTypeForm form;

	{
		form = new EiaTypeForm();
	}

	/**
	 * 
	 */
	public EIATypeAddForm() {
		super();
		setWidth100();
		setHeight(GHAUiHelper.getTabHeight());
		addMember(new GHALabel(GHAStrings.get("new-eiatype")));

		VLayout sideButtons = GHAUiHelper.createBar(new GHAImgButton(
				"../resources/icons/save.png", new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						save();
					}
				}), new GHACloseButton(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				canBeHide();
			}
		}));

		HLayout gridPanel = new HLayout();
		gridPanel.addMembers(form, new LayoutSpacer(), sideButtons);
		addMember(gridPanel);

		// register as listener to the eiatypeform
		// form.addEiaTypeSelectionListener(this);
	}

	// protected void cancel() {
	// form.hide();
	// super.hide();
	// }

	// @Override
	// public void open() {
	// super.open();
	// form.show();
	// }

	@Override
	public void addEiaTypeSelectionListener(
			EIATypeSelectionListener eIATypeSelectionListener) {
		form.addEiaTypeSelectionListener(eIATypeSelectionListener);
	}

	@Override
	public boolean canBeClosen() {
		return true;
	}

	@Override
	public boolean canBeHidden() {
		return true;
	}

	// Producer/consumer stuff

	@Override
	public void close() {
		hide(new AnimationCallback() {

			@Override
			public void execute(boolean earlyFinish) {
				destroy();
			}
		});
	}

	@Override
	public void notifyEiaType(EiaType eiaType) {
		form.notifyEiaType(eiaType);
	}

	@Override
	public void onResize(ResizeEvent event) {
		setHeight(GHAUiHelper.getTabHeight());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.UI.superclasses.GHASlideInWindow#open
	 * ()
	 */
	@Override
	public void open() {
		super.open();
		form.activate();
	}

	@Override
	public void removeEiaTypeSelectionListener(
			EIATypeSelectionListener eIATypeSelectionListener) {
		form.removeEiaTypeSelectionListener(eIATypeSelectionListener);

	}

	public void canBeHide() {
		form.canBeHidden(new BooleanCallback() {

			@Override
			public void execute(Boolean value) {
				if (value == true) {
					// user selected to discard changes or there are no changes
					EIATypeAddForm.this.hide();

				}
			}
		});
	}

	private void save() {
		form.save(new GHAAsyncCallback<EiaType>() {

			@Override
			public void onSuccess(EiaType arg0) {
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
	// @Override
	// public void select(EiaType eiaType) {
	// // cancel();
	// }
}
