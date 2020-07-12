plugins {
    `kotlin-dsl`
}

sourceSets {
    main {
        java.srcDir("src/main/kotlin")
    }
}

repositories {
    // The org.jetbrains.kotlin.jvm plugin requires a repository
    // where to download the Kotlin compiler dependencies from.
    jcenter()
}
