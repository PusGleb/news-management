package com.epam.dao;

import com.epam.entity.Tag;

import java.util.List;

/**
 * Created by Hleb_Pus on 11/23/2015.
 */
public interface TagDAO {

    /**
     * Method adds tag in database
     * @param tag
     */
    public long createTag(Tag tag);

    /**
     * Method delete tag from database by tag id
     * @param tagId
     */
    public void deleteTag(long tagId);

    /**
     * Method update tag int database
     * @param tag
     * @param tagId
     */
    public void updateTag(Tag tag, long tagId);

    /**
     * Method returns tag from database by tag id
     * @param tagId
     */
    public Tag getTagById(long tagId);


    public List<Tag> getTagsByNewsId(long newsId);

    public List<Tag> getAllTags();
}
