package org.fourgeeks.gha.ejb;

import java.util.List;

import javax.ejb.EJB;

import junit.framework.Assert;

import org.fourgeeks.gha.domain.ActivityType;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author naramirez
 */
@RunWith(Arquillian.class)
public class ActivityTypeServiceTest extends GHAArquillianBaseServiceTest {

	@EJB(lookup = "java:global/test/ActivityTypeService")
	private ActivityTypeServiceRemote service;

	private void getAllTypesTest() {
		final int expectedResult = 6;
		try {
			List<ActivityType> result = service.getAllTypes();
			Assert.assertEquals(expectedResult, result.size());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void getSubTypesTest() {
		final int expectedResult = 11;
		try {
			ActivityType type = new ActivityType();
			type.setId(1);

			List<ActivityType> result = service.getSubTypes(type);
			Assert.assertEquals(expectedResult, result.size());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** */
	@Before
	public void set() {
		try {
			System.out.println("CREATING TEST DATA: ACTIVITY TYPE AND SUBTYPE");

			final String activityTypeNames[] = { "Mantenimiento",
					"Asistencial", "Logística", "Operaciones",
					"Administrativa", "Del Sistema" };

			for (String typeName : activityTypeNames) {
				ActivityType type = new ActivityType();
				type.setDescription(typeName);
				service.save(type);
			}

			final String[] subTypesName = new String[] { "Medición",
					"Limpieza", "Calibración", "Desarme", "Armado",
					"Instalación", "Desinstalación", "Cambio de Repuesto",
					"Cambio de Consumibles", "Aceptación Mantenimiento",
					"Traslado" };

			for (String subTypeName : subTypesName) {
				final ActivityType subType = new ActivityType();
				subType.setDescription(subTypeName);
				subType.setParentActivityTypeId(1);
				service.save(subType);
			}

		} catch (final Exception e1) {
			System.out.println("Error Creating ActivityType test data: " + e1);
		}
	}

	/** */
	@Test
	public void test() {
		final String sep = "\n---------------------------------------\n";

		System.out.println("TESTING ACTIVITY TYPE AND SUBTYPE SERVICE\n");

		System.out.println(sep + "getAllTypesTest" + sep);
		getAllTypesTest();
		System.out.println(sep + "getSubTypesTest" + sep);
		getSubTypesTest();

	}

	/** */
	@After
	public void unset() {
		try {
			System.out.println("REMOVING TEST DATA: ACTIVITY TYPE AND SUBTYPE");

			final List<ActivityType> resultList = service.getAll();
			service.delete(resultList);

		} catch (final Exception e1) {
			System.out.println("Error Removing ActivityType test data: " + e1);
		}
	}
}
