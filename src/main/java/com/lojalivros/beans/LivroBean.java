package com.lojalivros.beans;


import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.lojalivros.models.Autor;
import com.lojalivros.models.Livro;
import com.lojalivros.models.dao.AutorDAO;
import com.lojalivros.models.dao.LivroDAO;
import javax.transaction.Transactional;

@Named
@ViewScoped
public class LivroBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Livro livro = new Livro();

	private Integer autorId;

	private List<Livro> livros;

	@Inject
	private LivroDAO livroDao;

	@Inject
	private AutorDAO autorDao;

	@Inject
	private FacesContext context;

	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}

	public Integer getAutorId() {
		return autorId;
	}

	public Livro getLivro() {
		return livro;
	}

	public List<Livro> getLivros() {

		if (this.livros == null) {
			this.livros = this.livroDao.listaTodos();
		}
		return livros;
	}

	public List<Autor> getAutores() {
		return this.autorDao.listaTodos();
	}

	public List<Autor> getAutoresDoLivro() {
		return this.livro.getAutores();
	}

	public void carregarLivroPelaId() {
		this.livro = this.livroDao.buscaPorId(this.livro.getId());
	}

	public void gravarAutor() {
		Autor autor = this.autorDao.buscaPorId(this.autorId);
		this.livro.adicionaAutor(autor);
		System.out.println("Escrito por: " + autor.getNome());
	}
	
	@Transactional
	public void gravar() {
		System.out.println("Gravando livro " + this.livro.getTitulo());

		if (livro.getAutores().isEmpty()) {
			context.addMessage("autor",
					new FacesMessage("Livro deve ter pelo menos um Autor."));
			return;
		}

		if (this.livro.getId() == null) {
			this.livroDao.adiciona(this.livro);
			this.livros = this.livroDao.listaTodos();
		} else {
			this.livroDao.atualiza(this.livro);
		}

		this.livro = new Livro();
	}

	@Transactional
	public void remover(Livro livro) {
		System.out.println("Removendo livro");
		this.livroDao.remove(livro);
		this.livros = this.livroDao.listaTodos();
	}

	public void removerAutorDoLivro(Autor autor) {
		this.livro.removeAutor(autor);
	}

	public void carregar(Livro livro) {
		this.livro = this.livroDao.buscaPorId(livro.getId());
	}

	public String formAutor() {
		System.out.println("Chamanda do formul�rio do Autor.");
		return "autor?faces-redirect=true";
	}

	public void comecaComDigitoUm(FacesContext fc, UIComponent component,
			Object value) throws ValidatorException {

		String valor = value.toString();
		if (!valor.startsWith("1")) {
			throw new ValidatorException(new FacesMessage(
					"ISBN deveria come�ar com 1"));
		}

	}
}