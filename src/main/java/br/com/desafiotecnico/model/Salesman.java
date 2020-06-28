package br.com.desafiotecnico.model;

import java.util.StringTokenizer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Salesman {
	String cpf;
	String name;
	Double salary;
	long saleQuant;

	public Salesman(StringTokenizer st) {
		this.cpf = (String) st.nextElement();
		this.name = (String) st.nextElement();
		this.salary = new Double((String) st.nextElement());
		
	}
}
