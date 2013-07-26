package org.fourgeeks.gha.webclient.client;

import javax.validation.Validator;

import org.fourgeeks.gha.domain.gmh.EiaType;

import com.google.gwt.core.client.GWT;
import com.google.gwt.validation.client.AbstractGwtValidatorFactory;
import com.google.gwt.validation.client.GwtValidation;
import com.google.gwt.validation.client.impl.AbstractGwtValidator;

public final class GhaValidatorFactory extends AbstractGwtValidatorFactory {

  /**
   * Only the classes and groups listed
   * in the GwtValidation annotation can be validated.
   */
	@GwtValidation(EiaType.class)
	public interface GwtValidator extends Validator {}

	@Override
	public AbstractGwtValidator createValidator() {
		return GWT.create(GwtValidator.class);
	}
}
