package com.meet.ck.controller.response;

import com.meet.ck.database.enums.RelationStatus;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RelationshipResponse {
    private Long userToWhoSaidHello;
    private RelationStatus status;
}
