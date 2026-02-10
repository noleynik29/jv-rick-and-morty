package mate.academy.rickandmorty;

import mate.academy.rickandmorty.dto.external.CharacterExternalDto;
import mate.academy.rickandmorty.dto.external.CharacterInputDto;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharactersClient;
import mate.academy.rickandmorty.service.CharactersService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
@Profile("!test")
public class Application implements ApplicationRunner {

    private final CharactersClient charactersClient;

    private final CharactersService charactersService;

    private final CharacterRepository characterRepository;

    public Application(CharactersClient charactersClient,
                       CharactersService charactersService,
                       CharacterRepository characterRepository) {
        this.charactersClient = charactersClient;
        this.charactersService = charactersService;
        this.characterRepository = characterRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        if (characterRepository.count() > 0) {
            return;
        }

        String url = "https://rickandmortyapi.com/api/character/?page=1";
        List<CharacterExternalDto> characters = new ArrayList<>();

        while (url != null) {
            CharacterInputDto result = charactersClient.getCharacter(url);

            if (result == null || result.getInfo() == null) {
                break;
            }

            url = result.getInfo().getNext();
            characters.addAll(result.getResults());

            Thread.sleep(200);
        }
        if (!characters.isEmpty()) {
            charactersService.saveCharacters(characters);
        }
    }
}
