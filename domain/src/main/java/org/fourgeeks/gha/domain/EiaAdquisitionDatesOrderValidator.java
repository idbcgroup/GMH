package org.fourgeeks.gha.domain;

import java.sql.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.fourgeeks.gha.domain.gmh.Eia;

public class EiaAdquisitionDatesOrderValidator implements
		ConstraintValidator<EiaAdquisitionDatesOrderValidation, Eia> {

	@Override
	public void initialize(EiaAdquisitionDatesOrderValidation arg0) {
	}

	@Override
	public boolean isValid(Eia eia, ConstraintValidatorContext arg1) {
		Date purchaseDate = eia.getPurchaseDate();
		Date receptionDate = eia.getReceptionDate();
		Date installationDate = eia.getInstallationDate();

		if (receptionDate != null) {
			if (purchaseDate == null)
				return false;
			if (purchaseDate.getTime() > receptionDate.getTime())
				return false;
		}

		if (installationDate != null) {
			if (receptionDate == null)
				return false;
			if (receptionDate.getTime() > installationDate.getTime())
				return false;
		}

		return true;
	}
}
