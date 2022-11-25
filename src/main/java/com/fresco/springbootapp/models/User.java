package com.fresco.springbootapp.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    @Column(name = "USER_NAME", unique = true)
    private String username;
    private String password;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
//    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @JoinTable(name = "UserRole", joinColumns = @JoinColumn(name = "userId", referencedColumnName = "userId"), inverseJoinColumns = @JoinColumn(name = "roleId", referencedColumnName = "roleId"))
//    @Enumerated(EnumType.STRING)
    private Set<Role> role;

}
