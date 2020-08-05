package com.lojalivros.models.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.lojalivros.models.Funcionario;

public class FuncionarioDAO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@PersistenceContext
	EntityManager em;
	
	private DAO<Funcionario> dao;
	
	@PostConstruct
	void init() {
		this.dao = new DAO<Funcionario>(this.em, Funcionario.class);
	}

	public void adiciona(Funcionario t) {
		dao.adiciona(t);
	}

	public void remove(Funcionario t) {
		dao.remove(t);
	}

	public void atualiza(Funcionario t) {
		dao.atualiza(t);
	}

	public List<Funcionario> listaTodos() {
		return dao.listaTodos();
	}

	public Funcionario buscaPorId(Integer id) {
		return dao.buscaPorId(id);
	}
	
}
