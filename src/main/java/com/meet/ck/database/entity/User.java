package com.meet.ck.database.entity;

import com.meet.ck.database.enums.Gender;
import com.meet.ck.database.enums.Interest;
import com.meet.ck.database.enums.RegistrationStatus;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 1, max = 30)
    @Column(length = 30)
    private String nickname;

    @Size(min = 1, max = 30)
    @Column(nullable = false, unique = true, length = 30)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Boolean enabled;

    private LocalDate dateOfBirth;

    @Size(max = 500)
    @Column(length = 500)
    private String description;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private ContactData contactData = new ContactData();

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = Interest.class)
    private List<Interest> interests;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private RegistrationStatus status;

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = Gender.class)
    private List<Gender> preferredGenderToMeet;

    @Size(min = 18, max = 100)
    @Column(length = 100)
    private Short preferredAgeToMeetFrom;

    @Size(min = 18, max = 100)
    @Column(length = 100)
    private Short preferredAgeToMeetTo;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Image avatar;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return enabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
