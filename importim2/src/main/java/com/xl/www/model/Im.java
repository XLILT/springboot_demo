package com.xl.www.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Im {
    private Long live_id;
    private Long sender_id;
    private String user_name;
    private String message_content;
    private Integer create_ts;
    private Integer terminal_type;
}
