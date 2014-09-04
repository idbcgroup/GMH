package org.fourgeeks.gha.webclient.client.materialcategory;

import org.fourgeeks.gha.domain.glm.ServicesResourceCategory;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridRecord;

/**
 * @author alacret Wrapper class for represent a ServicesResourceCategory as a Record
 *         for grid components
 */
public class MaterialCategoryRecord extends GHAGridRecord<ServicesResourceCategory> {

	private final ServicesResourceCategory servicesResourceCategory;

	/**
	 * @param servicesResourceCategory
	 */
	public MaterialCategoryRecord(ServicesResourceCategory servicesResourceCategory) {
		this.servicesResourceCategory = servicesResourceCategory;
		setAttribute("code", servicesResourceCategory.getCode());
		setAttribute("name", servicesResourceCategory.getName());
		// setAttribute("description", servicesResourceCategory.getDescription());
		// setAttribute("model", servicesResourceCategory.getModel());
		// setAttribute("extCode", servicesResourceCategory.getExternalCode());
		//
		// if (servicesResourceCategory.getType() != null)
		// setAttribute("type", servicesResourceCategory.getType().toString());

	}

	@Override
	public ServicesResourceCategory toEntity() {
		return servicesResourceCategory;
	}

}
