package com.wf.ew;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.wf.jwtp.configuration.EnableJwtPermission;

@EnableJwtPermission
@SpringBootApplication
public class HeasyWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(HeasyWebApplication.class, args);
    }
}
