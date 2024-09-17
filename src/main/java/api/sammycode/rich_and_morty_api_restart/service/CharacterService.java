package api.sammycode.rich_and_morty_api_restart.service;

import api.sammycode.rich_and_morty_api_restart.entity.CharacterDto;


import java.util.List;
import java.util.Optional;

public interface CharacterService {
    List<CharacterDto>fetchCharacterFromApi(List<Integer> ids);
    CharacterDto saveCharacter(CharacterDto.CharacterCreateDTO characterCreateDTO);
    List<CharacterDto> getAllCharacter();
    Optional<CharacterDto> getCharacterById(int id);
    void deleteCharacterById(int id);

}