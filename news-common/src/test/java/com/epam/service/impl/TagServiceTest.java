package com.epam.service.impl;

import com.epam.dao.TagDAO;
import com.epam.entity.Tag;
import com.epam.service.impl.TagServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;

import static org.mockito.Mockito.verify;

/**
 * Created by Hleb_Pus on 11/23/2015.
 */
@ContextConfiguration(locations = {"classpath:testContext.xml"})
public class TagServiceTest {

    @Mock
    private TagDAO tagDAO;

    @InjectMocks
    private TagServiceImpl tagService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddTag(){

        Tag tag = new Tag();
        tagService.createTag(tag);
        verify(tagDAO).createTag(tag);
    }

    @Test
    public void testGetTagById(){

        long tagId = 1;
        tagService.getTagById(tagId);
        verify(tagDAO).getTagById(tagId);
    }

    @Test
    public void testUpdateTag(){

        long tagId = 1;
        Tag tag = new Tag();
        tagService.updateTag(tag,tagId);
        verify(tagDAO).updateTag(tag,tagId);
    }

    @Test
    public void testDeleteTag(){

        long tagId = 1;
        tagService.deleteTag(tagId);
        verify(tagDAO).deleteTag(tagId);
    }
}
