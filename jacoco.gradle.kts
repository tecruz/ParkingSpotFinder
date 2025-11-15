import org.gradle.testing.jacoco.tasks.JacocoReport

val coverageExclusions = listOf(
    // Android
    "**/R.class",
    "**/R$*.class",
    "**/BuildConfig.*",
    "**/Manifest*.*",
    "**/*Test*.*",
    "android/**/*.*",
    // Hilt
    "**/*_HiltModules*.*",
    "**/Hilt_*.class",
    "**/*_Factory*.*",
    "**/*_Provide*Factory*.*",
    "**/*_ViewBinding*.*",
    "**/di/**",
    // Compose
    "**/Composable*.*",
    "**/previews/**",
    "**/*Screen*.*", 
    "**/*Theme*.*",
    // Data classes
    "**/*Entity*.*",
    "**/*Dao*.*",
    "**/MainActivity*.*",
    "**/ParkingSpotApp*.*",
    "**/HiltTestRunner*.*"
)

tasks.register("jacocoTestReport", JacocoReport::class) {
    dependsOn("testDebugUnitTest", "createDebugCoverageReport")

    reports {
        xml.required.set(true)
        html.required.set(true)
    }

    val sourceDirs = files("$projectDir/src/main/java")
    sourceDirectories.setFrom(sourceDirs)

    val classDirs = fileTree("$buildDir/tmp/kotlin-classes/debug") {
        exclude(coverageExclusions)
    }
    classDirectories.setFrom(classDirs)

    executionData.setFrom(fileTree(buildDir) {
        include("**/testDebugUnitTest.exec", "**/outputs/code_coverage/debugAndroidTest/connected/**/*.ec")
    })
}
