package org.fourgeeks.gha.webclient.client.user.loginlog;

import java.util.List;

import org.fourgeeks.gha.domain.ess.SSOUser;
import org.fourgeeks.gha.domain.logs.LogonLog;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.interfaces.ClosableListener;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideCloseAction;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideableListener;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHALabel;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAVerticalLayout;
import org.fourgeeks.gha.webclient.client.logonlog.LogonLogModel;
import org.fourgeeks.gha.webclient.client.logonlog.LogonLogRecord;
import org.fourgeeks.gha.webclient.client.logonlog.LogonLogUtil;
import org.fourgeeks.gha.webclient.client.user.UserSelectionListener;

import com.smartgwt.client.widgets.layout.HLayout;

/**
 * @author alacret
 * 
 */
public class UserLoginLogGridPanel extends GHAVerticalLayout implements
		ClosableListener, HideableListener, UserSelectionListener {

	private LogonLogGrid grid;
	{
		grid = new LogonLogGrid();
	}

	/**
	 */
	public UserLoginLogGridPanel() {
		super();
		final GHALabel title = new GHALabel("Log de Connecciones al Sistema");
		addMember(title);

		final HLayout mainLayout = new HLayout();
		mainLayout.addMembers(grid);
		addMember(mainLayout);
	}

	@Override
	public void close() {

	}

	@Override
	public boolean canBeHidden(HideCloseAction hideAction) {
		return true;
	}

	@Override
	public boolean canBeClosen(HideCloseAction hideAction) {
		return true;
	}

	@Override
	public void select(SSOUser ssoUser) {
		LogonLogModel.getLogsByBpu(ssoUser.getBpu(),
				new GHAAsyncCallback<List<LogonLog>>() {

					@Override
					public void onSuccess(List<LogonLog> list) {
						final List<LogonLogRecord> gridRecords = LogonLogUtil
								.toGridRecords(list);
						final LogonLogRecord[] array = gridRecords
								.toArray(new LogonLogRecord[] {});
						grid.setData(array);
					}
				});
	}

}
