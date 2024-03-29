package java;

/*
*
* 1.start():
* 2.run():同城需要o重写Thread类中的此方法，将创建的线程要执行的操作声明在此方法中
* 3.currentThread():静态方法，返回执行当前代码的线程
* 4.getName():获取当前线程的名字
* 5.setName():设置当前线程的名字
* 6.yield():释放当前CPU的执行权
* 7.join():在线程a中调用线程b的join(),此时线程a就进入阻塞状态，直到线程b结束为止
* 8.stop():已过时。当执行此方法时，强制结束当前线程。
* 9.sleep(long millitime):让当前线程”睡眠“指定的millitime毫秒。在指定的millitime毫秒时间内，
*                         当前线程是阻塞状态。
* 10.isAlive():判断当前线程是否存活。
*
*
* 线程的优先级
* 1.
* MAX_PRIORITY:10
* MIN_PRIORITY:1
* NORM_PRIORITY:5 ---> 默认优先级
* 2.获取和设置当前线程的优先级
* getPriority():
* setPriority():
*
*   说明：高优先级的线程要抢占低优先级线程cpu的执行权。但是只是从该概率上讲，
*   高优先级的线程高概率被执行。并不意味着只有当高优先级的线程执行完后，低优先级线程才执行。
*
* @author
* */

class HelloThread extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0){

                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread().getName() + ":" + i);
            }

            if (i % 20 == 0){
                this.yield();
            }
        }
    }

    public HelloThread(String name){
        super(name);
    }
}

public class ThreadMethodTest {
    public static void main(String[] args) {

        HelloThread h1 = new HelloThread("Thread1");

//        h1.setName("线程一");

        h1.start();

        //给主线程命名
        Thread.currentThread().setName("主线程");

        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0){
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }

            if (i == 20){
                try {
                    h1.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }


    }

}
