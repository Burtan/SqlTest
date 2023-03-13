plugins {
    //trick: for the same plugin versions in all submodules
    id("com.android.application").version("7.4.2").apply(false)
    id("com.android.library").version("7.4.2").apply(false)
    kotlin("android").version("1.8.10").apply(false)
    kotlin("multiplatform").version("1.8.10").apply(false)
    id("app.cash.sqldelight").version("2.0.0-SNAPSHOT").apply(false)
}

allprojects {
    repositories {
        google()
        mavenCentral()
        // sonatype needed for snapshots? e.g. sqldelight
        maven("https://oss.sonatype.org/content/repositories/snapshots")
    }
}
