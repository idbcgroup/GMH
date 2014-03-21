package org.fourgeeks.gha.webclient.client.utility;

import org.fourgeeks.gha.domain.glm.MaterialBrand;
import org.fourgeeks.gha.domain.glm.MaterialTypeEnum;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.alerts.GHAAlertManager;
import org.fourgeeks.gha.webclient.client.materialbrand.MaterialBrandAddForm;
import org.fourgeeks.gha.webclient.client.materialbrand.MaterialBrandSetType;

/**
 * @author emiliot
 * 
 */
public class UtilityAddForm extends MaterialBrandAddForm {

	/**
	 * @param title
	 */
	public UtilityAddForm(String title) {
		super(title);
		((MaterialBrandSetType) form).setType(MaterialTypeEnum.UTILITARIO);
	}

	@Override
	protected void save() {
		form.save(new GHAAsyncCallback<MaterialBrand>() {

			@Override
			public void onSuccess(MaterialBrand arg0) {
				GHAAlertManager.alert("utilityservice-save-success");
				hide();
			}
		});
	}
}
