plugins {
	id("aoc.kotlin-application-conventions")
}

dependencies {
	implementation(project(":utilities"))
}

application {
	mainClass.set("aoc2020.MainKt")
}

