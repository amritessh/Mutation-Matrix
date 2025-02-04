package com.mutationmatrix.user_management_service.model;

import java.util.Set;

// import org.springframework.data.redis.core.RedisHash;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;;

// @RedisHash
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    private String email;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

}
