import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    val kotlinVersion = "1.6.21"
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.serialization") version kotlinVersion
    `java-library`
    `maven-publish`
}

group = "com.hoho"
version = "1.0.3"

publishing {
    repositories {
        maven {
            val repoOwner = System.getenv("REPO_OWNER")
            val actor = System.getenv("ACTOR")
            val token = System.getenv("TOKEN")

            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/$repoOwner/${project.name}")
            credentials {
                username = actor
                password = token
            }
        }
    }
    publications {
        register<MavenPublication>("gpr") {
            from(components["java"])
        }
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform(kotlin("bom")))
    implementation(kotlin("stdlib-jdk8"))

    // kotlinx-serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.3")

    // retrofit
    api("com.squareup.retrofit2:retrofit:2.9.0")
    // retrofit kotlin converter
    api("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0")

    // java-jwt
    implementation("com.auth0:java-jwt:4.0.0")

    testImplementation(kotlin("test"))

    // mockwebserver
    testImplementation("com.squareup.okhttp3:mockwebserver:3.14.9")
}

tasks.test {
    useJUnitPlatform()
}

tasks.jar {
    manifest {
        attributes(
            mapOf(
                "Implementation-Title" to project.name,
                "Implementation-Version" to project.version
            )
        )
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions.freeCompilerArgs += "-opt-in=kotlin.RequiresOptIn"
}

java {
    withSourcesJar()
    withJavadocJar()
}
