package entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("Chef")
public class ChefCozinha extends Usuario {
	
	@OneToMany(mappedBy = "profissional", cascade = CascadeType.ALL)
	private List<Receita> receita;

	public ChefCozinha() {
	}

	public List<Receita> getReceita() {
		return receita;
	}

	public void setReceita(List<Receita> receita) {
		this.receita = receita;
	}

}
