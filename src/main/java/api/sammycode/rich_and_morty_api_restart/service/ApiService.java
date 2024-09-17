package api.sammycode.rich_and_morty_api_restart.service;

import api.sammycode.rich_and_morty_api_restart.entity.CharacterDto;

import java.util.List;

public interface ApiService {
    List<CharacterDto> fetchCharactersFromApi(List<Integer> ids);
}
