package br.com.desafiotecnico.model;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Sale{
	int saleId;
	String salesmanName;
	List<Item> sales;
	Double totalValue;
	int totalItems;
	
	public Sale(StringTokenizer st) {
		this.saleId = Integer.valueOf((String)st.nextElement());
		this.sales = buildSales((String) st.nextElement());
		this.salesmanName = (String) st.nextElement();
		
	}
	
	private List<Item> buildSales(String vendas) {
		StringTokenizer st = new StringTokenizer(vendas.replaceAll("[\\[\\]]", ""), ",");
		List<Item> sales = new ArrayList<>();
		while (st.hasMoreElements()) {
			sales.add(new Item(new StringTokenizer((String) st.nextElement(), "-")));
		}
		Double total = 0.0;
		for(Item item : sales) {
			total += item.getItemQuant() * item.getItemPrice();
		}
		this.totalValue = total;
		return sales;
	}
}
