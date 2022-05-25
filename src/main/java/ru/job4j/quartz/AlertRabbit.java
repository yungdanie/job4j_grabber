package ru.job4j.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;

public class AlertRabbit {
    public static void main(String[] args) {
        Properties properties = getProp("rabbit.properties");
        try (Connection connection = createConnection(properties)) {
            try {
                Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
                scheduler.start();
                JobDataMap data = new JobDataMap();
                data.put("cn", connection);
                JobDetail job = newJob(Rabbit.class)
                        .usingJobData(data)
                        .build();
                SimpleScheduleBuilder times = simpleSchedule()
                        .withIntervalInMilliseconds
                                (Integer.parseInt(properties.getProperty("rabbit.interval")))
                        .repeatForever();
                Trigger trigger = newTrigger()
                        .startNow()
                        .withSchedule(times)
                        .build();
                scheduler.scheduleJob(job, trigger);
                Thread.sleep(10000);
                scheduler.shutdown();
            } catch (Exception se) {
                se.printStackTrace();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static Connection createConnection(Properties properties)
            throws ClassNotFoundException, SQLException {
        Class.forName(properties.getProperty("driver"));
        return DriverManager.getConnection(properties.getProperty("url"),
                properties.getProperty("login"),
                properties.getProperty("password"));
    }
    private static Properties getProp(String property) {
        Properties properties = new Properties();
        try (InputStream in = AlertRabbit.class.getClassLoader().getResourceAsStream(property)) {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    public static class Rabbit implements Job {
        public Rabbit() {
            System.out.println(hashCode());
        }

        @Override
        public void execute(JobExecutionContext context) {
            Connection connection = (Connection)
                    context.getJobDetail().getJobDataMap().get("cn");
            try (PreparedStatement preparedStatement =
                         connection.prepareStatement("insert into rabbit(created_date) values (?)")) {
                preparedStatement.setTimestamp(1,
                        Timestamp.valueOf(LocalDateTime.now()));
                preparedStatement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}