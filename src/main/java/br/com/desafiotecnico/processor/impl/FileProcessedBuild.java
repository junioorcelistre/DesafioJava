package br.com.desafiotecnico.processor.impl;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import br.com.desafiotecnico.model.Customer;
import br.com.desafiotecnico.model.Sale;
import br.com.desafiotecnico.model.Salesman;
import br.com.desafiotecnico.processor.FileProcessedBuildInterface;

@Component
public class FileProcessedBuild implements FileProcessedBuildInterface {

	
	private static String directory = "/data/out/processado_";

	private final Logger logger = LoggerFactory.getLogger(FileProcessedBuild.class);

	final String newLine = System.getProperty("line.separator");

	@SuppressWarnings("deprecation")
	public void generateProcessedFile(List<Customer> customers, List<Sale> sales, List<Salesman> salesmans) {
		File fileDone = new File(directory + new SimpleDateFormat("ddHHmmss'.txt'").format(new Date()));
		try {
			logger.info("Customers: {}", customers.size());
			FileUtils.writeStringToFile(fileDone, Integer.toString(customers.size()) + newLine, true);
			logger.info("Salesman: {}", salesmans.size());
			FileUtils.writeStringToFile(fileDone, Integer.toString(salesmans.size()) + newLine, true);
			sales.sort(Comparator.comparingDouble(Sale::getTotalValue).reversed());
			FileUtils.writeStringToFile(fileDone, Integer.toString(sales.get(0).getSaleId()) + newLine, true);
			logger.info("Venda mais cara: {}", sales.get(0).getSaleId());
			FileUtils.writeStringToFile(fileDone, sales.get(sales.size() - 1).getSalesmanName() + newLine, true);
			salesmans.sort(Comparator.comparingDouble(Salesman::getSaleQuant));
			logger.info("Pior Vendedor: {}", sales.get(sales.size() - 1).getSalesmanName());
		} catch (IOException e) {
		}
	}
}
