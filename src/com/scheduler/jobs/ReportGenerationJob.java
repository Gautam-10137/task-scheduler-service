package com.scheduler.jobs;

import java.util.concurrent.CountDownLatch;
import com.scheduler.core.AbstractJob;
import com.scheduler.core.JobStatus;

public class ReportGenerationJob extends AbstractJob {

    private final String reportName;

    public ReportGenerationJob(String reportName, int priority, int maxRetries) {
        super(priority, maxRetries);
        this.reportName = reportName;
    }

    @Override
    public void run() {
        this.setStatus(JobStatus.RUNNING);
        System.out.println("[ReportGenerationJob] Generating report: " + reportName);

        // Simulate long-running report creation
        try {
            Thread.sleep(1000 + (int)(Math.random() * 1000));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        if (Math.random() < 0.3) {
            throw new RuntimeException("Report generation failed");
        }

        System.out.println("[ReportGenerationJob] Report generated: " + reportName);
        this.setStatus(JobStatus.SUCCESS);
    }
}
