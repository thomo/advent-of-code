plugins {
    id("aoc.kotlin-application-conventions")
}

dependencies {
    implementation(project(":utilities"))
}

application {
    // Define the main class for the application.
    mainClass.set("aoc.app.AppKt")
}
