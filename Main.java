import java.util.*;
import java.io.*;

class Main {
  public static void main(String[] args) {
    ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 4, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(2), new CustomThreadFactory(), new CustomRejectHander());

    for(int i = 0; i <=4; i++){
      executor.submit(() -> {
        try{
          Thread.sleep(5000);
        } catch(Exception e){
          //throw some exception
        }
        System.out.println("Task processed by: " + Thread.currentThread().getName());
      });
    }
    
  }

  class CustomThreadFactory implements ThreadFactory {

    @Override
    public Thread newThread(Runnable r) {
      Thread th = new Thread(r);
      th.setPrioity(Thread.NORM_PRIORITY);
      th.setDaemon();
      return th;
    }
  }

  class CustomRejectHander implements RejectedExecutionHandler {

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
      System.out.println("Task Rejected : " + r.toString());
    }
  }
}