package com.lojalivros.models.dao;


import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.lojalivros.models.Livro;
@Stateless
public class LivroDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	EntityManager em;
	
	private DAO<Livro> dao;
	
	@PostConstruct
	void init() {
		this.dao = new DAO<Livro>(this.em, Livro.class);
	}

	public Livro buscaPorId(Integer livroId) {
		return this.dao.buscaPorId(livroId);
	}

	public void adiciona(Livro livro) {
		this.dao.adiciona(livro);
	}

	public void atualiza(Livro livro) {
		this.dao.atualiza(livro);
	}

	public void remove(Livro livro) {
		this.dao.remove(livro);
	}

	public List<Livro> listaTodos() {
		return this.dao.listaTodos();
	}

}