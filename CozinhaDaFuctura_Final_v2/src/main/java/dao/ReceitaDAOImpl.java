package dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import javax.persistence.Query;

import entity.Ingredientes;
import entity.Receita;
import util.JpaUtil;

public class ReceitaDAOImpl implements ReceitaDAO {

	private EntityManager ent;
	private EntityTransaction tx;

	public ReceitaDAOImpl() {

	}

	@Override
	public boolean inserir(Receita receita) {

		try {
			this.ent = JpaUtil.getEntityManager();
			tx = ent.getTransaction();
			tx.begin();
			ent.merge(receita);
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
	public void remover(Receita receita) {
		try {
			this.ent = JpaUtil.getEntityManager();
			tx = ent.getTransaction();
			tx.begin();
			ent.remove(receita);
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
	public void alterar(Receita receita) {
		// USANDO O METODO INSERIR POIS O MESMO UTILIZA "MERGE"
	}

	@Override
	public Receita pesquisar(long idReceita) {

		Receita receita = new Receita();
		try {
			this.ent = JpaUtil.getEntityManager();
			tx = ent.getTransaction();
			tx.begin();
			receita = ent.find(Receita.class, idReceita);

		} catch (Exception e) {
			if (ent.isOpen()) {
				tx.rollback();
			}
		} finally {
			if (ent.isOpen()) {
				ent.close();
			}
		}

		return receita;
	}

	@Override
	public List<Receita> listarTodos(String categoriaSelecionada) {
		List<Receita> todasReceitas = new ArrayList<Receita>();
		try {
			this.ent = JpaUtil.getEntityManager();
			Query query = null;

			if (categoriaSelecionada.equals("")) {
				query = ent.createQuery(" from Receita r");
			} else {
				query = ent.createQuery(" from Receita r where categoria = '" + categoriaSelecionada + "'");
			}
			todasReceitas = query.getResultList();
			return todasReceitas;
		} catch (Exception e) {
			if (ent.isOpen()) {
				tx.rollback();
			}
		} finally {
			if (ent.isOpen()) {
				ent.close();
			}
		}
		return todasReceitas;
	}

	@Override
	public List<Receita> listaMinhasReceitas(String cpfChef) {
		List<Receita> receitas = new ArrayList<Receita>();
		try {
			this.ent = JpaUtil.getEntityManager();
			Query query = ent.createQuery("from Receita r where cpf_chef = :cpfchef");
			query.setParameter("cpfchef", cpfChef);

			receitas = query.getResultList();

		} catch (Exception e) {
			if (ent.isOpen()) {
				tx.rollback();
			}
		} finally {
			if (ent.isOpen()) {
				ent.close();
			}
		}
		return receitas;
	}

	@Override
	public void removerIngrediente(Ingredientes ingrediente) {

		try {
			this.ent = JpaUtil.getEntityManager();
			tx = ent.getTransaction();
			tx.begin();
			ent.remove(ingrediente);
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
	public Ingredientes pesquisarIngredientes(long idIngrediente) {

		Ingredientes ingrediente = new Ingredientes();
		try {
			this.ent = JpaUtil.getEntityManager();
			tx = ent.getTransaction();
			tx.begin();
			ingrediente = ent.find(Ingredientes.class, idIngrediente);

		} catch (Exception e) {
			if (ent.isOpen()) {
				tx.rollback();
			}
		} finally {
			if (ent.isOpen()) {
				ent.close();
			}
		}

		return ingrediente;
	}

}
