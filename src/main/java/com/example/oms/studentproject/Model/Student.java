package com.example.oms.studentproject.Model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Long studentId;

    @Column(name = "name")
    private String name;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "location")
    private String location;

    @Column(name = "mobile_no",unique = true)
    private double contact;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @CreationTimestamp
    @Column(name = "student_createdAt",updatable = false)
    private LocalDateTime studentCreatedAt;

    @UpdateTimestamp
    @Column(name = "student_updatedAt")
    private LocalDateTime studentUpdatedAt;

    private Boolean isDeleted;

    private Boolean isActive;








}
