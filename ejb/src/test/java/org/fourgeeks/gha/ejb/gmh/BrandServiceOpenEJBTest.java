package org.fourgeeks.gha.ejb.gmh;

import java.util.List;
import java.util.Properties;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;

import junit.framework.TestCase;

import org.fourgeeks.gha.domain.gmh.Brand;

public class BrandServiceOpenEJBTest extends TestCase {
	//
	// @EJB
	// private BrandService brandService;
	//
	// @Resource
	// private UserTransaction userTransaction;
	//
	// @PersistenceContext
	// private EntityManager entityManager;
	//
	// public void setUp() throws Exception {
	// Properties p = new Properties();
	//
	// p.put("gha", "new://Resource?type=DataSource");
	// p.put("gha.JdbcDriver", "org.postgresql.Driver");
	// p.put("gha.JdbcUrl", "jdbc:postgresql://localhost:5432/gha");
	// //p.put("gha.JtaManaged", "false");
	// p.put("gha.username", "postgres");
	// p.put("gha.password", "postgres");
	//
	// EJBContainer container = EJBContainer.createEJBContainer(p);
	// final Context context = container.getContext();
	// /*
	// * final Properties p = new Properties(); p.put("gha",
	// * "new://Resource?type=DataSource" ); p.put("gha.JdbcDriver" ,
	// * "org.hsqldb.jdbcDriver" ); p.put("gha.JdbcUrl",
	// * "jdbc:hsqldb:mem:moviedb" );
	// *
	// * EJBContainer container = EJBContainer .createEJBContainer (p); final
	// * Context context = container.getContext ();
	// */
	// }

	public void test() throws Exception {

		final Properties p = new Properties();
		p.put("gha", "new://Resource?type=DataSource");
		p.put("gha.JdbcDriver", "org.postgresql.Driver");
		p.put("gha.JdbcUrl", "jdbc:postgresql://localhost:5432/gha");
		p.put("gha.username", "postgres");
		p.put("gha.password", "postgres");

		final Context context = EJBContainer.createEJBContainer(p).getContext();

		BrandServiceRemote brands = (BrandServiceRemote) context
				.lookup("java:global/ejb/gmh.BrandService");

		List<Brand> list = brands.getAll();
		assertEquals("List.size()", 5, list.size());

		// BrandService brandService = (BrandService) context
		// .lookup("java:global/ejb/gmh.BrandService");
		//
		// List<Brand> list2 = brandService.getAll();
		// assertEquals("List2.size()", 5, list2.size());

		// assertNotNull(entityManager);
		// assertNotNull(brandService);
		//
		// /*
		// * userTransaction.begin();
		// *
		// * try { entityManager.persist(new Movie("Quentin Tarantino",
		// * "Reservoir Dogs", 1992)); entityManager.persist(new
		// * Movie("Joel Coen", "Fargo", 1996)); entityManager.persist(new
		// * Movie("Joel Coen", "The Big Lebowski", 1998));
		// *
		// * List<Movie> list = movies.getMovies(); assertEquals("List.size()",
		// 3,
		// * list.size());
		// *
		// * for (Movie movie : list) { movies.deleteMovie(movie); }
		// *
		// * assertEquals("Movies.getMovies()", 0, movies.getMovies().size());
		// *
		// * } finally { userTransaction.commit(); }
		// */

	}
}
