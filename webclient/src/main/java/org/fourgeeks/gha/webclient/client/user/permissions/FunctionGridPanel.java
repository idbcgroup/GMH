package org.fourgeeks.gha.webclient.client.user.permissions;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.ess.BpuFunction;
import org.fourgeeks.gha.domain.ess.Function;
import org.fourgeeks.gha.domain.ess.SSOUser;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridField;
import org.fourgeeks.gha.webclient.client.UI.interfaces.ClosableListener;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideCloseAction;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideableListener;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHALabel;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAVerticalLayout;
import org.fourgeeks.gha.webclient.client.function.FunctionModel;
import org.fourgeeks.gha.webclient.client.function.FunctionRecord;
import org.fourgeeks.gha.webclient.client.function.FunctionUtil;
import org.fourgeeks.gha.webclient.client.user.UserModel;

import com.smartgwt.client.widgets.grid.events.CellSavedEvent;
import com.smartgwt.client.widgets.grid.events.CellSavedHandler;
import com.smartgwt.client.widgets.layout.HLayout;

/**
 * @author alacret
 * 
 */
public class FunctionGridPanel extends GHAVerticalLayout implements
		ClosableListener, HideableListener {

	private FunctionGrid grid;
	private SSOUser ssoUser;
	{
		grid = new FunctionGrid();
	}

	/**
	 * @param eIATypeEquipmentSubTab
	 */
	public FunctionGridPanel(UserPermissionSubTab eIATypeEquipmentSubTab) {
		super();
		GHALabel title = new GHALabel("Permisos");
		addMember(title);

		HLayout mainLayout = new HLayout();
		mainLayout.addMembers(grid);
		addMember(mainLayout);

		GHAGridField activeField = grid.getActiveField();
		activeField.addCellSavedHandler(new CellSavedHandler() {

			@Override
			public void onCellSaved(CellSavedEvent event) {
				Function function = ((FunctionRecord) event.getRecord())
						.toEntity();
				boolean newValue = (Boolean) event.getNewValue();

				if (newValue)
					UserModel.save(new BpuFunction(ssoUser.getBpu(), function),
							new GHAAsyncCallback<BpuFunction>() {

								@Override
								public void onSuccess(BpuFunction arg0) {

								}
							});
				else
					UserModel.delete(
							new BpuFunction(ssoUser.getBpu(), function),
							new GHAAsyncCallback<Void>() {

								@Override
								public void onSuccess(Void arg0) {

								}
							});

			}
		});
	}

	@Override
	public void close() {

	}

	/**
	 * @param ssoUser
	 */
	public void loadData(final SSOUser ssoUser) {
		this.ssoUser = ssoUser;
		FunctionModel.getAll(new GHAAsyncCallback<List<Function>>() {

			@Override
			public void onSuccess(final List<Function> allPermissions) {
				UserModel.getFunctionsByBpu(ssoUser.getBpu(),
						new GHAAsyncCallback<List<BpuFunction>>() {

							@Override
							public void onSuccess(
									List<BpuFunction> userPermissions) {
								// All
								List<FunctionRecord> gridRecords = FunctionUtil
										.toGridRecords(allPermissions);
								FunctionRecord[] array = gridRecords
										.toArray(new FunctionRecord[] {});
								// User
								List<String> codes = new ArrayList<String>(
										userPermissions.size());
								for (BpuFunction bpuFunction : userPermissions)
									codes.add(bpuFunction.getFunction()
											.getCode());
								if (!codes.isEmpty())
									for (int i = 0; i < array.length; i++) {
										FunctionRecord functionRecord = array[i];
										String code = functionRecord.toEntity()
												.getCode();
										functionRecord.setActive(codes
												.contains(code));
									}

								grid.setData(array);

							}
						});
			}
		});
	}

	@Override
	public boolean canBeHidden(HideCloseAction hideAction) {
		return true;
	}

	@Override
	public boolean canBeClosen(HideCloseAction hideAction) {
		return true;
	}

}
