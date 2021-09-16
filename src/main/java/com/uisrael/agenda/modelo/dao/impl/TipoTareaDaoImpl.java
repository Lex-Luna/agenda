package com.uisrael.agenda.modelo.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.uisrael.agenda.modelo.dao.TipoTareaDao;
import com.uisrael.agenda.modelo.entidad.Cuenta;
import com.uisrael.agenda.modelo.entidad.TipoTarea;
import com.uisrael.agenda.util.HibernateUtil;

public class TipoTareaDaoImpl implements TipoTareaDao {

	@Override
	public void guardar(TipoTarea tipoTarea) {
		Session session = null;
		try {
			session = HibernateUtil.beginTransaction();
			session.persist(tipoTarea);
			System.out.println(tipoTarea.getIdTipoTarea());
			HibernateUtil.commitTransaction(session);
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	@Override
	public void actualizar(TipoTarea tipoTarea) {
		Session session = null;
		try {
			session = HibernateUtil.beginTransaction();
			session.merge(tipoTarea);
			HibernateUtil.commitTransaction(session);
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	@Override
	public Boolean eliminar(TipoTarea tipoTarea) {
		Boolean eliminacion = false ;
		Session session = null;
		try {
			session = HibernateUtil.beginTransaction();
			session.delete(tipoTarea);
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
	public TipoTarea buscarPorId(int id) {
		TipoTarea tipoTarea = null;
		Session session = null;
		try {
			session = HibernateUtil.beginTransaction();
			tipoTarea = session.find(TipoTarea.class, id);
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			HibernateUtil.closeSession(session);
		}
		return tipoTarea;
	}

	@Override
	public List<TipoTarea> listar() {
		List<TipoTarea> tipoTareas = new ArrayList<TipoTarea>();
		Session session = null;
		try {
			session = HibernateUtil.beginTransaction();
			tipoTareas = session.createQuery("from TipoTarea", TipoTarea.class).getResultList();
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			HibernateUtil.closeSession(session);
		}
		return tipoTareas;
	}

	@Override
	public List<TipoTarea> listarPorCuenta(Cuenta cuenta) {
		List<TipoTarea> tipoTareas = new ArrayList<TipoTarea>();
		Session session = null;
		try {
			session = HibernateUtil.beginTransaction();
			tipoTareas = session
					.createQuery("from TipoTarea where cuenta = '" + cuenta.getIdCuenta() + "'", TipoTarea.class)
					.getResultList();
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			HibernateUtil.closeSession(session);
		}
		return tipoTareas;
	}

}
