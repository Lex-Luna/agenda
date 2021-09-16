package com.uisrael.agenda.modelo.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.uisrael.agenda.modelo.dao.CuentaDao;
import com.uisrael.agenda.modelo.entidad.Cuenta;
import com.uisrael.agenda.util.HibernateUtil;

public class CuentaDaoImpl implements CuentaDao {

	@Override
	public void guardar(Cuenta cuenta) {
		Session session = null;
		try {
			session = HibernateUtil.beginTransaction();
			session.persist(cuenta);
			HibernateUtil.commitTransaction(session);
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	@Override
	public void actualizar(Cuenta cuenta) {
		Session session = null;
		try {
			session = HibernateUtil.beginTransaction();
			session.merge(cuenta);
			HibernateUtil.commitTransaction(session);
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	@Override
	public void eliminar(Cuenta cuenta) {
		Session session = null;
		try {
			session = HibernateUtil.beginTransaction();
			session.delete(cuenta);
			HibernateUtil.commitTransaction(session);
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	@Override
	public Cuenta buscarPorId(int id) {
		Cuenta cuenta = null;
		Session session = null;
		try {
			session = HibernateUtil.beginTransaction();
			cuenta = session.find(Cuenta.class, id);
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			HibernateUtil.closeSession(session);
		}
		return cuenta;
	}

	@Override
	public List<Cuenta> listar() {
		List<Cuenta> cuentas = null;
		Session session = null;
		try {
			session = HibernateUtil.beginTransaction();
			cuentas = session.createQuery("from Cuenta", Cuenta.class).getResultList();
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			HibernateUtil.closeSession(session);
		}
		return cuentas;
	}

	@Override
	public Cuenta buscarPorUsuario(String usuario) {
		Cuenta cuenta = null;
		Session session = null;
		try {
			session = HibernateUtil.beginTransaction();
			cuenta = session.createQuery("from Cuenta where usuarioCuenta ='" + usuario + "'", Cuenta.class)
					.getSingleResult();
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			HibernateUtil.closeSession(session);
		}
		return cuenta;
	}
}
