# Homework 4: RPG Raid System (Bridge + Composite)

## Project Overview
This project demonstrates the implementation of **Bridge** and **Composite** design patterns in a Java-based RPG combat simulation.

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

Фрагмент кода
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
Navigate to the project root directory.

Compile the project:

Bash
javac -d out $(find src -name "*.java")
Run the demonstration:

Bash
java -cp out com.narxoz.rpg.Main
Project Logic
Composite: Supports nested groups (Raid -> Party -> Hero).

Bridge: Separates skill types from elemental effects to avoid class explosion.

RaidEngine: Simulates turn-based combat using only top-level abstractions.


---
