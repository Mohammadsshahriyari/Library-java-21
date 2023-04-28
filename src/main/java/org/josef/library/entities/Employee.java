package org.josef.library.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
public class Employee {
    public long emp_id;
    private String fname;
    private String lname;
    private LocalDate joining_date;
    private String contact;

    public Employee(long emp_id, String fname, String lname, LocalDate joining_date, String contact) {
        this.emp_id = emp_id;
        this.fname = fname;
        this.lname = lname;
        this.joining_date = joining_date;
        this.contact = contact;
    }
}
