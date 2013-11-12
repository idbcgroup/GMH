package org.fourgeeks.gha.webclient.client.user.permissions;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.ess.SSOUser;
import org.fourgeeks.gha.domain.ess.ui.AppFormViewFunction;
import org.fourgeeks.gha.domain.ess.ui.AppFormViewFunctionBpu;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridField;
import org.fourgeeks.gha.webclient.client.UI.interfaces.ClosableListener;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideCloseAction;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideableListener;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHALabel;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAVerticalLayout;
import org.fourgeeks.gha.webclient.client.appformviewfunction.AppFormViewFunctionModel;
import org.fourgeeks.gha.webclient.client.appformviewfunction.AppFormViewFunctionRecord;
import org.fourgeeks.gha.webclient.client.appformviewfunction.AppFormViewFunctionUtil;
import org.fourgeeks.gha.webclient.client.appformviewfunctionbpu.AppFormViewFunctionBpuModel;

import com.smartgwt.client.widgets.grid.events.CellSavedEvent;
import com.smartgwt.client.widgets.grid.events.CellSavedHandler;
import com.smartgwt.client.widgets.layout.HLayout;

/**
 * @author alacret
 * 
 */
public class AppFormViewFunctionGridPanel extends GHAVerticalLayout implements
		ClosableListener, HideableListener {

	private AppFormViewFunctionGrid grid;
	private SSOUser ssoUser;
	{
		grid = new AppFormViewFunctionGrid();
	}

	/**
	 */
	public AppFormViewFunctionGridPanel() {
		super();
		GHALabel title = new GHALabel(GHAStrings.get("permissions"));
		addMember(title);

		HLayout mainLayout = new HLayout();
		mainLayout.addMembers(grid);
		addMember(mainLayout);

		GHAGridField activeField = grid.getActiveField();
		activeField.addCellSavedHandler(new CellSavedHandler() {

			@Override
			public void onCellSaved(CellSavedEvent event) {
				AppFormViewFunction function = ((AppFormViewFunctionRecord) event
						.getRecord()).toEntity();
				boolean newValue = (Boolean) event.getNewValue();

				if (newValue)
					AppFormViewFunctionBpuModel.save(
							new AppFormViewFunctionBpu(ssoUser.getBpu(),
									function.getAppForm(), function.getView(),
									function.getFunction()),
							new GHAAsyncCallback<AppFormViewFunctionBpu>() {

								@Override
								public void onSuccess(
										AppFormViewFunctionBpu arg0) {

								}
							});
				else
					AppFormViewFunctionBpuModel.delete(
							new AppFormViewFunctionBpu(ssoUser.getBpu(),
									function.getAppForm(), function.getView(),
									function.getFunction()),
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
		AppFormViewFunctionModel
				.getAll(new GHAAsyncCallback<List<AppFormViewFunction>>() {

					@Override
					public void onSuccess(
							final List<AppFormViewFunction> allPermissions) {
						AppFormViewFunctionBpuModel.getFunctionsByBpu(
								ssoUser.getBpu(),
								new GHAAsyncCallback<List<AppFormViewFunctionBpu>>() {

									@Override
									public void onSuccess(
											List<AppFormViewFunctionBpu> userPermissions) {
										// All
										List<AppFormViewFunctionRecord> gridRecords = AppFormViewFunctionUtil
												.toGridRecords(allPermissions);
										AppFormViewFunctionRecord[] array = gridRecords
												.toArray(new AppFormViewFunctionRecord[] {});
										// User
										List<String> codes = new ArrayList<String>(
												userPermissions.size());
										for (AppFormViewFunctionBpu bpuFunction : userPermissions)
											codes.add(bpuFunction.getFunction()
													.getCode());
										if (!codes.isEmpty())
											for (int i = 0; i < array.length; i++) {
												AppFormViewFunctionRecord functionRecord = array[i];
												String code = functionRecord
														.toEntity()
														.getFunction()
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
