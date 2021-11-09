package com.skaleto.things.sundry.turing.isvp.domain;

import lombok.Data;

import java.util.Date;
import java.util.Map;

@Data
public class CreateDBBody {

    private String name;

    private String desc;

    private int count;

    private Date createDate;

    private Date updateDate;

    private Map<String,String> tags;
}
