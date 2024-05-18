package com.unicalsocial.backend.message;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MessageServiceImpl implements MessageService {
    @Override
    public void publish(MessageRequest message) {
    }
}
