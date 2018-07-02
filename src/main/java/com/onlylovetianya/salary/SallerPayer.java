package com.onlylovetianya.salary;

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
 * <p>完成日期: 2018/6/28</p>
 *
 * @author : OnlyLoveTianYa
 */
public class SallerPayer {
    private volatile Double allSalary;
    private AtomicInteger employeeNum;
    private ConcurrentLinkedQueue<EmployeePOJO> unpaidEmployeesQueue;
    private static final long valueOffset;

    static {
        try {
            valueOffset = Initializer.getUnsafe().objectFieldOffset
                    (SallerPayer.class.getDeclaredField("allSalary"));
        } catch (Exception ex) {
            throw new Error(ex);
        }
    }


    public SallerPayer(Initializer initializer) {
        allSalary = initializer.getAllSalary();
        unpaidEmployeesQueue = initializer.getUnpaidEmployeesQueue();
        employeeNum = new AtomicInteger(unpaidEmployeesQueue.size());
    }

    public void sentSalary(ExecutorService ex) {
        for (int i = 0; i < 9; i++) {
            ex.execute(() -> {
                int employeeCode = employeeNum.decrementAndGet();
                while (employeeCode >= 0) {
                    EmployeePOJO employee = unpaidEmployeesQueue.poll();
                    while (!Initializer.getUnsafe().compareAndSwapObject(this, valueOffset, allSalary, allSalary - employee.getSalary())) {
                    }
//                    System.out.println("给员工" + employee.getName() + "发送工资" + employee.getSalary() + "元");
                    employeeCode = employeeNum.decrementAndGet();
                }
                //发完工资 唤醒主线程
                if (employeeCode == -9) {
                    synchronized (Initializer.class) {
                        Initializer.class.notify();
                    }
                }
            });
        }
    }

    public Double getAllSalary() {
        return allSalary;
    }
}
