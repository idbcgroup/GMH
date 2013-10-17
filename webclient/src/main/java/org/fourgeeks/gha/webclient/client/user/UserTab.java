package org.fourgeeks.gha.webclient.client.user;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.ess.SSOUser;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHATab;

import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret
 * 
 */
public class UserTab extends GHATab implements UserSelectionListener,
		UserSelectionProducer {

	/**
	 * 
	 */
	public static final String ID = "user";
	private static final String TITLE = "Usuarios";
	private UserTopSection topSection;
	private UserInternalTabset internalTabset;
	private SSOUser user;
	private List<UserSelectionListener> listeners;
	{
		listeners = new ArrayList<UserSelectionListener>();
	}

	/**
	 * @param token
	 * 
	 */
	public UserTab(String token) {
		super(token);
		getHeader().setTitle(TITLE);

		topSection = new UserTopSection(this);
		internalTabset = new UserInternalTabset(this);

		// Creacion de la tab de EIA
		VLayout verticalPanel = new VLayout();
		verticalPanel.setBackgroundColor("#E0E0E0");

		verticalPanel.addMember(topSection);
		verticalPanel.addMember(GHAUiHelper
				.verticalGraySeparator(GHAUiHelper.V_SEPARATOR_HEIGHT + "px"));
		verticalPanel.addMember(internalTabset);
		addMember(verticalPanel);

	}

	@Override
	protected void onDraw() {
		if (user == null)
			topSection.search();
	}

	@Override
	public String getId() {
		return ID;
	}

	@Override
	public void addUserSelectionListener(
			UserSelectionListener userSelectionListener) {
		listeners.add(userSelectionListener);

	}

	@Override
	public void removeUserSelectionListener(
			UserSelectionListener userSelectionListener) {
		listeners.remove(userSelectionListener);

	}

	@Override
	public void select(SSOUser ssoUser) {
		for (UserSelectionListener listener : listeners)
			listener.select(ssoUser);
	}
}