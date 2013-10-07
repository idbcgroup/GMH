package org.fourgeeks.gha.webclient.client.user;

import org.fourgeeks.gha.domain.ess.SSOUser;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAImgButton;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHASlideInWindow;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.smartgwt.client.types.AnimationEffect;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret, emiliot
 * 
 */
public class UserAddForm extends GHASlideInWindow implements
		UserSelectionProducer, UserSelectionListener {
	private UserForm userForm;
	{
		userForm = new UserForm();
	}

	/**
	 * 
	 */
	public UserAddForm() {
		super(2);
		setHeight(GHAUiHelper.getBottomSectionHeight());
		setTop(240);

		Label title = new Label("<h3>Nuevo Usuario</h3>");
		title.setHeight(30);
		title.setWidth100();
		title.setStyleName("title-label");
		addMember(title);

		VLayout sideButtons = GHAUiHelper.createBar(new GHAImgButton(
				"../resources/icons/save.png", new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						save();
					}
				}), new GHAImgButton("../resources/icons/cancel.png",
				new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						cancel();
					}
				}));

		HLayout gridPanel = new HLayout();
		gridPanel.addMembers(userForm, new LayoutSpacer(), sideButtons);
		addMember(gridPanel);

		// register as listener to the userForm
		userForm.addUserSelectionListener(this);
	}

	protected void cancel() {
		userForm.hide();
		super.hide();
	}

	private void save() {
		userForm.save();
	}

	@Override
	public void onResize(ResizeEvent event) {
		setHeight(GHAUiHelper.getBottomSectionHeight());
	}

	@Override
	public void close() {
		userForm.destroy();
		destroy();
	}

	@Override
	public void open() {
		this.show();
		animateShow(AnimationEffect.FLY);
	}

	public void show() {
		super.show();
		userForm.show();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.user.UserSelectionProducer#
	 * addUserSelectionListener
	 * (org.fourgeeks.gha.webclient.client.user.UserSelectionListener)
	 */
	@Override
	public void addUserSelectionListener(
			UserSelectionListener userSelectionListener) {
		userForm.addUserSelectionListener(userSelectionListener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.user.UserSelectionProducer#
	 * removeUserSelectionListener
	 * (org.fourgeeks.gha.webclient.client.user.UserSelectionListener)
	 */
	@Override
	public void removeUserSelectionListener(
			UserSelectionListener userSelectionListener) {
		userForm.removeUserSelectionListener(userSelectionListener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.user.UserSelectionListener#select(
	 * org.fourgeeks.gha.domain.ess.SSOUser)
	 */
	@Override
	public void select(SSOUser ssoUser) {
		// called when the userForm finish saving new entity
		cancel();
	}
}
