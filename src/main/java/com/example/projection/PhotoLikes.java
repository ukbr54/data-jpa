package com.example.projection;

import java.time.LocalDateTime;

public record PhotoLikes(Long photoId,
                         String username,
                         String imageUrl,
                         Long count) {

}
