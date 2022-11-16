package implement;

import classes.Arena;
import classes.Playerv2;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.Input;

public class UseHeroAbility {
    public static void useHeroAbility(Input inputData, ArrayNode output, Arena arena, int affected_row, Playerv2 player, int p_idx) {
        if (player.getMana() >= player.getHero().getMana()){
            if (player.getHero().attacked_tur == 0) {
                if (player.getHero().getName().equals("Lord Royce") || player.getHero().getName().equals("Empress Thorina")) {
                    if (p_idx == 1){
                        if (affected_row < 2){
                            if (player.getHero().getName().equals("Lord Royce")){
                                int max_attack = arena.getMap().get(affected_row).get(0).getAttackDamage(), index = 0;
                                for (int k = 1; k < arena.getMap().get(affected_row).size(); k ++){
                                    if (max_attack < arena.getMap().get(affected_row).get(k).getAttackDamage()){
                                        max_attack = arena.getMap().get(affected_row).get(k).getAttackDamage();
                                        index = k;
                                    }
                                }
                                arena.getMap().get(affected_row).get(index).frozen = 1;
                                player.setMana(player.getMana() - player.getHero().getMana());
                                player.getHero().attacked_tur = 1;
                            }
                            if (player.getHero().getName().equals("Empress Thorina")) {
                                int max_health = arena.getMap().get(affected_row).get(0).getHealth();
                                int index = 0;
                                for (int k = 1; k < arena.getMap().get(affected_row).size(); k ++){
                                    if (max_health < arena.getMap().get(affected_row).get(k).getHealth()){
                                        max_health = arena.getMap().get(affected_row).get(k).getHealth();
                                        index = k;
                                    }
                                }
                                arena.getMap().get(affected_row).remove(index);
                                player.setMana(player.getMana() - player.getHero().getMana());
                                player.getHero().attacked_tur = 1;
                            }
                        }else {
                            output.addObject().put("command", "useHeroAbility")
                                    .put("affectedRow", affected_row)
                                    .put("error", "Selected row does not belong to the enemy.");
                        }
                    }else {
                        if (affected_row >= 2){
                            if (player.getHero().getName().equals("Lord Royce")){
                                int max_attack = arena.getMap().get(affected_row).get(0).getAttackDamage(), index = 0;
                                for (int k = 1; k < arena.getMap().get(affected_row).size(); k ++){
                                    if (max_attack < arena.getMap().get(affected_row).get(k).getAttackDamage()){
                                        max_attack = arena.getMap().get(affected_row).get(k).getAttackDamage();
                                        index = k;
                                    }
                                }
                                arena.getMap().get(affected_row).get(index).frozen = 1;
                                player.setMana(player.getMana() - player.getHero().getMana());
                                player.getHero().attacked_tur = 1;
                            }
                            if (player.getHero().getName().equals("Empress Thorina")) {
                                int max_health = arena.getMap().get(affected_row).get(0).getHealth();
                                int index = 0;
                                for (int k = 1; k < arena.getMap().get(affected_row).size(); k ++){
                                    if (max_health < arena.getMap().get(affected_row).get(k).getHealth()){
                                        max_health = arena.getMap().get(affected_row).get(k).getHealth();
                                        index = k;
                                    }
                                }
                                arena.getMap().get(affected_row).remove(index);
                                player.setMana(player.getMana() - player.getHero().getMana());
                                player.getHero().attacked_tur = 1;
                            }
                        }else {
                            output.addObject().put("command", "useHeroAbility")
                                    .put("affectedRow", affected_row)
                                    .put("error", "Selected row does not belong to the enemy.");
                        }
                    }
                }else {
                    if (p_idx == 1) {
                        if (affected_row >= 2){
                            if (player.getHero().getName().equals("King Mudface")) {
                                for (int k = 0; k < arena.getMap().get(affected_row).size(); k++){
                                    arena.getMap().get(affected_row).get(k).setHealth(
                                            arena.getMap().get(affected_row).get(k).getHealth() + 1);
                                }
                                player.setMana(player.getMana() - player.getHero().getMana());
                                player.getHero().attacked_tur = 1;
                            }
                            if (player.getHero().getName().equals("General Kocioraw")) {
                                for (int k = 0; k < arena.getMap().get(affected_row).size(); k++){
                                    arena.getMap().get(affected_row).get(k).setAttackDamage(
                                            arena.getMap().get(affected_row).get(k).getAttackDamage() + 1);
                                }
                                player.setMana(player.getMana() - player.getHero().getMana());
                                player.getHero().attacked_tur = 1;
                            }
                        }else {
                            output.addObject().put("command", "useHeroAbility")
                                    .put("affectedRow", affected_row)
                                    .put("error", "Selected row does not belong to the current player.");
                        }
                    }else {
                        if (affected_row < 2){
                            if (player.getHero().getName().equals("King Mudface")) {
                                for (int k = 0; k < arena.getMap().get(affected_row).size(); k++){
                                    arena.getMap().get(affected_row).get(k).setHealth(
                                            arena.getMap().get(affected_row).get(k).getHealth() + 1);
                                }
                                player.setMana(player.getMana() - player.getHero().getMana());
                                player.getHero().attacked_tur = 1;
                            }
                            if (player.getHero().getName().equals("General Kocioraw")) {
                                for (int k = 0; k < arena.getMap().get(affected_row).size(); k++){
                                    arena.getMap().get(affected_row).get(k).setAttackDamage(
                                            arena.getMap().get(affected_row).get(k).getAttackDamage() + 1);
                                }
                                player.setMana(player.getMana() - player.getHero().getMana());
                                player.getHero().attacked_tur = 1;
                            }
                        }else {
                            output.addObject().put("command", "useHeroAbility")
                                    .put("affectedRow", affected_row)
                                    .put("error", "Selected row does not belong to the current player.");
                        }
                    }
                }
            }else{
                output.addObject().put("command", "useHeroAbility")
                        .put("affectedRow", affected_row)
                        .put("error", "Hero has already attacked this turn.");
            }
        }else {
            output.addObject().put("command", "useHeroAbility")
                    .put("affectedRow", affected_row)
                    .put("error", "Not enough mana to use hero's ability.");
        }
    }
}
