plugins {
    java
    id("org.springframework.boot") version "4.0.0" apply false
    id("io.spring.dependency-management") version "1.1.7" apply false
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")

    group = "at.spengergasse"
    version = "0.0.1-SNAPSHOT"

    java {
        toolchain {
            languageVersion = JavaLanguageVersion.of(21)
        }
    }

    configurations {
        compileOnly {
            extendsFrom(configurations.annotationProcessor.get())
        }
    }

    repositories {
        mavenCentral()
    }

    dependencies {
        // JPA
        "implementation"("org.springframework.boot:spring-boot-starter-data-jpa")
        "testImplementation"("org.springframework.boot:spring-boot-starter-data-jpa-test")

        // Bean Validation
        "implementation"("org.springframework.boot:spring-boot-starter-validation")

        // Lombok
        "compileOnly"("org.projectlombok:lombok")
        "annotationProcessor"("org.projectlombok:lombok")

        // H2 Database (pinned version)
        "runtimeOnly"("com.h2database:h2:2.2.224")

        // Testing
        "testRuntimeOnly"("org.junit.platform:junit-platform-launcher")
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    // Only aufgabe3 is a bootable application; others are libraries
    tasks.named<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
        enabled = project.name == "aufgabe3"
    }
    tasks.named<Jar>("jar") {
        enabled = project.name != "aufgabe3"
    }
}
