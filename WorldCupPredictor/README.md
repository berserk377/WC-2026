# World Cup 2026 Predictor — Android App

## Project Overview (The "Vibe Coding" Journey)
Ei application ti sampurna **"Vibe Coding"** method-e banano hoyeche. Mane holo, kono manual heavy coding chhara sudhu matro AI-ke natural language instructions diye (Android Studio + Gemini/Vibe) ei fully functional app-ti dar korano hoyeche. 

### Why Vibe Coding?
Conventional coding-er bodole, ekhane focus chhilo "Intent" ba uddeshyer upor. AI-ke bola hoyeche "Amake ekta 48-team bracket predictor baniye dao", ebong AI proyojoniyo HTML, CSS, JavaScript ebong Kotlin code generate kore diyeche. Dev-er kaj chhilo sudhu matro "Vibe" check kora ebong kono somossya hole conversational-ly seta solve kora.

### Tech Stack:
- **Frontend:** HTML5, CSS3 (Custom Gold/Dark UI), Vanilla JavaScript.
- **Backend/Database:** Firebase Realtime Database (for predictions storage).
- **Authentication:** Firebase Auth (Email/Password).
- **Native Wrapper:** Android WebView (Kotlin based).
- **Data Source:** Football-API (for live scores).

### Key Features:
- **48 Team Bracket:** FIFA World Cup 2026-er nuton 48-team format support kore.
- **Interactive Predictor:** Group stage theke shuru kore Final porjonto protyekti match predict korar sujog.
- **Live Score Integration:** Real-time football API (football-api-sports) babohar kore live scores ebong match events (scorers) dekhano hoy.
- **Firebase Authentication:** Secure Login, Sign Up, ebong Account Deletion functionality.
- **Custom Branding:** Dark mode UI-er shathe premium Gold branding ebong smooth animations.
- **Progress Tracking:** User koyti match predict korlo tar ekta live progress bar dashboard-e dekhano hoy.

---

## Android Studio-te Import kore Build korar Steps

### Step 1: Project Open koro
1. Android Studio open koro
2. "Open" click koro (New Project na)
3. `WorldCupPredictor` folder ta select koro
4. "OK" press koro

### Step 2: Gradle Sync
- Android Studio automatically Gradle sync korbe
- Internet connection lagbe (first time dependencies download hobe)
- Bottom-e "Sync Now" dekhale click koro
- 2-5 minute lagbe

### Step 3: ic_launcher fix (Important!)
Mipmap folder-e proper icon lagbe. Easiest way:
1. Left panel-e `res/mipmap-hdpi/` right-click koro
2. "New > Image Asset" select koro
3. "Launcher Icons" choose koro
4. Finish click koro — auto generate hobe

### Step 4: Phone-e Build koro
**USB diye:**
1. Phone-e Developer Options on koro
   - Settings > About Phone > Build Number-e 7 baar tap koro
2. Developer Options-e "USB Debugging" on koro
3. USB diye PC-te connect koro
4. Android Studio-te tomar phone dekhabe top-e
5. Green ▶ Play button press koro — install hobe!

**APK banate (share korte):**
1. Build > Build Bundle(s) / APK(s) > Build APK(s)
2. APK ready hole "locate" click koro
3. `app/build/outputs/apk/debug/app-debug.apk` — ei file ta phone-e copy koro
4. Phone-e install koro (Unknown Sources allow korte hobe)

## File Structure
```
WorldCupPredictor/
├── app/
│   └── src/main/
│       ├── assets/
│       │   └── index.html          ← App-er poora UI ekhane
│       ├── java/com/worldcup/predictor/
│       │   └── MainActivity.kt     ← WebView setup
│       ├── res/
│       │   ├── layout/
│       │   │   └── activity_main.xml
│       │   └── values/
│       │       └── strings.xml
│       └── AndroidManifest.xml
├── build.gradle
└── settings.gradle
```

## App customize korte
- **HTML/CSS/JS change** korte: `assets/index.html` file edit koro
- **App name change**: `strings.xml` edit koro → `app_name` value badlao
- **Teams update**: `index.html` er `GROUPS` object edit koro
