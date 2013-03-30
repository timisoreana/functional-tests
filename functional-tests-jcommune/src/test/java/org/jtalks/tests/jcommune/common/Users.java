/**
 * Copyright (C) 2011  JTalks.org Team
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */

package org.jtalks.tests.jcommune.common;

import org.jtalks.tests.jcommune.exceptions.CouldNotOpenPageException;
import org.jtalks.tests.jcommune.exceptions.CouldNotSignInUserException;
import org.jtalks.tests.jcommune.exceptions.CouldNotSignUpUserException;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.StringHelp;

import static org.jtalks.tests.jcommune.common.JCommuneSeleniumTest.*;

/**
 * Contain user actions like sign in, sign out etc.
 *
 * @author Guram Savinov
 */
public class Users {

    private static final String EMAIL_ACTIVATION_INFO = "На указанный e-mail отправлено письмо со ссылкой для " +
            "подтверждения регистрации.";

    /**
     * Sign in user by dialog. Action should by started from any page of JCommune.
     *
     * @param username the username
     * @param password the password
     * @throws org.jtalks.tests.jcommune.exceptions.CouldNotOpenPageException
     * @throws org.jtalks.tests.jcommune.exceptions.CouldNotSignInUserException
     * @return the {@code User} instance
     */
    public static User signInByDialog(String username, String password)
            throws CouldNotOpenPageException, CouldNotSignInUserException {
        mainPage.getLoginLink().click();
        try {
            driver.findElement(By.id(signInPage.signInDialogFormSel));
        } catch (NoSuchElementException e) {
            throw new CouldNotOpenPageException("sign in dialog form", e);
        }

        User user = new User(username, password);
        signInPage.getUsernameField().sendKeys(user.getUsername());
        signInPage.getPasswordField().sendKeys(user.getPassword());
        signInPage.getSubmitButton().click();
        try {
            driver.findElement(By.xpath(mainPage.currentUsernameLinkSel));
        } catch (NoSuchElementException e) {
            throw new CouldNotSignInUserException(user, e);
        }

        return user;
    }

    /**
     * Sign in user by page. Action should be started from sign in page of JCommune.
     *
     * @param username the username
     * @param password the password
     * @throws org.jtalks.tests.jcommune.exceptions.CouldNotOpenPageException
     * @throws org.jtalks.tests.jcommune.exceptions.CouldNotSignInUserException
     * @return the {@code User} instance
     */
    public static User signInByPage(String username, String password)
            throws CouldNotOpenPageException, CouldNotSignInUserException {
        try {
            driver.findElement(By.id(signInPage.signInPageFormSel));
        } catch (NoSuchElementException e) {
            throw new CouldNotOpenPageException("sign in page form", e);
        }

        User user = new User(username, password);
        signInPage.getUsernameField().sendKeys(user.getUsername());
        signInPage.getPasswordField().sendKeys(user.getPassword());
        signInPage.getSubmitButtonAfterRegistration().click();
        try {
            driver.findElement(By.xpath(mainPage.currentUsernameLinkSel));
        } catch (NoSuchElementException e) {
            throw new CouldNotSignInUserException(user, e);
        }

        return user;
    }

    /**
     * Sign up new user with random data by dialog. Action should be started from any page of JCommune.
     *
     * @throws CouldNotOpenPageException
     * @throws CouldNotSignUpUserException
     * @return the {@code User} instance
     */
    public static User signUpNewUserByDialog() throws CouldNotOpenPageException, CouldNotSignUpUserException {
        // Check opening sign up form
        signUpPage.getSignUpButton().click();
        try {
            driver.findElement(By.id(signUpPage.signUpFormSel));
        } catch (NoSuchElementException e) {
            throw new CouldNotOpenPageException("sign up form", e);
        }

        // JCommune add captcha value to session on image request. Because HtmlUnit doesn't load images, captcha image
        // should be requested manually.
        if (selDriverType.equals("html")) {
            driver.get(signUpPage.getCaptchaImage().getAttribute("src"));
            driver.navigate().back();
            signUpPage.getSignUpButton().click();
        }

        // Fill sign up form and submit
        User user = new User(StringHelp.getRandomString(8), StringHelp.getRandomString(9), StringHelp.getRandomEmail());
        signUpPage.getUsernameField().sendKeys(user.getUsername());
		signUpPage.getEmailField().sendKeys(user.getEmail());
        signUpPage.getPasswordField().sendKeys(user.getPassword());
        signUpPage.getPasswordConfirmField().sendKeys(user.getPassword());
        signUpPage.getCaptchaField().sendKeys(validCaptchaValue);
        signUpPage.getSubmitButton().submit();
        new WebDriverWait(driver, 10).until(ExpectedConditions
                .textToBePresentInElement(By.className("modal-body"), EMAIL_ACTIVATION_INFO));
        signUpPage.getOkButtonOnInfoWindow().click();

        // Waiting for message with activation link
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
        }

        return user;
    }

    /**
     * Open activation link from message sent by JCommune to confirm user registration.
     *
     * @param email the user email
     */
    public static void openActivationLink(String email) {
        String activationLink = mailtrapServer.getActivationLink(email);
        driver.get(activationLink);
    }
}