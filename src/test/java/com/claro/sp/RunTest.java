package com.claro.sp;

import com.claro.sp.model.Properties;
import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features="src/test/resources/features",tags = "@PSP-8191")
public class RunTest {
   @BeforeClass
    public static void beforeTest() {

            //se obtienen las variables parametricas, utilizar las necesarias en cada caso
            Properties.userCcard     = "ghct";//System.getProperty("UserCcard");
            Properties.passwordCcard = "auto_333";//System.getProperty("PasswordCcard");
//
            Properties.userProd      = "ghct";//System.getProperty("UserProd");
            Properties.passwordProd  = "auto_333";//System.getProperty("PasswordProd");
//
            Properties.environment   = "TEST";//System.getProperty("Environment");
            Properties.country       = "AR";//System.getProperty("Country");

            //Se validan que las propiedades no sean nulas o tengan los valores esperados
            //Si se agregan o se eliminan propiedades, se debe actualizar el validate()
            //Properties.validate();
        }
    }

