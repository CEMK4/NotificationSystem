/**
 * The NotificationJob class implements the Quartz Job interface and defines the task to be executed by Quartz scheduler.
 */
package com.PersonalBank.NotificationSystem.jobs;

import com.PersonalBank.NotificationSystem.services.NotificationService;
import lombok.RequiredArgsConstructor;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * A Quartz job responsible for executing the notification service to check the expiration date of bank cards.
 */
@RequiredArgsConstructor
public class NotificationJob implements Job {
    private final NotificationService service;

    /**
     * Executes the notification service to check the expiration date of bank cards.
     *
     * @param jobExecutionContext The job execution context.
     * @throws JobExecutionException If there is an error executing the job.
     */
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        service.CheckExpirationDateOfCard();
    }
}
