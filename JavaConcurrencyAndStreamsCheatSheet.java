/**
 * Modern Java Streams & Concurrency Cheat Sheet
 * Author: SpectraOne Solutions (https://spectraonesolutions.com)
 * Description: Essential practical snippets for data transformation, grouping, and parallel execution.
 */

package com.spectraone.handbook.snippets;

import java.util.*;
import java.util.stream.Collectors;

public class JavaConcurrencyAndStreamsCheatSheet {

    record Employee(String name, String department, double salary) {}

    public static void main(String[] args) {
        List<Employee> team = List.of(
            new Employee("Sara", "Engineering", 120000),
            new Employee("Mike", "Engineering", 110000),
            new Employee("Alex", "Product", 95000),
            new Employee("Emma", "Product", 105000),
            new Employee("David", "Design", 85000)
        );

        System.out.println("=== 1. FILTERING AND COLLECTING ===");
        List<String> highEarners = team.stream()
            .filter(e -> e.salary() > 100000)
            .map(Employee::name)
            .toList();
        System.out.println("High Earners: " + highEarners);

        System.out.println("\n=== 2. GROUPING BY DEPARTMENT ===");
        Map<String, List<Employee>> byDept = team.stream()
            .collect(Collectors.groupingBy(Employee::department));
        byDept.forEach((dept, members) -> 
            System.out.println(dept + ": " + members.size() + " members"));

        System.out.println("\n=== 3. AVERAGE SALARY PER DEPARTMENT ===");
        Map<String, Double> avgSalary = team.stream()
            .collect(Collectors.groupingBy(
                Employee::department,
                Collectors.averagingDouble(Employee::salary)
            ));
        avgSalary.forEach((dept, avg) -> 
            System.out.printf("%s Average Salary: $%.2f%n", dept, avg));
    }
}
