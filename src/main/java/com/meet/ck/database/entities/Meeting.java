package com.meet.ck.database.entities;

import com.meet.ck.database.enums.MeetingCategory;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Meeting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 1, max = 70)
    @Column(length = 70)
    private String name;

    @Size(max = 500)
    @Column(length = 500)
    private String description;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Image cover;

    private LocalDate date;

    private int maxNumOfParticipants;

    @ManyToOne
    private User host;

    @ManyToMany
    private List<User> participants;

    @Enumerated(EnumType.STRING)
    private MeetingCategory category;

    @OneToMany
    private List<Comment> comments;
}
