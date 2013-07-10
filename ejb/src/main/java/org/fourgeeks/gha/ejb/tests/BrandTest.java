package org.fourgeeks.gha.ejb.tests;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.fourgeeks.gha.ejb.gmh.BrandServiceRemote;

class BrandTest {
	@PersistenceContext
	EntityManager em;

	@EJB(name = "gmh.BrandSErvice")
	BrandServiceRemote bServiceRemote;

	// public void test() {
	// try {
	// em.getTransaction().begin();
	// Brand brand = new Brand();
	// brand.setName("");
	// brand = bServiceRemote.save(brand);
	//
	// Brand brand2 = new Brand();
	// brand2.setName("TEST Brand 2");
	// brand2 = bServiceRemote.save(brand2);
	//
	// System.out.println("BRAND: testing find");
	// Brand search = new Brand();
	// search.setName("TEST");
	// List<Brand> findResult = bServiceRemote.find(search);
	// for (Brand br : findResult) {
	// System.out.println("brand id=" + Long.toString(br.getId())
	// + " name=" + br.getName());
	// }
	//
	// // System.out.println("BRAND: testing delete");
	// // bServiceRemote.delete(1L);
	//
	// System.out.println("BRAND: testing getAll");
	// List<Brand> getAllResult = bServiceRemote.getAll();
	// for (Brand br : getAllResult) {
	// System.out.println("manufacturer id="
	// + Long.toString(br.getId()) + " name=" + br.getName());
	// }
	//
	// em.getTransaction().rollback();
	// } catch (EJBException e) {
	// System.out.println("Test data failed for brand");
	// System.out.println(e.getCause().getMessage());
	// }
	//
	// }

}
