package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mate.academy.rickandmorty.dto.internal.CharacterOutputDto;
import mate.academy.rickandmorty.service.CharactersService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Character management", description = "Endpoint for managing characters")
@RestController
@RequestMapping("/characters")
public class CharactersController {
    private final CharactersService charactersService;

    public CharactersController(CharactersService charactersService) {
        this.charactersService = charactersService;
    }

    @Operation(summary = "Get random character", description = "Get random character")
    @GetMapping("/random")
    public CharacterOutputDto getRandomCharacter() {
        return charactersService.getRandomCharacter();
    }

    @Operation(summary = "Get character by name",
            description = "Part of character name (case-insensitive)")
    @GetMapping
    public List<CharacterOutputDto> getCharacterByName(@RequestParam String name) {
        return charactersService.getCharacterByName(name);
    }
}
