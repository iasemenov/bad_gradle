plugins {
    application
}

apply {
    plugin("java")
}

application {
    mainClassName = "io.ktor.server.netty.EngineMain"
}

repositories {
    maven { url = uri("https://repo.labs.intellij.net/intdev") }
    maven { url = uri("https://kotlin.bintray.com/ktor") }
    maven { url = uri("https://kotlin.bintray.com/kotlinx") }
}

val kotlinVersion: String by rootProject
val ktorVersion: String by project
val exposedVersion: String by project
val log4jVersion: String by project
val testContainersVersion: String by project

dependencies {
    compile("org.jetbrains.kotlin", "kotlin-stdlib-jdk8", kotlinVersion)

    implementation("org.jetbrains.exposed", "exposed-core", exposedVersion)
    implementation("org.jetbrains.exposed", "exposed-jdbc", exposedVersion)
    implementation("org.jetbrains.exposed", "exposed-dao", exposedVersion)
    implementation("org.jetbrains.exposed", "exposed-java-time", exposedVersion)
    implementation("com.zaxxer", "HikariCP", "3.3.1")
    implementation("mysql", "mysql-connector-java", "8.0.16")
    implementation("org.postgresql", "postgresql", "42.2.6")
    implementation("com.microsoft.sqlserver", "mssql-jdbc", "7.2.2.jre8")
    
    compile("org.jetbrains.intdev.api", "client-api", "2019.07-SNAPSHOT")

    compile("org.plumelib", "plume-util", "1.0.10")

    compile("io.ktor", "ktor-server-netty", ktorVersion)
    compile("io.ktor", "ktor-server-core", ktorVersion)

    api("org.slf4j", "slf4j-api", "1.7.26")
    implementation("org.apache.logging.log4j", "log4j-slf4j-impl", log4jVersion)
    implementation("org.apache.logging.log4j", "log4j-api", log4jVersion)
    implementation("org.apache.logging.log4j", "log4j-core", log4jVersion)

    testApi("io.ktor", "ktor-server-tests", ktorVersion) {
        exclude("ch.qos.logback")
    }
    
    testImplementation("org.junit.jupiter", "junit-jupiter-api", "5.6.0")
    testRuntimeOnly("org.junit.jupiter", "junit-jupiter-engine", "5.6.0")

    testApi("org.testcontainers", "junit-jupiter", testContainersVersion)
    testImplementation("org.testcontainers", "testcontainers", testContainersVersion)
    testImplementation("org.testcontainers", "mysql", testContainersVersion)
    testImplementation("org.testcontainers", "postgresql", testContainersVersion)
    testImplementation("org.testcontainers", "mssqlserver", testContainersVersion)

}

tasks.withType<Test> {
    useJUnitPlatform()
}