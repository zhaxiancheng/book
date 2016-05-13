package junit.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Test {
        public static volatile boolean bo = true;
        public static Map chm = new ConcurrentHashMap();  
        
        public static  boolean get() {
           throw new RuntimeException();

        }
        
        public static void readDictory(File file,BufferedReader bufReader,BufferedWriter bufWriter) throws IOException{
            if(file == null){
                throw new RuntimeException();
            }
            File[] listFiles = file.listFiles();
            for (File file2 : listFiles) {
                if(file.isFile()){
                    String line = bufReader.readLine();
                    while(line!=null){
                        bufWriter.write(line);
                    }
                    bufWriter.close();
                    bufReader.close();
                }else{
                    readDictory(file2, bufReader, bufWriter);
                    
                }
            }
        }
        
        public static void main(String[] args) throws InterruptedException  { 
            int[] arr = new int[10];
            for (int i = 1; i < 1000; i++) {
                int index = i & (arr.length-1);
                arr[index] = i;
            }
            System.err.println(Arrays.toString(arr));
            
            /* 
            ByteBuffer buf = ByteBuffer.allocate(1024);
            byte b = 1;
            buf = buf.put(b);
            System.err.println(buf.get());
            
            CharBuffer cbuf = CharBuffer.allocate(1024);
            cbuf = cbuf.put("1");
            System.err.println(cbuf.get());
            
           *  String path = "";
            
            File file = new File(path);*/
            
            
            
            
            
          /*  ArrayList<Integer> list = new ArrayList<Integer>();
            list.add(1);
            list.add(2);
            list.add(3);
            list.add(4);
            
            list.remove(3);
            System.err.println(list.size());*/
            
            
            
            /* 
            int x = 0x1_______2;
            System.err.println(x);
            List<String>  list = new ArrayList<>();
            if(get()){
                System.err.println(111);
            }
            Test.chm.put("Test", 1);  
            tht t1 = new tht();  
            t1.start();  
            for(int i=1;i<1000;i++)  
            {  
                th t = new th(i);  
                t.start();  
            }  
            */
          //BlockingQueue<Integer>  queue = new ArrayBlockingQueue<Integer>(1);
          //queue.remove();
          //queue.add(0);
          //queue.add(1);
          //queue.put(1);
          //Integer take = queue.take();
          //System.err.println(take);
         // System.err.println(queue.peek());
          /*ReentrantLock lock1 = new ReentrantLock();
          ReentrantLock lock2 = new ReentrantLock();
          Condition c1 = lock1.newCondition();
          Condition c2 = lock2.newCondition();
          for(;;){
              System.err.println("lock1开始锁定");
              //lock1.lock();
              c2.await();
          }
          System.err.println(11);
          Test test = new Test();
          test.wait();
         */ 
        }  
        
        
    }  
    class th extends Thread  
    {  
        private int number = 0;  
        public th(int _number)  
        {  
            number = _number;  
        }  
          
        @Override  
        public void run()  
        {  
              
            synchronized (this) {
                
                try {
                    wait();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
          }
            while(Test.bo){  
                int state = (Integer)Test.chm.get("Test");  
                if(state == 9 )  
                {  
                   System.out.println("线程:"+number+",停止!***************************");  
                    Test.bo = false;  
                }else  
                {  
                    System.out.println("线程:"+number+",state="+state+",time:"+System.currentTimeMillis());  
                }  
            }  
        }  
    }  
      
    class tht extends Thread  
    {  
        public tht()  
        {  
        }  
          
        @Override  
        public void run()  
        {  
              
            while(Test.bo){  
                    int state = (int)(Math.random() * 1000000);  
                    Test.chm.put("Test", state);  
                    if(state == 9)  
                    {  
                        System.out.println("线程:-1,停止,time="+System.currentTimeMillis()+",--------------------------------------------");  
                        Test.bo = false;  
                    }  
            }  
        }
    
}
