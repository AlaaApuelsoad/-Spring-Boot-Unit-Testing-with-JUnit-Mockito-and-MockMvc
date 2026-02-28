package org.example.alaa.springbootjunit.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CollegeStudent implements Student{

    private String firstname;
    private String lastname;
    private String emailAddress;
    private StudentGrades studentGrades;

    @Override
    public String studentInformation() {
        return "";
    }

    @Override
    public String getFullName() {
        return "";
    }


}
