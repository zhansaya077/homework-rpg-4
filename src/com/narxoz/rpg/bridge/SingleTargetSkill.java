package com.narxoz.rpg.bridge;

import com.narxoz.rpg.composite.CombatNode;

public class SingleTargetSkill extends Skill {
    public SingleTargetSkill(String skillName, int basePower, EffectImplementor effect) {
        super(skillName, basePower, effect);
    }

    @Override
    public void cast(CombatNode target) {
        if (!target.isAlive()) {
            return;
        }

        int finalDamage = resolvedDamage();
        System.out.println("[" + getSkillName() + "] (" + getEffectName() + ") -> " + target.getName() + ": " + finalDamage);
        target.takeDamage(finalDamage);
    }
}
