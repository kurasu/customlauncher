
plugins {
    `cpp-application`
}

application {
    //targetMachines.add(machines.macOS.architecture("aarch64"))
    var os = org.gradle.nativeplatform.platform.internal.DefaultNativePlatform.getCurrentOperatingSystem()
    var arch = org.gradle.nativeplatform.platform.internal.DefaultNativePlatform.getCurrentArchitecture()
    val machine = machines.os(os.toFamilyName()).architecture(arch.name)
    targetMachines.add(machine)
}
