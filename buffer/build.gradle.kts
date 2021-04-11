plugins {
    `maven-publish`
    kotlin("jvm")
}

dependencies {
    api(libs.guice)
    api(libs.netty.buffer)

    implementation(libs.guava)
}

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])

        pom {
            packaging = "jar"
            name.set("OpenRS2 Buffer")
            description.set(
                """
                Provides RuneScape-specific extension methods for Netty's
                ByteBuf type.
            """.trimIndent()
            )
        }
    }
}
