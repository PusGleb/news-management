package com.epam.dao.impl;

import com.epam.dao.TagDAO;
import com.epam.entity.Tag;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileInputStream;

/**
 * Created by Hleb_Pus on 11/23/2015.
 */
public class TagDAOTest extends AbstractTest {

    //@Autowired
   // private TagDAOImpl tagDAO;

    private TagDAO tagDAO = (TagDAO) context.getBean("tagDAO");

    @Test
    public void testCreateTag() throws Exception {

        Tag tag = returnTagFromTable();
        long tagId = tagDAO.createTag(tag);
        Tag expTag = tagDAO.getTagById(tagId);

        assertEquals(tag, expTag);
    }

    @Test
    public void testGetTagById() throws Exception {

        Tag tag = returnTagFromTable();
        Tag expTag = tagDAO.getTagById(tag.getId());
        assertEquals(tag,expTag);
    }

    @Test
    public void testDeleteTag() throws Exception {

        Tag tag = returnTagFromTable();
        tagDAO.deleteTag(tag.getId());
        assertNull(tagDAO.getTagById(tag.getId()));
    }

    @Test
    public void testUpdateTag() throws Exception {

        Tag tag = returnTagFromTable();
        tagDAO.updateTag(tag, tag.getId());
        Tag expTag = tagDAO.getTagById(tag.getId());

        assertEquals(tag,expTag);

    }

    public Tag returnTagFromTable() throws Exception{

        IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new FileInputStream("src/main/resources/expectedDataSource.xml"));
        ITable expectedTable = expectedDataSet.getTable("tag");
        String expId = (String) expectedTable.getValue(0,"tag_id");
        String expName = (String) expectedTable.getValue(0, "tag_name");
        Tag tag = new Tag(Long.parseLong(expId),expName);

        return tag;
    }
}
