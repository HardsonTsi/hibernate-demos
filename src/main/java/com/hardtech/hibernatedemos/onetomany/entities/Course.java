package com.hardtech.hibernatedemos.onetomany.entities;

import com.hardtech.hibernatedemos.entities.Student;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(unique = true)
    String title;
    //do not apply cascading deletes
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH,
            CascadeType.PERSIST})
    @JoinColumn(name = "instructor_id")
    Instructor instructor;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
    List<Review> reviews = new ArrayList<>();


    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH,
            CascadeType.PERSIST})
    @JoinTable(name = "course_student",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    List<Student> students = new ArrayList<>();

    public Course(String title) {
        this.title = title;
    }

    //add convenience method
    public void addReview(Review review) {
        this.reviews.add(review);
        review.setCourse(this);
    }

    public void addStudent(Student student) {
        this.students.add(student);
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
