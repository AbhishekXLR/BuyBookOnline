package com.rest.books.bootrestbooks.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;


@Getter
@Setter
@NoArgsConstructor
@Entity
public class Customer implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerId;

    @Column(name = "customer_name", nullable = false, length = 25)
    private String name;

    @Column(unique = true,nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @JsonBackReference
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Address> addressList = new ArrayList<>();

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Orders> orders = new HashSet<>();

    @ManyToMany(cascade= CascadeType.ALL,fetch= FetchType.EAGER)
    @JoinTable(name="customer_role", joinColumns = @JoinColumn(name="customer",referencedColumnName ="customerId"), inverseJoinColumns = @JoinColumn(name="role", referencedColumnName = "id"))
    private Set<Role> roles= new HashSet<>();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> authorities = roles.stream().map((role) -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());
        return authorities;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    // getPassword is implemented by @Getter


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {return true;}

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}


