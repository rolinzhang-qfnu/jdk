package coucurrent;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.BrokenBarrierException;

public class CyclicBarrierSample {
    public static void main(String[] args) throws InterruptedException {
        CyclicBarrier barrier = new CyclicBarrier(2, new Runnable() {
            @Override
            public void run() {
                System.out.println("done!");
            }
        });
        for (int i = 0; i < 5; i++) {
            Thread t = new Thread(new CyclicWorker(barrier));
            t.start();
        }
    }
    static class CyclicWorker implements Runnable {
        private CyclicBarrier barrier;
        public CyclicWorker(CyclicBarrier barrier) {
            this.barrier = barrier;
        }
        @Override
        public void run() {
            try {
                for (;  ; ) {
                    System.out.println(this.hashCode()+"begin!");
                    barrier.await();
                }

            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

