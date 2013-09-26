package org.fourgeeks.gha.webclient.client;

import javax.validation.Validator;

import org.fourgeeks.gha.domain.ess.SSOUser;
import org.fourgeeks.gha.domain.gar.Bpu;
import org.fourgeeks.gha.domain.glm.Material;
import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.MaintenanceActivity;
import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.domain.gmh.MaintenanceProtocol;
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
	@GwtValidation({ EiaType.class, Eia.class, Material.class, SSOUser.class, Citizen.class, LegalEntity.class, Bpu.class, MaintenanceProtocol.class, MaintenancePlan.class, MaintenanceActivity.class })
	public interface GwtValidator extends Validator {
	}

	@Override
	public AbstractGwtValidator createValidator() {
		return GWT.create(GwtValidator.class);
	}
}
