package com.onlylovetianya.salary;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>文件名称: </p>
 * <p>文件描述: </p>
 * <p>版权所有: 版权所有(C)2017</p>
 * <p>公 司: 深圳竹云科技有限公司</p>
 * <p>内容摘要: </p>
 * <p>其他说明: 无</p>
 * <p>完成日期: 2018/6/27</p>
 *
 * @author : OnlyLoveTianYa
 */
public class Initializer {

    private volatile Double allSalary = 0.0;
    private AtomicInteger employeeNum;
    private ConcurrentLinkedQueue<EmployeePOJO> unpaidEmployeesQueue;
    private static final Unsafe unsafe;
    private static final long valueOffset;

    static {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe) field.get(null);
            valueOffset = unsafe.objectFieldOffset
                    (Initializer.class.getDeclaredField("allSalary"));
        } catch (Exception ex) {
            throw new Error(ex);
        }
    }

    public Initializer(ExecutorService ex, int employees) {

        //要发薪水的员工数
        employeeNum = new AtomicInteger(employees);
        unpaidEmployeesQueue = new ConcurrentLinkedQueue<>();
        Random random = new Random();
        for (int i = 0; i < 9; i++) {
            ex.execute(() -> {
                int employeeCode = employeeNum.decrementAndGet();
                while (employeeCode >= 0) {
                    //获取0~10000之间的随机薪水
                    double salary = random.nextInt(10000);
                    EmployeePOJO employee = new EmployeePOJO("employeeCode" + employeeCode, salary);
                    while (!unsafe.compareAndSwapObject(this, valueOffset, allSalary, allSalary + salary)) {
                    }
                    unpaidEmployeesQueue.offer(employee);
                    employeeCode = employeeNum.decrementAndGet();
                }
                //初始化完人数 唤醒主线程
                if (employeeCode == -9) {
                    synchronized (Initializer.class) {
                        Initializer.class.notify();
                    }
                }
            });
        }
    }

    public static Unsafe getUnsafe() {
        return unsafe;
    }

    public static long getValueOffset() {
        return valueOffset;
    }

    public Double getAllSalary() {
        return allSalary;
    }

    public ConcurrentLinkedQueue getUnpaidEmployeesQueue() {
        return unpaidEmployeesQueue;
    }

}
