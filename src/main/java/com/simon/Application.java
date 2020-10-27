package com.simon;

import com.simon.core.ApplicationContext;
import com.simon.server.HttpServer;

public class Application {
    public static void main(String[] args) {
        ApplicationContext context = new ApplicationContext();
        context.run(Application.class);
    }
}
