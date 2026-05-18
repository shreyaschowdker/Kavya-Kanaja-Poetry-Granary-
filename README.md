<h1 align="center">
  Kavya-Kanaja (ಕಾವ್ಯ-ಕಣಜ)
</h1>

<p align="center">
  <strong>A modern Android application dedicated to exploring, reading, and understanding classical and contemporary Kannada poetry.</strong>
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Platform-Android-3DDC84?style=for-the-badge&logo=android&logoColor=white" alt="Android">
  <img src="https://img.shields.io/badge/Kotlin-1.9+-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white" alt="Kotlin">
  <img src="https://img.shields.io/badge/UI-Jetpack%20Compose-4285F4?style=for-the-badge&logo=jetpackcompose&logoColor=white" alt="Jetpack Compose">
  <img src="https://img.shields.io/badge/Architecture-MVVM%20%7C%20Clean-FF9900?style=for-the-badge" alt="Architecture">
</p>

## 📖 Project Overview

**Kavya-Kanaja** ("The Granary of Poetry") is a highly optimized, beautifully designed Android application created to bring Kannada literature to the digital forefront. The app acts as an immersive repository, offering users access to famous Kannada poems, detailed poet biographies, integrated audio recitations, and interactive features like in-line difficult word meanings.

Built entirely using modern Android development practices, the app serves as a robust demonstration of **Jetpack Compose**, **Clean Architecture**, and **Offline-First Data Synchronization**.

---

## ✨ Key Features

- 🎭 **Poem of the Day**: A dynamically updating feature highlighting a specific piece of literature.
- 📖 **Interactive Reading**: Tap on "difficult words" directly within the poem to instantly reveal their context and meaning.
- 🎧 **Audio Integration**: Listen to professional recitations of poems via a lifecycle-aware Media3 ExoPlayer integration.
- 👤 **Poet Profiles**: Dedicated screens detailing poet biographies, timelines, and their famous works.
- 🔍 **Real-time Search**: Search instantly across poem titles, poet names, and poem content using debounced StateFlow operators.
- ❤️ **Favorites System**: Save poems to a persistent local Room database for easy access later.
- 🌗 **Material 3 UI**: Beautiful, fully responsive typography and layout with seamless Light/Dark mode support.
- 🌐 **Standalone Backend**: Communicates with a custom Ktor backend server for fetching fresh literature.

---

## 🛠 Tech Stack

**Core Android:**
- [Kotlin](https://kotlinlang.org/) - 100% Kotlin codebase
- [Jetpack Compose](https://developer.android.com/jetpack/compose) - Modern, declarative UI toolkit
- [Material 3 (M3)](https://m3.material.io/) - Theming and UI components

**Architecture & Async:**
- **MVVM Architecture** - Strict separation of UI, Domain, and Data layers
- [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) & [StateFlow](https://developer.android.com/kotlin/flow) - Asynchronous programming and state management
- [Dagger Hilt](https://dagger.dev/hilt/) - Dependency Injection

**Networking & Local Storage:**
- [Retrofit](https://square.github.io/retrofit/) - Type-safe REST client for API integration
- [Kotlinx Serialization](https://github.com/Kotlin/kotlinx.serialization) - High-performance JSON parsing
- [Room Database](https://developer.android.com/training/data-storage/room) - SQLite abstraction for offline-first data caching

**Media & Images:**
- [Coil](https://coil-kt.github.io/coil/compose/) - Coroutine-based image loading
- [AndroidX Media3 (ExoPlayer)](https://developer.android.com/guide/topics/media/media3) - Reliable audio playback engine

---

## 🏗 Architecture

This project strictly adheres to **Clean Architecture** principles, enforcing a unidirectional data flow (UDF):

1. **Presentation Layer (`/presentation`)**: Contains Jetpack Compose UI components, Screens, and ViewModels. ViewModels observe domain data streams and expose `StateFlow` objects representing UI states.
2. **Domain Layer (`/domain`)**: Contains pure Kotlin data models (`Poem`, `Poet`, `WordMeaning`) and repository interfaces. Completely decoupled from the Android framework.
3. **Data Layer (`/data`)**: Houses implementations of the repository interfaces. Uses a **Single Source of Truth** pattern where network data from Retrofit is safely parsed, cached into the Room database (`PoemEntity`), and instantly emitted to the UI via Flow.

---

## 📱 Screenshots

> *Note: Add screenshots of your actual application running here.*

| Home Screen | Poem Detail | Favorites | Search |
| :---: | :---: | :---: | :---: |
| <img src="screenshots/home.png" width="200"/> | <img src="screenshots/detail.png" width="200"/> | <img src="screenshots/favorites.png" width="200"/> | <img src="screenshots/search.png" width="200"/> |

---

## 🚀 Installation & Setup

1. **Clone the repository:**
   ```bash
   git clone https://github.com/yourusername/Kavya-Kanaja.git
   ```
2. **Open in Android Studio:**
   Ensure you are using the latest stable version of Android Studio (Giraffe or newer).
3. **Backend Configuration (Optional):**
   The app connects to a local Ktor backend (located in the `/backend` folder). 
   - To run the backend locally, navigate to `/backend` and execute `./gradlew run`. 
   - The Android app is already configured in `AppModule.kt` to securely point to `http://10.0.2.2:8080/` for emulator loopback testing.
4. **Build and Run:**
   Sync Gradle files and deploy to an Emulator or physical Android device.

---

## 🔮 Future Improvements

- [ ] **Pagination**: Implement Android Paging 3 to lazily load massive lists of poems from the backend.
- [ ] **User Authentication**: Allow users to sync their favorites across multiple devices.
- [ ] **Audio Caching**: Enable offline listening by caching `ExoPlayer` streams to disk.
- [ ] **Localization**: Complete Kannada UI translations natively utilizing Android `strings.xml`.

---

## 🤝 Contribution Guide

Contributions are welcome! Please follow these steps to contribute:
1. Fork the repository.
2. Create a new branch: `git checkout -b feature/your-feature-name`
3. Commit your changes: `git commit -m 'Add some feature'`
4. Push to the branch: `git push origin feature/your-feature-name`
5. Open a Pull Request.

---

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
