plugins {
	id("org.jetbrains.kotlin.jvm")
}

repositories {
	mavenCentral()
}

dependencies {
	testImplementation("org.hamcrest:hamcrest:2.2")
	testImplementation("org.junit.jupiter:junit-jupiter:5.9.1")
}

tasks.named<Test>("test") {
	useJUnitPlatform()
}
