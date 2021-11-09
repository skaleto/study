package com.skaleto.things.sundry.turing.uts.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UTSRequest {

    private String sceneKey;

    private String text;

    private String uid;

    private String stype;

    private String wtime;

    private boolean force;
}
