package com.lojalivros.beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import com.lojalivros.models.Funcionario;
import com.lojalivros.models.Livro;
import com.lojalivros.models.dao.FuncionarioDAO;

@Named
@ViewScoped
public class FuncionarioBean implements Serializable {
	

	private static final long serialVersionUID = 1L;

	@Inject
	FuncionarioDAO funcionarioDAO;
	
	private Integer funcionarioID;
	
	private Funcionario funcionario = new Funcionario();
	
	@Transactional
	public String gravar() {
		if (this.funcionario.getId() == null) {
			this.funcionarioDAO.adiciona(this.funcionario);
		} else {
			this.funcionarioDAO.atualiza(this.funcionario);
		}
		this.funcionario = new Funcionario();
		return "funcionario?faces-redirect=true";
	}

	public List<Funcionario> getFuncionarios() {
		return this.funcionarioDAO.listaTodos();
	}
	public void carregarFuncionarioPelaId() {
		this.funcionario = this.funcionarioDAO.buscaPorId(funcionarioID);
	}

	@Transactional
	public void remover(Funcionario funcionario) {
		this.funcionarioDAO.remove(funcionario);
	}
	
	public void carregar(Funcionario funcionario) {
		this.funcionario = this.funcionarioDAO.buscaPorId(funcionario.getId());
	}
	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Integer getFuncionarioID() {
		return funcionarioID;
	}

	public void setFuncionarioID(Integer funcionarioID) {
		this.funcionarioID = funcionarioID;
	}
	
	
	
}
