package com.skaleto.things.sundry.turing.tts.v2.domain.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Business {

    private String ent;

    private String vcn;

    private String auf;

    private int volume;

    private int speed;
}
