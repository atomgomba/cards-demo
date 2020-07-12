object Deps {
    object Androidx {
        const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
        const val appcompat = "androidx.appcompat:appcompat:${Versions.androidx}"
        const val preference = "androidx.preference:preference-ktx:${Versions.androidx}"
        const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.androidx}"
        const val viewPager2 = "androidx.viewpager2:viewpager2:${Versions.viewPager2}"
        const val swipeRefresh = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipeRefresh}"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"

        object Lifecycle {
            const val common = "androidx.lifecycle:lifecycle-common-java8:${Versions.lifecycle}"
            const val extensions = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
            const val runtimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleKtx}"
        }
    }

    object Kotlin {
        const val stdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
        const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinCoroutines}"
        const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlinCoroutines}"
    }

    object Dagger {
        const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
        const val compiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
        const val androidSupport = "com.google.dagger:dagger-android-support:${Versions.dagger}"
        const val androidProcessor = "com.google.dagger:dagger-android-processor:${Versions.dagger}"
    }

    object Retrofit {
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val moshiConverter = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    }

    object OkHttp {
        const val OkHttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
        const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"
    }

    object Moshi {
        const val moshi = "com.squareup.moshi:moshi:${Versions.moshi}"
        const val moshiKotlin = "com.squareup.moshi:moshi-kotlin:${Versions.moshi}"
        const val moshiCodegen = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshi}"
    }

    object Test {
        object JUnit {
            const val api = "org.junit.jupiter:junit-jupiter-api:${Versions.junit}"
            const val engine = "org.junit.jupiter:junit-jupiter-engine:${Versions.junit}"
            const val params = "org.junit.jupiter:junit-jupiter-params:${Versions.junit}"
        }
        const val lifecycle = "androidx.arch.core:core-testing:${Versions.archCore}"
        const val mockk = "io.mockk:mockk:${Versions.mockk}"
        const val coroutineTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.kotlinCoroutines}"
    }

    const val material = "com.google.android.material:material:${Versions.material}"
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
    const val jodaTime = "joda-time:joda-time:${Versions.jodaTime}"

    const val firebaseAnalytics = "com.google.firebase:firebase-analytics:${Versions.firebaseAnalytics}"
    const val crashlytics = "com.crashlytics.sdk.android:crashlytics:${Versions.crashlytics}"
}
