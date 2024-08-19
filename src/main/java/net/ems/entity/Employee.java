package net.ems.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter // automatically inject getter function
@Setter // automatically inject getter function
@NoArgsConstructor // automatically inject empty constructor function
@AllArgsConstructor // automatically inject initialize constructor function
@Entity // used to mapping the object to the database table
@Table(name = "employees") // indicate the table name, if you don't add @Table,// it'll automatically create the table name as entity name;

public class Employee {
//    public Employee(String firstName, String lastName, String email) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.email = email;
//    }

    @Id // setting up the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // set the primary to automatically increase by 1
    @Column(name = "first_name") // to rename the column name; otherwise, it will be firstName;
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email_id",nullable = false,unique = true)
    private String email;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="department_id")
    private Department department;
    // to store the department entity helps increase efficiency and more straight forward

}
