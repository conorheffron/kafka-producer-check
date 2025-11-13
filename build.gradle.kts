plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "net.ironoc.kafka"
version = "1.0.0"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

repositories {
    mavenCentral()
}

dependencies {
    // Apache Kafka client library
    implementation("org.apache.kafka:kafka-clients:3.7.0")

    // Logging (SLF4J API + Simple Logger)
    implementation("org.slf4j:slf4j-api:2.0.12")
    implementation("org.slf4j:slf4j-simple:2.0.12")

    // JUnit for testing
    testImplementation("org.junit.jupiter:junit-jupiter:6.0.1")
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.jar {
    manifest {
        attributes(
            "Main-Class" to "net.ironoc.kafka.ProducerApp"
        )
    }
}

// Configure Shadow JAR
tasks.shadowJar {
    archiveClassifier.set("") // Replace default "-all" with just ".jar"
    mergeServiceFiles() // Handles META-INF/services merging
    manifest {
        attributes(
            "Main-Class" to "net.ironoc.kafka.ProducerApp"
        )
    }
}

// Make `build` also produce the fat JAR
tasks.build {
    dependsOn(tasks.shadowJar)
}
