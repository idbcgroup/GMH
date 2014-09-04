package org.fourgeeks.gha.webclient.client.UI;

import javax.validation.Validator;

import org.fourgeeks.gha.domain.Activity;
import org.fourgeeks.gha.domain.ess.auth.SSOUser;
import org.fourgeeks.gha.domain.gar.Bpu;
import org.fourgeeks.gha.domain.glm.Material;
import org.fourgeeks.gha.domain.glm.MaterialBrand;
import org.fourgeeks.gha.domain.glm.ServicesResourceCategory;
import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.GlaLog;
import org.fourgeeks.gha.domain.gmh.EiaMaintenancePlanification;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.domain.mix.Bpi;
import org.fourgeeks.gha.domain.mix.Citizen;
import org.fourgeeks.gha.domain.mix.LegalEntity;

import com.google.gwt.core.client.GWT;
import com.google.gwt.validation.client.AbstractGwtValidatorFactory;
import com.google.gwt.validation.client.GwtValidation;
import com.google.gwt.validation.client.impl.AbstractGwtValidator;

/**
 * @author alacret
 * 
 */
public final class GhaValidatorFactory extends AbstractGwtValidatorFactory {

	/**
	 * Only the classes and groups listed in the GwtValidation annotation can be
	 * validated.
	 */
	@GwtValidation({ EiaType.class, Citizen.class, Eia.class, Material.class,
			ServicesResourceCategory.class, MaterialBrand.class, SSOUser.class,
			SSOUser.class, LegalEntity.class, Bpu.class, Bpi.class,
			MaintenancePlan.class, Activity.class, GlaLog.class,
			EiaMaintenancePlanification.class })
	public interface GwtValidator extends Validator {
	}

	@Override
	public AbstractGwtValidator createValidator() {
		return GWT.create(GwtValidator.class);
	}
}
