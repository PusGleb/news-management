package com.epam.service.impl;

import com.epam.dao.TagDAO;
import com.epam.dao.impl.TagDAOImpl;
import com.epam.entity.Tag;
import com.epam.service.TagService;

import java.util.List;

/**
 * Created by Hleb_Pus on 11/23/2015.
 */
public class TagServiceImpl implements TagService {

    private TagDAO tagDAO;

    public TagServiceImpl() {}

    public TagServiceImpl(TagDAO tagDAO) {
        this.tagDAO = tagDAO;
    }

    /**
     * {@inheritDoc}
     */
    public long createTag(Tag tag) {
        return tagDAO.createTag(tag);
    }

    /**
     * {@inheritDoc}
     */
    public void deleteTag(long tagId) {
        tagDAO.deleteTag(tagId);
    }

    /**
     * {@inheritDoc}
     */
    public void updateTag(Tag tag, long tagId) {
        tagDAO.updateTag(tag, tagId);
    }

    /**
     * {@inheritDoc}
     */
    public Tag getTagById(long tagId) {
        Tag tag = tagDAO.getTagById(tagId);
        return tag;
    }


    public List<Tag> getAllTags(){
        List<Tag> tagList = tagDAO.getAllTags();
        return tagList;
    }

    public TagDAO getTagDAO() {
        return tagDAO;
    }

    public void setTagDAO(TagDAO tagDAO) {
        this.tagDAO = tagDAO;
    }
}

