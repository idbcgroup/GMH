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
		Date purchaseOrderDate = eia.getPurchaseOrderDate();
		Date purchaseInvoiceDate = eia.getPurchaseInvoiceDate();
		Date receptionDate = eia.getReceptionDate();
		Date installationDate = eia.getInstallationDate();

		// fecha de orden de compra
		if (purchaseOrderDate != null) {
			if (purchaseDate == null)
				return false;
			if (purchaseDate.getTime() > purchaseOrderDate.getTime())
				return false;
		}

		// fecha de facturacion
		if (purchaseInvoiceDate != null) {
			if (purchaseDate == null)
				return false;
			if (purchaseDate.getTime() > purchaseInvoiceDate.getTime())
				return false;

			if (purchaseOrderDate != null)
				if (purchaseOrderDate.getTime() > purchaseInvoiceDate.getTime())
					return false;
		}

		// fecha de repecion del equipo
		if (receptionDate != null) {
			if (purchaseDate == null)
				return false;
			if (purchaseDate.getTime() > receptionDate.getTime())
				return false;

			if (purchaseInvoiceDate != null)
				if (purchaseInvoiceDate.getTime() > receptionDate.getTime())
					return false;
			if (purchaseOrderDate != null)
				if (purchaseOrderDate.getTime() > receptionDate.getTime())
					return false;

		}

		// fecha de instalacion del equipo
		if (installationDate != null) {
			if (receptionDate == null)
				return false;
			if (receptionDate.getTime() > installationDate.getTime())
				return false;
		}

		return true;
	}
}
