package com.artists.artlasapi.dto.artists.response;

import com.artists.artlasapi.entity.Museum;
import com.artists.artlasapi.entity.auth.User;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
public class MuseumByCityResponse {
    private User user;
    private String nameCity;
    private List<Museum> museumEntityList;

}
