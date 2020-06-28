package br.com.desafiotecnico.schedule;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.desafiotecnico.processor.ScheduleProcessorInterface;

@Component
public class ScheduledTasks {

	@Autowired
	ScheduleProcessorInterface scheduleProcessor;
	
	@Scheduled(fixedRate=5000)
	public void task() throws IOException {
		scheduleProcessor.fileProcessor();
	}
}
