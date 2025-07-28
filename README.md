# 🎲 Scrabble Timer

<div align="center">

![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![Kotlin](https://img.shields.io/badge/kotlin-%237F52FF.svg?style=for-the-badge&logo=kotlin&logoColor=white)
![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-4285F4?style=for-the-badge&logo=jetpackcompose&logoColor=white)

**A modern Android timer application designed specifically for Scrabble players to limit turn time and enhance gameplay experience.**

</div>

## 📱 Features

<img width="384" height="277" alt="ssst" src="https://github.com/user-attachments/assets/eca9b6a2-7bfc-4e7d-a54d-bb2fd6bbe6ba" />


### ⏱️ Timer Functionality
- **Multiple time options**: 1, 2, 3, 4, or 5 minutes per turn
- **Persistent state**: Timer survives screen rotations and app lifecycle changes
- **Audio & vibration alerts**: Sound and vibration notifications when time expires
- **Intuitive controls**: Play/Pause and Reset buttons with clear visual feedback

### 🎨 Modern UI/UX
- **Material 3 Design**: Following Google's latest design guidelines
- **Custom Splash Screen**: Branded entry screen with custom Scrabbles font
- **Responsive design**: Optimized for different screen sizes and orientations

### 🌍 Multi-language Support
The app supports **9 languages**:
- 🇺🇸 English (default)
- 🇪🇸 Spanish (Español)
- 🇵🇹 Portuguese (Português)
- 🇮🇹 Italian (Italiano)
- 🇫🇷 French (Français)
- 🇷🇺 Russian (Русский)
- 🇨🇳 Chinese Simplified (简体中文)
- 🇰🇷 Korean (한국어)
- 🇯🇵 Japanese (日本語)

### 🏗️ Architecture
- **MVVM Pattern**: Clean separation of concerns with ViewModels
- **Jetpack Compose**: Modern declarative UI framework
- **StateFlow**: Reactive state management
- **Navigation Compose**: Type-safe navigation between screens
- **Material 3**: Latest Material Design components

## 🚀 Getting Started

### Prerequisites
- Android Studio Hedgehog (2023.1.1) or later
- Android SDK API 26 (Android 8.0) or higher
- Kotlin 2.2.0

### Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/ScrabbleTimer.git
   ```

2. Open the project in Android Studio

3. Sync the project with Gradle files

4. Run the app on an emulator or physical device

### Building
```bash
# Debug build
./gradlew assembleDebug

# Release build
./gradlew assembleRelease

# Install on connected device
./gradlew installDebug
```

## 📋 Usage

1. **Launch the app**: Opens with splash screen displaying "SCRABBLE TIMER"
2. **Select time**: Choose from 1-5 minute options on the main screen
3. **Start timer**: Tap the play button to begin countdown
4. **Control timer**: Use play/pause and reset buttons as needed
5. **Timer completion**: Receive audio and vibration alerts when time expires

## 🛠️ Technical Details

### Project Structure
```
app/
├── src/main/java/dev/javierfuentes/scrabbletimer/
│   ├── data/           # Data classes and models
│   ├── ui/
│   │   ├── components/ # Reusable UI components
│   │   ├── screens/    # App screens
│   │   ├── theme/      # UI theme and colors
│   │   ├── viewmodel/  # ViewModels for state management
│   │   └── navigation/ # Navigation configuration
│   └── utils/          # Utility classes
└── src/main/res/
    ├── font/           # Custom fonts
    ├── values/         # Default strings and resources
    ├── values-es/      # Spanish translations
    ├── values-pt/      # Portuguese translations
    └── ...             # Other language translations
```

### Key Dependencies
```kotlin
// Compose BOM
implementation(platform("androidx.compose:compose-bom:2025.07.00"))

// Core Compose dependencies
implementation("androidx.compose.ui:ui")
implementation("androidx.compose.ui:ui-tooling-preview")
implementation("androidx.compose.material3:material3")

// Navigation
implementation("androidx.navigation:navigation-compose:2.9.2")

// ViewModel
implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.7")
implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.7")
```

### Build Configuration
- **Compile SDK**: 35
- **Min SDK**: 26 (Android 8.0)
- **Target SDK**: 35
- **Kotlin**: 2.2.0
- **AGP**: 8.11.1

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

### Development Guidelines
- Follow Android development best practices
- Use Jetpack Compose for UI development
- Implement proper state management with ViewModels
- Add unit tests for new functionality
- Ensure all strings are externalized for internationalization

## 📄 License

```
MIT License

Copyright (c) 2023 Javier Fuentes

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

For more information about the author and his projects, visit http://javierfuentes.dev
```


## 👨‍💻 Author

**Javier Fuentes**
- GitHub: [@javfuentes](https://github.com/javfuentes)
- Email: contacto@javierfuentes.dev

---

<div align="center">

**Did you like the project? Give it a ⭐!**

<a href='https://ko-fi.com/I2I2OPHE0' target='_blank'>
  <img height='36' src='https://storage.ko-fi.com/cdn/kofi6.png?v=6' alt='Cómprame un café en ko-fi.com' />
</a>

</div>