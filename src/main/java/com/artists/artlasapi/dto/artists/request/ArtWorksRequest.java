package com.artists.artlasapi.dto.artists.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
public class ArtWorksRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String Country;
    @NotBlank
    @Min(0)
    private int fkIdArtist;
}
