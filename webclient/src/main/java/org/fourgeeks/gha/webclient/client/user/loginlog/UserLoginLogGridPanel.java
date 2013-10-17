package org.fourgeeks.gha.webclient.client.user.loginlog;

import java.util.List;

import org.fourgeeks.gha.domain.ess.SSOUser;
import org.fourgeeks.gha.domain.logs.LogonLog;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.interfaces.GHAClosable;
import org.fourgeeks.gha.webclient.client.UI.interfaces.GHAHideable;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHALabel;
import org.fourgeeks.gha.webclient.client.logonlog.LogonLogModel;
import org.fourgeeks.gha.webclient.client.logonlog.LogonLogRecord;
import org.fourgeeks.gha.webclient.client.logonlog.LogonLogUtil;

import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret
 * 
 */
public class UserLoginLogGridPanel extends VLayout implements GHAClosable,
		GHAHideable {

	private LogonLogGrid grid;
	{
		grid = new LogonLogGrid();
	}

	/**
	 */
	public UserLoginLogGridPanel() {
		super();
		setStyleName("sides-padding padding-top");
		setWidth100();
		setBackgroundColor("#E0E0E0");

		GHALabel title = new GHALabel("Log de Connecciones al Sistema");
		addMember(title);

		HLayout mainLayout = new HLayout();
		mainLayout.addMembers(grid);
		addMember(mainLayout);
	}

	@Override
	public void close() {

	}

	/**
	 * @param ssoUser
	 */
	public void loadData(SSOUser ssoUser) {
		LogonLogModel.getLogsByBpu(ssoUser.getBpu(),
				new GHAAsyncCallback<List<LogonLog>>() {

					@Override
					public void onSuccess(List<LogonLog> list) {
						List<LogonLogRecord> gridRecords = LogonLogUtil
								.toGridRecords(list);
						LogonLogRecord[] array = gridRecords
								.toArray(new LogonLogRecord[] {});
						grid.setData(array);
					}
				});
	}

}
