import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Created by Khang Nguyen.
 * Email: khang.nguyen@banvien.com
 * Date: 09/05/2018
 * Time: 10:20 PM
 */
public class MainApp {
    static Scheduler scheduler = null;
    static JobDetail job = null;
    public static void main(String[] args){
        try {
            SchedulerFactory schedulerFactory = new StdSchedulerFactory();
            scheduler = schedulerFactory.getScheduler();
            job = JobBuilder.newJob(MyJob1.class).build();
            CronTrigger trigger = TriggerBuilder.newTrigger().withSchedule(CronScheduleBuilder.cronSchedule("0/3 * * ? * *")).build();
            scheduler.scheduleJob(job, trigger);
            scheduler.start();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static class MyJob1 implements Job {

        public MyJob1(){

        }

        @Override
        public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
            System.out.println("Hello Quartz 1!");
            try {
                scheduler.deleteJob(job.getKey());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
