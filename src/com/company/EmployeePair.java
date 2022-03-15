package com.company;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

public class EmployeePair {
    private Employee employee1;
    private Employee employee2;
    private int projectId;
    private Period timeWorkedTogether;

    public EmployeePair() {
        this.timeWorkedTogether = Period.between(LocalDate.now(), LocalDate.now());
    }

    public EmployeePair(Employee employee1, Employee employee2, int projectId, Period timeWorkedTogether) {
        this.employee1 = employee1;
        this.employee2 = employee2;
        this.projectId = projectId;
        this.timeWorkedTogether = timeWorkedTogether;
    }

    public Employee getEmployee1() {
        return employee1;
    }

    public void setEmployee1(Employee employee1) {
        this.employee1 = employee1;
    }

    public Employee getEmployee2() {
        return employee2;
    }

    public void setEmployee2(Employee employee2) {
        this.employee2 = employee2;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public Period getTimeWorkedTogether() {
        return timeWorkedTogether;
    }

    public void setTimeWorkedTogether(Period timeWorkedTogether) {
        this.timeWorkedTogether = timeWorkedTogether;
    }

    @Override
    public String toString() {
        return "employee1=" + employee1.getId() +
                ", employee2=" + employee2.getId() +
                ", projectId=" + projectId +
                ", timeWorkedTogether=" + timeWorkedTogether.getDays();
    }
}
