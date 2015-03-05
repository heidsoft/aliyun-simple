package com.heidsoft.aliyun.simple;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by heidsoft on 2015/3/5.
 * @author jake.liu
 * @version 1.0
 */
public class ReadWriteLockSimple {

    public static void main(String[] args) {

        Data data = new Data();
        TestTask task1 = new TestTask(true,data);
        TestTask task2 = new TestTask(true,data);
        task1.start();
        task2.start();
    }

}

class TestTask extends Thread implements Runnable{

    private boolean read = false;
    private Data data = null;
    public TestTask(boolean read, Data data){
        this.read = read;
        this.data = data;
    }

    @Override
    public void run() {
        if(data !=null ){
            if(this.read){
                data.get();
            }else{
                data.set();
            }
        }
    }
}

class Data {

    public ReadWriteLock lock = new ReentrantReadWriteLock();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Lock read = lock.readLock();
    Lock write = lock.writeLock();

    public void set(){
        //锁住写操作
        write.lock();
        System.out.println(Thread.currentThread().hashCode()+" set begin:"+sdf.format(new Date()));

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            System.out.println(Thread.currentThread().hashCode()+" set begin:"+sdf.format(new Date()));
            write.unlock();
        }

    }

    public void get(){
        //锁住读操作
        read.lock();
        System.out.println(Thread.currentThread().hashCode()+" get begin:"+sdf.format(new Date()));

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            System.out.println(Thread.currentThread().hashCode()+" get begin:"+sdf.format(new Date()));
            read.unlock();
        }
    }

}
