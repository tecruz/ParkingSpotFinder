# Parking Spot Finder

This is an Android application that allows users to find and mark parking spots on a map.

## Badges

[![Android CI](https://github.com/tecruz/ParkingSpotFinder/actions/workflows/android_ci.yml/badge.svg)](https://github.com/tecruz/ParkingSpotFinder/actions/workflows/android_ci.yml)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=tecruz_ParkingSpotFinder&metric=alert_status&token=6e724044ef05dd5abaf8f726a4d1140081793c86)](https://sonarcloud.io/summary/new_code?id=tecruz_ParkingSpotFinder)
[![codecov](https://codecov.io/gh/tecruz/ParkingSpotFinder/graph/badge.svg?token=lJ8xhAZsgH)](https://codecov.io/gh/tecruz/ParkingSpotFinder)


## Screenshots
<img width="270" height="600" alt="Screenshot_20251116_113304" src="https://github.com/user-attachments/assets/c717a129-2c30-4dc9-ab86-349634cc2167" />


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
