package com.meet.ck.database.entities;

import com.meet.ck.database.enums.MeetingCategory;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User commentator;

    @Size(max = 500)
    @Column(length = 500)
    private String content;
}
