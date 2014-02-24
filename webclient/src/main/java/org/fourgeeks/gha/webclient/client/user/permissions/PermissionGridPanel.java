package org.fourgeeks.gha.webclient.client.user.permissions;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.ess.auth.SSOUser;
import org.fourgeeks.gha.domain.ess.ui.PermissionBpu;
import org.fourgeeks.gha.domain.ess.ui.ViewPermission;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridField;
import org.fourgeeks.gha.webclient.client.UI.interfaces.ClosableListener;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideCloseAction;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideableListener;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAFormLayout;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHALabel;
import org.fourgeeks.gha.webclient.client.permissionbpu.PermissionBpuModel;
import org.fourgeeks.gha.webclient.client.viewpermission.ViewPermissionModel;
import org.fourgeeks.gha.webclient.client.viewpermission.ViewPermissionRecord;
import org.fourgeeks.gha.webclient.client.viewpermission.ViewPermissionUtil;

import com.smartgwt.client.widgets.grid.events.CellSavedEvent;
import com.smartgwt.client.widgets.grid.events.CellSavedHandler;
import com.smartgwt.client.widgets.layout.HLayout;

/**
 * @author alacret
 * 
 */
public class PermissionGridPanel extends GHAFormLayout implements
		ClosableListener, HideableListener {

	private ViewPermissionGrid grid;
	private SSOUser ssoUser;
	{
		grid = new ViewPermissionGrid();
	}

	/**
	 */
	public PermissionGridPanel() {
		super();
		final GHALabel title = new GHALabel(GHAStrings.get("permissions"));
		addMember(title);

		final HLayout mainLayout = new HLayout();
		mainLayout.addMembers(grid);
		addMember(mainLayout);

		final GHAGridField activeField = grid.getActiveField();
		activeField.addCellSavedHandler(new CellSavedHandler() {

			@Override
			public void onCellSaved(CellSavedEvent event) {
				final ViewPermission function = ((ViewPermissionRecord) event
						.getRecord()).toEntity();
				final boolean newValue = (Boolean) event.getNewValue();

				if (newValue)
					PermissionBpuModel.save(new PermissionBpu(ssoUser.getBpu(),
							function.getPermission()),
							new GHAAsyncCallback<PermissionBpu>() {

								@Override
								public void onSuccess(PermissionBpu arg0) {

								}
							});
				else
					PermissionBpuModel.delete(
							new PermissionBpu(ssoUser.getBpu(), function
									.getPermission()),
							new GHAAsyncCallback<Void>() {

								@Override
								public void onSuccess(Void arg0) {

								}
							});

			}
		});
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

	}

	/**
	 * @param ssoUser
	 */
	public void loadData(final SSOUser ssoUser) {
		this.ssoUser = ssoUser;
		ViewPermissionModel
				.getAll(new GHAAsyncCallback<List<ViewPermission>>() {

					@Override
					public void onSuccess(
							final List<ViewPermission> allPermissions) {
						PermissionBpuModel.getPermissionsByBpu(
								ssoUser.getBpu(),
								new GHAAsyncCallback<List<PermissionBpu>>() {

									@Override
									public void onSuccess(
											List<PermissionBpu> userPermissions) {
										// All
										final List<ViewPermissionRecord> gridRecords = ViewPermissionUtil
												.toGridRecords(allPermissions);
										final ViewPermissionRecord[] array = gridRecords
												.toArray(new ViewPermissionRecord[] {});
										// User
										final List<String> codes = new ArrayList<String>(
												userPermissions.size());
										for (final PermissionBpu bpuFunction : userPermissions)
											codes.add(bpuFunction.getFunction()
													.getCode());
										if (!codes.isEmpty())
											for (int i = 0; i < array.length; i++) {
												final ViewPermissionRecord functionRecord = array[i];
												final String code = functionRecord
														.toEntity()
														.getPermission()
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

}
