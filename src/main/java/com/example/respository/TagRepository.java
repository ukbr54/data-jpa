package com.example.respository;

import com.example.entity.Tag;
import com.example.projection.TagInfo;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag,Long> {

    /**
     * select tags.id,tag_name,count(*) as count
     * from tags INNER JOIN photo_tags
     * ON tags.id = photo_tags.tag_id
     * group by tags.id
     * order by count desc;
     */
    @Query("SELECT t.id as id ,t.tagName as tagName,COUNT(t.id) as count FROM Tag t JOIN PhotoTag pt ON t.id=pt.tagId GROUP BY t.id ORDER BY COUNT(t.id) DESC")
    List<TagInfo> topFiveMostCommonlyUsedTag(Limit limit);
}
