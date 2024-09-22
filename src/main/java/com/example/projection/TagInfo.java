package com.example.projection;

import org.springframework.beans.factory.annotation.Value;

public interface TagInfo {
    @Value("#{target.id}")
    Long getId();
    @Value("#{target.tagName}")
    String getTagName();
    @Value("#{target.count}")
    Long getCount();
}
