/**
 * The QuartzTask class provides methods for scheduling Quartz jobs.
 */
package com.PersonalBank.NotificationSystem.jobs;

import lombok.RequiredArgsConstructor;
import org.quartz.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * A component for scheduling Quartz jobs.
 */
@Component
@RequiredArgsConstructor
public class QuartzTask {
    private final Scheduler scheduler;

    /**
     * Schedules a notification job to run at a specified time interval.
     *
     * @throws SchedulerException If there is an error scheduling the job.
     */
    public void scheduleTask() throws SchedulerException {
        // Create a job detail
        JobDetail job = JobBuilder.newJob(NotificationJob.class)
                .withIdentity("BringNotification", "group1")
                .build();

        // Create a trigger to specify when the job will run
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("NotificationTrigger", "group1")
//                .startNow()
                .startAt(Date.from(LocalDateTime.now(ZoneId.systemDefault()).plusSeconds(10).atZone(ZoneId.systemDefault()).toInstant())) // Start the job after 10 seconds
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(10) // Run every 10 seconds
                        .repeatForever()) // Repeat indefinitely
                .build();

        // Schedule the job with the trigger
        scheduler.scheduleJob(job, trigger);
    }
}
