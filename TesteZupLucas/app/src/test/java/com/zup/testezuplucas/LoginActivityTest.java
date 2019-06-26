package com.zup.testezuplucas;


import com.zup.testezuplucas.login.LoginActivity;
import com.zup.testezuplucas.login.LoginController;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class LoginActivityTest {

    @Test
    public void LoginActivity_ShouldNoy_be_Null() {
        LoginActivity activity = Mockito.spy(LoginActivity.class);
        Assert.assertNotNull(activity);
    }

    @Test
    public void user_ShouldBe_Valid() {
        LoginActivity activity = Mockito.mock(LoginActivity.class);
        LoginController controller = new LoginController(activity);
        Assert.assertTrue(controller.isUserValid("39949462800"));
        Assert.assertFalse(controller.isUserValid("3994800"));
        Assert.assertTrue(controller.isUserValid("ld.rodrigues@hotmail.com"));
        Assert.assertFalse(controller.isUserValid("ld.rodrigues"));
    }

    @Test
    public void password_ShouldBe_Valid() {
        LoginActivity activity = Mockito.mock(LoginActivity.class);
        LoginController controller = new LoginController(activity);
        Assert.assertFalse(controller.isPasswordValid("hvdyuvweyivf"));
        Assert.assertFalse(controller.isPasswordValid("dede1568"));
        Assert.assertFalse(controller.isPasswordValid("AGYD4516181hduibwy"));
        Assert.assertTrue(controller.isPasswordValid("Lucas@123_ABC"));
    }

}
