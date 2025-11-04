package com.logistics.delivery_optimizer.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AIServiceImpl implements AIService {

    private final ChatClient chatClient;

    @Autowired
    public AIServiceImpl(ChatClient.Builder chatClientBuilder) {

        this.chatClient = chatClientBuilder.build();
    }

    @Override
    public String ask(String prompt) {

        return chatClient.prompt()
                .user(prompt)
                .call()
                .content();
    }
}