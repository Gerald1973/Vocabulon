
# Vocabulon

## Description

Vocabulon is a Java-based vocabulary learning application designed to help users practice and memorize word translations between two languages. It features an interactive graphical interface where users can load vocabulary files, answer translation questions, and track their scores. The application supports text-to-speech functionality using eSpeak-NG to read words aloud, enhancing the learning experience. Key features include:

- **Vocabulary File Support**: Load custom vocabulary files in a specific format (title, languages, and word pairs separated by `|`).
- **Interactive Quiz**: Users translate words, with visual feedback (correct answers in white, incorrect in red) and sound effects for success or failure.
- **Text-to-Speech**: Pronounces words using eSpeak-NG, configurable via a properties file.
- **Score Tracking**: Displays the user's score as they progress through the quiz.
- **Modular Design**: Organized into packages (`controller`, `model`, `view`, `event`) for maintainability.

Vocabulon is ideal for language learners seeking a simple, customizable tool to practice vocabulary with auditory reinforcement.


## Installation Procedure for eSpeak-NG

This guide provides instructions to install eSpeak-NG, a compact open-source text-to-speech synthesizer, on Debian, Ubuntu, and Windows systems.

### Debian/Ubuntu

1. **Update Package Lists**:
   ```bash
   sudo apt update
   ```

2. **Install eSpeak-NG**:
   ```bash
   sudo apt install espeak-ng
   ```

3. **Verify Installation**:
   ```bash
   espeak-ng --version
   ```
   Expected output: version information (e.g., `eSpeak NG text-to-speech: 1.50`).

4. **Test eSpeak-NG**:
   ```bash
   espeak-ng -v en "Hello, this is a test"
   ```

5. **Optional: Install MBROLA for Enhanced Voices**:
   ```bash
   sudo apt install mbrola mbrola-fr1
   ```
   Test with:
   ```bash
   espeak-ng -v mb-fr1 "Bonjour le monde"
   ```

**Note**: If eSpeak-NG is not in the repository, compile from source (see [eSpeak-NG GitHub](https://github.com/espeak-ng/espeak-ng/blob/master/docs/building.md)).

### Windows

1. **Download Installer**:
   - Visit [eSpeak-NG Releases](https://github.com/espeak-ng/espeak-ng/releases).
   - Download the latest `.msi` file (e.g., `espeak-ng-<version>-x64.msi`).

2. **Run Installer**:
   - Double-click the `.msi` file.
   - Follow prompts to install, selecting desired voices for SAPI5 integration.

3. **Add to System PATH**:
   - Locate the eSpeak-NG executable (e.g., `C:\Program Files\eSpeak NG\espeak-ng.exe`).
   - Add its directory to the system PATH:
     - Right-click Start > System > Advanced system settings > Environment Variables.
     - Under "System variables," edit `Path`, add the eSpeak-NG directory.

4. **Verify Installation**:
   - Open Command Prompt and run:
     ```cmd
     espeak-ng --version
     ```

5. **Test eSpeak-NG**:
   ```cmd
   espeak-ng -v en "Hello, this is a test"
   ```

**Note**: MBROLA voices are not natively supported on Windows; use standard eSpeak-NG voices or compile MBROLA manually.

### Troubleshooting

- **No Sound**: Ensure audio drivers are installed and speakers are on. On Linux, pipe output to `aplay` or `paplay` if needed:
  ```bash
  espeak-ng "Hello" | aplay
  ```
- **Voice Not Found**: Verify voice files in `/usr/share/espeak-ng-data` (Linux) or installation directory (Windows).
- **Dependencies (Linux)**: Install `libportaudio2` if audio issues persist:
  ```bash
  sudo apt install libportaudio2
  ```

For further details, consult the [eSpeak-NG documentation](https://github.com/espeak-ng/espeak-ng/blob/master/docs/guide.md).


WARNING: FIRST OF ALL INSTALL:

<http://espeak.sourceforge.net/download.html>




## Installation Procedure for Vocabulon

This guide provides step-by-step instructions to install and run the Vocabulon Java application on Linux (Debian/Ubuntu) or Windows.

### Prerequisites

- **Java 8 or higher**: Ensure Java Development Kit (JDK) is installed.
  - Verify: `java -version`
  - Install (Linux): `sudo apt install openjdk-11-jdk`
  - Install (Windows): Download from [Oracle JDK](https://www.oracle.com/java/technologies/javase-downloads.html).
- **Maven**: Required for building the project.
  - Verify: `mvn -version`
  - Install (Linux): `sudo apt install maven`
  - Install (Windows): Download from [Apache Maven](https://maven.apache.org/download.cgi) and add to PATH.
- **eSpeak-NG**: For text-to-speech functionality.
  - Linux: `sudo apt install espeak-ng`
  - Windows: Download `.msi` from [eSpeak-NG Releases](https://github.com/espeak-ng/espeak-ng/releases) and add to PATH.

### Installation Steps

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/<user>/Vocabulon.git
   ```
   If the repository includes submodules, use:
   ```bash
   git clone --recursive https://github.com/<user>/Vocabulon.git
   ```

2. **Navigate to Project Directory**:
   ```bash
   cd Vocabulon
   ```

3. **Build the Project**:
   ```bash
   mvn clean install
   ```
   This compiles the code and generates `Vocabulon.jar` in the `target` directory.

4. **Configure vocabulon.properties**:
   - Create a file named `vocabulon.properties` in the project root or the same directory as `Vocabulon.jar`.
   - Add the path to the eSpeak-NG executable:
     ```properties
     # vocabulon.properties
     # Path to eSpeak-NG executable
     # Use executable name if in PATH (e.g., espeak-ng)
     # Linux example: /usr/bin/espeak-ng
     # Windows example: C:\\Program Files\\eSpeak NG\\espeak-ng.exe
     pathespeak=espeak-ng
     ```

5. **Run the Application**:
   ```bash
   java -jar target/Vocabulon.jar
   ```
   The graphical interface should launch, allowing you to load vocabulary files.

### Loading Vocabulary Files

1. Create a vocabulary file (e.g., `vocab.txt`) in the following format:
   ```
   Vocabulary Title
   SourceLanguage|TargetLanguage
   ColumnTitle1|ColumnTitle2
   Word1|Translation1
   Word2|Translation2
   ...
   ```
   Example:
   ```
   French-English Vocabulary
   fr|en
   French|English
   maison|house
   voiture|car
   ```

2. In the application, go to **File > Open** and select your vocabulary file.

### Troubleshooting

- **eSpeak-NG Not Found**: Ensure `pathespeak` in `vocabulon.properties` is correct or the executable is in the system PATH.
- **No Sound**: Verify eSpeak-NG installation and audio drivers.
  - Test: `espeak-ng -v en "Test"`
- **Build Errors**: Check Maven dependencies and Java version compatibility.
- **File Not Found**: Place `vocabulon.properties` and vocabulary files in the same directory as the JAR.

For additional support, refer to the [eSpeak-NG documentation](https://github.com/espeak-ng/espeak-ng/blob/master/docs/guide.md) or project issues on GitHub.

