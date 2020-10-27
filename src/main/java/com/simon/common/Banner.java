package com.simon.common;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Banner {

    // banner made by https://www.bootschool.net/ascii
    public static final String DEFAULT_BANNER_FILE = "banner.txt";

    public static void print() {
        URL url = Thread.currentThread().getContextClassLoader().getResource(DEFAULT_BANNER_FILE);
        if (null != url) {
            try {
                Path path = Paths.get(url.toURI());
                Files.lines(path).forEach(System.out::println);
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        }
    }
}
