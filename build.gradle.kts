plugins {
    java
    id("org.springframework.boot") version "3.5.14"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "org.devofblue"
version = "1.0.0"
description =
    "A Task Management Backend is a RESTful API that handles user authentication, project creation, and task operations like creating, assigning, updating status, and setting priorities and deadlines. It manages relationships between users, projects, and tasks, supports role-based access control, and optionally sends notifications when tasks are assigned or updated."

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    
    // Spring Boot
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    
    // PostgreSQL
    runtimeOnly("org.postgresql:postgresql")
    
    // Lombok
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    
    testCompileOnly("org.projectlombok:lombok")
    testAnnotationProcessor("org.projectlombok:lombok")
    
    // JWT
    implementation("io.jsonwebtoken:jjwt-api:0.12.6")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.12.6")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.12.6")
    
    // MapStruct
    implementation("org.mapstruct:mapstruct:1.6.3")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.6.3")
    
    // Lombok + MapStruct Binding
    annotationProcessor("org.projectlombok:lombok-mapstruct-binding:0.2.0")
    
    // Swagger / OpenAPI
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.9")
    
    // Jackson Java Time
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")
    
    // Monitoring
    runtimeOnly("io.micrometer:micrometer-registry-prometheus")
    
    // Development
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    
    // Testing
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
