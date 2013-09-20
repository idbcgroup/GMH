package org.fourgeeks.gha.webclient.client.user.permissions;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.ess.BpuFunction;
import org.fourgeeks.gha.domain.ess.Function;
import org.fourgeeks.gha.domain.ess.SSOUser;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHASessionData;
import org.fourgeeks.gha.webclient.client.UI.interfaces.GHAClosable;
import org.fourgeeks.gha.webclient.client.UI.interfaces.GHAHideable;
import org.fourgeeks.gha.webclient.client.function.FunctionModel;
import org.fourgeeks.gha.webclient.client.function.FunctionRecord;
import org.fourgeeks.gha.webclient.client.function.FunctionUtil;

import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret
 * 
 */
public class FunctionGridPanel extends VLayout implements GHAClosable,
		GHAHideable {

	private FunctionGrid grid;
	{
		grid = new FunctionGrid();
	}

	/**
	 * @param eIATypeEquipmentSubTab
	 */
	public FunctionGridPanel(UserPermissionSubTab eIATypeEquipmentSubTab) {
		super();
		setStyleName("sides-padding padding-top");
		setWidth100();
		setBackgroundColor("#E0E0E0");

		Label title = new Label("<h3>Permisos</h3>");
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
		FunctionModel.getAll(new GHAAsyncCallback<List<Function>>() {

			@Override
			public void onSuccess(List<Function> all) {
				List<FunctionRecord> gridRecords = FunctionUtil
						.toGridRecords(all);
				FunctionRecord[] array = gridRecords
						.toArray(new FunctionRecord[] {});

				List<BpuFunction> permissions = GHASessionData.getLoggedUser()
						.getPermissions();
				List<String> codes = new ArrayList<String>(permissions.size());
				for (BpuFunction bpuFunction : permissions)
					codes.add(bpuFunction.getFunction().getCode());

				for (int i = 0; i < array.length; i++) {
					FunctionRecord functionRecord = array[i];
					String code = functionRecord.toEntity().getCode();
					functionRecord.setActive(codes.contains(code));
				}

				grid.setData(array);
			}
		});
	}

}
