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
public class DatabaseConstraints {

	private final static Logger logger = Logger
			.getLogger(DatabaseConstraints.class.getName());

	@Resource(mappedName = "java:/jdbc/gha")
	DataSource dataSource;

	/**
	 * 
	 */
	@PostConstruct
	public void inicializar() {
		createIndexs();
		createChecks();
	}

	private void createIndexs() {

		logger.info("Creating indexes...");
		Connection con = null;
		try {
			con = dataSource.getConnection();
			PreparedStatement ps;
			try {
				ps = con.prepareStatement("CREATE INDEX username_index ON ssouser (username)");
				ps.execute();
				logger.info("username_index created...");
			} catch (SQLException e) {
				if (e.getSQLState().equals("42P07"))
					logger.info("username_index already created... skipping");
				else
					logger.info("ERROR: unable to create username_index : "
							+ e.getMessage());
			}
			try {
				ps = con.prepareStatement("CREATE INDEX eiaType_index ON eiatype (type)");
				ps.execute();
				logger.info("eiaType_index created...");
			} catch (SQLException e) {
				if (e.getSQLState().equals("42P07"))
					logger.info("eiaType_index  already created... skipping");
				else
					logger.info("ERROR: unable to create eiaType_index :"
							+ e.getMessage());
			}

			try {
				ps = con.prepareStatement("CREATE INDEX eia_state_index ON eia (state)");
				ps.execute();
				logger.info("eia_state_index created...");
			} catch (SQLException e) {
				if (e.getSQLState().equals("42P07"))
					logger.info("eia_state_index  already created... skipping");
				else
					logger.info("ERROR: unable to create eia_state_index :"
							+ e.getMessage());
			}

		} catch (SQLException e) {
			logger.log(Level.INFO,
					"Error getting the connection to create the indexes", e);
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (SQLException e1) {
				logger.log(Level.INFO,
						"ERROR: unable to close manual datasource connection",
						e1);
			}
		}

		logger.info("...done creating indexes!");
	}

	private void createChecks() {
		logger.info("Creating checks...");
		Connection con = null;
		try {
			con = dataSource.getConnection();
			PreparedStatement ps;
			try {
				ps = con.prepareStatement("ALTER TABLE eiatypecomponent ADD CONSTRAINT myself_component_check CHECK (eiaTypeFk != parentEiaTypeFk)");
				ps.execute();
				logger.info("myself_component_check created...");
			} catch (SQLException e) {
				if (e.getSQLState().equals("42P07"))
					logger.info("myself_component_check already created... skipping");
				else
					logger.info("ERROR: unable to create myself_component_check : "
							+ e.getMessage());
			}

		} catch (SQLException e) {
			logger.log(Level.INFO,
					"Error getting the connection to create the checks", e);
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (SQLException e1) {
				logger.log(Level.INFO,
						"ERROR: unable to close manual datasource connection",
						e1);
			}
		}

		logger.info("...done creating checks!");
	}
}