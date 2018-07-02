package com.onlylovetianya.salary;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 同时给10万个人发工资，怎么样设计并发方案，能确保在1分钟内全部发完
 *
 * @author : OnlyLoveTianYa
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        //四核CPU 线程2*n+1
        ExecutorService ex = Executors.newFixedThreadPool(9);
        long initStartTime = System.currentTimeMillis();
        Initializer initializer = new Initializer(ex, 100000);
        synchronized (Initializer.class) {
            Initializer.class.wait();
        }
        long initEndTime = System.currentTimeMillis();
        System.out.println("初始化时间" + (initEndTime - initStartTime) + "ms");
        System.out.println("要发工资的人数" + initializer.getUnpaidEmployeesQueue().size() + "工资总额" + initializer.getAllSalary());
        SallerPayer sallerPayer = new SallerPayer(initializer);
        long sentStartTime = System.currentTimeMillis();
        sallerPayer.sentSalary(ex);
        synchronized (Initializer.class) {
            Initializer.class.wait();
        }
        long sentEndTime = System.currentTimeMillis();
        System.out.println("发工资时间" + (sentEndTime - sentStartTime) + "ms");
        System.out.println("剩余工资" + sallerPayer.getAllSalary());
        ex.shutdown();
    }
}
