package main;

import checker.Checker;

import classes.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import checker.CheckerConstants;
import fileio.Input;

import java.util.Collections;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

import static implement.CardUsesAbility.cardUsesAbility;
import static implement.UseAttackHero.useAttackHero;
import static implement.UseHeroAbility.useHeroAbility;

/**
 * The entry point to this homework. It runs the checker that tests your implentation.
 */
public final class Main {
    /**
     * for coding style
     */
    private Main() {
    }

    /**
     * DO NOT MODIFY MAIN METHOD
     * Call the checker
     * @param args from command line
     * @throws IOException in case of exceptions to reading / writing
     */
    public static void main(final String[] args) throws IOException {
        File directory = new File(CheckerConstants.TESTS_PATH);
        Path path = Paths.get(CheckerConstants.RESULT_PATH);

        if (Files.exists(path)) {
            File resultFile = new File(String.valueOf(path));
            for (File file : Objects.requireNonNull(resultFile.listFiles())) {
                file.delete();
            }
            resultFile.delete();
        }
        Files.createDirectories(path);

        for (File file : Objects.requireNonNull(directory.listFiles())) {
            String filepath = CheckerConstants.OUT_PATH + file.getName();
            File out = new File(filepath);
            boolean isCreated = out.createNewFile();
            if (isCreated) {
                action(file.getName(), filepath);
            }
        }

        Checker.calculateScore();
    }

    /**
     * @param filePath1 for input file
     * @param filePath2 for output file
     * @throws IOException in case of exceptions to reading / writing
     */
    public static void action(final String filePath1,
                              final String filePath2) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Input inputData = objectMapper.readValue(new File(CheckerConstants.TESTS_PATH + filePath1),
                Input.class);

        ArrayNode output = objectMapper.createArrayNode();

        //TODO add here the entry point to your implementation
        Arena arena = new Arena();
        for(int k = 0; k < 4; k++){
            arena.getMap().add(new ArrayList<>());
        }
        int player1_wins = 0;
        int player2_wins = 0;

        for(int i = 0; i < inputData.getGames().size(); i ++){
            int nr_rounds = 0;
            int nr_turs = 1;
            Playerv2 player1 = new Playerv2();
            int idx_player1 = inputData.getGames().get(i).getStartGame().getPlayerOneDeckIdx();
            player1.setNrCardsInDeck(inputData.getPlayerOneDecks().getNrCardsInDeck());
            player1.setHero(new Hero(inputData.getGames().get(i).getStartGame().getPlayerOneHero().getMana(),
                    inputData.getGames().get(i).getStartGame().getPlayerOneHero().getName(),
                    inputData.getGames().get(i).getStartGame().getPlayerOneHero().getColors(),
                    inputData.getGames().get(i).getStartGame().getPlayerOneHero().getDescription(), 30));

            Playerv2 player2 = new Playerv2();
            player2.setNrCardsInDeck(inputData.getPlayerOneDecks().getNrCardsInDeck());
            int idx_player2 = inputData.getGames().get(i).getStartGame().getPlayerTwoDeckIdx();
            player2.setHero(new Hero(inputData.getGames().get(i).getStartGame().getPlayerTwoHero().getMana(),
                    inputData.getGames().get(i).getStartGame().getPlayerTwoHero().getName(),
                    inputData.getGames().get(i).getStartGame().getPlayerTwoHero().getColors(),
                    inputData.getGames().get(i).getStartGame().getPlayerTwoHero().getDescription(), 30));

            player1.setMana(1);
            player2.setMana(1);

            int starting_player = inputData.getGames().get(i).getStartGame().getStartingPlayer();
            int seed = inputData.getGames().get(i).getStartGame().getShuffleSeed();

            Add_Deck.Add_deck(player1, inputData, idx_player1, 1);
            Add_Deck.Add_deck(player2, inputData, idx_player2, 2);

            Collections.shuffle(player1.getDeck(), new Random(seed));
            Collections.shuffle(player2.getDeck(), new Random(seed));

            player1.getHand().add(player1.getDeck().get(0));
            player1.getDeck().remove(0);

            player2.getHand().add(player2.getDeck().get(0));
            player2.getDeck().remove(0);

            for (int j = 0; j < inputData.getGames().get(i).getActions().size(); j++){
                String command = inputData.getGames().get(i).getActions().get(j).getCommand();
                if (command.equals("getPlayerDeck")){
                    int player_id = inputData.getGames().get(i).getActions().get(j).getPlayerIdx();
                    Playerv2 c_player1 = new Playerv2(player1);
                    Playerv2 c_player2 = new Playerv2(player2);
                    if(player_id == 1){
                        output.addObject().put("command", "getPlayerDeck").put("playerIdx", player_id)
                                .putPOJO("output", c_player1.getDeck());
                    }else {
                        output.addObject().put("command", "getPlayerDeck").put("playerIdx", player_id)
                                .putPOJO("output", c_player2.getDeck());
                    }
                }
                if (command.equals("getPlayerHero")){
                    Playerv2 c_player1 = new Playerv2(player1);
                    Playerv2 c_player2 = new Playerv2(player2);
                    int hero_idx = inputData.getGames().get(i).getActions().get(j).getPlayerIdx();
                    if (hero_idx == 1){
                        output.addObject().put("command", "getPlayerHero").put("playerIdx", hero_idx)
                                .putPOJO("output", c_player1.getHero());
                    }else {
                        output.addObject().put("command", "getPlayerHero").put("playerIdx", hero_idx)
                                .putPOJO("output", c_player2.getHero());
                    }
                }
                if (command.equals("getPlayerTurn")){
                    output.addObject().put("command", "getPlayerTurn").put("output", starting_player);
                }
                if (command.equals("endPlayerTurn")){
                    nr_rounds ++;
                    if(nr_rounds % 2 == 0){
                        nr_turs++;
                        if(player1.getDeck().size() != 0) {
                            player1.getHand().add(player1.getDeck().get(0));
                            player1.getDeck().remove(0);
                        }
                        if(player2.getDeck().size() != 0) {
                            player2.getHand().add(player2.getDeck().get(0));
                            player2.getDeck().remove(0);
                        }
                        if(nr_turs >= 10) {
                            player1.setMana(player1.getMana() + 10);
                            player2.setMana(player2.getMana() + 10);
                        }else {
                            player1.setMana(player1.getMana() + nr_turs);
                            player2.setMana(player2.getMana() + nr_turs);
                        }
                    }
                    if (starting_player == 1){
                        for (int l = 2; l < 4; l ++){
                            for (int c = 0; c < arena.getMap().get(l).size(); c++){
                                arena.getMap().get(l).get(c).frozen = 0;
                                arena.getMap().get(l).get(c).attacked_tur = 0;
                                player1.getHero().attacked_tur = 0;
                            }
                        }
                        starting_player = 2;
                    }else {
                        for (int l = 0; l < 2; l ++){
                            for (int c = 0; c < arena.getMap().get(l).size(); c++){
                                arena.getMap().get(l).get(c).frozen = 0;
                                arena.getMap().get(l).get(c).attacked_tur = 0;
                                player2.getHero().attacked_tur = 0;
                            }
                        }
                        starting_player = 1;
                    }

                }
                if (command.equals("placeCard")){
                    int hand_idx = inputData.getGames().get(i).getActions().get(j).getHandIdx();
                    if (starting_player == 1){
                        if (player1.getHand().get(hand_idx).getName().equals("Firestorm") ||
                        player1.getHand().get(hand_idx).getName().equals("Winterfell") ||
                        player1.getHand().get(hand_idx).getName().equals("Heart Hound")){
                            output.addObject().put("command", "placeCard")
                                    .put("error", "Cannot place environment card on table.")
                                    .put("handIdx", hand_idx);
                        }else {
                            if (player1.getMana() >= player1.getHand().get(hand_idx).getMana()){
                                if (player1.getHand().get(hand_idx).getName().equals("The Ripper") ||
                                player1.getHand().get(hand_idx).getName().equals("Miraj") ||
                                player1.getHand().get(hand_idx).getName().equals("Goliath") ||
                                player1.getHand().get(hand_idx).getName().equals("Warden")){
                                    if(arena.getMap().get(2).size() < 5){
                                        arena.getMap().get(2).add((Minion) player1.getHand().get(hand_idx));
                                        player1.setMana(player1.getMana() - player1.getHand().get(hand_idx).getMana());
                                        player1.getHand().remove(hand_idx);
                                    }else {
                                        output.addObject().put("command", "placeCard")
                                                .put("error", "Cannot place card on table since row is full.")
                                                .put("handIdx", hand_idx);
                                    }
                                }else{
                                    if(arena.getMap().get(3).size() < 5){
                                        arena.getMap().get(3).add((Minion) player1.getHand().get(hand_idx));
                                        player1.setMana(player1.getMana() - player1.getHand().get(hand_idx).getMana());
                                        player1.getHand().remove(hand_idx);
                                    }else {
                                        output.addObject().put("command", "placeCard")
                                                .put("error", "Cannot place card on table since row is full.")
                                                .put("handIdx", hand_idx);
                                    }
                                }
                            }else{
                                output.addObject().put("command", "placeCard")
                                        .put("error", "Not enough mana to place card on table.")
                                        .put("handIdx", hand_idx);
                            }
                        }
                    }
                    if(starting_player == 2){
                        if (player2.getHand().get(hand_idx).getName().equals("Firestorm") ||
                                player2.getHand().get(hand_idx).getName().equals("Winterfell") ||
                                player2.getHand().get(hand_idx).getName().equals("Heart Hound")){
                            output.addObject().put("command", "placeCard")
                                    .put("error", "Cannot place environment card on table.")
                                    .put("handIdx", hand_idx);
                        }else {
                            if (player2.getMana() >= player2.getHand().get(hand_idx).getMana()){
                                if (player2.getHand().get(hand_idx).getName().equals("The Ripper") ||
                                        player2.getHand().get(hand_idx).getName().equals("Miraj") ||
                                        player2.getHand().get(hand_idx).getName().equals("Goliath") ||
                                        player2.getHand().get(hand_idx).getName().equals("Warden")){
                                    if(arena.getMap().get(1).size() < 5){
                                        arena.getMap().get(1).add((Minion) player2.getHand().get(hand_idx));
                                        player2.setMana(player2.getMana() - player2.getHand().get(hand_idx).getMana());
                                        player2.getHand().remove(hand_idx);
                                    }else {
                                        output.addObject().put("command", "placeCard")
                                                .put("error", "Cannot place card on table since row is full.")
                                                .put("handIdx", hand_idx);
                                    }
                                }else {
                                    if(arena.getMap().get(0).size() < 5){
                                        arena.getMap().get(0).add((Minion) player2.getHand().get(hand_idx));
                                        player2.setMana(player2.getMana() - player2.getHand().get(hand_idx).getMana());
                                        player2.getHand().remove(hand_idx);
                                    }else {
                                        output.addObject().put("command", "placeCard")
                                                .put("error", "Cannot place card on table since row is full.")
                                                .put("handIdx", hand_idx);
                                    }
                                }
                            }else{
                                output.addObject().put("command", "placeCard")
                                        .put("error", "Not enough mana to place card on table.")
                                        .put("handIdx", hand_idx);
                            }
                        }
                    }
                }
                if (command.equals("getCardsInHand")){
                    ArrayList<Cards> new_array = new ArrayList<>();
                    int player_idx = inputData.getGames().get(i).getActions().get(j).getPlayerIdx();
                    if(player_idx == 1){
                        for (int k = 0; k < player1.getHand().size(); k++){
                            if (player1.getHand().get(k).getName().equals("Firestorm") ||
                                    player1.getHand().get(k).getName().equals("Winterfell") ||
                                    player1.getHand().get(k).getName().equals("Heart Hound")) {
                                Environment new_env = new Environment(player1.getHand().get(k).getMana(),
                                        player1.getHand().get(k).getName(),
                                        player1.getHand().get(k).getColors(),
                                        player1.getHand().get(k).getDescription());
                                new_array.add(new_env);
                            }else{
                                Minion new_min = new Minion((Minion) player1.getHand().get(k));
                                new_array.add(new_min);
                            }
                        }
                    }else {
                        for (int k = 0; k < player2.getHand().size(); k++){
                            if (player2.getHand().get(k).getName().equals("Firestorm") ||
                                    player2.getHand().get(k).getName().equals("Winterfell") ||
                                    player2.getHand().get(k).getName().equals("Heart Hound")) {
                                Environment new_env = new Environment(player2.getHand().get(k).getMana(),
                                        player2.getHand().get(k).getName(),
                                        player2.getHand().get(k).getColors(),
                                        player2.getHand().get(k).getDescription());
                                new_array.add(new_env);
                            }else{
                                Minion new_min = new Minion((Minion) player2.getHand().get(k));
                                new_array.add(new_min);
                            }
                        }
                    }
                    output.addObject().put("command", "getCardsInHand").put("playerIdx", player_idx)
                            .putPOJO("output", new_array);
                }
                if (command.equals("getPlayerMana")) {
                    int player_idx = inputData.getGames().get(i).getActions().get(j).getPlayerIdx();
                    Playerv2 c_player1 = new Playerv2(player1);
                    Playerv2 c_player2 = new Playerv2(player2);
                    if(player_idx == 1){
                        output.addObject().put("command", "getPlayerMana").put("playerIdx", player_idx)
                                .putPOJO("output", c_player1.getMana());
                    }else {
                        output.addObject().put("command", "getPlayerMana").put("playerIdx", player_idx)
                                .putPOJO("output", c_player2.getMana());
                    }
                }
                if (command.equals("getCardsOnTable")) {
                    Arena new_arena = new Arena();
                    new_arena.setMap(new ArrayList<>());
                    for(int k = 0; k < arena.getMap().size(); k++){
                        new_arena.getMap().add(new ArrayList<>());
                    }
                    for (int k = 0; k < arena.getMap().size(); k++){
                        for (int k2 = 0; k2 < arena.getMap().get(k).size(); k2  ++)
                            new_arena.getMap().get(k).add(new Minion(arena.getMap().get(k).get(k2)));
                    }
                    output.addObject().put("command", "getCardsOnTable").putPOJO("output", new_arena.getMap());
                }
                if (command.equals("getEnvironmentCardsInHand")){
                    int player_idx = inputData.getGames().get(i).getActions().get(j).getPlayerIdx();
                    ArrayList<Environment> new_environment = new ArrayList<>();
                    if(player_idx == 1) {
                        for (int k = 0; k < player1.getHand().size(); k++){
                            if (player1.getHand().get(k).getName().equals("Firestorm") ||
                            player1.getHand().get(k).getName().equals("Winterfell") ||
                            player1.getHand().get(k).getName().equals("Heart Hound")){
                                new_environment.add((Environment) player1.getHand().get(k));
                            }
                        }
                    }else{
                        for (int k = 0; k < player2.getHand().size(); k++){
                            if (player2.getHand().get(k).getName().equals("Firestorm") ||
                                    player2.getHand().get(k).getName().equals("Winterfell") ||
                                    player2.getHand().get(k).getName().equals("Heart Hound")){
                                new_environment.add((Environment) player2.getHand().get(k));
                            }
                        }
                    }
                    output.addObject().put("command", "getEnvironmentCardsInHand")
                            .put("playerIdx", player_idx).putPOJO("output", new_environment);
                }
                if (command.equals("useEnvironmentCard")) {
                    int hand_idx = inputData.getGames().get(i).getActions().get(j).getHandIdx();
                    int affected_row = inputData.getGames().get(i).getActions().get(j).getAffectedRow();
                    if (starting_player == 1){
                        if(player1.getHand().get(hand_idx).getName().equals("Firestorm") ||
                        player1.getHand().get(hand_idx).getName().equals("Winterfell") ||
                        player1.getHand().get(hand_idx).getName().equals("Heart Hound")) {
                            if (player1.getMana() >= player1.getHand().get(hand_idx).getMana()){
                                if(affected_row == 0 || affected_row == 1){
                                    if(player1.getHand().get(hand_idx).getName().equals("Heart Hound") &&
                                      arena.getMap().get(3 - affected_row).size() == 5) {
                                        output.addObject().put("affectedRow", affected_row)
                                                .put("command", "useEnvironmentCard")
                                                .put("error", "Cannot steal enemy card since the player's row is full.")
                                                .put("handIdx", hand_idx);
                                    }else {
                                        Environment new_environment = new Environment(player1.getHand().get(hand_idx).getMana(),
                                        player1.getHand().get(hand_idx).getName(), player1.getHand().get(hand_idx).getColors(),
                                        player1.getHand().get(hand_idx).getDescription());
                                        UseEnvironment.Environment(arena, new_environment, affected_row, player1, player2);
                                        player1.getHand().remove(hand_idx);
                                    }
                                }else{
                                    output.addObject().put("affectedRow", affected_row)
                                            .put("command", "useEnvironmentCard")
                                            .put("error", "Chosen row does not belong to the enemy.")
                                            .put("handIdx", hand_idx);
                                }
                            }else {
                                output.addObject().put("affectedRow", affected_row)
                                        .put("command", "useEnvironmentCard")
                                        .put("error", "Not enough mana to use environment card.")
                                        .put("handIdx", hand_idx);
                            }
                        }else{
                            output.addObject().put("affectedRow", affected_row)
                                    .put("command", "useEnvironmentCard")
                                    .put("error", "Chosen card is not of type environment.")
                                    .put("handIdx", hand_idx);
                        }
                    }
                    if (starting_player == 2){
                        if(player2.getHand().get(hand_idx).getName().equals("Firestorm") ||
                                player2.getHand().get(hand_idx).getName().equals("Winterfell") ||
                                player2.getHand().get(hand_idx).getName().equals("Heart Hound")) {
                            if (player2.getMana() >= player2.getHand().get(hand_idx).getMana()){
                                if(affected_row == 2 || affected_row == 3){
                                    if(player2.getHand().get(hand_idx).getName().equals("Heart Hound") &&
                                            arena.getMap().get(3 - affected_row).size() == 5) {
                                        output.addObject().put("affectedRow", affected_row)
                                                .put("command", "useEnvironmentCard")
                                                .put("error", "Cannot steal enemy card since the player's row is full.")
                                                .put("handIdx", hand_idx);
                                    }else {
                                        Environment new_environment = new Environment(player2.getHand().get(hand_idx).getMana(),
                                                player2.getHand().get(hand_idx).getName(), player2.getHand().get(hand_idx).getColors(),
                                                player2.getHand().get(hand_idx).getDescription());
                                        UseEnvironment.Environment(arena, new_environment, affected_row, player1, player2);
                                        player2.getHand().remove(hand_idx);
                                    }
                                }else{
                                    output.addObject().put("affectedRow", affected_row)
                                            .put("command", "useEnvironmentCard")
                                            .put("error", "Chosen row does not belong to the enemy.")
                                            .put("handIdx", hand_idx);
                                }
                            }else {
                                output.addObject().put("affectedRow", affected_row)
                                        .put("command", "useEnvironmentCard")
                                        .put("error", "Not enough mana to use environment card.")
                                        .put("handIdx", hand_idx);
                            }
                        }else{
                            output.addObject().put("affectedRow", affected_row)
                                    .put("command", "useEnvironmentCard")
                                    .put("error", "Chosen card is not of type environment.")
                                    .put("handIdx", hand_idx);
                        }
                    }
                }
                if (command.equals("getCardAtPosition")) {
                    int row = inputData.getGames().get(i).getActions().get(j).getX();
                    int column = inputData.getGames().get(i).getActions().get(j).getY();
                    if (row > arena.getMap().size() || column > arena.getMap().get(row).size()){
                        output.addObject().put("command", "getCardAtPosition").put("x", row)
                                .put("y", column).put("output", "No card available at that position.");
                    }
                    if (row < arena.getMap().size() && column < arena.getMap().get(row).size()) {
                        if (arena.getMap().get(row).get(column) != null) {
                            Minion new_min = new Minion(arena.getMap().get(row).get(column));
                            output.addObject().put("command", "getCardAtPosition").put("x", row)
                                    .put("y", column).putPOJO("output", new_min);
                        } else {
                            output.addObject().put("command", "getCardAtPosition").put("x", row)
                                    .put("y", column).put("output", "No card at that position.");
                        }
                    }
                }
                if (command.equals("getFrozenCardsOnTable")){
                    ArrayList<Minion> new_array = new ArrayList<Minion>();
                    for (int l = 0; l < arena.getMap().size(); l++) {
                        for (int c = 0; c < arena.getMap().get(l).size(); c++) {
                            if (arena.getMap().get(l).get(c).frozen == 1){
                                new_array.add(arena.getMap().get(l).get(c));
                            }
                        }
                    }
                    output.addObject().put("command", "getFrozenCardsOnTable")
                            .putPOJO("output", new_array);
                }
                if (command.equals("cardUsesAttack")){
                    int x_attacker = inputData.getGames().get(i).getActions().get(j).getCardAttacker().getX();
                    int y_attacker = inputData.getGames().get(i).getActions().get(j).getCardAttacker().getY();
                    int x_attacked = inputData.getGames().get(i).getActions().get(j).getCardAttacked().getX();
                    int y_attacked = inputData.getGames().get(i).getActions().get(j).getCardAttacked().getY();
                    Coord coordinates_attacked = new Coord(x_attacked, y_attacked);
                    Coord coordinates_attacker = new Coord(x_attacker, y_attacker);

                    if ((x_attacker < 2 && x_attacked >= 2) || (x_attacker >= 2 && x_attacked < 2)){
                        if (arena.getMap().get(x_attacker).get(y_attacker).attacked_tur == 0) {
                            if (arena.getMap().get(x_attacker).get(y_attacker).frozen == 0){
                                int sem_tank = -1;
                                if (x_attacker < 2){
                                    for (int k = 0; k < arena.getMap().get(2).size(); k++) {
                                        if (arena.getMap().get(2).get(k).getName().equals("Goliath") ||
                                        arena.getMap().get(2).get(k).getName().equals("Warden")) {
                                            sem_tank = k;
                                            break;
                                        }
                                    }
                                    if (sem_tank != -1){
                                        if (arena.getMap().get(x_attacked).get(y_attacked).getName().equals("Goliath") ||
                                                arena.getMap().get(x_attacked).get(y_attacked).getName().equals("Warden")){

                                            arena.getMap().get(x_attacked).get(y_attacked).setHealth(
                                                    arena.getMap().get(x_attacked).get(y_attacked).getHealth() -
                                                            arena.getMap().get(x_attacker).get(y_attacker).getAttackDamage());
                                            arena.getMap().get(x_attacker).get(y_attacker).attacked_tur = 1;

                                            if (arena.getMap().get(x_attacked).get(y_attacked).getHealth() <= 0){
                                                arena.getMap().get(x_attacked).remove(y_attacked);
                                            }
                                        }else {
                                            addPojo_coordinates clas = new addPojo_coordinates("cardUsesAttack",
                                                    "Attacked card is not of type 'Tank'.", coordinates_attacked, coordinates_attacker);
                                            output.addPOJO(clas);
                                        }
                                    }else {
                                        if(y_attacked < arena.getMap().get(x_attacked).size()) {
                                            arena.getMap().get(x_attacked).get(y_attacked).setHealth(
                                                    arena.getMap().get(x_attacked).get(y_attacked).getHealth() -
                                                            arena.getMap().get(x_attacker).get(y_attacker).getAttackDamage());
                                            arena.getMap().get(x_attacker).get(y_attacker).attacked_tur = 1;
                                            if (arena.getMap().get(x_attacked).get(y_attacked).getHealth() <= 0){
                                                arena.getMap().get(x_attacked).remove(y_attacked);
                                            }
                                        }
                                    }
                                }else {
                                    for (int k = 0; k < arena.getMap().get(1).size(); k++) {
                                        if (arena.getMap().get(1).get(k).getName().equals("Goliath") ||
                                                arena.getMap().get(1).get(k).getName().equals("Warden")) {
                                            sem_tank = k;
                                            break;
                                        }
                                    }
                                    if (sem_tank != -1){
                                        if (arena.getMap().get(x_attacked).get(y_attacked).getName().equals("Goliath") ||
                                                arena.getMap().get(x_attacked).get(y_attacked).getName().equals("Warden")){


                                            arena.getMap().get(x_attacked).get(y_attacked).setHealth(
                                                    arena.getMap().get(x_attacked).get(y_attacked).getHealth() -
                                                            arena.getMap().get(x_attacker).get(y_attacker).getAttackDamage());
                                            arena.getMap().get(x_attacker).get(y_attacker).attacked_tur = 1;
                                            if (arena.getMap().get(x_attacked).get(y_attacked).getHealth() <= 0){
                                                arena.getMap().get(x_attacked).remove(y_attacked);
                                            }

                                        }else {
                                            addPojo_coordinates clas = new addPojo_coordinates("cardUsesAttack",
                                                    "Attacked card is not of type 'Tank'.", coordinates_attacked, coordinates_attacker);
                                            output.addPOJO(clas);
                                        }
                                    }else {
                                        arena.getMap().get(x_attacked).get(y_attacked).setHealth(
                                                arena.getMap().get(x_attacked).get(y_attacked).getHealth() -
                                                        arena.getMap().get(x_attacker).get(y_attacker).getAttackDamage());
                                        arena.getMap().get(x_attacker).get(y_attacker).attacked_tur = 1;
                                        if (arena.getMap().get(x_attacked).get(y_attacked).getHealth() <= 0){
                                            arena.getMap().get(x_attacked).remove(y_attacked);
                                        }
                                    }
                                }
                            }else {
                                addPojo_coordinates clas = new addPojo_coordinates("cardUsesAttack",
                                        "Attacker card is frozen.", coordinates_attacked, coordinates_attacker);
                                output.addPOJO(clas);
                            }
                        }else {
                            addPojo_coordinates clas = new addPojo_coordinates("cardUsesAttack",
                                    "Attacker card has already attacked this turn.", coordinates_attacked, coordinates_attacker);
                            output.addPOJO(clas);
                        }
                    }else{
                        addPojo_coordinates clas = new addPojo_coordinates("cardUsesAttack",
                                "Attacked card does not belong to the enemy.", coordinates_attacked, coordinates_attacker);
                        output.addPOJO(clas);
                    }

                }
                if (command.equals("cardUsesAbility")){
                    cardUsesAbility(inputData, output, arena, i, j);
                }
                if (command.equals("useAttackHero")) {
                    int x = useAttackHero(inputData, output, arena, i, j, player1, player2);
                    if (x == 1){
                        player1_wins ++;
                    }
                    if (x == -1){
                        player2_wins ++;
                    }
                }
                if (command.equals("useHeroAbility")){
                    int affected_row = inputData.getGames().get(i).getActions().get(j).getAffectedRow();
                    if (starting_player == 1){
                        useHeroAbility(inputData, output, arena, affected_row, player1, 1);
                    }else {
                        useHeroAbility(inputData, output, arena, affected_row, player2, 2);
                    }
                }
                if (command.equals("getPlayerOneWins")){
                    output.addObject().put("command", "getPlayerOneWins")
                            .put("output", player1_wins);
                }
                if (command.equals("getPlayerTwoWins")){
                    output.addObject().put("command", "getPlayerTwoWins")
                            .put("output", player2_wins);
                }
                if (command.equals("getTotalGamesPlayed")){
                    output.addObject().put("command", "getTotalGamesPlayed")
                            .put("output", player1_wins + player2_wins);
                }
            }
            for (int c = 0; c < 4; c++)
                arena.getMap().get(c).clear();
        }

        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        objectWriter.writeValue(new File(filePath2), output);
    }
}
