package junit.test;

import java.util.ArrayList;
import java.util.List;

public class MutiThreadTest {
    
    public static MyStack stack = new MyStack();
    public static void main(String[] args) throws InterruptedException {
        /**
         * 场景：线程1先执行，然后wait。
         *      线程2执行，在执行notify前，线程获3执行
         *      线程2执行结束，线程3获取锁执行，线程1等待锁
         *      线程3执行结束，线程1执行，出错。抛出角标越界异常
         */
        
        
        //线程1：pop
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    stack.pop();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"T1").start();
        
        //线程2：push
        new Thread(new Runnable() {
            @Override
            public void run() {
              stack.push("test");
            }
        },"T2").start();
        
        Thread.currentThread().sleep(500);//延迟线程2创建
        //线程3：pop
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    stack.pop();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"T3").start();
    }

}


class  MyStack {
    private List<String> list = new ArrayList<String>();  
    
    public synchronized void push(String value) {  
        synchronized (this) {  
            String threadName = Thread.currentThread().getName();
            System.err.println(threadName+"-----在执行");
            System.err.println(threadName+"-----add");
            list.add(value);  
            notify();  
            if("T2".equals(threadName)){
                try {
                    Thread.currentThread().sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }  
    }   
  
    public synchronized String pop() throws InterruptedException {  
        synchronized (this) {  
            String threadName = Thread.currentThread().getName();
            if (list.size() <= 0) {
                System.err.println(threadName+"-----在执行");
                wait();  
                if("T1".equals(threadName)){
                    Thread.currentThread().sleep(5000);
                }
            }  
            String remove = list.remove(list.size() - 1);
            System.err.println(threadName+"-----remove");
            return remove;  
        }  
    }  
}