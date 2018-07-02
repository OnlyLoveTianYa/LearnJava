package com.onlylovetianya.salary;

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
public class EmployeePOJO {
    private String name;
    private double salary;

    public EmployeePOJO() {
    }

    public EmployeePOJO(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
