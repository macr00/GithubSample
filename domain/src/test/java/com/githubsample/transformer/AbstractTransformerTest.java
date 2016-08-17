package com.githubsample.transformer;

import com.fernandocejas.arrow.collections.Lists;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Created by Rory_McCormack on 17/08/2016.
 */
public class AbstractTransformerTest {

    private AbstractTransformer<Object, Object> abstractTransformer;

    @Before
    public void setUp() throws Exception {
        abstractTransformer = new AbstractTransformer<Object, Object>() {
            @Override
            public Object transform(Object o) {
                return null;
            }
        };
    }

    @Test
    public void shouldReturnEmptyList_WhenListIsEmpty() {
        Assert.assertTrue(abstractTransformer.transform(Lists.newArrayList()).isEmpty());
    }

    @Test
    public void shouldReturnEmptyList_WhenListIsNull() {
        Assert.assertTrue(abstractTransformer.transform(null).isEmpty());
    }

    @Test
    public void shouldFilterOutNullElements() {
        final List<Object> objectList = Lists.newArrayList(new Object(), null, new Object());
        final List<Object> transformedList = abstractTransformer.transform(objectList);

        Assert.assertEquals(objectList.size() - 1, transformedList.size());
    }

    @Test
    public void shouldReturnSameSize() {
        final List<Object> objectList = Lists.newArrayList(new Object(), new Object());
        final List<Object> transformedList = abstractTransformer.transform(objectList);

        Assert.assertEquals(objectList.size(), transformedList.size());
    }

}