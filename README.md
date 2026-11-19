# ENDER CLIENT

A client-side Minecraft Java utility client for Fabric 26.1.

## First development build

The initial foundation includes:
- A purple/black ENDER HUD overlay
- Player coordinates and armor value
- Right Shift to toggle the overlay

The project is structured for additional visual and quality-of-life modules. It does not include automated combat, weapon-use automation, or anti-cheat bypass features.

## Build requirements

- Minecraft Java 26.1
- Java 25
- Fabric Loader 0.18.4+
- Fabric API 0.143.11+26.1
- Fabric Loom 1.15.5
- Gradle 9.4.0

Run `gradle build`. The mod JAR is written to `build/libs/`.

GitHub Actions builds the project on pushes to `main` and uploads the JAR as a workflow artifact.

Not an official Minecraft product. Not approved by or associated with Mojang.
