# toolarium-ansi

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [ 0.9.3 ] - 2026-10-26

## [ 0.9.2 ] - 2026-10-26
### Fixed
- Fixed thread-safety race condition in ANSI auto-detection.
- Fixed null consumer causing partial formatting corruption in AnsiStringBuilder.
- Fixed double environment variable lookups in detection methods.
- Fixed missing null guard for system property `os.name`.
- Fixed incorrect Javadoc on `AnsiColor.OFF` enum value.
- Fixed typos in field names and Javadoc.

### Changed
- Moved debug logging to singleton instance method for consistency.
- Added `isDebugEnabled` guard to prevent unnecessary string concatenation.
- Pre-computed ANSI code strings in color enums for better performance.
- Replaced throwaway `StringBuilder` in `ansify()` with string concatenation.
- Broadened exception handling in Jansi console detection.
- Documented thread-safety contract on `AnsiStringBuilder`.
- Documented SGR 22 reset behavior on `resetBold()` and `resetDim()`.

### Added
- Added Javadoc to all `ForegroundColor` and `BackgroundColor` enum values.
- Added `getValueAsString()` to `ForegroundColor` and `BackgroundColor`.
- Added null consumer validation to all consumer-accepting methods.
- Added unit tests for null consumer rejection, property change detection, debug logging, and `getValueAsString()`.

## [ 0.9.1 ] - 2026-07-21
### Changed
- Set Java source and target compatibility to 1.8.
- Added license information (licenseText, licenseUrl) to gradle.properties.
- Added legacy color reference comments to ForegroundColor.

## [ 0.9.0 ] - 2026-05-14
### Changed
- Fixed Javadoc HTML parse error in AnsiColor.java.
- Declared inner HOLDER classes as final in OSUtil and TerminalUtil.
- removed slf4j dependency.

### Added
- Added additional test cases.
- Added com.github.toolarium.ansi.AnsiMain.

## [ 0.8.1 ] - 2024-06-28
### Changed
- Updated library dependencies.

## [ 0.8.0 ] - 2024-01-07
### Changed
- Setup initial version.
