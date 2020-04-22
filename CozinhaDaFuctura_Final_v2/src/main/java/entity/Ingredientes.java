package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table (name = "Ingredientes")
public class Ingredientes {
	@Id
	@Column ( name = "id")
	@GeneratedValue
	private long id;
	
	@Column (name = "descricao")
	private String descricao;
	
	@Column (name = "unidadeMedida")
	private String unidadeMedida;
	
	@Column (name = "quantidade")
	private double quantidade;
	
	@ManyToOne
	@JoinColumn(name = "id_receita", referencedColumnName = "id", nullable = false)
	private Receita receita;

	public Ingredientes() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getUnidadeMedida() {
		return unidadeMedida;
	}

	public void setUnidadeMedida(String unidadeMedida) {
		this.unidadeMedida = unidadeMedida;
	}

	public double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(double quantidade) {
		this.quantidade = quantidade;
	}

	public Receita getReceita() {
		return receita;
	}

	public void setReceita(Receita receita) {
		this.receita = receita;
	}

}
