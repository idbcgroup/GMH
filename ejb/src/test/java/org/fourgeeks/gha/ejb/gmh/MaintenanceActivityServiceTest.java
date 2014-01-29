package org.fourgeeks.gha.ejb.gmh;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.EJB;

import junit.framework.Assert;

import org.fourgeeks.gha.domain.Activity;
import org.fourgeeks.gha.domain.enu.ActivityCategoryEnum;
import org.fourgeeks.gha.domain.enu.ActivityState;
import org.fourgeeks.gha.domain.enu.ActivitySubCategoryEnum;
import org.fourgeeks.gha.domain.enu.CurrencyTypeEnum;
import org.fourgeeks.gha.domain.enu.TimePeriodEnum;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.MaintenanceActivity;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author vivi.torresg
 * 
 */
@RunWith(Arquillian.class)
public class MaintenanceActivityServiceTest {
	/**
	 * @return the deployment descriptor
	 */
	@Deployment
	public static Archive<?> createDeployment() {
		return ShrinkWrap
				.create(WebArchive.class, "test.war")
				.addClass(GHAEJBException.class)
				.addClass(MaintenanceActivityServiceRemote.class)
				.addClass(Activity.class)
				.addAsResource("test-persistence.xml",
						"META-INF/persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@EJB(name = "java:global/test/MaintenanceActivityService")
	MaintenanceActivityServiceRemote service;

	private Activity activity;
	private MaintenanceActivity maintenanceActivity;

	/** */
	@Before
	public void set() {
		activity = new Activity();
		activity.setName("unit test activity");
		activity.setDescription("activity for unit test");
		activity.setState(ActivityState.CREATED);
		activity.setCategory(ActivityCategoryEnum.MAINTENANCE);
		activity.setSubCategory(ActivitySubCategoryEnum.CALIBRATION);
		activity.setEstimatedDuration(new BigDecimal(1));
		activity.setEstimatedDurationPoT(TimePeriodEnum.DAYS);
		activity.setEstimatedCost(new BigDecimal(1));
		activity.setEstimatedCostCurrency(CurrencyTypeEnum.BS);
		activity.setIsSubProtocol(true);
	}

	/** */
	@After
	public void unset() {

	}

	/** */
	@Test
	public void test() {
		System.out.println("TESTING MAINTENANCE ACTIVITY SERVICE\n\n\n");

		System.out.println("getAllTest MaintenanceActivity");
		getAllTest();
		System.out.println("getAllTest2 MaintenanceActivity");
		getAllTest2();
		System.out.println("findByMaintenanceActivityTest MaintenanceActivity");
		findByMaintenanceActivityTest();
		System.out.println("findByIdTest MaintenanceActivity");
		findByIdTest();
		System.out.println("saveTest MaintenanceActivity");
		saveTest();
		System.out.println("updateTest MaintenanceActivity");
		updateTest();
		System.out.println("deleteTest MaintenanceActivity");
		deleteTest();

	}

	private void saveTest() {
		maintenanceActivity = new MaintenanceActivity();
		maintenanceActivity.setActivity(activity);

		try {
			maintenanceActivity = service.save(maintenanceActivity);
		} catch (GHAEJBException e) {
			e.printStackTrace();
		}
		Assert.assertNotNull(maintenanceActivity);
	}

	private void deleteTest() {
		try {
			service.delete(maintenanceActivity.getId());
			Assert.assertEquals(11, service.getAll().size());
		} catch (GHAEJBException e) {
			e.printStackTrace();
		}
	}

	private void findByMaintenanceActivityTest() {
		try {
			Activity activity = new Activity();
			activity.setName("Desconectar");
			activity.setDescription("Desconecte el equipo de la corriente eléctrica");
			activity.setState(ActivityState.CREATED);
			activity.setCategory(ActivityCategoryEnum.MAINTENANCE);
			activity.setSubCategory(ActivitySubCategoryEnum.CALIBRATION);
			activity.setEstimatedDuration(new BigDecimal(1));
			activity.setEstimatedDurationPoT(TimePeriodEnum.DAYS);
			activity.setEstimatedCost(new BigDecimal(1300.42));
			activity.setEstimatedCostCurrency(CurrencyTypeEnum.BS);
			activity.setIsSubProtocol(false);

			MaintenanceActivity maintenanceActivity = new MaintenanceActivity();
			maintenanceActivity.setActivity(activity);

			List<MaintenanceActivity> result = service
					.find(maintenanceActivity);

			Assert.assertEquals(1, result.size());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void findByIdTest() {
		try {
			MaintenanceActivity result = service.find(1);
			Assert.assertNotNull(result);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void getAllTest() {
		try {
			List<MaintenanceActivity> result = service.getAll();
			Assert.assertEquals(11, result.size());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void getAllTest2() {
		try {
			List<MaintenanceActivity> result = service.getAll(1, 4);
			Assert.assertEquals(4, result.size());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void updateTest() {
		try {

			MaintenanceActivity result = service.update(maintenanceActivity);
			Activity activityResult = result.getActivity();

			Assert.assertEquals(activityResult.getName(), activity.getName());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @throws NotSupportedException
	 * @throws SystemException
	 * @throws SecurityException
	 * @throws IllegalStateException
	 * @throws RollbackException
	 * @throws HeuristicMixedException
	 * @throws HeuristicRollbackException
	 * @throws GHAEJBException
	 */
	// public void test() throws NotSupportedException, SystemException,
	// SecurityException, IllegalStateException, RollbackException,
	// HeuristicMixedException, HeuristicRollbackException,
	// GHAEJBException {
	// Assert.assertNotNull(em);
	// Assert.assertNotNull(service);
	//
	// em.joinTransaction();
	//
	// MaintenanceActivity entity = new MaintenanceActivity();
	// Activity activity = new Activity();
	// activity.setName("MaintenanceActivity test name");
	// activity.setDescription("MaintenanceActivity test description");
	// entity.setActivity(activity);
	//
	// entity = service.save(entity);
	//
	// Assert.assertNotNull(service.find(entity.getId()));
	// System.out.println("BEFORE " + entity.getId() + " "
	// + activity.getDescription() + "\nAFTER "
	// + service.find(entity.getId()).getId() + " "
	// + service.find(entity.getId()).getActivity().getDescription());
	// // Assert.assertEquals(entity, service.find(entity.getId()));
	//
	// Assert.assertTrue(service.findByServiceResource(super
	// .getMaintenanceActivityServiceResource(em, entity,
	// super.getServiceResource(em)).getServiceResource()) != null
	// && service.findByServiceResource(
	// super.getMaintenanceActivityServiceResource(em, entity,
	// super.getServiceResource(em))
	// .getServiceResource()).size() >= 1);
	//
	// em.remove(super.getMaintenanceActivityServiceResource(em, entity,
	// super.getServiceResource(em)));
	// em.flush();
	// Assert.assertTrue(service.findByServiceResource(super
	// .getMaintenanceActivityServiceResource(em, entity,
	// super.getServiceResource(em)).getServiceResource()) == null
	// || service.findByServiceResource(
	// super.getMaintenanceActivityServiceResource(em, entity,
	// super.getServiceResource(em))
	// .getServiceResource()).size() == 0);
	//
	// Assert.assertTrue(service.getAll() != null
	// && service.getAll().size() >= 1);
	// Assert.assertTrue(service.getAll(0, 10) != null
	// && service.getAll(0, 10).size() >= 1);
	// activity = entity.getActivity();
	// activity.setDescription("MaintenanceActivity test description updated");
	// entity = service.update(entity);
	// Assert.assertEquals("MaintenanceActivity test description updated",
	// service.find(entity.getId()).getActivity().getDescription());
	// Assert.assertFalse("MaintenanceActivity test description" == service
	// .find(entity.getId()).getActivity().getDescription());
	// long id = entity.getId();
	// service.delete(entity.getId());
	// Assert.assertNull(service.find(id));
	//
	// }
}
