package com.simon.demo;

import com.simon.october.annotation.ComponentScan;
import com.simon.october.core.ApplicationContext;

@ComponentScan({"com.simon.demo"})
public class DemoApplication {
    public static void main(String[] args) {
        ApplicationContext context = new ApplicationContext();
        context.run(DemoApplication.class);
    }
}
