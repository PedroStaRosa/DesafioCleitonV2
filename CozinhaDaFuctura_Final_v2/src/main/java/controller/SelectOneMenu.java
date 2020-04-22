package controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "SelectOnCategory")
@RequestScoped
public class SelectOneMenu {

	private List<String> categorias;
	private List<String> unidadeMedida;

	public SelectOneMenu() {

		// CARREGA AS CATEGORIAS DAS RECEITAS.
		categorias = new ArrayList<String>();
		categorias.add("Arroz");
		categorias.add("Carnes");
		categorias.add("Doces");
		categorias.add("Feijão");
		categorias.add("Frango");
		categorias.add("Macarrão");
		categorias.add("Pão");
		categorias.add("Peixe");
		categorias.add("Pizza");
		categorias.add("Salgados");
		
		
		// CARREGA AS UNIDADES DE MEDIDAS.
		unidadeMedida = new ArrayList<String>();
		unidadeMedida.add("Colher chá");
		unidadeMedida.add("Colher Sopa");
		unidadeMedida.add("Gramas");
		unidadeMedida.add("Kg");
		unidadeMedida.add("Litros");
		unidadeMedida.add("ML");
		unidadeMedida.add("Und");
		unidadeMedida.add("Xícara");
		
	}

	public List<String> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<String> categorias) {
		this.categorias = categorias;
	}

	public List<String> getUnidadeMedida() {
		return unidadeMedida;
	}

	public void setUnidadeMedida(List<String> unidadeMedida) {
		this.unidadeMedida = unidadeMedida;
	}

}
