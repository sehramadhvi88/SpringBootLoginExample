package com.learningtechspring.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
@Entity
@Table(name="user")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="user_id")
	private int id;
	@Column(name="email")
	@Email(message="Please provide a valid email")
	@NotEmpty(message="Please provide an email")
	private String email;
	@Column(name="password")
	@Length(min=5 , message="Password must have atleast 5 characters")
	@NotEmpty(message="Please provide password")
	private String password;
	@Column(name="firstName")
	@NotEmpty(message="Please provide first name")
	private String firstName;
	@Column(name="lastName")
	@NotEmpty(message="Please provide last name")
	private String lastName;
	@Column(name="activeName")
	private int active;
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="user_roles",joinColumns=@JoinColumn(name="user_id"),inverseJoinColumns=@JoinColumn(name="role_id"))
	private Set<Role> roles;
	

}


/* 
 *  @Data	[ Lombok]
 *  @Entity
 *  @Table(name ="")
 *  
 *  @Id
 *  @GeneratedValue()
 *  
 *  @Column(name="")
 *  @NotEmpty [ javax validation]
 *  @Length	[ javax validation]
 *  @Email	[ javax validation]
 *  
 *  
 *  
 *  Example 1:

    // In Customer class:

    @ManyToMany
    @JoinTable(name="CUST_PHONES")
    public Set<PhoneNumber> getPhones() { return phones; }

    // In PhoneNumber class:

    @ManyToMany(mappedBy="phones")
    public Set<Customer> getCustomers() { return customers; }

    Example 2:

    // In Customer class:

    @ManyToMany(targetEntity=com.acme.PhoneNumber.class)
    public Set getPhones() { return phones; }

    // In PhoneNumber class:

    @ManyToMany(targetEntity=com.acme.Customer.class, mappedBy="phones")
    public Set getCustomers() { return customers; }

    Example 3:

    // In Customer class:

    @ManyToMany
    @JoinTable(name="CUST_PHONE",
        joinColumns=
            @JoinColumn(name="CUST_ID", referencedColumnName="ID"),
        inverseJoinColumns=
            @JoinColumn(name="PHONE_ID", referencedColumnName="ID")
        )
    public Set<PhoneNumber> getPhones() { return phones; }

    // In PhoneNumberClass:

    @ManyToMany(mappedBy="phones")
    public Set<Customer> getCustomers() { return customers; }

 *  
 *  
 */
 