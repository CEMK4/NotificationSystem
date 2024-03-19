package com.PersonalBank.NotificationSystem.jobs;

import com.PersonalBank.NotificationSystem.services.NotificationService;
import lombok.RequiredArgsConstructor;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

@RequiredArgsConstructor
public class CashLoadJob implements Job {
    private final NotificationService service;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        service.CheckExpirationDateOfCard();
    }
}
