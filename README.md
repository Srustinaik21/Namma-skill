# Namma-Skill: Smart Rural Skill Development Platform

Namma-Skill is a full-stack platform designed to bridge the gap between rural youth and government skill development programs.

## Features
- **Android App**: Kotlin, Jetpack Compose, MVVM, Hilt, Room, Firebase, Google Maps, Gemini AI.
- **Web Admin Dashboard**: React.js, Tailwind CSS, Firebase, Recharts.
- **AI Integration**: Smart course recommendations and career guidance.
- **Offline Support**: Course caching using Room database.

## Project Structure
- `app/`: Android mobile application source code.
- `web-admin/`: React.js admin dashboard source code.
- `firebase/`: Firebase configuration and Cloud Functions (to be added).

## Getting Started

### Android App
1. Open the project in Android Studio.
2. Add your `google-services.json` to the `app/` directory.
3. Replace `YOUR_GOOGLE_MAPS_API_KEY` in `AndroidManifest.xml`.
4. Build and run.

### Web Admin
1. Navigate to `web-admin/`.
2. Run `npm install`.
3. Run `npm start`.

## Architecture
The Android app follows **Clean Architecture** with **MVVM**:
- **UI Layer**: Jetpack Compose screens and ViewModels.
- **Domain Layer**: Models, Repository interfaces, and Use Cases.
- **Data Layer**: Repository implementations, Room DB, and Firebase integration.
