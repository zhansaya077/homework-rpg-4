# Homework 4: RPG Raid System

## 1. Composite Pattern
```mermaid
classDiagram
    class CombatNode {
        <<interface>>
        +getName() String
        +getHealth() int
        +takeDamage(amount: int)
        +isAlive() boolean
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
2. Bridge Pattern
classDiagram
    class Skill {
        <<abstract>>
        #effect: EffectImplementor
        +cast(target: CombatNode)
    }
    class EffectImplementor {
        <<interface>>
        +computeDamage(basePower: int) int
    }
    
    Skill <|-- SingleTargetSkill
    Skill <|-- AreaSkill
    Skill o-- EffectImplementor : Bridge
    EffectImplementor <|.. FireEffect
    EffectImplementor <|.. IceEffect
    EffectImplementor <|.. PhysicalEffect
How to Run
Bash
javac -d out $(find src -name "*.java")
java -cp out com.narxoz.rpg.Main
