package org.fourgeeks.gha.ejb.gmh;

import static org.junit.Assert.*;

import org.junit.Test;
import junit.framework.TestCase;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import java.util.List;
import java.util.Properties;

public class BrandServiceOpenEJBTest extends TestCase {

	@EJB
	private BrandService brandService;

	@Resource
	private UserTransaction userTransaction;

	@PersistenceContext
	private EntityManager entityManager;

	public void setUp() throws Exception {
		Properties p = new Properties();

		p.put("gha", "new://Resource?type=DataSource");
		p.put("gha.JdbcDriver", "org.postgresql.Driver");
		p.put("gha.JdbcUrl", "jdbc:postgresql://localhost:5432/gha");
		//p.put("gha.JtaManaged", "false");
		p.put("gha.username", "postgres");
		p.put("gha.password", "postgres");

		EJBContainer container = EJBContainer.createEJBContainer(p);
		final Context context = container.getContext();
		/*
		 * final Properties p = new Properties(); p.put("gha",
		 * "new://Resource?type=DataSource" ); p.put("gha.JdbcDriver" ,
		 * "org.hsqldb.jdbcDriver" ); p.put("gha.JdbcUrl",
		 * "jdbc:hsqldb:mem:moviedb" );
		 * 
		 * EJBContainer container = EJBContainer .createEJBContainer (p); final
		 * Context context = container.getContext ();
		 */
	}

	public void test() throws Exception {
		System.out.println("TESTING");
		assertNotNull(entityManager);
		assertNotNull(brandService);

		/*
		 * userTransaction.begin();
		 * 
		 * try { entityManager.persist(new Movie("Quentin Tarantino",
		 * "Reservoir Dogs", 1992)); entityManager.persist(new
		 * Movie("Joel Coen", "Fargo", 1996)); entityManager.persist(new
		 * Movie("Joel Coen", "The Big Lebowski", 1998));
		 * 
		 * List<Movie> list = movies.getMovies(); assertEquals("List.size()", 3,
		 * list.size());
		 * 
		 * for (Movie movie : list) { movies.deleteMovie(movie); }
		 * 
		 * assertEquals("Movies.getMovies()", 0, movies.getMovies().size());
		 * 
		 * } finally { userTransaction.commit(); }
		 */

	}
}
