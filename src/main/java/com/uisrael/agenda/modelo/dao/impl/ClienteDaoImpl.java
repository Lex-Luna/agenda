package com.uisrael.agenda.modelo.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.uisrael.agenda.modelo.dao.ClienteDao;
import com.uisrael.agenda.modelo.entidad.Cliente;
import com.uisrael.agenda.util.HibernateUtil;

public class ClienteDaoImpl implements ClienteDao {

	@Override
	public void guardar(Cliente cliente) {
		Session session = null;
		try {
			session = HibernateUtil.beginTransaction();
			session.persist(cliente);
			HibernateUtil.commitTransaction(session);
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	@Override
	public void actualizar(Cliente cliente) {
		Session session = null;
		try {
			session = HibernateUtil.beginTransaction();
			session.merge(cliente);
			HibernateUtil.commitTransaction(session);
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	@Override
	public void eliminar(Cliente cliente) {
		Session session = null;
		try {
			session = HibernateUtil.beginTransaction();
			session.delete(cliente);
			HibernateUtil.commitTransaction(session);
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	@Override
	public Cliente buscarPorId(int id) {
		Cliente cliente = null;
		Session session = null;
		try {
			session = HibernateUtil.beginTransaction();
			cliente = session.find(Cliente.class, id);
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			HibernateUtil.closeSession(session);
		}
		return cliente;
	}

	@Override
	public List<Cliente> listar() {
		List<Cliente> clientes = null;
		Session session = null;
		try {
			session = HibernateUtil.beginTransaction();
			clientes = session.createQuery("from Cliente where estadoCliente=" + Boolean.TRUE, Cliente.class).getResultList();
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			HibernateUtil.closeSession(session);
		}
		return clientes;
	}
}
