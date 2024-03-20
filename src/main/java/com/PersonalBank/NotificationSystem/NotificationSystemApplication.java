package com.PersonalBank.NotificationSystem;

import com.PersonalBank.NotificationSystem.jobs.QuartzTask;
import com.PersonalBank.NotificationSystem.services.BankCardService;
import com.PersonalBank.NotificationSystem.services.ClientService;
import org.quartz.SchedulerException;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * The main class of the Notification System application.
 */
@SpringBootApplication
@EnableScheduling
public class NotificationSystemApplication {

	/**
	 * The main method initializes and starts the Spring Boot application.
	 *
	 * @param args Command line arguments.
	 */
	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(NotificationSystemApplication.class);
		application.setBannerMode(Banner.Mode.OFF);
		application.run(args);
	}

	/**
	 * Initializes necessary services and schedules tasks using Quartz.
	 *
	 * @param clientService The client service for initializing clients and writing logs.
	 * @param bankCardService The bank card service for initializing bank cards.
	 * @param task The Quartz task for scheduling notifications.
	 * @return An ApplicationRunner bean.
	 * @throws SchedulerException if there is an error scheduling the task.
	 */
	@Bean
	public ApplicationRunner init(ClientService clientService, BankCardService bankCardService, QuartzTask task) throws SchedulerException {
		clientService.init();
		bankCardService.init();
		clientService.write();
		task.scheduleTask();
		return args -> {
		};
	}

}
