package com.artists.artlasapi.utils;

import com.artists.artlasapi.entity.Artist;

public class ArtistTestUtils {

    public static Artist buildArtist() {
        return new Artist.ArtistBuilder()
                .setName("Johan Santiago")
                .setLastName("Romero Duarte")
                .setTypeIDE("AB-CD")
                .setNumberIDE("EF-GH")
                .build();
    }
}
