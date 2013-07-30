package org.fourgeeks.gha.ejb.gmh;

import java.util.Properties;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;

/**
 * @author vivi.torresg
 * 
 */
public class ContextDeployment {

	private Context context;

	public ContextDeployment() {
		final Properties p = new Properties();
		p.put(Context.INITIAL_CONTEXT_FACTORY,
				"org.apache.openejb.client.LocalInitialContextFactory");
		p.put("gha", "new://Resource?type=DataSource");
		p.put("gha.hibernate.hbm2ddl.auto", "create");
		p.put("gha.JdbcDriver", "org.postgresql.Driver");
		p.put("gha.JdbcUrl", "jdbc:postgresql://localhost:5432/gha");
		p.put("gha.username", "postgres");
		p.put("gha.password", "postgres");
		context = EJBContainer.createEJBContainer(p).getContext();
	}

	public Context getContext() {
		return context;
	}
}
