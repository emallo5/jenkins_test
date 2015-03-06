package com.aparcsystems;

import android.test.AndroidTestCase;

import com.aparcsystems.model.Feed;

import java.util.List;

/**
 * Created by emi91_000 on 17/11/2014.
 */
public class DateFormatterTest extends AndroidTestCase {
    private  List<Feed> feeds;

    @Override
    public void setUp() throws Exception {
        super.setUp();
    }


    public void testFormmatter(){
        assertEquals("123456","123456");
    }


}
