package csc340.demo;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/pokemon")
public class CharizardController {

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("/charizard")
    public Charizard getCharizard() {
        String url = "https://pokeapi.co/api/v2/pokemon/charizard";
        String response = restTemplate.getForObject(url, String.class);

        // Return an empty Charizard if the response is null
        if (response == null) {
            return new Charizard();
        }

        // Parse the response and build the Charizard object
        JsonNode root = null;
        Charizard charizard = new Charizard();

        if (response != null && !response.isEmpty()) {
            root = parseJson(response);

            if (root != null) {
                // Set fields if they are present
                if (root.has("name")) {
                    charizard.setName(root.path("name").asText("unknown"));
                }
                if (root.has("height")) {
                    charizard.setHeight(root.path("height").asInt(0));
                }
                if (root.has("weight")) {
                    charizard.setWeight(root.path("weight").asInt(0));
                }

                JsonNode abilitiesNode = root.path("abilities");
                if (abilitiesNode.isArray()) {
                    String[] abilities = new String[abilitiesNode.size()];
                    for (int i = 0; i < abilitiesNode.size(); i++) {
                        JsonNode abilityNode = abilitiesNode.get(i).path("ability");
                        if (abilityNode.has("name")) {
                            abilities[i] = abilityNode.path("name").asText("unknown");
                        }
                    }
                    charizard.setAbilities(abilities);
                }
            }
        }

        return charizard;
    }

    // Method to parse JSON string to JsonNode
    private JsonNode parseJson(String response) {
        if (response != null && !response.isEmpty()) {
            try {
                return objectMapper.readTree(response);
            } catch (Exception e) {
                // Log the error (consider using a logging framework)
                System.err.println("Error parsing JSON: " + e.getMessage());
            }
        }
        return null;
    }
}