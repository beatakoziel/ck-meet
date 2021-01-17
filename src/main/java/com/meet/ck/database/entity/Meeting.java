package com.meet.ck.database.entity;

import com.meet.ck.database.enums.MeetingCategory;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;
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

    private LocalDate date;

    private int maxNumOfParticipants;

    @ManyToOne
    private User host;

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<User> participants;

    @Enumerated(EnumType.STRING)
    private MeetingCategory category;

    @OneToMany( orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Comment> comments;
}
