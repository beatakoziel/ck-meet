package com.meet.ck.controller.converters;

import com.meet.ck.controller.response.RelationshipResponse;
import com.meet.ck.database.entities.Relationship;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class RelationshipConverter {
    public static RelationshipResponse entityToResponse(Relationship entity) {
        return RelationshipResponse.builder()
                .userToWhoSaidHello(entity.getUserWhoSaidHelloSecond().getId())
                .status(entity.getStatus())
                .build();
    }

}
