package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
List<Employee> empList = new ArrayList<>();

        empList.add(new Employee("A", 30000.00, "HR"));
        empList.add(new Employee("B", 20000.00, "HR"));
        empList.add(new Employee("C", 100000.00, "HR"));
        empList.add(new Employee("D", 30000.00, "HR"));

        empList.add(new Employee("X", 30000.00, "Finance"));
        empList.add(new Employee("Y", 20000.00, "Finance"));
        empList.add(new Employee("X", 1000.00, "Finance"));
        empList.add(new Employee("P", 90000.00, "Finance"));

        Comparator<Employee> salaryComparator = Comparator.comparing(Employee::getSalary);

        Map<String, Optional<Employee>> maxSalByDeptMap = empList.stream().collect(Collectors.groupingBy(Employee::getDeptName,
                Collectors.reducing(BinaryOperator.maxBy(salaryComparator))));

        maxSalByDeptMap.forEach((dentName,emp)->{
            System.out.println(dentName);
            Employee employee = emp.get();
            System.out.println(employee);
        });

        System.out.println("--------------------------------------");
        Map<String, Optional<Employee>> minSalByDeptMap = empList.stream().collect(Collectors.groupingBy(Employee::getDeptName,
                Collectors.reducing(BinaryOperator.minBy(salaryComparator))));

        minSalByDeptMap.forEach((dentName,emp)->{
            System.out.println(dentName);
            Employee employee = emp.get();
            System.out.println(employee);
        });
    }

}
