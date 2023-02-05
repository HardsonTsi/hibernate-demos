package com.hardtech.hibernatedemos.onetomany.entities;

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
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "instructor")
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "first_name")
    String firstName;
    @Column(name = "last_name")
    String lastName;
    @Column(name = "email")
    String email;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "instructor_detail_id")
    InstructorDetail instructorDetail;

    //do not apply cascading deletes
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "instructor", cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.PERSIST})
    @ToString.Exclude
    List<Course> courses = new ArrayList<>();

    public Instructor(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    //convenience methods for bidirectional relationship
    public void addCourse(Course course) {
        this.courses.add(course);
        course.setInstructor(this);
    }


}
