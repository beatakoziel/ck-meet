package com.meet.ck.database.entities;

import com.meet.ck.database.enums.Gender;
import com.meet.ck.database.enums.Interests;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 1, max = 30)
    @Column(nullable = false, unique = true, length = 30)
    private String nickname;

    @Column(nullable = false)
    private Date dateOfBirth;

    @Size(max = 500)
    @Column(nullable = false, length = 500)
    private String description;

    @Column(nullable = false)
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private ContactData contactData;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = Interests.class)
    private List<Interests> interests;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = Interests.class)
    private List<Gender> preferredGenderToMeet;

    @Size(min = 18, max = 100)
    @Column(nullable = false, length = 100)
    private Short preferredAgeToMeetFrom;

    @Size(min = 18, max = 100)
    @Column(nullable = false, length = 100)
    private Short preferredAgeToMeetTo;

}
