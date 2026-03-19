package com.narxoz.rpg.battle;

import com.narxoz.rpg.bridge.Skill;
import com.narxoz.rpg.composite.CombatNode;
import java.util.Random;

public class RaidEngine {
    private Random random = new Random(1L);

    public RaidEngine setRandomSeed(long seed) {
        this.random = new Random(seed);
        return this;
    }

    public RaidResult runRaid(CombatNode teamA, CombatNode teamB, Skill skillA, Skill skillB) {
        RaidResult result = new RaidResult();
        int round = 1;

        result.addLine("=== RAID START: " + teamA.getName() + " VS " + teamB.getName() + " ===");

        while (teamA.isAlive() && teamB.isAlive() && round <= 100) {
            result.addLine("\n--- Round " + round + " ---");

            if (teamA.isAlive()) {
                result.addLine("[Turn] " + teamA.getName() + " uses " + skillA.getSkillName());
                skillA.cast(teamB);
            }

            if (teamB.isAlive()) {
                result.addLine("[Turn] " + teamB.getName() + " uses " + skillB.getSkillName());
                skillB.cast(teamA);
            }

            round++;
        }

        result.setRounds(round - 1);
        String winner = teamA.isAlive() ? teamA.getName() : (teamB.isAlive() ? teamB.getName() : "No Survivors");
        
        result.setWinner(winner);
        result.addLine("\n========================================");
        result.addLine("VICTORY: " + winner);
        result.addLine("TOTAL ROUNDS: " + (round - 1));
        result.addLine("========================================");

        return result;
    }
}
  
