package com.sqreen.notification.manager.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SlackMessage {

    private String channel;
    private String text;
    private String username;

}
