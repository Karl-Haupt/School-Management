package za.ac.cput.domain.lookup;
/*
Author: Trevor Ngcobo (220477019)
Subject: Applications Development Practice 3
Group: 18
Assessment: June Group Assignment 2022
*/

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class Name implements Serializable
{
    @NotNull
    private String firstName;
    @NotNull
    private String middleName;
    @NotNull
    private String lastName;

    protected Name() {}

    private Name(Builder builder)
    {
        this.firstName = builder.firstName;
        this.middleName = builder.middleName;
        this.lastName = builder.lastName;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getMiddleName()
    {
        return middleName;
    }

    public String getLastName()
    {
        return lastName;
    }

    @Override
    public String toString()
    {
        return "Name{" +
                "firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    public static class Builder
    {
        private String firstName;
        private String middleName;
        private String lastName;

        public Builder setFirstName(String firstName)
        {
            this.firstName = firstName;
            return this;
        }

        public Builder setMiddleName(String middleName)
        {
            this.middleName = middleName;
            return this;
        }

        public Builder setLastName(String lastName)
        {
            this.lastName = lastName;
            return this;
        }

        public Builder copy (Name name)
        {
            this.firstName = name.getFirstName();
            this.middleName = name.getMiddleName();
            this.lastName = name.getLastName();
            return this;
        }

        public Name build()
        {
            return new Name(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name name = (Name) o;
        return firstName.equals(name.firstName) && middleName.equals(name.middleName) && lastName.equals(name.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, middleName, lastName);
    }
}
