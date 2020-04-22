package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import entity.ChefCozinha;
import entity.Receita;
import entity.Usuario;
import util.JpaUtil;

public class UsuarioDAOImpl implements UsuarioDAO {

	private EntityManager ent;
	private EntityTransaction tx;

	public UsuarioDAOImpl() {

	}

	@Override
	public boolean inserir(Usuario usuario) {
		try {
			this.ent = JpaUtil.getEntityManager();
			tx = ent.getTransaction();
			tx.begin();

			ent.merge(usuario);
			tx.commit();

			return true;

		} catch (Exception e) {
			if (ent.isOpen()) {
				tx.rollback();
			}
		} finally {
			if (ent.isOpen()) {
				ent.close();
			}
		}
		return false;
	}

	@Override
	public void remover(String cpfUsuario) {
		// FUTURA IMPLEMENTAÇÃO PARA O USUARIO DELETAR SEU PERFIL.
		try {
			this.ent = JpaUtil.getEntityManager();
			Usuario usuarioRemover = ent.find(Usuario.class, cpfUsuario);
			tx = ent.getTransaction();
			tx.begin();
			ent.remove(usuarioRemover);
			tx.commit();

		} catch (Exception e) {
			if (ent.isOpen()) {
				tx.rollback();
			}
		} finally {
			if (ent.isOpen()) {
				ent.close();
			}
		}

	}

	@Override
	public void alterar(Usuario usuario) {

	}

	@Override
	public Usuario pesquisar(String cpf) {

		Usuario usuario = new Usuario();
		try {
			this.ent = JpaUtil.getEntityManager();
			tx = ent.getTransaction();
			tx.begin();
			usuario = ent.find(Usuario.class, cpf);

		} catch (Exception e) {
			if (ent.isOpen()) {
				tx.rollback();
			}
		} finally {
			if (ent.isOpen()) {
				ent.close();
			}
		}

		return usuario;

	}

	@Override
	public List<Usuario> listarTodos() {
		return null;
	}
}
