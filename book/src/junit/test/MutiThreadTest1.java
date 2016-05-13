package junit.test;

import java.util.ArrayList;
import java.util.List;

public class MutiThreadTest1 {
    
    public static MyStack1 stack = new MyStack1();
    public static void main(String[] args) throws InterruptedException {
        /**
         * 场景：线程1先执行，然后wait。
         *      线程2执行，在执行notify前，线程获3执行
         *      线程2执行结束，线程3获取锁执行，线程1等待锁
         *      线程3执行结束，线程1执行，出错。抛出角标越界异常
         */
        int x = 0;
       while(x<100){
            x++;
            //线程2：push
            new Thread(new Runnable() {
                @Override
                public void run() {
                    stack.push("test");
                }
            },"Push-"+x).start();
            
            
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
            },"Pop-"+x).start();
            
        }
    }

}


class  MyStack1 {
    private List<String> list = new ArrayList<String>();  
    
    public synchronized void push(String value) {  
        synchronized (this) {  
            String threadName = Thread.currentThread().getName();
            System.err.println(threadName+"------执行");
            list.add(value);  
            System.err.println(threadName+"------add");
            notify();  
        }  
    }   
  
    public synchronized String pop() throws InterruptedException {  
        synchronized (this) {  
            String threadName = Thread.currentThread().getName();
            if (list.size() <= 0) {
                wait();  
            }  
            System.err.println(threadName+"------执行");
            String remove = list.remove(list.size() - 1);
            System.err.println(threadName+"------remove");
            return remove;  
        }  
    }  
}