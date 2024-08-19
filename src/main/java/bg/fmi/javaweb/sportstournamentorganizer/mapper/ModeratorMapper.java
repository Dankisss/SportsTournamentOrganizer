package bg.fmi.javaweb.sportstournamentorganizer.mapper;


import bg.fmi.javaweb.sportstournamentorganizer.dto.ModeratorInputDto;
import bg.fmi.javaweb.sportstournamentorganizer.dto.ModeratorOutputDto;
import bg.fmi.javaweb.sportstournamentorganizer.model.Moderator;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ModeratorMapper {
    @Mapping(source = "username", target = "username")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "password", target = "password")
    Moderator mapFromInputDto(ModeratorInputDto moderatorInputDto);

    @Mapping(source="userId", target="id")
    @Mapping(source = "username", target = "username")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "password", target = "password")
    ModeratorOutputDto mapToOutputDto(Moderator moderator);
}
