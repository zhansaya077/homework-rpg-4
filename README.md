
# Homework 4: RPG Raid System - Bridge + Composite

## Overview
This project implements a raid system for an RPG using structural design patterns. It allows for flexible skill-effect combinations and uniform handling of individual units and nested teams.

## Implementation Details

### 1. Composite Pattern (Team Hierarchy)
We use the **Composite Pattern** to treat single heroes and entire raid groups identically.
- **Component:** `CombatNode` interface.
- **Leaf:** `UnitLeaf` (Base for `HeroUnit`, `EnemyUnit`).
- **Composite:** `BaseComposite` (Base for `PartyComposite`, `RaidGroup`).

#### Composite UML Diagram
```mermaid
classDiagram
    class CombatNode {
        <<interface>>
        +getName() String
        +getHealth() int
        +getAttackPower() int
        +takeDamage(amount: int)
        +isAlive() boolean
        +getChildren() List
    }
    class UnitLeaf { <<abstract>> }
    class BaseComposite { <<abstract>> }
    
    CombatNode <|.. UnitLeaf
    CombatNode <|.. BaseComposite
    BaseComposite "1" *-- "many" CombatNode : children
    UnitLeaf <|-- HeroUnit
    UnitLeaf <|-- EnemyUnit
    BaseComposite <|-- PartyComposite
    BaseComposite <|-- RaidGroup


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

