package com.example.projection;

import org.springframework.beans.factory.annotation.Value;

public interface AveragePhotoPerUser {
    @Value("#{target.avg}")
    Double getAvg();
}
