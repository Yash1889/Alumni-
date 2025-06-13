package com.alumni.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "alumni")
public class Alumni {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Name is required")
    @Column(nullable = false)
    private String name;
    
    @NotNull(message = "Batch year is required")
    @Column(nullable = false)
    private Integer batch;
    
    @NotBlank(message = "Company is required")
    @Column(nullable = false)
    private String company;
    
    @NotBlank(message = "Contact is required")
    @Email(message = "Please provide a valid email address")
    @Column(nullable = false)
    private String contact;
    
    // Default constructor
    public Alumni() {}
    
    // Constructor with parameters
    public Alumni(String name, Integer batch, String company, String contact) {
        this.name = name;
        this.batch = batch;
        this.company = company;
        this.contact = contact;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Integer getBatch() {
        return batch;
    }
    
    public void setBatch(Integer batch) {
        this.batch = batch;
    }
    
    public String getCompany() {
        return company;
    }
    
    public void setCompany(String company) {
        this.company = company;
    }
    
    public String getContact() {
        return contact;
    }
    
    public void setContact(String contact) {
        this.contact = contact;
    }
    
    @Override
    public String toString() {
        return "Alumni{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", batch=" + batch +
                ", company='" + company + '\'' +
                ", contact='" + contact + '\'' +
                '}';
    }
}