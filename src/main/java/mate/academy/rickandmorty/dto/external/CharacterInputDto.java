package mate.academy.rickandmorty.dto.external;

import lombok.Data;

import java.util.List;

@Data
public class CharacterInputDto {
    private CharacterInfoDto info;
    private List<CharacterExternalDto> results;
}
