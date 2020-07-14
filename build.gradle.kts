import org.jetbrains.kotlin.gradle.dsl.KotlinJvmCompile

plugins {
    id("tanvd.kosogor") version "1.0.7" apply true
    kotlin("jvm") version "1.3.72" apply true
}

allprojects {
    apply {
        plugin("kotlin")
        plugin("tanvd.kosogor")
    }

    repositories {
        jcenter()
    }

    tasks.withType<KotlinJvmCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }
}