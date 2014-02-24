package org.fourgeeks.gha.webclient.client.UI.formItems.selectitems;

import java.util.LinkedHashMap;
import java.util.List;

import org.fourgeeks.gha.domain.ess.auth.Role;
import org.fourgeeks.gha.domain.gar.Job;
import org.fourgeeks.gha.domain.glm.Bsp;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASelectItem;
import org.fourgeeks.gha.webclient.client.obu.JobModel;

import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;

/**
 * @author emiliot, naramirez
 * 
 */
public class GHAJobSelectItem extends GHASelectItem {

	/**
	 * 
	 */
	public GHAJobSelectItem() {
		super(GHAStrings.get("responsible-role"));
	}

	/**
	 * @param width
	 */
	public GHAJobSelectItem(int width) {
		this();
		setWidth(width);
	}

	/**
	 * @param width
	 * @param required
	 * @param changedHandler
	 */
	public GHAJobSelectItem(int width, boolean required,
			ChangedHandler changedHandler) {
		this(width);
		setRequired(required);
		addChangedHandler(changedHandler);
	}

	/**
	 * @param required
	 * @param changedHandler
	 */
	public GHAJobSelectItem(boolean required, ChangedHandler changedHandler) {
		this();
		setRequired(required);
		addChangedHandler(changedHandler);
	}

	/**
	 * @param selectedBsp
	 * 
	 */
	public void fill(Bsp selectedBsp) {
		clearValue();

		Job entity = new Job();
		entity.setObu(selectedBsp.getObu());

		JobModel.find(entity, new GHAAsyncCallback<List<Job>>() {
			@Override
			public void onSuccess(List<Job> result) {
				LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
				for (Job job : result) {
					Role role = job.getRole();
					valueMap.put(job.getId() + "", role.getName());
				}
				setValueMap(valueMap);
			}
		});
	}
}
