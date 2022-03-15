package com.company;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Employee {
    private final int id;
    private final int projectId;
    private final LocalDateTime dateFrom;
    private final LocalDateTime dateTo;

    public Employee(int id, int projectId, LocalDateTime dateFrom, LocalDateTime dateTo) {
        this.id = id;
        this.projectId = projectId;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public int getId() {
        return id;
    }

    public int getProjectId() {
        return projectId;
    }

    public LocalDateTime getDateFrom() {
        return dateFrom;
    }

    public LocalDateTime getDateTo() {
        return dateTo;
    }

}
