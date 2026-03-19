package com.narxoz.rpg.bridge;

import com.narxoz.rpg.composite.CombatNode;

public class AreaSkill extends Skill {
    public AreaSkill(String skillName, int basePower, EffectImplementor effect) {
        super(skillName, basePower, effect);
    }

    @Override
    public void cast(CombatNode target) {
        int damage = resolvedDamage();
        System.out.println( getSkillName() + " (" + getEffectName() + ") " + target.getName());

        if (!target.getChildren().isEmpty()) {
            for (CombatNode child : target.getChildren()) {
                if (child.isAlive()) {
                    child.takeDamage(damage);
                }
            }
        } else {
            target.takeDamage(damage);
        }
    }
}
