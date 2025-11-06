package com.logistics.delivery_optimizer.service.optimizer;

import com.logistics.delivery_optimizer.Model.Delivery;
import com.logistics.delivery_optimizer.Model.Vehicle;
import com.logistics.delivery_optimizer.Model.Warehouse;
import com.logistics.delivery_optimizer.Model.DeliveryHistory;
import com.logistics.delivery_optimizer.repository.DeliveryHistoryRepository;
import com.logistics.delivery_optimizer.service.AIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@ConditionalOnProperty(name = "app.optimization.algorithm", havingValue = "ai")
public class AlOptimizer implements TourOptimizer {

    private final AIService aiService;
    private final DeliveryHistoryRepository deliveryHistoryRepository;
    private final ObjectMapper objectMapper; // Added for JSON parsing

    @Autowired
    public AlOptimizer(AIService aiService,
                       DeliveryHistoryRepository deliveryHistoryRepository,
                       ObjectMapper objectMapper) { // Injected by Spring
        this.aiService = aiService;
        this.deliveryHistoryRepository = deliveryHistoryRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public List<Delivery> calculateOptimalTour(Warehouse startPoint, List<Delivery> deliveries, Vehicle vehicle) {

        List<DeliveryHistory> history = deliveryHistoryRepository.findAll();
        String historyJson = convertToJson(history);
        String deliveriesJson = convertToJson(deliveries);
        String prompt = buildPrompt(startPoint, vehicle, deliveriesJson, historyJson);

        System.out.println("Sending 3 prompts to AI for voting...");

        // 1️⃣ Get 3 AI responses (as you requested)
        String aiResponse1 = aiService.ask(prompt);
        String aiResponse2 = aiService.ask(prompt);
        String aiResponse3 = aiService.ask(prompt);

        System.out.println("AI Responses received.");
        System.out.println("Res 1: " + aiResponse1);
        System.out.println("Res 2: " + aiResponse2);
        System.out.println("Res 3: " + aiResponse3);

        // 2️⃣ Parse JSON responses
        Map<String, Object> res1 = parseJson(aiResponse1);
        Map<String, Object> res2 = parseJson(aiResponse2);
        Map<String, Object> res3 = parseJson(aiResponse3);

        // 3️⃣ Extract delivery ID lists with safe type conversion
        List<Long> order1 = extractOrderList(res1);
        List<Long> order2 = extractOrderList(res2);
        List<Long> order3 = extractOrderList(res3);

        // Check for failed parsing
        if (order1 == null || order2 == null || order3 == null) {
            System.err.println("Failed to parse one or more AI responses. Falling back to default order.");
            return deliveries; // Return original order as fallback
        }

        // 4️⃣ Find the order that appeared at least twice
        List<Long> finalOrder = findMajorityOrder(order1, order2, order3);

        System.out.println("Final agreed order: " + finalOrder);

        // 5️⃣ Reorder the delivery list based on the final order
        // (Optimization: use a Map for faster O(N log N) sorting instead of O(N^2))
        Map<Long, Integer> orderMap = new HashMap<>();
        for (int i = 0; i < finalOrder.size(); i++) {
            orderMap.put(finalOrder.get(i), i);
        }

        List<Delivery> sorted = deliveries.stream()
                .sorted((d1, d2) -> {
                    int i1 = orderMap.getOrDefault(d1.getId(), Integer.MAX_VALUE);
                    int i2 = orderMap.getOrDefault(d2.getId(), Integer.MAX_VALUE);
                    return Integer.compare(i1, i2);
                })
                .collect(Collectors.toList());

        return sorted;
    }

    /**
     * Helper function to parse a JSON string into a Map.
     */
    private Map<String, Object> parseJson(String json) {
        try {
            // Check that the JSON is not empty
            if (json == null || json.trim().isEmpty()) {
                System.err.println("AI response was empty.");
                return Map.of();
            }
            return objectMapper.readValue(json, Map.class);
        } catch (Exception e) {
            System.err.println("Failed to parse AI JSON response: " + json);
            e.printStackTrace();
            return Map.of(); // Return empty Map on failure
        }
    }

    /**
     * Helper function to safely extract a list of IDs and convert to List<Long>.
     */
    @SuppressWarnings("unchecked")
    private List<Long> extractOrderList(Map<String, Object> responseMap) {
        Object rawList = responseMap.get("orderedDeliveryIds");
        if (rawList instanceof List) {
            try {
                // Convert numbers (possibly Integers) to Longs
                return ((List<Object>) rawList).stream()
                        .map(obj -> ((Number) obj).longValue())
                        .collect(Collectors.toList());
            } catch (Exception e) {
                System.err.println("Error converting list of IDs: " + e.getMessage());
                return null;
            }
        }
        return null; // Return null if key not found or not a list
    }

    /**
     * Compare 3 lists and return the one that appears at least twice.
     */
    private List<Long> findMajorityOrder(List<Long> a, List<Long> b, List<Long> c) {
        // Use Objects.equals to safely handle nulls
        if (Objects.equals(a, b) || Objects.equals(a, c)) {
            return a;
        }
        if (Objects.equals(b, c)) {
            return b;
        }

        // Fallback if all responses differ
        System.out.println("AI responses differed. Using first response as fallback.");
        return a;
    }

    /**
     * Helper function to convert objects into a JSON string.
     */
    private String convertToJson(Object data) {
        try {
            // Note: you may need simplified DTOs if entities cause infinite recursion
            return objectMapper.writeValueAsString(data);
        } catch (Exception e) {
            e.printStackTrace();
            return "[]";
        }
    }

    /**
     * Builds the AI prompt based on the project summary requirements.
     */
    private String buildPrompt(Warehouse start, Vehicle v, String deliveriesJson, String historyJson) {
        return "You are an expert in logistics optimization. Your task is to reorder the following list of deliveries in the best possible way to minimize total distance and delivery time."
                + " Starting point (warehouse): " + start.getName() + " (lat: " + start.getLatitude() + ", lon: " + start.getLongitude() + ").\n"
                + " Vehicle: " + v.getType() + " (max weight: " + v.getType().getMaxWeightKg() + "kg, max volume: " + v.getType().getMaxVolumeM3() + "m3).\n"
                + " Deliveries to be optimized (JSON): \n" + deliveriesJson + "\n\n"
                + " To leverage data, here is the historical delivery record (JSON): \n" + historyJson + "\n\n"
                + " Please analyze historical data (e.g., frequent delays in certain areas or preferred delivery times for clients) while considering vehicle constraints."
                + " Return the response strictly in JSON format with two fields: \n"
                + " 1. 'orderedDeliveryIds': an array of delivery IDs (as Long) ordered by the optimal route.\n"
                + " 2. 'recommendations': a string explaining the reason for this order and recommendations based on historical data.";
    }
}
