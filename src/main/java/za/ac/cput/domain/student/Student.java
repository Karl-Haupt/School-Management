/*
Author: Trevor Ngcobo (220477019)
Subject: Applications Development Practice 3
Group: 18
Assessment: June Group Assignment 2022
*/

package za.ac.cput.domain.student;

import za.ac.cput.domain.lookup.Name;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;


@Entity
public class Student implements Serializable
{
    @NotNull
    @Id
    private String studentId;
    @NotNull
    @Column(unique = true)
    private String email;
    @NotNull
    @Embedded
    private Name name;

    protected Student() {}

    private Student(Builder builder)
    {
        this.studentId = builder.studentId;
        this.email = builder.email;
        this.name = builder.name;
    }

    public String getStudentId()
    {
        return studentId;
    }

    public String getEmail()
    {
        return email;
    }

    public Name getName()
    {
        return name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", email='" + email + '\'' +
                ", name=" + name +
                '}';
    }

    public static class Builder
    {
        private String studentId;
        private String email;
        private Name name;

        public Builder setStudentId(String studentId)
        {
            this.studentId = studentId;
            return this;
        }

        public Builder setEmail(String email)
        {
            this.email = email;
            return this;
        }

        public Builder setName(Name name)
        {
            this.name = name;
            return this;
        }

        public Builder copy (Student student)
        {
            this.studentId = student.getStudentId();
            this.name = student.getName();
            this.email = student.getEmail();
            return this;
        }

        public Student build()
        {
            return new Student(this);
        }
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return studentId.equals(student.studentId);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(studentId);
    }
}
