package com.artists.artlasapi.dto.artists.response;

import com.artists.artlasapi.entity.auth.User;
import com.artists.artlasapi.entity.ArtWorks;
import java.util.List;
import lombok.*;


@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
public class  ArtWorksByArtistResponse {
    private User user;
    private int idArtist;
    private String nameArtist;
    private List<ArtWorks> worksArts;
}
