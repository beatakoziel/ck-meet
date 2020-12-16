package com.meet.ck.database.entities;

import com.meet.ck.database.enums.RelationStatus;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Relationship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User userWhoSaidHelloFirst;

    @ManyToOne
    private User userWhoSaidHelloSecond;

    @Enumerated(EnumType.STRING)
    private RelationStatus status;
}
