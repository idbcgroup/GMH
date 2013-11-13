package org.fourgeeks.gha.domain;

import java.sql.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.fourgeeks.gha.domain.gmh.Eia;

public class EiaWarrantyDatesValidator implements
		ConstraintValidator<EiaWarrantyDatesValidation, Eia> {

	@Override
	public void initialize(EiaWarrantyDatesValidation arg0) {
	}

	@Override
	public boolean isValid(Eia eia, ConstraintValidatorContext contex) {
		Date purchaseDate = eia.getPurchaseDate();
		Date intWarrantyDate = eia.getIntWarrantyBegin();
		Date realWarrantyDate = eia.getRealWarrantyBegin();

		// fecha de inicio de garantia del intermediario
		if (intWarrantyDate != null) {
			if (purchaseDate != null)
				if (purchaseDate.getTime() > intWarrantyDate.getTime())
					return false;
		}

		// fecha de inicio de ganrantia real
		if (realWarrantyDate != null) {
			if (purchaseDate != null)
				if (purchaseDate.getTime() > realWarrantyDate.getTime())
					return false;

		}

		return true;
	}
}
