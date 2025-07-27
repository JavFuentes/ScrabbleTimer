# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

ScrabbleTimer is an Android application built with Kotlin and Jetpack Compose. The app follows modern Android development practices using Material 3 design system.

### App Functionality
A simple timer app for Scrabble players to limit turn time with two main screens:

**Screen 1 - Time Selection:**
- Choose turn duration: 1, 2, 3, 4, or 5 minutes
- Navigate to timer screen with selected time

**Screen 2 - Timer Interface:**
- Countdown display showing selected time
- Play/Pause button (toggles between play and pause states)
- Reset button (restarts countdown to original time)
- Emergency pause/resume functionality

### Implementation Phases
- **Phase 1:** Time selection screen with duration options
- **Phase 2:** Timer screen with countdown and controls
- **Phase 3:** Navigation between screens and state management

## Build and Development Commands

### Building the project
```bash
./gradlew build
```

### Running tests
```bash
# Unit tests
./gradlew test

# Instrumented tests (requires Android device/emulator)
./gradlew connectedAndroidTest

# Run tests for specific build variant
./gradlew testDebugUnitTest
```

### Building and installing APK
```bash
# Debug build
./gradlew assembleDebug

# Release build
./gradlew assembleRelease

# Install debug APK to connected device
./gradlew installDebug
```

### Code quality and linting
```bash
# Run lint checks
./gradlew lint

# Generate lint report
./gradlew lintDebug
```

### Cleaning the project
```bash
./gradlew clean
```

## Architecture and Structure

### Module Structure
- `app/` - Main application module containing all source code
- Root-level Gradle files manage project configuration

### Package Structure
- `dev.javierfuentes.scrabbletimer` - Main package containing MainActivity
- `dev.javierfuentes.scrabbletimer.data` - Data models and classes
- `dev.javierfuentes.scrabbletimer.ui.components` - Reusable UI components
- `dev.javierfuentes.scrabbletimer.ui.screens` - Application screens
- `dev.javierfuentes.scrabbletimer.ui.navigation` - Navigation configuration
- `dev.javierfuentes.scrabbletimer.ui.theme` - UI theming components (Color, Theme, Type)

### Key Technologies
- **Jetpack Compose** - Modern declarative UI framework with new Compose Compiler plugin
- **Material 3** - Latest Material Design components
- **Kotlin** - Primary programming language (v2.2.0)
- **AndroidX libraries** - Core Android components
- **Navigation Compose** - Type-safe navigation between screens

### Configuration
- **compileSdk**: 35
- **minSdk**: 26  
- **targetSdk**: 35
- **Java compatibility**: 1.8
- **Kotlin**: 2.2.0
- **AGP**: 8.11.1
- **Compose BOM**: 2025.07.00
- **Navigation Compose**: 2.9.2

### Testing Framework
- **JUnit 4** for unit tests
- **Espresso** for UI instrumented tests  
- **Compose UI Test** for Compose-specific testing

### Build Configuration
The project uses Gradle Version Catalogs (`gradle/libs.versions.toml`) for dependency management, which centralizes version definitions and library declarations. Uses the new Compose Compiler plugin for better performance and compatibility.

### Internationalization
- Default language: English
- All user-facing text stored in `strings.xml` for future multi-language support
- Text resources accessed via `stringResource()` in Compose