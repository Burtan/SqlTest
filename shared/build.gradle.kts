@file:Suppress("UNUSED_VARIABLE")

plugins {
    id("com.android.library")
    kotlin("multiplatform")
    id("app.cash.sqldelight")
}

kotlin {
    targets {
        android()
        jvm()
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                // coroutines
                api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")

                // database extension
                implementation("app.cash.sqldelight:coroutines-extensions:2.0.0-SNAPSHOT")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))

                // coroutines
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")
            }
        }

        val androidMain by getting {
            dependencies {
                // sql
                implementation("app.cash.sqldelight:android-driver:2.0.0-SNAPSHOT")
            }
        }
        val androidUnitTest by getting {
            dependencies {
                implementation("junit:junit:4.13.2")
                implementation("org.robolectric:robolectric:4.9.2")
            }
        }

        val jvmMain by getting {
            dependencies {
                // sql
                implementation("app.cash.sqldelight:sqlite-driver:2.0.0-SNAPSHOT")
            }
        }
        val jvmTest by getting
    }
}

sqldelight {
    databases {
        create("SqlTestDb") {
            packageName.set("app.sqltest.shared")
            generateAsync.set(true)
            verifyMigrations.set(true)
            //deriveSchemaFromMigrations.set(true)
            schemaOutputDirectory.set(buildDir)
        }
    }
}

android {
    namespace = "app.sqltest.shared"
    compileSdk = 33
    defaultConfig {
        minSdk = 26
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}
