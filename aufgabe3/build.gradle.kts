// Aufgabe 3: REST API Layer

dependencies {
    implementation(project(":aufgabe2"))
    implementation("org.springframework.boot:spring-boot-starter-webmvc")
    testImplementation("org.springframework.boot:spring-boot-starter-webmvc-test")
    implementation("org.springframework.boot:spring-boot-h2console")
    testImplementation("io.rest-assured:rest-assured:6.0.0")
}
