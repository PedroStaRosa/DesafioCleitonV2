package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;

import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import dao.ReceitaDAO;
import dao.ReceitaDAOImpl;
import dao.UsuarioDAO;
import dao.UsuarioDAOImpl;
import entity.ChefCozinha;
import entity.Ingredientes;
import entity.Receita;
import util.JpaUtil;

@ManagedBean(name = "ChefBean")
@SessionScoped
public class ChefBean {

	private ChefCozinha chef;
	private Receita receita;
	private Ingredientes ingredientes;
	private List<Receita> listaMinhasReceitas;
	private List<Receita> listaTodasReceitas;
	private int minhaReceitaSelecionada;
	private long idReceitaSelecionada;
	private long idIngredienteSelecionado;
	private String categoriaSelecionada;

	private ReceitaDAO receitaDAO;
	private UsuarioDAO usuarioDAO;

	private String mensagem;
	private ChefCozinha usuarioLogado;

	// private String cpfLogado;
	private ChefCozinha dadosChef;

	private static final String VER_RECEITA = "verReceita.xhtml";
	private static final String VER_MINHA_RECEITA = "verMinhasReceitas.xhtml";
	private static final String INDEX = "index.xhtml";
	private static final String MEUSDADOS = "meusDados.xhtml";
	// private static final String LOGIN = "login.xhtml";

	public ChefBean() throws IOException {

		this.usuarioLogado = (ChefCozinha) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get("UsuarioLogado");
		// this.cpfLogado = this.usuarioLogado.getCpf();
		this.dadosChef = this.usuarioLogado;

		this.receita = new Receita();
		this.receita.setIngredientes(new ArrayList<Ingredientes>());
		this.ingredientes = new Ingredientes();
		this.listaMinhasReceitas = new ArrayList<Receita>();
		this.listaTodasReceitas = new ArrayList<Receita>();
		this.receitaDAO = new ReceitaDAOImpl();
		this.usuarioDAO = new UsuarioDAOImpl();

		this.receita.setProfissional(this.usuarioLogado);

		// this.listaMinhasReceitas =
		// this.receitaDAO.listaMinhasReceitas(this.usuarioLogado.getCpf());
		this.listaMinhasReceitas = this.receitaDAO.listaMinhasReceitas(this.dadosChef.getCpf());
		if (listaMinhasReceitas.isEmpty()) {
			System.out.println("Lista vazia");
		}

	}

	// METODOS
	public void salvarChef() throws IOException {

		if (this.usuarioDAO.inserir(this.dadosChef)) {
			addMessage("Usuario cadastrado com sucesso!");
			abrirIndex();
		} else {
			addMessageError("Erro ao cadastrar usuário!");
		}

	}

	public void salvarReceita() throws IOException {

		if (this.receitaDAO.inserir(this.receita)) {
			System.out.println("OK");
			this.clear();
			addMessage("Receita cadastrada com sucesso!");
			FacesContext.getCurrentInstance().getExternalContext().redirect(INDEX);
		} else {
			System.out.println("ERRO!!!");

		}
	}

	public void excluirReceita() {

		Receita receitaExluir = this.receitaDAO.pesquisar(idReceitaSelecionada);
		this.receitaDAO.remover(receitaExluir);

		this.listaMinhasReceitas = receitaDAO.listaMinhasReceitas(this.dadosChef.getCpf());
	}

	public void excluirIngre() {

		System.out.println(idIngredienteSelecionado);
		Ingredientes ing = this.receitaDAO.pesquisarIngredientes(idIngredienteSelecionado);
		this.receitaDAO.removerIngrediente(ing);

	}


	public void pesquisarMinhasReceitas() { // BUSCA RECEITAS DO CHEF(OU USUARIO) LOGADO
		/*
		 * (BUG)ao apertar o "x" do dialog, estou perdendo a referencia do
		 * usuarioLogado, solução provisoria - criar uma String que receber o cpf e
		 * salva. (cpfLogado)
		 */
		// this.listaMinhasReceitas = this.receitaDAO.listaMinhasReceitas(cpfLogado); //
		// PROVISÓRIO.
		this.listaMinhasReceitas = this.receitaDAO.listaMinhasReceitas(this.dadosChef.getCpf()); // PROVISÓRIO.
		// ORIGINAL.
		// this.listaMinhasReceitas =
		// this.receitaDAO.listaMinhasReceitas(this.usuarioLogado.getCpf());

	}

	public void pesquisarTodasReceitas() { // BUSCA TODAS AS RECEITAS DE TODOS OS CHEFS.
		this.listaTodasReceitas = this.receitaDAO.listarTodos(categoriaSelecionada);
	}

	public void verReceitaSelecionada() throws IOException {
		Receita receitaSelecionada = this.receitaDAO.pesquisar(idReceitaSelecionada);
		this.receita = receitaSelecionada;
		if (this.minhaReceitaSelecionada == 1) {
			abrirMinhaReceita();
		} else {
			abrirReceita();
		}
	}

	public void addIngrediente() {
		Ingredientes novoIngrediente = new Ingredientes();

		novoIngrediente.setDescricao(this.ingredientes.getDescricao());
		novoIngrediente.setQuantidade(this.ingredientes.getQuantidade());
		novoIngrediente.setUnidadeMedida(this.ingredientes.getUnidadeMedida());
		novoIngrediente.setReceita(this.receita);

		this.receita.getIngredientes().add(novoIngrediente);
		this.ingredientes = new Ingredientes();

	}

	public void clear() {
		this.usuarioLogado = (ChefCozinha) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get("UsuarioLogado");
		this.receita = new Receita();
		this.receita.setProfissional(usuarioLogado);
		this.receita.setIngredientes(new ArrayList<Ingredientes>());
		this.ingredientes = new Ingredientes();
		this.listaMinhasReceitas = new ArrayList<Receita>();
		this.minhaReceitaSelecionada = 0;

	}

	public void zeraLista() {
		this.listaTodasReceitas = new ArrayList<Receita>();
	}
	
	// DIRECIONA PAGINAS
	public void abrirReceita() throws IOException {
		FacesContext.getCurrentInstance().getExternalContext().redirect(VER_RECEITA);
	}

	public void abrirMinhaReceita() throws IOException {
		FacesContext.getCurrentInstance().getExternalContext().redirect(VER_MINHA_RECEITA);
	}

	public void abrirIndex() throws IOException {
		this.clear();
		FacesContext.getCurrentInstance().getExternalContext().redirect(INDEX);
	}

	public void abrirMeusDados() throws IOException {
		FacesContext.getCurrentInstance().getExternalContext().redirect(MEUSDADOS);
	}

	// ADICIONA MENSAGENS

	public void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void addMessageError(String titulo) {

		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, titulo, null);
		FacesContext.getCurrentInstance().addMessage(null, message);

	}

	// GETTERS AND SETTERS
	public ChefCozinha getChef() {
		return chef;
	}

	public void setChef(ChefCozinha chef) {
		this.chef = chef;
	}

	public Receita getReceita() {
		return receita;
	}

	public void setReceita(Receita receita) {
		this.receita = receita;
	}

	public Ingredientes getIngredientes() {
		return ingredientes;
	}

	public void setIngredientes(Ingredientes ingredientes) {
		this.ingredientes = ingredientes;
	}

	public List<Receita> getListaMinhasReceitas() {
		return listaMinhasReceitas;
	}

	public void setListaMinhasReceitas(List<Receita> listaMinhasReceitas) {
		this.listaMinhasReceitas = listaMinhasReceitas;
	}

	public long getIdReceitaSelecionada() {
		return idReceitaSelecionada;
	}

	public void setIdReceitaSelecionada(long idReceitaSelecionada) {
		this.idReceitaSelecionada = idReceitaSelecionada;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public ChefCozinha getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(ChefCozinha usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public List<Receita> getListaTodasReceitas() {
		return listaTodasReceitas;
	}

	public void setListaTodasReceitas(List<Receita> listaTodasReceitas) {
		this.listaTodasReceitas = listaTodasReceitas;
	}

	public String getCategoriaSelecionada() {
		return categoriaSelecionada;
	}

	public void setCategoriaSelecionada(String categoriaSelecionada) {
		this.categoriaSelecionada = categoriaSelecionada;
	}

	public ChefCozinha getDadosChef() {
		return dadosChef;
	}

	public void setDadosChef(ChefCozinha dadosChef) {
		this.dadosChef = dadosChef;
	}

	public int getMinhaReceitaSelecionada() {
		return minhaReceitaSelecionada;
	}

	public void setMinhaReceitaSelecionada(int minhaReceitaSelecionada) {
		this.minhaReceitaSelecionada = minhaReceitaSelecionada;
	}

	public long getIdIngredienteSelecionado() {
		return idIngredienteSelecionado;
	}

	public void setIdIngredienteSelecionado(long idIngredienteSelecionado) {
		this.idIngredienteSelecionado = idIngredienteSelecionado;
	}
}
