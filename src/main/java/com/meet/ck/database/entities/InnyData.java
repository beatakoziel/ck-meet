package com.meet.ck.database.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InnyData {
    @Id
    @Column(unique = true)
    private String email;

    private String phoneNumber;

    private String linkToFacebookProfile;
}
