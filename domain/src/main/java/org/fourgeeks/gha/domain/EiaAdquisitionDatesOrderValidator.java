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

		if (purchaseDate == null && receptionDate == null
				&& installationDate != null)
			return false;

		if (purchaseDate == null && receptionDate != null
				&& installationDate != null)
			return false;

		if (installationDate != null && receptionDate != null
				&& purchaseDate != null) {
			return purchaseDate.getTime() <= receptionDate.getTime()
					&& purchaseDate.getTime() <= installationDate.getTime();
		}

		return purchaseDate.getTime() <= receptionDate.getTime();
	}
}
