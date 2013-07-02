package org.fourgeeks.gha.ejb.tests;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.fourgeeks.gha.ejb.gmh.BrandServiceRemote;

class ManufacturerTest {
	@PersistenceContext
	EntityManager em;

	@EJB(name = "gmh.BrandSErvice")
	BrandServiceRemote bServiceRemote;

	// public void test() {
	// try {
	// Manufacturer manufacturer = new Manufacturer();
	// manufacturer.setName("TEST Manufacturer 1");
	// manufacturer = mServiceRemote.save(manufacturer);
	//
	// Manufacturer manufacturer2 = new Manufacturer();
	// manufacturer2.setName("TEST Manufacturer 2");
	// mServiceRemote.save(manufacturer2);
	//
	// System.out.println("MANUFACTURER: testing find");
	// Manufacturer search = new Manufacturer();
	// search.setName("TEST");
	// List<Manufacturer> findResult = mServiceRemote.find(search);
	// for (Manufacturer man : findResult) {
	// System.out
	// .println("manufacturer id="
	// + Long.toString(man.getId()) + " name="
	// + man.getName());
	// }
	//
	// // System.out.println("MANUFACTURER: testing delete");
	// // mServiceRemote.delete(1L);
	//
	// System.out.println("MANUFACTURER: testing getAll");
	// List<Manufacturer> getAllResult = mServiceRemote.getAll();
	// for (Manufacturer man : getAllResult) {
	// System.out
	// .println("manufacturer id="
	// + Long.toString(man.getId()) + " name="
	// + man.getName());
	// }
	// } catch (EJBException e) {
	// System.out.println("Test data failed for manufacturer");
	// System.out.println(e.getCause().getMessage());
	// }
	// }

}
