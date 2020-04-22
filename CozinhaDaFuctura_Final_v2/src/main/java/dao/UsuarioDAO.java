package dao;

import java.util.List;

import entity.Receita;
import entity.Usuario;

public interface UsuarioDAO {

	public boolean inserir(Usuario usuario);

	public void remover(String cpfUsuario);

	public void alterar(Usuario usuario);

	public Usuario pesquisar(String cpf);

	public List<Usuario> listarTodos();

}
