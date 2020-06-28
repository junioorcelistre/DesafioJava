package br.com.desafiotecnico.processor.impl;

import static br.com.desafiotecnico.enums.Identity.CUSTOMER;
import static br.com.desafiotecnico.enums.Identity.SALES;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.desafiotecnico.model.Customer;
import br.com.desafiotecnico.model.Sale;
import br.com.desafiotecnico.model.Salesman;
import br.com.desafiotecnico.processor.FileProcessedBuildInterface;
import br.com.desafiotecnico.processor.ScheduleProcessorInterface;
import br.com.desafiotecnico.utils.FilesLinesReader;

@Component
public class SheduleProcessor implements ScheduleProcessorInterface {

	private final Logger logger = LoggerFactory.getLogger(SheduleProcessor.class);

	private static String directory = "/data/in/";

	@Autowired
	FileProcessedBuildInterface fileProcessedBuild;

	@Override
	public void fileProcessor() {
		List<String> lines = FilesLinesReader.readAllLineFromAllFiles(directory,
				"txt");
		if (lines.size() > 0) {
			List<Customer> customers = new ArrayList<>();
			List<Salesman> salesmans = new ArrayList<>();
			List<Sale> salesList = new ArrayList<>();
			for (String line : lines) {
				StringTokenizer st = new StringTokenizer(line, "ç");
				String identificador = (String) st.nextElement();
				if (identificador.equals(CUSTOMER.getIdentity())) {
					customers.add(new Customer(st));
				} else if (identificador.equals(SALES.getIdentity())) {
					salesList.add(new Sale(st));
				} else {
					salesmans.add(new Salesman(st));
				}
			}
			salesmans.forEach(f -> f.setSaleQuant(
					salesList.stream().map(Sale::getSalesmanName).filter(f.getName()::equals).count()));
			fileProcessedBuild.generateProcessedFile(customers, salesList, salesmans);
			try {
				FileUtils.cleanDirectory(new File(directory));
			} catch (IOException e) {
				logger.error("Falha ao limpar diretorio: {}", directory);
			}
		}

	}
}
