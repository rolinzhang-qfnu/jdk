package coucurrent;
import java.util.concurrent.CountDownLatch;


public  class CountDownLatchSample {
  public static void main(String[]args) throws InterruptedException {
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch doneSignal = new CountDownLatch(2);
       for (int i = 0; i < 2; ++i) { // create and start threads
            new Thread(new CountDownLatchWorker(startSignal, doneSignal)).start();
        }

        System.out.println("begin1");            // don't let run yet
        startSignal.countDown();      // let all threads proceed
        System.out.println("begin2");
        doneSignal.await();
        System.out.println("done");// wait for all to finish
  }

    public static class CountDownLatchWorker implements Runnable {
        private final CountDownLatch startSignal;
        private final CountDownLatch doneSignal;

        CountDownLatchWorker(CountDownLatch startSignal, CountDownLatch doneSignal) {
            this.startSignal = startSignal;
            this.doneSignal = doneSignal;
        }

        public void run() {
            try {
                startSignal.await();
                System.out.println(this.hashCode()+"begin");
                doneSignal.countDown();
            } catch (InterruptedException ex) {
            }
        }

    }

}
