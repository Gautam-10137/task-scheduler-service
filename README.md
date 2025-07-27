
  <h1> Job Scheduler Service in Java</h1>

  <p>
    A real-world, multi-threaded Job Scheduler built from scratch in Java.
    This service simulates how backend systems handle background tasks like email notifications, report generation, and reminders — with support for prioritization, retries, and extensibility.
  </p>

  <hr>

  <h2>🚀 Features</h2>
  <ul>
    <li><strong>Priority-Based Job Queue</strong><br>Uses <code>PriorityBlockingQueue</code> to schedule high-priority jobs first.</li>
    <li><strong>Multi-Threaded Execution</strong><br>Jobs are executed using a <code>ScheduledExecutorService</code> with a configurable thread pool.</li>
    <li><strong>Retry Mechanism with Exponential Backoff</strong><br>Automatically retries failed jobs with exponential delay (e.g., 1s, 4s, 9s...).</li>
    <li><strong>Job Lifecycle Tracking</strong><br>Tracks job status: <code>PENDING</code>, <code>RUNNING</code>, <code>RETRYING</code>, <code>SUCCESS</code>, <code>EXHAUSTED</code>.</li>
    <li><strong>Graceful Completion with CountDownLatch</strong><br>Main thread waits for all jobs to finish or exhaust before exiting.</li>
    <li><strong>Extensible Job Types</strong><br>Easily add new job types by extending the <code>AbstractJob</code> class.</li>
    <li><strong>Simulated Failures</strong><br>Jobs randomly fail to test the robustness of retry and scheduling logic.</li>
  </ul>

  <hr>

  <h2>📦 Technologies Used</h2>
  <ul>
    <li>Java 17+</li>
    <li><code>ScheduledExecutorService</code></li>
    <li><code>PriorityBlockingQueue</code></li>
    <li><code>CountDownLatch</code></li>
    <li>Object-Oriented Design Patterns</li>
  </ul>

  <hr>

  <h2>📂 Job Types Implemented</h2>
  <table border="1" cellpadding="8" cellspacing="0">
    <thead>
      <tr>
        <th>Job Type</th>
        <th>Description</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td><code>EmailJob</code></td>
        <td>Simulates sending emails</td>
      </tr>
      <tr>
        <td><code>ReminderJob</code></td>
        <td>Simulates sending user reminders</td>
      </tr>
      <tr>
        <td><code>ReportGenerationJob</code></td>
        <td>Simulates generating long-running reports</td>
      </tr>
    </tbody>
  </table>

  <hr>

  <h2>📁 Project Structure</h2>
  <pre>
src/
├── com.scheduler.core/          # Core interfaces, abstract job class, and job status enum
│   ├── AbstractJob.java
│   ├── Job.java
│   ├── JobStatus.java
│   └── RetryPolicy.java
│
├── com.scheduler.engine/        # Job execution engine, queue manager, and job submission logic
│   ├── JobExecutor.java
│   ├── JobQueue.java
│   └── JobManager.java
│
├── com.scheduler.jobs/          # Concrete job implementations
│   ├── EmailJob.java
│   ├── ReminderJob.java
│   └── ReportGenerationJob.java
│
└── Main.java                    # Entry point of the application
  </pre>

  <hr>

  <h2> What I Learned</h2>
  <ul>
    <li>Java concurrency (<code>ScheduledExecutorService</code>, <code>CountDownLatch</code>)</li>
    <li>Designing scalable and extensible background job systems</li>
    <li>Retry strategies using exponential backoff</li>
    <li>Proper separation of concerns and clean code architecture</li>
    <li>Graceful job coordination and failure handling</li>
  </ul>

  <hr>

  <h2>How to Run</h2>
  <ol>
    <li>
      <strong>Clone the repository:</strong><br>
      <code>git clone https://github.com/Gautam-10137/task-scheduler-service.git</code><br>
      <code>cd task-scheduler-service</code>
    </li>
    <li>
      <strong>Run from IDE</strong> (e.g., IntelliJ, Eclipse)<br>
      or compile from terminal:<br>
      <code>javac -d out src/**/*.java</code><br>
      <code>java -cp out com.scheduler.Main</code>
    </li>
  </ol>
  <p><strong>✅ Make sure you're using Java 17+</strong></p>

  <hr>

  <h2> What's Next</h2>
  <ul>
    <li> Kafka Integration for distributed job ingestion and event streaming</li>
    <li> REST API using Spring Boot for dynamic job submission</li>
    <li> Docker support for containerized deployment</li>
  </ul>

  <hr>

  <h2>🤝 Contributing</h2>
  <p>Have ideas or want to improve the scheduler?<br>
  Feel free to open an issue or create a pull request — all contributions are welcome!</p>

  <h2>👨‍💻 Author</h2>
  <p><strong>Gautam Pahwa</strong><br>
  🔗 <a href="https://www.linkedin.com/in/gautam-pahwa-a0537720a">LinkedIn</a><br>
  💻 <a href="https://github.com/Gautam-10137">GitHub</a></p>


