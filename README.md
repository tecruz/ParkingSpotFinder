# Parking Spot Finder

This is an Android application that allows users to find and mark parking spots on a map.

## Badges

<!-- Add your badges here -->
[![CI](https://github.com/tecruz/parking-spot-finder/actions/workflows/ci.yml/badge.svg)](https://github.com/tecruz/parking-spot-finder/actions/workflows/ci.yml)

## Screenshots

<!-- Add your screenshots here -->

## Building the Project

To build the project, run the following command:

```bash
./gradlew build
```

## Static Analysis

This project uses `detekt` for static code analysis and `ktlint` for enforcing a consistent code style.

To run the static analysis checks, use the following commands:

```bash
./gradlew detekt
./gradlew ktlintCheck
```

## Testing

The project includes both unit and instrumented tests.

### Unit Tests

To run the unit tests, use the following command:

```bash
./gradlew test
```

### Instrumented Tests

To run the instrumented tests, you'll need a connected device or emulator. Use the following command:

```bash
./gradlew connectedCheck
```

## Code Coverage

This project uses JaCoCo to generate code coverage reports.

To generate a combined report for both unit and instrumented tests, run the following command:

```bash
./gradlew jacocoTestReport
```

The report will be available in the `app/build/reports/jacoco/jacocoTestReport/html` directory.
