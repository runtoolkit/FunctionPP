# Datalib - Advanced Datapack System Mod

A Fabric mod for Minecraft 1.20.1 (using Yarn mappings) that adds three powerful features to enhance datapack functionality.

## Features

### 1. Command Alias System
**Purpose**: Simplify complex mcfunction calls by creating custom command aliases

- Allows datapack creators to define shorter names for frequently used commands
- Reduces code duplication in mcfunctions
- Improves maintainability of complex datapack structures
- **Command**: `/dalias` (Operator only)

**Files**:
- `CommandAliasSystem.java` - Main feature handler
- `CommandAliasRegistry.java` - Registry for storing and managing aliases

### 2. Conditional Datapack Loader
**Purpose**: Load datapacks selectively based on server conditions

- Checks world border size
- Evaluates difficulty levels
- Detects experimental features
- Enables conditional datapack activation
- Useful for adapting datapack behavior to different server configurations

**Files**:
- `ConditionalDatapackLoader.java` - Main feature handler
- `DatapackConditionChecker.java` - Condition evaluation logic

### 3. Advanced Function Builder
**Purpose**: Build and execute complex mcfunction sequences with enhanced debugging

- Function queueing system with delay support
- Execution debugging and logging
- Performance monitoring
- Error tracking and reporting
- Tick-based execution scheduling

**Files**:
- `AdvancedFunctionBuilder.java` - Main feature handler
- `FunctionDebugger.java` - Debugging utilities
- `FunctionExecutor.java` - Function execution and queueing engine

## Technical Details

- **Minecraft Version**: 1.20.1
- **Modloader**: Fabric
- **Mappings**: Yarn
- **Java Version**: 17+
- **Fabric Loader**: 0.14.21+

## Integration Points

The mod integrates with:
- Server lifecycle events (`ServerLifecycleEvents`)
- Command registration system
- Server tick events (`ServerTickEvents`)
- World and difficulty data

## Usage

Datapack creators can leverage these features to:
1. Create readable command aliases for complex function calls
2. Load different datapacks based on server configuration
3. Build scheduled function sequences with debugging capabilities

## Architecture

All features are modular and can be independently registered. The `DatapackSystemInitializer` orchestrates the initialization of all three features during mod startup.
