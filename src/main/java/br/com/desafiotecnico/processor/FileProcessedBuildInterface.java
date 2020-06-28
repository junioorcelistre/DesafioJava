package br.com.desafiotecnico.processor;

import java.util.List;

import br.com.desafiotecnico.model.Customer;
import br.com.desafiotecnico.model.Sale;
import br.com.desafiotecnico.model.Salesman;

public interface FileProcessedBuildInterface {
	
	public void generateProcessedFile(List<Customer> customers, List<Sale> sales, List<Salesman> salesmans);

}
