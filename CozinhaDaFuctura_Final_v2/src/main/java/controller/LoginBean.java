package controller;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.swing.text.StyledEditorKit.BoldAction;

import dao.UsuarioDAO;
import dao.UsuarioDAOImpl;
import entity.ChefCozinha;
import entity.Usuario;
import util.JpaUtil;

@ManagedBean(name = "LoginBean")
@RequestScoped
public class LoginBean {

	// USUARIO PADRAO PARA INICIO DA APLICAÇÃO
	final private String usuarioAdm = "admin";
	final private String senhaAdm = "admin";

	// RECEBE VALORES DE ACESSO DA LOGIN.XHTML
	private String usuarioInput, senhaInput;
	private UsuarioDAO usuarioDAO;
	private String mensagem;

	private static final String LOGIN = "login.xhtml";
	private static final String USUARIO = "index.xhtml";
	// private static final String CHEF = "chef.xhtml"; // FUTUTA IMPLEMENTAÇÃO DE
	// PAGINAS SEPARADAS "CHEF X USUARIO".

	private Usuario novoUsuario;
	private ChefCozinha novoChef;

	public LoginBean() {
		this.usuarioDAO = new UsuarioDAOImpl();
		this.novoUsuario = new Usuario();
		this.novoChef = new ChefCozinha();

	}

	public void entrar() throws IOException {

		Usuario usuarioLogin = this.usuarioDAO.pesquisar(this.usuarioInput);
		if (usuarioLogin != null) {
			if (usuarioLogin.getSenha().equals(this.senhaInput)) {

				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("UsuarioLogado",
						usuarioLogin);

				FacesContext.getCurrentInstance().getExternalContext().redirect(USUARIO);

			} else {
				addMessageError("Usuario ou Senha inválida.");
			}
		} else {
			addMessageError("Usuario ou Senha inválida.");
		}
	}

	public void salvarUsuario() throws IOException {
		if (this.usuarioDAO.inserir(this.novoUsuario)) {
			addMessage("Usuario cadastrado com sucesso!");

		} else {
			addMessageError("Desculpe, usuário não cadastrato");
		}

	}

	public void salvarChef() throws IOException {

		if (this.usuarioDAO.inserir(this.novoChef)) {
			// addMessage("Usuario cadastrado com sucesso!");
			FacesContext.getCurrentInstance().getExternalContext().redirect(LOGIN);

		} else {
			addMessageError("Erro ao cadastrar usuário!");
		}

	}

	public void ValidarChefExiste() throws IOException {
		Usuario usuarioExiste = this.usuarioDAO.pesquisar(this.novoChef.getCpf());

		if (usuarioExiste == null) {
			addMessage("Usuario cadastrado com sucesso!");
			salvarChef();
		} else {
			addMessageError("CPF já cadastrado no sistema");

		}
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

	public String getUsuarioInput() {
		return usuarioInput;
	}

	public void setUsuarioInput(String usuarioInput) {
		this.usuarioInput = usuarioInput;
	}

	public String getSenhaInput() {
		return senhaInput;
	}

	public void setSenhaInput(String senhaInput) {
		this.senhaInput = senhaInput;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Usuario getNovoUsuario() {
		return novoUsuario;
	}

	public void setNovoUsuario(Usuario novoUsuario) {
		this.novoUsuario = novoUsuario;
	}

	public ChefCozinha getNovoChef() {
		return novoChef;
	}

	public void setNovoChef(ChefCozinha novoChef) {
		this.novoChef = novoChef;
	}

}
