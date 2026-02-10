package mate.academy.rickandmorty.repository;

import mate.academy.rickandmorty.entity.Character;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterRepository extends JpaRepository<Character, Long> {
    List<Character> findByNameContainingIgnoreCase(String name);
}
