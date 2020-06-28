package br.com.desafiotecnico.model;

import java.util.StringTokenizer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Customer {
	String cnpj;
	String name;
	String businessArea;

	public Customer(StringTokenizer st) {
		this.cnpj = (String) st.nextElement();
		this.name = (String) st.nextElement();
		this.businessArea = (String) st.nextElement();
	}
}
