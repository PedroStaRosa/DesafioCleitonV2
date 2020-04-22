package entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "RECEITA")
public class Receita {
	@Id
	@Column(name = "id")
	@GeneratedValue
	private long id;

	@Column(name = "nomereceita")
	private String nomeReceita;

	@Column(name = "modopreparo", length = 500)
	private String modoPreparo;

	@Column(name = "categoria")
	private String categoria;

	@Column(name = "numeropessoas")
	private int numeroPessoas;

	@OneToMany(mappedBy = "receita", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Ingredientes> ingredientes;

	@ManyToOne
	@JoinColumn(name = "cpf_chef", referencedColumnName = "cpf", nullable = false)
	private ChefCozinha profissional;

	public Receita() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getModoPreparo() {
		return modoPreparo;
	}

	public void setModoPreparo(String modoPreparo) {
		this.modoPreparo = modoPreparo;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public ChefCozinha getProfissional() {
		return profissional;
	}

	public void setProfissional(ChefCozinha profissional) {
		this.profissional = profissional;
	}

	public List<Ingredientes> getIngredientes() {
		return ingredientes;
	}

	public String getNomeReceita() {
		return nomeReceita;
	}

	public void setNomeReceita(String nomeReceita) {
		this.nomeReceita = nomeReceita;
	}

	public void setIngredientes(List<Ingredientes> ingredientes) {
		this.ingredientes = ingredientes;
	}

	public int getNumeroPessoas() {
		return numeroPessoas;
	}

	public void setNumeroPessoas(int numeroPessoas) {
		this.numeroPessoas = numeroPessoas;
	}

}
