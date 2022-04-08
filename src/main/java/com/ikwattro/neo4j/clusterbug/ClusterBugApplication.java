package com.ikwattro.neo4j.clusterbug;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(proxyBeanMethods = false)
public class ClusterBugApplication {

	public static void main(String[] args) {

		System.exit(
				SpringApplication.exit(
						SpringApplication.run(ClusterBugApplication.class, args)
				)
		);
	}

}
