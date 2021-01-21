package com.meet.ck.controller.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnumResponse {
    private String key;
    private String value;
    private String iconName;
}
