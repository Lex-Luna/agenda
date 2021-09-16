package com.uisrael.agenda.modelo.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.uisrael.agenda.modelo.dao.TareaDao;
import com.uisrael.agenda.modelo.entidad.Cuenta;
import com.uisrael.agenda.modelo.entidad.Tarea;
import com.uisrael.agenda.util.HibernateUtil;

public class TareaDaoImpl implements TareaDao {

	@Override
	public void guardar(Tarea tarea) {
		Session session = null;
		try {
			session = HibernateUtil.beginTransaction();
			session.persist(tarea);
			System.out.println(tarea.getIdTarea());
			HibernateUtil.commitTransaction(session);
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	@Override
	public void actualizar(Tarea tarea) {
		Session session = null;
		try {
			session = HibernateUtil.beginTransaction();
			session.merge(tarea);
			HibernateUtil.commitTransaction(session);
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	@Override
	public void eliminar(Tarea tarea) {
		Session session = null;
		try {
			session = HibernateUtil.beginTransaction();
			session.delete(tarea);
			HibernateUtil.commitTransaction(session);
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	@Override
	public Tarea buscarPorId(int id) {
		Tarea tarea = null;
		Session session = null;
		try {
			session = HibernateUtil.beginTransaction();
			tarea = session.find(Tarea.class, id);
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			HibernateUtil.closeSession(session);
		}
		return tarea;
	}

	@Override
	public List<Tarea> listar() {
		List<Tarea> tareas = new ArrayList<Tarea>();
		Session session = null;
		try {
			session = HibernateUtil.beginTransaction();
			tareas = session.createQuery("from Tarea", Tarea.class).getResultList();
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			HibernateUtil.closeSession(session);
		}
		return tareas;
	}


	@Override
	public List<Tarea> listarPorCuenta(Cuenta cuenta) {
		List<Tarea> tareas = new ArrayList<Tarea>();
		Session session = null;
		try {
			session = HibernateUtil.beginTransaction();
			tareas = session.createQuery("from Tarea where cuenta = '" + cuenta.getIdCuenta() + "'", Tarea.class).getResultList();
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			HibernateUtil.closeSession(session);
		}
		return tareas;
	}

}
