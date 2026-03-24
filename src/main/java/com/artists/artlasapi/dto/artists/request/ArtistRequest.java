package com.artists.artlasapi.dto.artists.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
public class ArtistRequest {
    @NotBlank
    private String typeIDE;
    @NotBlank
    private String numberIDE;
    @NotBlank
    private String name;
    @NotBlank
    private String lastName;
}
