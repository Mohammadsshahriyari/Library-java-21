package org.josef.library.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
public class Book {
    private long id;
    private String title;
    private String general;
    private LocalDate publication_date;
    private String author;
    private String description;
    private float borrowing_fee_per_day;
    private float late_fee_per_day;
    private float rating;
    private boolean available;

    public Book(long id,
                String title,
                String general,
                LocalDate publication_date,
                String author,
                String description,
                float borrowing_fee_per_day,
                float late_fee_per_day,
                float rating,
                boolean available) {
        this.id = id;
        this.title = title;
        this.general = general;
        this.publication_date = publication_date;
        this.author = author;
        this.description = description;
        this.borrowing_fee_per_day = borrowing_fee_per_day;
        this.late_fee_per_day = late_fee_per_day;
        this.rating = rating;
        this.available = available;
    }

    @Override
    public String toString() {
        return "Book [ " +
                "  BOOK ID =" + id +
                ", TITLE='" + title + '\'' +
                ", GENERAL='" + general + '\'' +
                ", PUBLICATION DATE=" + publication_date +
                ", Author='" + author + '\'' +
                ", BORROW FEE / DAY=" + borrowing_fee_per_day +
                ", Rating=" + rating +
                ", AVAILABLE =" + String.valueOf(available).toUpperCase() +
                " ]";
    }
}
