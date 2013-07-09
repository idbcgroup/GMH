package org.fourgeeks.gha.ejb.tests;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.fourgeeks.gha.ejb.gmh.BrandServiceRemote;

class EiaTypeTest {
	@PersistenceContext
	EntityManager em;

	@EJB(name = "gmh.BrandSErvice")
	BrandServiceRemote bServiceRemote;

	// public void test() {
	//
	// String query = "SELECT t from EiaType t WHERE id = 1 ";
	// try {
	// em.createQuery(query).getSingleResult();
	// } catch (NoResultException e) {
	// try {
	// EiaType eiaType = new EiaType(bServiceRemote.find(1),
	// mServiceRemote.find(1), "Impresora Tinta",
	// EiaMobilityEnum.FIXED, EiaTypeEnum.EQUIPMENT,
	// EiaSubTypeEnum.IT_SYSTEM, "Stylus", "90001");
	// eiaType = eiaTypeServ.save(eiaType);
	//
	// EiaType eiaType2 = new EiaType(bServiceRemote.find(2),
	// mServiceRemote.find(2), "Impresora Laser",
	// EiaMobilityEnum.FIXED, EiaTypeEnum.EQUIPMENT,
	// EiaSubTypeEnum.IT_SYSTEM, "Deskjet", "90002");
	// eiaType2 = eiaTypeServ.save(eiaType2);
	//
	// Bpi bpi = new Bpi();
	// em.persist(bpi);
	// em.flush();
	//
	// BuildingLocation buildingLocation = new BuildingLocation(bpi,
	// "Building001", LocationLevelEnum.AREA_HALL, 2);
	// em.persist(buildingLocation);
	// em.flush();
	//
	// Facility facility = new Facility();
	// facility.setBuildingLocation(buildingLocation);
	// em.persist(facility);
	// em.flush();
	//
	// Eia eia = new Eia(facility, eiaType,
	// WarrantySinceEnum.ACCEPTATION, TimePeriodEnum.DAYS,
	// EiaStateEnum.TEST, WarrantyStateEnum.VALID);
	// eia.setCode("Stylus-001");
	// eia.setSerialNumber("001");
	// eia = eiaServ.save(eia);
	//
	// System.out.println("Testing EiaTypeComponent");
	// EiaType eiaType3 = new EiaType(brand, manufacturer,
	// "Cartucho Tricolor", EiaMobilityEnum.FIXED,
	// EiaTypeEnum.PART, EiaSubTypeEnum.IT_SYSTEM, "EP60",
	// "90003");
	// eiaType3 = eiaTypeServ.save(eiaType3);
	//
	// EiaType eiaType4 = new EiaType(brand2, manufacturer,
	// "Toner Laser", EiaMobilityEnum.FIXED, EiaTypeEnum.PART,
	// EiaSubTypeEnum.IT_SYSTEM, "HP60", "90004");
	// eiaType4 = eiaTypeServ.save(eiaType4);
	//
	// EiaType eiaType5 = new EiaType(brand, manufacturer,
	// "Cartucho Negro", EiaMobilityEnum.FIXED,
	// EiaTypeEnum.PART, EiaSubTypeEnum.IT_SYSTEM, "EPN60",
	// "90005");
	// eiaType5 = eiaTypeServ.save(eiaType5);
	//
	// System.out.println("TESTING: EiaType find service");
	// eiaType = eiaTypeServ.find(1L);
	// eiaType2 = eiaTypeServ.find(2L);
	// eiaType3 = eiaTypeServ.find(3L);
	// eiaType4 = eiaTypeServ.find(4L);
	// eiaType5 = eiaTypeServ.find(5L);
	//
	// EiaTypeComponent eiaTypeComponent = new EiaTypeComponent(
	// eiaType, eiaType3, true, true);
	// EiaTypeComponent eiaTypeComponent2 = new EiaTypeComponent(
	// eiaType2, eiaType4, true, true);
	// EiaTypeComponent eiaTypeComponent3 = new EiaTypeComponent(
	// eiaType, eiaType5, true, true);
	//
	// etcService.save(eiaTypeComponent);
	// etcService.save(eiaTypeComponent2);
	// etcService.save(eiaTypeComponent3);
	// System.out.println("EiaTypeComponent Guardados");
	//
	// System.out.println("Testing EiaTypeComponents getAll/find");
	// List<EiaTypeComponent> eiaTypeComponents = etcService.getAll();
	// for (EiaTypeComponent next : eiaTypeComponents) {
	// System.out.println("ID = " + Long.toString(next.getId()));
	// System.out.println("Parent = "
	// + next.getParentEiaType().getName());
	// System.out.println("EiaType = "
	// + next.getEiaType().getName());
	// }
	//
	// System.out.println("Testing EiaTypeComponents findByEiaTypeId");
	// eiaTypeComponents = etcService.findByEiaTypeId(1L);
	// for (EiaTypeComponent next : eiaTypeComponents) {
	// System.out.println("ID = " + Long.toString(next.getId()));
	// System.out.println("Parent = "
	// + next.getParentEiaType().getName());
	// System.out.println("EiaType = "
	// + next.getEiaType().getName());
	// }
	//
	// System.out.println("TESTING: EIA components services");
	//
	// Eia eia2 = new Eia(facility, eiaType3,
	// WarrantySinceEnum.ACCEPTATION, TimePeriodEnum.YEARS,
	// EiaStateEnum.TEST, WarrantyStateEnum.VALID);
	//
	// Eia eia3 = new Eia(facility, eiaType5,
	// WarrantySinceEnum.ACCEPTATION, TimePeriodEnum.YEARS,
	// EiaStateEnum.TEST, WarrantyStateEnum.VALID);
	//
	// Eia eia4 = new Eia(facility, eiaType4,
	// WarrantySinceEnum.ACCEPTATION, TimePeriodEnum.DAYS,
	// EiaStateEnum.TEST, WarrantyStateEnum.VALID);
	//
	// Eia eia5 = new Eia(facility, eiaType2,
	// WarrantySinceEnum.ACCEPTATION, TimePeriodEnum.DAYS,
	// EiaStateEnum.TEST, WarrantyStateEnum.VALID);
	//
	// System.out.println("Testing part 1: eia services");
	// eia2 = eiaServ.save(eia2);
	// eia3 = eiaServ.save(eia3);
	// eia4 = eiaServ.save(eia4);
	// eia5 = eiaServ.save(eia5);
	//
	// eia = eiaServ.find(1L); // parent Impresora Epson
	// eia2 = eiaServ.find(2L); // cartucho tricolor
	// eia3 = eiaServ.find(3L); // cartucho negro
	// eia4 = eiaServ.find(4L); // toner
	// eia5 = eiaServ.find(5L); // parent Impresora Hp
	//
	// EiaComponent eiaComponent = new EiaComponent(eia, eia2,
	// "Cartucho Tricolor Impresora Epson Stylus-001");
	// EiaComponent eiaComponent2 = new EiaComponent(eia, eia3,
	// "Cartucho Negro Impresora Epson Stylus-001");
	// EiaComponent eiaComponent3 = new EiaComponent(eia5, eia4,
	// "Toner de Tinta para la Impresora Laser HP60");
	//
	// System.out.println("Testing part 2: save eiacomponent");
	// ecService.save(eiaComponent);
	// ecService.save(eiaComponent2);
	// ecService.save(eiaComponent3);
	//
	// System.out.println("Testing part 3: eiacomponent listing");
	// List<EiaComponent> eiaComponents = ecService.getAll();
	// for (EiaComponent next : eiaComponents) {
	// System.out.println(next.getComponentObs());
	// }
	//
	// System.out.println("Testing part 4: eiacomponent find by eia");
	// List<EiaComponent> eiaComponents2 = ecService.findByEiaId(1L);
	// for (EiaComponent next : eiaComponents2) {
	// System.out.println(next.getComponentObs());
	// }
	//
	// System.out.println("TESTING: update eiacomponent");
	// EiaComponent eiaCompUpd = eiaComponents2.get(0);
	// eiaCompUpd
	// .setComponentObs("Update: Cartucho Tricolor Impresora Epson Stylus-001");
	// ecService.update(eiaCompUpd);
	//
	// } catch (EJBException e1) {
	// System.out.println("Failed to initialize test data");
	// e1.printStackTrace();
	// }
	// }
	//
	// }
}
