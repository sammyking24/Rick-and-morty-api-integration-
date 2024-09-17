package api.sammycode.rich_and_morty_api_restart.service.impl;

import api.sammycode.rich_and_morty_api_restart.entity.ApprovalStatus;
import api.sammycode.rich_and_morty_api_restart.entity.Character;
import api.sammycode.rich_and_morty_api_restart.repository.CharacterRepository;
import api.sammycode.rich_and_morty_api_restart.service.JsonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class JsonServiceImpl implements JsonService {

    @Value("${jsonblob.api.url}")
    private String BaseUrl;
    @Autowired
    private CharacterRepository characterRepository;

    private final RestTemplate restTemplate = new RestTemplate();


    @Override
    public String submitCharForApproval(int id) {
        Optional<Character> charOptional = characterRepository.findById(id);
        if (!charOptional.isPresent()) {
            throw new RuntimeException("NOT FOUND");
        }
        Character character = charOptional.get();
        HttpEntity<Character> request = new HttpEntity<>(character);
        ResponseEntity<String> response = restTemplate.postForEntity(BaseUrl, request, String.class);

        HttpHeaders headers = response.getHeaders();
        String location = headers.getLocation().toString();

        String blobId = location.substring(location.lastIndexOf('/') + 1);
        return blobId;
    }

    @Override
    public Character getCharFromJson(String blobId) {
        String url = BaseUrl + "/" + blobId;
        ResponseEntity<Character> response = restTemplate.getForEntity(url, Character.class);
        return response.getBody();
    }
    @Override
    public void updateCharApprovalStatus(int id, ApprovalStatus approvalStatus) {
        Character character = characterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CHARACTER NOT FOUND"));
        if (character.getApprovalStatus()
                == ApprovalStatus.Pending && approvalStatus == ApprovalStatus.Approved ) {
            character.setApprovalStatus(approvalStatus);
            characterRepository.save(character);

        }else {
            throw new RuntimeException("InValid Status Code");

        }
    }
}

//} else if (character.getApprovalStatus()
//            == ApprovalStatus.Pending && approvalStatus == ApprovalStatus.Rejected) {
//        character.setApprovalStatus(approvalStatus);
//            characterRepository.save(character);
//
//        }