package com.zup.testezuplucas;

import com.zup.testezuplucas.home.HomeActivity;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class HomeActivityTest {

    @Test
    public void HomeActivity_ShouldNoy_be_Null(){
        HomeActivity activity = Mockito.spy(HomeActivity.class);
        Assert.assertNotNull(activity);
    }

}
