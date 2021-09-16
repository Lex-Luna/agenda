package com.uisrael.agenda.util;

import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import com.uisrael.agenda.modelo.entidad.Cliente;
import com.uisrael.agenda.modelo.entidad.Cuenta;
import com.uisrael.agenda.modelo.entidad.Prioridad;
import com.uisrael.agenda.modelo.entidad.Tarea;
import com.uisrael.agenda.modelo.entidad.TipoTarea;

public class HibernateUtil {
	private static SessionFactory sessionFactory;

	public static Session beginTransaction() {
		Session hibernateSession = getSessionFactory().openSession();
		hibernateSession.beginTransaction();
		return hibernateSession;
	}

	public static void commitTransaction(Session s) {
		s.getTransaction().commit();
	}

	public static void rollbackTransaction(Session s) {
		s.getTransaction().rollback();
	}

	public static void closeSession(Session s) {
		s.close();
	}

	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			try {
				Configuration configuration = new Configuration();

				Properties settings = new Properties();
				settings.put(Environment.DRIVER, "org.postgresql.Driver");
				settings.put(Environment.URL, "jdbc:postgresql://localhost:5432/agendadb");
				settings.put(Environment.USER, "postgres");
				settings.put(Environment.PASS, "root");
				settings.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");

				settings.put(Environment.SHOW_SQL, "true");
				settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
				settings.put(Environment.HBM2DDL_AUTO, "update");

				configuration.setProperties(settings);

				configuration.addAnnotatedClass(Cliente.class);
				configuration.addAnnotatedClass(Cuenta.class);
				configuration.addAnnotatedClass(Tarea.class);
				configuration.addAnnotatedClass(Prioridad.class);
				configuration.addAnnotatedClass(TipoTarea.class);

				ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
						.applySettings(configuration.getProperties()).build();

				sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return sessionFactory;
	}
}