plugins {
    id("aoc.kotlin-application-conventions")
}

dependencies {
    implementation(project(":utilities"))
}

application {
    mainClass.set("aoc.app.AppKt")
}