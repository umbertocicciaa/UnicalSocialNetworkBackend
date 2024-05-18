package com.unicalsocial.backend.message;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/Message")
@AllArgsConstructor
@Tag(name = "Message")
public class MessageRestController {

    private final MessageService messageService;

    @PostMapping(value = "/messages")
    public void publish(@RequestBody MessageRequest messageRequest){
        this.messageService.publish(messageRequest);
    }
}
