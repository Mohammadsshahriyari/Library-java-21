package org.josef.library.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BorrowBook {
    private long id;
    private Book book;
    private Customer customer;
    private LocalDate issue_date;
    private LocalDate return_date;
    private float total_paid;
    private boolean returned;
    private String description;

}
