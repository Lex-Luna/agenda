package com.uisrael.agenda.modelo.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.uisrael.agenda.modelo.dao.PrioridadDao;
import com.uisrael.agenda.modelo.entidad.Cuenta;
import com.uisrael.agenda.modelo.entidad.Prioridad;
import com.uisrael.agenda.util.HibernateUtil;

public class PrioridadDaoImpl implements PrioridadDao {

	@Override
	public void guardar(Prioridad prioridad) {
		Session session = null;
		try {
			session = HibernateUtil.beginTransaction();
			session.persist(prioridad);
			System.out.println(prioridad.getIdPrioridad());
			HibernateUtil.commitTransaction(session);
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	@Override
	public void actualizar(Prioridad prioridad) {
		Session session = null;
		try {
			session = HibernateUtil.beginTransaction();
			session.merge(prioridad);
			HibernateUtil.commitTransaction(session);
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	@Override
	public boolean eliminar(Prioridad prioridad) {
		Boolean eliminacion = false ;
		Session session = null;
		try {
			session = HibernateUtil.beginTransaction();
			session.delete(prioridad);
			HibernateUtil.commitTransaction(session);
			eliminacion = true ;
		} catch (Exception e) {
			eliminacion = false ;
			System.out.println("Error: " + e.getMessage());
		} finally {
			HibernateUtil.closeSession(session);
		}
		return eliminacion;
	}

	@Override
	public Prioridad buscarPorId(int id) {
		Prioridad prioridad = null;
		Session session = null;
		try {
			session = HibernateUtil.beginTransaction();
			prioridad = session.find(Prioridad.class, id);
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			HibernateUtil.closeSession(session);
		}
		return prioridad;
	}

	@Override
	public List<Prioridad> listar() {
		List<Prioridad> prioridades = new ArrayList<Prioridad>();
		Session session = null;
		try {
			session = HibernateUtil.beginTransaction();
			prioridades = session.createQuery("from Prioridad", Prioridad.class).getResultList();
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			HibernateUtil.closeSession(session);
		}
		return prioridades;
	}

	@Override
	public List<Prioridad> listarPorCuenta(Cuenta cuenta) {
		List<Prioridad> prioridades = new ArrayList<Prioridad>();
		Session session = null;
		try {
			session = HibernateUtil.beginTransaction();
			prioridades = session
					.createQuery("from Prioridad where cuenta = '" + cuenta.getIdCuenta() + "'", Prioridad.class)
					.getResultList();
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			HibernateUtil.closeSession(session);
		}
		return prioridades;
	}

}
