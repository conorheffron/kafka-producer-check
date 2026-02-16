plugins {
    id("java")
    id("com.gradleup.shadow") version "9.3.1"
}

group = "net.ironoc.kafka"
version = "1.0.3"

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
    implementation("org.apache.kafka:kafka-clients:4.1.1")

    // Logging (SLF4J API + Simple Logger)
    implementation("org.slf4j:slf4j-api:2.0.17")
    implementation("org.slf4j:slf4j-simple:2.0.17")

    // JUnit for testing
    testImplementation("org.junit.jupiter:junit-jupiter:6.0.3")
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
