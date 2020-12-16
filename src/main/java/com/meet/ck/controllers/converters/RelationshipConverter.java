package com.meet.ck.controllers.converters;

import com.meet.ck.controllers.response.RelationshipResponse;
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
