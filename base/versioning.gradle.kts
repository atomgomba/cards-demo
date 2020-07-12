tasks.create("updateBuildId") {
    dependsOn(tasks["build"])

    val pwd = rootProject.projectDir
    val branch = System.getenv("CIRCLE_BRANCH") ?: "git rev-parse --abbrev-ref HEAD".execute(pwd)?.trim()
    val hash = "git rev-parse --short HEAD".execute(pwd)?.trim()
    val buildId = "$hash-$branch"
    val buildNumber = System.getenv("CIRCLE_BUILD_NUM") ?: "N/A"

    println("Build id: $buildId")
    println("Build number: $buildNumber")

    val xmlFile = File(project.projectDir, "src/main/res/values/build_id.xml")
    if (!xmlFile.exists()) {
        xmlFile.createNewFile()
    }
    val xml = """<?xml version="1.0" encoding="utf-8"?>
<resources xmlns:tools="http://schemas.android.com/tools">
    <!-- This is a generated file, please do not edit manually -->
    <string tools:ignore="UnusedResources" name="build_id" translatable="false">$buildId</string>
    <string tools:ignore="UnusedResources" name="build_number" translatable="false">$buildNumber</string>
</resources>
"""
    xmlFile.writeText(xml)

    println("  File: ${xmlFile.path}")
}
