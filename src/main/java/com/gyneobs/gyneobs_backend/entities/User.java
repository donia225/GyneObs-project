package com.gyneobs.gyneobs_backend.entities;

import java.util.Collection;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.gyneobs.gyneobs_backend.enums.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "users")
@Getter 
@Setter 
@NoArgsConstructor

public class User implements UserDetails {
@Id 
@GeneratedValue(strategy = GenerationType.IDENTITY)
private long id;

@Column (nullable = false)
private String firstname;

@Column (nullable = false)
private String lastname;

@Column (nullable = false, unique = true)
private String username;

@Column(unique = true, length = 100, nullable = false)
private String email;

@Column (nullable = false)
private String password;

@CreationTimestamp
@Column(updatable = false, name = "created_at")
private Date createdAt;

@UpdateTimestamp
@Column(name = "updated_at")
private Date updatedAt;

@Column (name = "is_active", nullable = false)
private boolean active;

@Column(nullable = false)
private boolean enabled = true;

@Enumerated(EnumType.STRING)
@Column(nullable = false)
private Role role;


@Override
public Collection<? extends GrantedAuthority> getAuthorities() {
     throw new UnsupportedOperationException("Not implemented yet");
}

@Override
public String getPassword() {
       return password;
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
        return enabled;
}

public boolean setActivate() {
        return true;
}






}
