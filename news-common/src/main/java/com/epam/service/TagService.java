package com.epam.service;

import com.epam.entity.Tag;

import java.util.List;


public interface TagService {

    /**
     * Method calls the method createTag from TagDAO
     * @param tag
     */
    public long createTag(Tag tag);

    /**
     * Method calls the method deleteTag from TagDAO
     * @param tagId
     */
    public void deleteTag(long tagId);

    /**
     * Method calls the method updateTag from TagDAO
     * @param tag
     * @param tagId
     */
    public void updateTag(Tag tag, long tagId);

    /**
     * Method calls the method getTagById from TagDAO
     * @param tagId
     */
    public Tag getTagById(long tagId);


    public List<Tag> getAllTags();
}
