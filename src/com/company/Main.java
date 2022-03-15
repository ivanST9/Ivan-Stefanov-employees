package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        File file = new File("C:" + File.separator + "ItFiles" + File.separator + "CSV_file.txt");
        EmployeePair result = new EmployeePair();
        List<Employee> employees = populateEmployeesFromFile(file);

        for(int i = 0; i < employees.size(); i++) {
            Employee employee1 = employees.get(i);
            for(int j = 0; j < employees.size(); j++) {
                if (i == j) {
                    continue;
                }
                Employee employee2 = employees.get(j);
                //find employees with the same project
                if(employee1.getProjectId() == employee2.getProjectId()) {
                   //check if dates overlap
                   //two dates overlap if (StartDate1 <= EndDate2) and (StartDate2 <= EndDate1)
                    if (doDatesOverlap(employee1, employee2)) {
                        Period timeOnSameProject = findTimeWorkedOnSameProject(employee1, employee2);
                        if(result.getTimeWorkedTogether().getDays() < timeOnSameProject.getDays()) {
                            result.setEmployee1(employee1);
                            result.setEmployee2(employee2);
                            result.setProjectId(employee1.getProjectId());
                            result.setTimeWorkedTogether(timeOnSameProject);
                        }
                    }
                }
            }
        }

        System.out.println(result);
    }

    private static Period findTimeWorkedOnSameProject(Employee employee1, Employee employee2) {
        int startDateDifferance = employee1.getDateFrom().compareTo(employee2.getDateFrom());
        int endDateDifferance = employee1.getDateTo().compareTo(employee2.getDateTo());
        LocalDateTime firstCommonDay;
        LocalDateTime lastCommonDay;
        if (startDateDifferance <= 0) {
            firstCommonDay = employee2.getDateFrom();
        } else {
            firstCommonDay = employee1.getDateFrom();
        }
        if (endDateDifferance <= 0) {
            lastCommonDay = employee1.getDateTo();
        } else {
            lastCommonDay = employee2.getDateTo();
        }
        return Period.between(firstCommonDay.toLocalDate(), lastCommonDay.toLocalDate());
    }

    private static boolean doDatesOverlap(Employee employee1, Employee employee2) {
        LocalDateTime dateFrom1 = employee1.getDateFrom();
        LocalDateTime dateFrom2 = employee2.getDateFrom();
        LocalDateTime dateTo1 = employee1.getDateTo();
        LocalDateTime dateTo2 = employee2.getDateTo();
        return dateFrom1.compareTo(dateTo2) <= 0 && dateTo1.compareTo(dateFrom2) >= 0;
    }

    private static List<Employee> populateEmployeesFromFile(File file) throws IOException {
        List<Employee> result = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            br.lines().forEach((line) -> {
                String[] columns = line.split(",");
                int employeeId = Integer.parseInt(columns[0].strip());
                int projectId = Integer.parseInt(columns[1].strip());
                LocalDateTime dateFrom= LocalDate.parse(columns[2].strip()).atStartOfDay();
                LocalDateTime dateTo = columns[3].strip().equals("NULL") ? LocalDate.now().atStartOfDay() : LocalDate.parse(columns[3].strip()).atStartOfDay();
                Employee employee = new Employee(employeeId, projectId, dateFrom, dateTo);
                result.add(employee);
            });
        }
        return result;
    }
}
