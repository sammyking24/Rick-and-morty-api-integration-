package api.sammycode.rich_and_morty_api_restart.repository;

import api.sammycode.rich_and_morty_api_restart.entity.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Integer> {
}
