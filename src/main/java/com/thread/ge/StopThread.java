package com.thread.ge;

/**
 * @author tangquanbin
 * @date 2018/10/08 21:09
 */
public class StopThread extends Thread{

    /**
     * 停止一个线程可用Thread.stop(),但这个方法是不安全的，被弃用的。别用
     * 还可以用Thread.interrupt()
     */
    @Override
    public void run() {
        /**
         * 在沉睡中停止：如果线程在睡眠中被interrupt，将java.lang.InterruptedException: sleep interrupted
         * 使用“异常法”停止线程
         */
        try {
            for (int i=0;i<500000;i++){
               if (this.isInterrupted()) {
                   System.out.println("线程是停止状态。。。");
                   Thread.yield();
//                   break;
//                   throw new InterruptedException();
               }
               System.out.println("i=" + (i + 1));

           }
            System.out.println("===for end===");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试线程是否是中断状态
     * isInterrupted  不清除状态标志
     * interrupted    清除状态标志
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        StopThread thread = new StopThread();
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
//        System.out.println("interrupted===================="+thread.interrupted());
//        System.out.println("interrupted===================="+thread.interrupted());
//        System.out.println("isInterrupted===================="+thread.isInterrupted());
//        System.out.println("isInterrupted===================="+thread.isInterrupted());
    }

}
