package com.wmltyq.be;
import lombok.Data;

@Data
public class Step {
    private String platform;
    private String action;
    private String path;
    private String value;
    private int wait;
    private String screenshot;
}
