package implement;

import classes.Arena;
import classes.Playerv2;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.Input;

public final class UseHeroAbility {
    private UseHeroAbility() {
    }

    /**
     * useHeroAbility implements the entire task
     * inputData is the input, output is the output
     * arena is the game arena
     * affectedrow is the row where hero will use the ability
     * player is the player from where we will take the hero
     * and pidx is the index of the player (1 for player 1 and 2 for player 2)
     * @param inputData
     * @param output
     * @param arena
     * @param affectedrow
     * @param player
     * @param pidx
     */
    public static void useHeroAbility(final Input inputData, final ArrayNode output,
                                      final Arena arena, final int affectedrow,
                                      final Playerv2 player, final int pidx) {
        if (player.getMana() >= player.getHero().getMana()) {
            if (player.getHero().getAttackedtur() == 0) {
                if (player.getHero().getName().equals("Lord Royce")
                        || player.getHero().getName().equals("Empress Thorina")) {
                    if (pidx == 1) {
                        if (affectedrow < 2) {
                            if (player.getHero().getName().equals("Lord Royce")) {
                                int maxattack = arena.getMap().get(affectedrow).get(0)
                                        .getAttackDamage();
                                int index = 0;
                                for (int k = 1; k < arena.getMap().get(affectedrow).size(); k++) {
                                    if (maxattack < arena.getMap().get(affectedrow).get(k)
                                            .getAttackDamage()) {
                                        maxattack = arena.getMap().get(affectedrow).get(k)
                                                .getAttackDamage();
                                        index = k;
                                    }
                                }
                                arena.getMap().get(affectedrow).get(index).setFrozen(1);
                                player.setMana(player.getMana() - player.getHero().getMana());
                                player.getHero().setAttackedtur(1);
                            }
                            if (player.getHero().getName().equals("Empress Thorina")) {
                                int maxhealth = arena.getMap().get(affectedrow).get(0)
                                        .getHealth();
                                int index = 0;
                                for (int k = 1; k < arena.getMap().get(affectedrow).size(); k++) {
                                    if (maxhealth < arena.getMap().get(affectedrow).get(k)
                                            .getHealth()) {
                                        maxhealth = arena.getMap().get(affectedrow).get(k)
                                                .getHealth();
                                        index = k;
                                    }
                                }
                                arena.getMap().get(affectedrow).remove(index);
                                player.setMana(player.getMana() - player.getHero().getMana());
                                player.getHero().setAttackedtur(1);
                            }
                        } else {
                            output.addObject().put("command", "useHeroAbility")
                                    .put("affectedRow", affectedrow)
                                    .put("error", "Selected row does not belong to the enemy.");
                        }
                    } else {
                        if (affectedrow >= 2) {
                            if (player.getHero().getName().equals("Lord Royce")) {
                                int maxattack = arena.getMap().get(affectedrow).get(0)
                                        .getAttackDamage(), index = 0;
                                for (int k = 1; k < arena.getMap().get(affectedrow).size(); k++) {
                                    if (maxattack < arena.getMap().get(affectedrow).get(k)
                                            .getAttackDamage()) {
                                        maxattack = arena.getMap().get(affectedrow).get(k)
                                                .getAttackDamage();
                                        index = k;
                                    }
                                }
                                arena.getMap().get(affectedrow).get(index).setFrozen(1);
                                player.setMana(player.getMana() - player.getHero().getMana());
                                player.getHero().setAttackedtur(1);
                            }
                            if (player.getHero().getName().equals("Empress Thorina")) {
                                int maxhealth = arena.getMap().get(affectedrow).get(0)
                                        .getHealth();
                                int index = 0;
                                for (int k = 1; k < arena.getMap().get(affectedrow).size(); k++) {
                                    if (maxhealth < arena.getMap().get(affectedrow).get(k)
                                            .getHealth()) {
                                        maxhealth = arena.getMap().get(affectedrow).get(k)
                                                .getHealth();
                                        index = k;
                                    }
                                }
                                arena.getMap().get(affectedrow).remove(index);
                                player.setMana(player.getMana() - player.getHero().getMana());
                                player.getHero().setAttackedtur(1);
                            }
                        } else {
                            output.addObject().put("command", "useHeroAbility")
                                    .put("affectedRow", affectedrow)
                                    .put("error", "Selected row does not belong to the enemy.");
                        }
                    }
                } else {
                    if (pidx == 1) {
                        if (affectedrow >= 2) {
                            if (player.getHero().getName().equals("King Mudface")) {
                                for (int k = 0; k < arena.getMap().get(affectedrow).size(); k++) {
                                    arena.getMap().get(affectedrow).get(k).setHealth(
                                            arena.getMap().get(affectedrow).get(k).getHealth()
                                                    + 1);
                                }
                                player.setMana(player.getMana() - player.getHero().getMana());
                                player.getHero().setAttackedtur(1);
                            }
                            if (player.getHero().getName().equals("General Kocioraw")) {
                                for (int k = 0; k < arena.getMap().get(affectedrow).size(); k++) {
                                    arena.getMap().get(affectedrow).get(k).setAttackDamage(
                                            arena.getMap().get(affectedrow).get(k)
                                                    .getAttackDamage() + 1);
                                }
                                player.setMana(player.getMana() - player.getHero().getMana());
                                player.getHero().setAttackedtur(1);
                            }
                        } else {
                            output.addObject().put("command", "useHeroAbility")
                                    .put("affectedRow", affectedrow)
                                    .put("error",
                                            "Selected row does not belong to the current player.");
                        }
                    } else {
                        if (affectedrow < 2) {
                            if (player.getHero().getName().equals("King Mudface")) {
                                for (int k = 0; k < arena.getMap().get(affectedrow).size(); k++) {
                                    arena.getMap().get(affectedrow).get(k).setHealth(
                                            arena.getMap().get(affectedrow).get(k).getHealth()
                                                    + 1);
                                }
                                player.setMana(player.getMana() - player.getHero().getMana());
                                player.getHero().setAttackedtur(1);
                            }
                            if (player.getHero().getName().equals("General Kocioraw")) {
                                for (int k = 0; k < arena.getMap().get(affectedrow).size(); k++) {
                                    arena.getMap().get(affectedrow).get(k).setAttackDamage(
                                            arena.getMap().get(affectedrow).get(k)
                                                    .getAttackDamage() + 1);
                                }
                                player.setMana(player.getMana() - player.getHero().getMana());
                                player.getHero().setAttackedtur(1);
                            }
                        } else {
                            output.addObject().put("command", "useHeroAbility")
                                    .put("affectedRow", affectedrow)
                                    .put("error",
                                            "Selected row does not belong to the current player.");
                        }
                    }
                }
            } else {
                output.addObject().put("command", "useHeroAbility")
                        .put("affectedRow", affectedrow)
                        .put("error", "Hero has already attacked this turn.");
            }
        } else {
            output.addObject().put("command", "useHeroAbility")
                    .put("affectedRow", affectedrow)
                    .put("error", "Not enough mana to use hero's ability.");
        }
    }
}
