package org.fourgeeks.gha.ejb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.sql.DataSource;

/**
 * @author alacret
 * 
 */
@Startup
@Singleton
public class InitialData {

	private final static Logger logger = Logger.getLogger(InitialData.class
			.getName());

	@Resource(mappedName = "java:/jdbc/gha")
	DataSource dataSource;

	/**
	 * 
	 */
	@PostConstruct
	public void inicializar() {
		createIndexs();
	}

	private void createIndexs() {

		logger.info("Creating indexes...");
		Connection con = null;
		PreparedStatement ps;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement("CREATE INDEX username_index ON singlesignonuser (username)");
			ps.execute();
			ps = con.prepareStatement("CREATE INDEX eiaType_index ON eiatype (type)");
			ps.execute();
		} catch (SQLException e) {
			if (e.getSQLState().equals("42P07"))
				logger.info("username_index already created... skipping");
			else
				logger.log(Level.INFO,
						"ERROR: unable to create username_index", e);
		} finally {
			try {
				con.close();
			} catch (SQLException e1) {
				logger.log(Level.INFO,
						"ERROR: unable to close manual datasource connection",
						e1);
			}
		}
		logger.info("...done creating indexes!");
	}
}