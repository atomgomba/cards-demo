plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}

android {
    compileSdkVersion(Versions.compileSdk)

    sourceSets {
        getByName("main") {
            java.srcDir("src/main/kotlin")
        }
        getByName("test") {
            java.srcDir("src/test/kotlin")
        }
    }

    flavorDimensions("default")

    defaultConfig {
        minSdkVersion(Versions.minSdk)
        targetSdkVersion(Versions.targetSdk)

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }
}

dependencies {
    implementation(Deps.Kotlin.stdLib)
    implementation(Deps.Kotlin.coroutinesCore)
    implementation(Deps.Kotlin.coroutinesAndroid)

    implementation(Deps.Androidx.coreKtx)
    implementation(Deps.Androidx.appcompat)
    implementation(Deps.Androidx.Lifecycle.common)
    implementation(Deps.Androidx.Lifecycle.extensions)
    implementation(Deps.Androidx.Lifecycle.runtimeKtx)

    implementation(Deps.Dagger.dagger)
    kapt(Deps.Dagger.compiler)
    implementation(Deps.Dagger.androidSupport)
    kapt(Deps.Dagger.androidProcessor)

    implementation(Deps.timber)

    testImplementation(Deps.Test.JUnit.api)
    testRuntimeOnly(Deps.Test.JUnit.engine)
    testImplementation(Deps.Test.JUnit.params)
    testImplementation(Deps.Test.mockk)
    testImplementation(Deps.Test.lifecycle)
}
