package api.sammycode.rich_and_morty_api_restart.service.impl;

import api.sammycode.rich_and_morty_api_restart.entity.ApprovalStatus;
import api.sammycode.rich_and_morty_api_restart.entity.Character;
import api.sammycode.rich_and_morty_api_restart.entity.CharacterDto;
import api.sammycode.rich_and_morty_api_restart.repository.CharacterRepository;
import api.sammycode.rich_and_morty_api_restart.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApiServiceImpl implements ApiService {
    @Value("${rickmorty.api.url}")
    private String baseUrl;
    @Autowired
    private CharacterRepository characterRepository;
    private final RestTemplate restTemplate = new RestTemplate();

    private Character convertToEntity(CharacterDto characterDTO) {
        Character character = new Character();
        character.setName(characterDTO.getName());
        character.setStatus(characterDTO.getStatus());
        character.setSpecies(characterDTO.getSpecies());
        character.setType(characterDTO.getType());
        character.setGender(characterDTO.getGender());
//        character.setOrigin(characterDTO.getOrigin());
//        character.setLocation(characterDTO.getLocation());
        character.setImage(characterDTO.getImage());
        character.setEpisode(characterDTO.getEpisode());
        character.setUrl(characterDTO.getUrl());
        character.setApprovalStatus(ApprovalStatus.Pending);
        return character;
    }
//     fetching Rick and morty Api to our app
    @Override
    public List<CharacterDto> fetchCharactersFromApi(List<Integer> ids) {
        String url = baseUrl + "/character/" + ids.stream()
                .map(String::valueOf)
                .collect(Collectors
                        .joining(","));
        CharacterDto[] charArray = restTemplate.getForObject(url,CharacterDto[].class);

        List<CharacterDto> characterDTOs = Arrays.asList(charArray);
        List<Character> characters = characterDTOs.stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList());
        characterRepository.saveAll(characters);
        return characterDTOs;
    }
}
