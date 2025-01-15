plugins {
    id("java")
    id("application")
    id ("org.openjfx.javafxplugin") version "0.1.0"
}

javafx {
    version = "22.0.1"
    modules = listOf("javafx.controls", "javafx.fxml")
}

application {
    mainClass = ("be.howest.ti.ooansd.exam.ui.fx.StartUp")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("mysql:mysql-connector-java:8.0.33")
    implementation ("org.springframework.security:spring-security-crypto:6.4.1")
}

tasks.test {
    useJUnitPlatform()
}