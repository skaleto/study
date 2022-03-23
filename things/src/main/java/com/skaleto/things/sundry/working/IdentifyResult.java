package com.skaleto.things.sundry.working;

import lombok.Data;

import java.util.List;

@Data
public class IdentifyResult {

    private Integer orientation;

    private Object details;

    private Integer type;

    private List<Integer> region;
}
