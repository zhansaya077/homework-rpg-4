Markdown
# Homework 4: RPG Raid System (Bridge + Composite)

## Project Overview
This project demonstrates the implementation of **Bridge** and **Composite** design patterns in a Java-based RPG combat simulation.

## Core Features
- **Composite Pattern**: Allows creating complex team structures (Raid -> Party -> Unit). Both individual units and groups can be targeted and take damage uniformly.
- **Bridge Pattern**: Decouples skill logic (Single Target vs AOE) from elemental effects (Fire, Ice, Physical). This prevents "class explosion" (e.g., no need for FireSlash, IceSlash, etc.).
- **Raid Engine**: A deterministic battle simulator that processes rounds between two combat nodes.

---

## 1. Composite Pattern (Hierarchy)
The `CombatNode` interface is the core component. `UnitLeaf` represents individual fighters, while `BaseComposite` allows nesting parties within raids.

```mermaid
classDiagram
    class CombatNode {
        <<interface>>
        +getName() String
        +getHealth() int
        +takeDamage(amount: int)
        +isAlive() boolean
        +getChildren() List
    }
    class UnitLeaf { <<abstract>> }
    class BaseComposite { <<abstract>> }
    
    CombatNode <|.. UnitLeaf
    CombatNode <|.. BaseComposite
    BaseComposite "1" *-- "many" CombatNode : contains
    UnitLeaf <|-- HeroUnit
    UnitLeaf <|-- EnemyUnit
    BaseComposite <|-- PartyComposite
    BaseComposite <|-- RaidGroup

2. Bridge Pattern (Skills & Effects)
The Skill abstraction maintains a reference to an EffectImplementor. This allows any skill to use any elemental effect dynamically.


classDiagram
    class Skill {
        <<abstract>>
        #effect: EffectImplementor
        +cast(target: CombatNode)
    }
    class EffectImplementor {
        <<interface>>
        +computeDamage(basePower: int) int
        +getEffectName() String
    }
    
    Skill <|-- SingleTargetSkill
    Skill <|-- AreaSkill
    Skill o-- EffectImplementor : Bridge
    EffectImplementor <|.. FireEffect
    EffectImplementor <|.. IceEffect
    EffectImplementor <|.. PhysicalEffect
How to Run
Navigate to the project root.

Compile the project:

Bash
javac -d out $(find src -name "*.java")
Run the demonstration:

Bash
java -cp out com.narxoz.rpg.Main
Battle Simulation Logic
Round-based: Team A attacks, then Team B (if alive).

AOE Damage: Distributed among all living members of a composite group.

Deterministic: Uses a fixed random seed for consistent results.


---
