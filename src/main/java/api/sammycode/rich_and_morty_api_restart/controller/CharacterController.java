package api.sammycode.rich_and_morty_api_restart.controller;

import api.sammycode.rich_and_morty_api_restart.entity.CharacterDto;
import api.sammycode.rich_and_morty_api_restart.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/character")
public class CharacterController {
    @Autowired
    private CharacterService characterService;

    @GetMapping("/fetch")
    public List<CharacterDto> getCharacterFromApi(@RequestParam("ids") List<Integer> ids){
        return characterService.fetchCharacterFromApi(ids);

    }

    @PostMapping
    public CharacterDto createCharacter(@RequestBody CharacterDto
            .CharacterCreateDTO characterCreateDTO){
        return characterService.saveCharacter(characterCreateDTO);
    }

    @GetMapping("/{id}")
    public Optional<CharacterDto> getCharacterById(@PathVariable("id") int id){
        return characterService.getCharacterById(id);
    }
    @GetMapping
    public List<CharacterDto> getAllCharacter(){
        return characterService.getAllCharacter();
    }
    @DeleteMapping("/{id}")
    public void deleteCharacterById(@PathVariable int id){
        characterService.deleteCharacterById(id);
    }
}
