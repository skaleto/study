package com.skaleto.things.sundry.working;

import lombok.Data;

import java.util.List;

@Data
public class IocrscData {

    private Integer result;

    private String sha1;

    private String extra;

    private Integer time_cost;

    private List<IdentifyResult> identify_results;

    private String id;

    private String message;

    private String version;

    private Long timestamp;
}
