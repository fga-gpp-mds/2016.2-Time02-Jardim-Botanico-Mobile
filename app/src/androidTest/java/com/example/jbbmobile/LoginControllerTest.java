package com.example.jbbmobile;

import android.content.Context;
import android.support.test.InstrumentationRegistry;

import com.example.jbbmobile.controller.LoginController;
import com.example.jbbmobile.controller.PreferenceController;
import com.example.jbbmobile.controller.RegisterController;
import com.example.jbbmobile.dao.ExplorerDAO;
import com.example.jbbmobile.model.Explorer;

import org.junit.Before;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.assertEquals;


public class LoginControllerTest {

    private ExplorerDAO explorerDAO;
    private Context context;
    private LoginController loginController;
    private RegisterController registerController;
    @Before
    public void setup(){
        Context context = InstrumentationRegistry.getTargetContext();
        this.context = context;
        explorerDAO = new ExplorerDAO(context);
        explorerDAO.onUpgrade(explorerDAO.getReadableDatabase(),1,1);
        loginController = new LoginController();
        registerController = new RegisterController();
    }

    @Test
    public void testIfLoginOnOnlineDatabaseWasMade() throws Exception{
        registerController.Register("testUser0", "testUser@user.com", "000000", "000000", context);
        while(!registerController.isAction());
        loginController.doLogin("testUser@user.com", "000000", context);
        while(!loginController.isAction());
        assertEquals(true, loginController.isResponse());
    }
}