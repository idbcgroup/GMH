package org.fourgeeks.gha.ejb.helpers;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.fourgeeks.gha.domain.enu.DocumentTypeEnum;
import org.fourgeeks.gha.domain.enu.GenderTypeEnum;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gar.Bpu;
import org.fourgeeks.gha.domain.mix.Bpi;
import org.fourgeeks.gha.domain.mix.Citizen;
import org.fourgeeks.gha.domain.mix.Institution;
import org.fourgeeks.gha.domain.mix.LegalEntity;
import org.fourgeeks.gha.ejb.gar.BpuServiceRemote;
import org.fourgeeks.gha.ejb.mix.BpiServiceRemote;
import org.fourgeeks.gha.ejb.mix.CitizenServiceRemote;
import org.fourgeeks.gha.ejb.mix.InstitutionServiceRemote;
import org.fourgeeks.gha.ejb.mix.LegalEntityServiceRemote;

/**
 * @author alacret
 * 
 */
public class BpuHelper {

	private Bpu bpu;
	private Citizen citizen;
	private LegalEntity legalEntity, legalEntity2;
	private Institution institution;
	private Bpi bpi;
	private final LegalEntityServiceRemote legalEntityServiceRemote;
	private final CitizenServiceRemote citizenServiceRemote;
	private final InstitutionServiceRemote institutionServiceRemote;
	private final BpuServiceRemote bpuServiceRemote;
	private final BpiServiceRemote bpiServiceRemote;

	/**
	 * @param legalEntityServiceRemote
	 * @param bpiServiceRemote
	 * @param bpuServiceRemote
	 * @param institutionServiceRemote
	 * @param citizenServiceRemote
	 */
	public BpuHelper(final LegalEntityServiceRemote legalEntityServiceRemote,
			final CitizenServiceRemote citizenServiceRemote,
			final InstitutionServiceRemote institutionServiceRemote,
			final BpuServiceRemote bpuServiceRemote,
			final BpiServiceRemote bpiServiceRemote) {
		this.legalEntityServiceRemote = legalEntityServiceRemote;
		this.citizenServiceRemote = citizenServiceRemote;
		this.institutionServiceRemote = institutionServiceRemote;
		this.bpuServiceRemote = bpuServiceRemote;
		this.bpiServiceRemote = bpiServiceRemote;
	}

	/**
	 * @return a newly create bpu
	 */
	public Bpu createBpu() {
		try {
			legalEntity = legalEntityServiceRemote.save(new LegalEntity());
			legalEntity2 = legalEntityServiceRemote.save(new LegalEntity());
		} catch (final GHAEJBException e) {
			System.out
					.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
			removeBpu();
			Assert.fail("failing creating the legalentity");
		}

		try {
			final Citizen localCitizen = new Citizen();
			localCitizen.setLegalEntity(legalEntity);
			localCitizen.setIdNumber("id-number-legal-entity" + Math.random());
			localCitizen.setIdType(DocumentTypeEnum.LOCAL);
			localCitizen.setGender(GenderTypeEnum.FEMALE);
			citizen = citizenServiceRemote.save(localCitizen);
		} catch (final GHAEJBException e) {
			removeBpu();
			Assert.fail("failing creating the bpu: " + e.getMessage());
		}

		final Institution localInstitution = new Institution();
		localInstitution.setName("Institution name test");
		localInstitution.setLegalEntity(legalEntity2);
		try {
			institution = institutionServiceRemote.save(localInstitution);
		} catch (final GHAEJBException e) {
			removeBpu();
			Assert.fail("failing creating the intitution");
		}

		try {
			final Bpi localBpi = new Bpi();
			localBpi.setInstitution(institution);
			bpi = bpiServiceRemote.save(localBpi);
		} catch (final GHAEJBException e1) {
			removeBpu();
			Assert.fail("failing creating the bpi");
		}

		try {
			final Bpu localBpu = new Bpu();
			localBpu.setCitizen(citizen);
			localBpu.setBpi(bpi);
			bpu = bpuServiceRemote.save(localBpu);
		} catch (final GHAEJBException e) {
			removeBpu();
			Assert.fail("failing creating the bpu: " + e.getMessage());
		}
		return bpu;
	}

	/**
	 * 
	 */
	public void removeBpu() {
		try {
			bpuServiceRemote.delete(bpu.getId());
		} catch (final GHAEJBException e) {
		}

		try {
			final List<Citizen> citizens = new ArrayList<Citizen>();
			citizens.add(citizen);
			citizenServiceRemote.delete(citizens);
		} catch (final GHAEJBException e) {
		}

		try {
			bpiServiceRemote.delete(bpi.getId());
		} catch (final GHAEJBException e) {
		}

		try {
			institutionServiceRemote.delete(institution.getId());
		} catch (final GHAEJBException e) {
		}

		try {
			legalEntityServiceRemote.delete(legalEntity.getId());
			legalEntityServiceRemote.delete(legalEntity2.getId());
		} catch (final GHAEJBException e) {
		}

	}

}
