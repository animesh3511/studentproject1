package com.example.oms.studentproject.Model.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentRequest {



    private Long studentId;


    private String name;

    private String userName;


    private String lastName;

    private String location;


    private double contact;


    private String email;


    private String password;


}
