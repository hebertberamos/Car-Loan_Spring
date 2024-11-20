package com.personalproject.carloan.entities;

import com.personalproject.carloan.entities.enums.UserRole;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_user")
public class User implements UserDetails, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;
    private String userName;
    @Column(unique = true)
    private String email;
    private String userPassword;
    @Column(unique = true)
    private String cpf;
    private Integer age;
    private UserRole userRole;

    @OneToMany(mappedBy = "user", fetch=FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Notification> notifications = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch=FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rental> rentals = new ArrayList<>();

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();


    public User(){
    }

    public User(Long id, String userName, String email, String userPassword, String cpf, Integer age, UserRole userRole){
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.userPassword = userPassword;
        this.cpf = cpf;
        this.age = age;
        this.userRole = userRole;
    }

    public User (String userName, String email, String userPassword, String cpf, Integer age, UserRole userRole){
        this.userName = userName;
        this.email = email;
        this.userPassword = userPassword;
        this.cpf = cpf;
        this.age = age;
        this.userRole = userRole;
    }

    public String getName() {
        return userName;
    }

    public void setName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return userPassword;
    }

    public void setPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserRole getRole() {
        return userRole;
    }

    public void setRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public List<Rental> getRentals() {
        return rentals;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        switch(this.userRole) {
            case ADMIN:
                return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_EMPLOYEE"), new SimpleGrantedAuthority("ROLE_USER"));
            case MEMBER:
                return List.of(new SimpleGrantedAuthority("ROLE_MEMBER"), new SimpleGrantedAuthority("ROLE_USER"));
            case USER:
                return List.of(new SimpleGrantedAuthority("ROLE_USER"));
            default:
                return null;
        }
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public boolean hasRoleAdmin(){
        if(this.userRole.equals(UserRole.ADMIN)){
            return true;
        }
        return false;
    }
}
