package org.fourgeeks.gha.ejb.mix;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;

import junit.framework.Assert;

import org.fourgeeks.gha.domain.enu.DocumentTypeEnum;
import org.fourgeeks.gha.domain.enu.GenderTypeEnum;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.mix.Citizen;
import org.fourgeeks.gha.domain.mix.LegalEntity;
import org.fourgeeks.gha.ejb.GHAArquillianBaseServiceTest;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author vivi.torresg
 * 
 */
@RunWith(Arquillian.class)
public class CitizenServiceTest extends GHAArquillianBaseServiceTest {

	@EJB(lookup = "java:global/test/CitizenService!"
			+ "org.fourgeeks.gha.ejb.mix.CitizenServiceRemote")
	private CitizenServiceRemote serviceRemote;
	private LegalEntity legalEntity;
	private Citizen citizen;

	private void deleteTest(Citizen entity) {
		final int resultExpected = 0;
		try {
			List<Citizen> deleteList = new ArrayList<Citizen>();
			deleteList.add(citizen);
			serviceRemote.delete(deleteList);
			final List<Citizen> result = serviceRemote.getAll();
			Assert.assertEquals(resultExpected, result.size());
		} catch (final GHAEJBException e) {
			e.printStackTrace();
		}
	}

	private void findByIdTest(Citizen entity) {
		try {
			final long id = entity.getId();
			final Citizen result = serviceRemote.find(id);

			Assert.assertNotNull(result);

		} catch (final GHAEJBException e) {
			e.printStackTrace();
		}
	}

	private void findTest(Citizen entity) {
		final int expectedResult = 1;
		try {
			final List<Citizen> result = serviceRemote.find(entity);

			Assert.assertEquals(expectedResult, result.size());

		} catch (final GHAEJBException e) {
			e.printStackTrace();
		}
	}

	private void getAll() {
		final int expectedResult = 1;
		try {
			final List<Citizen> result = serviceRemote.getAll();

			Assert.assertEquals(expectedResult, result.size());

		} catch (final GHAEJBException e) {
			e.printStackTrace();
		}
	}

	private Citizen saveTest() {
		try {
			Citizen result = serviceRemote.save(citizen);
			Assert.assertNotNull(result);

			return result;
		} catch (GHAEJBException e) {
			e.printStackTrace();
			return null;
		}
	}

	/** */
	@Before
	public void set() {
		legalEntity = new LegalEntity("V-123456789-J");

		citizen = new Citizen();
		citizen.setLegalEntity(legalEntity);
		citizen.setGender(GenderTypeEnum.MALE);
		citizen.setIdType(DocumentTypeEnum.LOCAL);
		citizen.setIdNumber("123456789");
		citizen.setFirstName("firstName");
		citizen.setSecondName("secondName");
		citizen.setFirstLastName("firstLastname");
		citizen.setSecondLastName("SecondLastname");
		citizen.setBirthDate(new Date(100, 1, 1));
		citizen.setNationality("Venezolano");
		citizen.setPrimaryEmail("primary@email.com");
		citizen.setAlternativeEmail("alternative@email.com");
	}

	/** */
	@Test
	public void test() {
		final String sep = "\n---------------------------------------\n";
		System.out.println("TESTING CITIZEN SERVICE\n");
		System.out.println(sep + "saveTest" + sep);
		Citizen entity = this.citizen = saveTest();
		System.out.println(sep + "updateTest" + sep);
		entity = updateTest(entity);
		System.out.println(sep + "findByIdTest" + sep);
		findByIdTest(entity);
		System.out.println(sep + "findTest" + sep);
		findTest(entity);
		System.out.println(sep + "getAll" + sep);
		getAll();
		System.out.println(sep + "deleteTest" + sep);
		deleteTest(entity);
	}

	/** */
	@After
	public void unset() {

	}

	private Citizen updateTest(Citizen entity) {
		try {
			final String newFirstName = "newFirstName";
			entity.setFirstName(newFirstName);

			final Citizen result = serviceRemote.update(entity);

			Assert.assertEquals(newFirstName, result.getFirstName());
			return result;

		} catch (final GHAEJBException e) {
			e.printStackTrace();
		}

		return null;
	}
}
