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

@SpringBootApplication
@EnableScheduling
public class NotificationSystemApplication {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(NotificationSystemApplication.class);
		application.setBannerMode(Banner.Mode.OFF);
		application.run(args);
	}

	@Bean
	public ApplicationRunner init(ClientService clientService, BankCardService bankCardService, QuartzTask task) throws SchedulerException {
		clientService.init();
		bankCardService.init();
		task.scheduleTask();
		return args -> {
		};
	}

}
