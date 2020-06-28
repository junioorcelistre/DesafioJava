package br.com.desafiotecnico.model;

import java.util.StringTokenizer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Item {
	int itemId;
	int itemQuant;
	double itemPrice;
	
	public Item(StringTokenizer st) {
		this.itemId = Integer.valueOf((String) st.nextElement());
		this.itemQuant = Integer.valueOf((String)st.nextElement());
		this.itemPrice = new Double((String) st.nextElement());
	}
}
