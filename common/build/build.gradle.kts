plugins {
    `kotlin-dsl`
}

repositories { mavenCentral() }

java.toolchain.languageVersion = JavaLanguageVersion.of(21)

gradlePlugin {
    plugins {
        create("sodiumpp") {
            id = "sodiumpp"
            implementationClass = "com.dimsteams.sodiumpp.sodiumppGradlePlugin"
        }
    }
}