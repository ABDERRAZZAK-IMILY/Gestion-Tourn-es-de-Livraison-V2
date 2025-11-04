package com.logistics.delivery_optimizer.Controller;

import com.logistics.delivery_optimizer.dto.AIPromptDTO;
import com.logistics.delivery_optimizer.service.AIService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AIController {

    private final AIService aiService;

    public AIController(AIService aiService) {
        this.aiService = aiService;
    }

    @PostMapping("/ask")
    public String ask(@RequestBody AIPromptDTO promptDTO) {
        return aiService.ask(promptDTO.getPrompt());
    }
}