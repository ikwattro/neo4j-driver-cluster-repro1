package com.ikwattro.neo4j.clusterbug;

import org.neo4j.driver.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.stereotype.Component;


@Component
public class AppRunner implements CommandLineRunner, ExitCodeGenerator {

    @Override
    public void run(String... args) throws Exception {
        try (Driver driver = GraphDatabase.driver("neo4j://core1:7687", AuthTokens.basic("neo4j", "password"))) {
            try (Session session = driver.session(SessionConfig.forDatabase("system"))) {
                session.run("DROP DATABASE foo IF EXISTS").consume();
                session.run("CREATE DATABASE foo IF NOT EXISTS").consume();
                session.run("SHOW DATABASE foo").list().forEach(record -> {
                    System.out.println(record.asMap());
                });
            }

            try (Session session = driver.session(SessionConfig.forDatabase("foo"))) {
                session.run("CREATE INDEX ON :Person(name)");
            }
        }
    }

    @Override
    public int getExitCode() {
        return 0;
    }
}
