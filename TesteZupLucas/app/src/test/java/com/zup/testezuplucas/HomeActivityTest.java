package com.zup.testezuplucas;

import com.zup.testezuplucas.home.HomeActivity;
import com.zup.testezuplucas.model.User;
import com.zup.testezuplucas.util.ValueFormatter;

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

    @Test
    public void CurrencyValue_Should_be_WellFormatted(){
        Assert.assertEquals("R$ 300,00", ValueFormatter.formatCurrency(300.0f));
        Assert.assertEquals("R$ 2.800,00", ValueFormatter.formatCurrency(2800.0000f));
        Assert.assertEquals("-R$ 1,50", ValueFormatter.formatCurrency(-1.5f));
    }

    @Test
    public void DateValue_Should_be_WellFormatted(){
        Assert.assertEquals("17/12/1992", ValueFormatter.formatDate("1992-12-17"));
    }

    @Test
    public void UserAccountNumber_Should_be_WellConstructed(){
        User user = new User(123, "TestUser","2050", "012314564", -500f);
        Assert.assertEquals("2050 / 01.231456-4", ValueFormatter.formatUserAccount(user));
    }

}
