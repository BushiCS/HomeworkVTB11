package org.example.test;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "books_readers")
public class Review {
    @Embeddable
    public static class Id implements Serializable {
        @Column(name = "reader_id")
        int readerId;

        @Column(name = "book_id")
        int bookId;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Id id = (Id) o;
            return readerId == id.readerId && bookId == id.bookId;
        }

        @Override
        public int hashCode() {
            return Objects.hash(readerId, bookId);
        }
    }

    @EmbeddedId
    Id id;

    @ManyToOne
    @JoinColumn(name = "reader_id")
    Reader reader;

    @ManyToOne
    @JoinColumn(name = "book_id")
    Book book;

    @Column(name = "score")
    int score;

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Review() {
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", reader=" + reader +
                ", book=" + book +
                ", score=" + score +
                '}';
    }
}
