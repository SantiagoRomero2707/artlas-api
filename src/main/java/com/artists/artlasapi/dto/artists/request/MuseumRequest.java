package com.artists.artlasapi.dto.artists.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
public class MuseumRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String Country;
    @NotBlank
    private String city;
    @NotBlank
    private String address;
}
