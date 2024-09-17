package api.sammycode.rich_and_morty_api_restart.service.impl;

import api.sammycode.rich_and_morty_api_restart.entity.*;
import api.sammycode.rich_and_morty_api_restart.entity.Character;
import api.sammycode.rich_and_morty_api_restart.repository.CharacterRepository;
import api.sammycode.rich_and_morty_api_restart.service.ApiService;
import api.sammycode.rich_and_morty_api_restart.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CharacterServiceImpl implements CharacterService {
    @Autowired
    private CharacterRepository characterRepository;
    @Autowired
    private ApiService apiService;

//    =====    fetch character Api from Rick and morty Api  =====
    @Override
    public List<CharacterDto>fetchCharacterFromApi(List<Integer> ids) {
        return apiService.fetchCharactersFromApi(ids);
    }


//       ======    Create Character Api   =======

    @Override
    public CharacterDto saveCharacter(CharacterDto.CharacterCreateDTO characterCreateDTO) {
        Character character  = new Character();
        character.setName(characterCreateDTO.getName());
        character.setStatus(characterCreateDTO.getStatus());
        character.setSpecies(characterCreateDTO.getSpecies());
        character.setType(characterCreateDTO.getType());
        character.setGender(characterCreateDTO.getGender());
        character.setOrigin(convertToOrigin(new CharacterDto.CharacterCreateDTO.OriginDto()));
        character.setLocation(convertToLocation(new CharacterDto.CharacterCreateDTO.LocationDto()));
        character.setImage(characterCreateDTO.getImage());
        character.setEpisode(characterCreateDTO.getEpisode());
        character.setUrl(characterCreateDTO.getUrl());
//      ======  ApprovalStatus is set to pending upon creation (in the database it will be pending ======
        character.setApprovalStatus(ApprovalStatus.Pending);
        Character savedCharacter = characterRepository.save(character);
        return convertToDto(savedCharacter);

    }

    private CharacterDto convertToDto(Character character) {
        CharacterDto characterDTO = new CharacterDto();
        characterDTO.setId(character.getId());
        characterDTO.setName(character.getName());
        characterDTO.setStatus(character.getStatus());
        characterDTO.setSpecies(character.getSpecies());
        characterDTO.setType(character.getType());
        characterDTO.setGender(character.getGender());
//        characterDTO.setOrigin(character.getOrigin());
//        characterDTO.setLocation(character.getLocation());
        characterDTO.setImage(character.getImage());
        characterDTO.setEpisode(character.getEpisode());
        characterDTO.setUrl(character.getUrl());
        characterDTO.setCreated(character.getCreated());
        characterDTO.setApprovalStatus(character.getApprovalStatus());
        return characterDTO;
    }
//      ====  Implementation of Origin and location to be part of the table (MANY TO ONE)  ====
    public Origin convertToOrigin( CharacterDto.CharacterCreateDTO.OriginDto originDto ) {
        if (originDto == null){
            return null;
        }
        return new Origin(originDto.getName(), originDto.getUrl());
    }

    public Location convertToLocation(CharacterDto.CharacterCreateDTO.LocationDto locationDto) {
        if (locationDto == null){
            return null;
        }
        return new Location(locationDto.getName(), locationDto.getUrl());
    }

//    =======  Get all character from the Character database  =====
    @Override
    public List<CharacterDto> getAllCharacter() {
        return characterRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
//     ====== Get Character byId from the database === =
    @Override
    public Optional<CharacterDto> getCharacterById(int id) {
        Optional<Character> character = characterRepository.findById(id);
        return character.map(this::convertToDto);
    }

//   ===== Delete character by Id from the database  =====
    @Override
    public void deleteCharacterById(int id) {
        characterRepository.deleteById(id); 
    }
}
