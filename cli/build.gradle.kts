plugins {
    `maven-publish`
    kotlin("jvm")
}

dependencies {
    api("com.github.ajalt:clikt:${Versions.clikt}")
}

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])

        pom {
            packaging = "jar"
            name.set("OpenRS2 CLI")
            description.set(
                """
                Clikt extensions.
            """.trimIndent()
            )
        }
    }
}
