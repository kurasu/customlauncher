plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

java.sourceCompatibility = JavaVersion.VERSION_16
java.targetCompatibility = JavaVersion.VERSION_16

open class CustomJavaExec : JavaExec()
{
    override fun getJavaVersion(): JavaVersion {
        return JavaVersion.VERSION_16
    }
}

fun getLauncher(): String {
    var launcherTask = project("launcher").getTasksByName("linkDebug", false).first()
    for (f in launcherTask.outputs.files.files)
    {
        if (f.name.contains("launcher"))
            return f.absolutePath
    }
    throw Exception("Couldn't find custom launcher executable")
}

tasks.register<CustomJavaExec>("LaunchWithCustomLauncher")
{
    group = "run"
    classpath = sourceSets.main.get().runtimeClasspath
    mainClass.set("org.example.Main")

    setExecutable(file(getLauncher()))
    dependsOn("launcher::build")
}