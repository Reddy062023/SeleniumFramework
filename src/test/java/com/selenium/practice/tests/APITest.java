package com.selenium.practice.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.HttpURLConnection;
import java.net.URL;

public class APITest {

    @Test
    public void validateStatusCode() throws Exception {

        URL url = new URL("https://jsonplaceholder.typicode.com/posts");
        HttpURLConnection connection =
                (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");
        int status = connection.getResponseCode();

        Assert.assertEquals(status, 200);
    }
}
