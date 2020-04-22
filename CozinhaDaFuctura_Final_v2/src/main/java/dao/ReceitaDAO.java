package dao;

import java.util.List;

import entity.Ingredientes;
import entity.Receita;

public interface ReceitaDAO {

	public boolean inserir(Receita receita);

	public void remover(Receita receita);

	public void alterar(Receita receita);

	public Receita pesquisar(long idReceita);

	public List<Receita> listarTodos(String categoriaSelecionada);

	public List<Receita> listaMinhasReceitas(String cpfChef);
	
	public void removerIngrediente(Ingredientes ingrediente);
	
	public Ingredientes pesquisarIngredientes(long idIngrediente);
	
}
