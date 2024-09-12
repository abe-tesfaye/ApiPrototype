package csc340.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
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
    public Charizard getCharizard() throws JsonProcessingException {
        String url = "https://pokeapi.co/api/v2/pokemon/charizard";
        String response = restTemplate.getForObject(url, String.class);

        if (response == null) {
            return new Charizard(); // Return an empty Charizard if the response is null
        }

        JsonNode root = objectMapper.readTree(response);
        Charizard charizard = new Charizard();

        // Set fields if they are present
        charizard.setName(root.path("name").asText("unknown"));
        charizard.setHeight(root.path("height").asInt(0));
        charizard.setWeight(root.path("weight").asInt(0));

        JsonNode abilitiesNode = root.path("abilities");
        if (abilitiesNode.isArray()) {
            String[] abilities = new String[abilitiesNode.size()];
            for (int i = 0; i < abilitiesNode.size(); i++) {
                abilities[i] = abilitiesNode.get(i).path("ability").path("name").asText("unknown");
            }
            charizard.setAbilities(abilities);
        }

        return charizard;
    }
}