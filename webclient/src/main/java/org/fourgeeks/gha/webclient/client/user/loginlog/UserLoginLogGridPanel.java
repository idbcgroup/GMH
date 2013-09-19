package org.fourgeeks.gha.webclient.client.user.loginlog;

import java.util.List;

import org.fourgeeks.gha.domain.ess.SSOUser;
import org.fourgeeks.gha.domain.logs.LogonLog;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.interfaces.GHAClosable;
import org.fourgeeks.gha.webclient.client.UI.interfaces.GHAHideable;
import org.fourgeeks.gha.webclient.client.log.LogonLogModel;
import org.fourgeeks.gha.webclient.client.log.LogonLogRecord;
import org.fourgeeks.gha.webclient.client.log.LogonLogUtil;

import com.smartgwt.client.widgets.Label;
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
	 * @param eIATypeEquipmentSubTab
	 */
	public UserLoginLogGridPanel(UserLoginLogSubTab eIATypeEquipmentSubTab) {
		super();
		setStyleName("sides-padding padding-top");
		setWidth100();
		setBackgroundColor("#E0E0E0");

		Label title = new Label("<h3>Log de Connecciones al Sistema</h3>");
		title.setHeight(30);
		title.setWidth100();
		title.setStyleName("title-label");
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
