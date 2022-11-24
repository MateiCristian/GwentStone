package main;

import checker.Checker;

import classes.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import checker.CheckerConstants;
import fileio.Input;
import implement.AddDeck;
import implement.AddPojocoordinates;
import implement.Coord;
import implement.UseEnvironment;

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
    //Declaring final variables for checkstyle
    static final int int30 = 30;
    static final int int10 = 10;
    static final int int5 = 5;
    static final int int4 = 4;
    static final int int3 = 3;
    private Main() {
    }

    /**
     * DO NOT MODIFY MAIN METHOD
     * Call the checker
     *
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
        Input inputData = objectMapper.readValue(new File(CheckerConstants.TESTS_PATH
                        + filePath1),
                Input.class);

        ArrayNode output = objectMapper.createArrayNode();

        //TODO add here the entry point to your implementation
        //Declaring a game arena
        Arena arena = new Arena();
        for (int k = 0; k < 2 * 2; k++) {
            arena.getMap().add(new ArrayList<>());
        }
        //Numbers of victories
        int player1wins = 0;
        int player2wins = 0;

        for (int i = 0; i < inputData.getGames().size(); i++) {
            int nrrounds = 0; //number of rounds played
            int nrturs = 1; //number of turns played
            //Declaring and setting values for player1
            Playerv2 player1 = new Playerv2();
            int idxplayer1 = inputData.getGames().get(i).getStartGame().getPlayerOneDeckIdx();
            player1.setNrCardsInDeck(inputData.getPlayerOneDecks().getNrCardsInDeck());
            player1.setHero(new Hero(inputData.getGames().get(i).getStartGame()
                    .getPlayerOneHero().getMana(),
                    inputData.getGames().get(i).getStartGame().getPlayerOneHero().getName(),
                    inputData.getGames().get(i).getStartGame().getPlayerOneHero().getColors(),
                    inputData.getGames().get(i).getStartGame().getPlayerOneHero()
                            .getDescription(), int30));

            //Declaring and setting values for player1
            Playerv2 player2 = new Playerv2();
            player2.setNrCardsInDeck(inputData.getPlayerOneDecks().getNrCardsInDeck());
            int idxplayer2 = inputData.getGames().get(i).getStartGame().getPlayerTwoDeckIdx();
            player2.setHero(new Hero(inputData.getGames().get(i).getStartGame()
                    .getPlayerTwoHero().getMana(),
                    inputData.getGames().get(i).getStartGame().getPlayerTwoHero().getName(),
                    inputData.getGames().get(i).getStartGame().getPlayerTwoHero().getColors(),
                    inputData.getGames().get(i).getStartGame().getPlayerTwoHero()
                            .getDescription(), int30));

            player1.setMana(1);
            player2.setMana(1);

            int startingplayer = inputData.getGames().get(i).getStartGame().getStartingPlayer();
            int seed = inputData.getGames().get(i).getStartGame().getShuffleSeed();
            //Setting decks for players
            AddDeck.adddeck(player1, inputData, idxplayer1, 1);
            AddDeck.adddeck(player2, inputData, idxplayer2, 2);

            Collections.shuffle(player1.getDeck(), new Random(seed));
            Collections.shuffle(player2.getDeck(), new Random(seed));
            //Setting hands cards for players
            player1.getHand().add(player1.getDeck().get(0));
            player1.getDeck().remove(0);

            player2.getHand().add(player2.getDeck().get(0));
            player2.getDeck().remove(0);


            for (int j = 0; j < inputData.getGames().get(i).getActions().size(); j++) {
                //Obtain a command
                String command = inputData.getGames().get(i).getActions().get(j).getCommand();
                //Parsing the command by its name

                if (command.equals("getPlayerDeck")) {
                    int playerid = inputData.getGames().get(i).getActions().get(j).getPlayerIdx();
                    //Creating deep copy for output
                    Playerv2 cplayer1 = new Playerv2(player1);
                    Playerv2 cplayer2 = new Playerv2(player2);
                    //Creating the output
                    if (playerid == 1) {
                        output.addObject().put("command", "getPlayerDeck")
                                .put("playerIdx", playerid)
                                .putPOJO("output", cplayer1.getDeck());
                    } else {
                        output.addObject().put("command", "getPlayerDeck")
                                .put("playerIdx", playerid)
                                .putPOJO("output", cplayer2.getDeck());
                    }
                }

                if (command.equals("getPlayerHero")) {
                    //Creating deep copy for output
                    Playerv2 cplayer1 = new Playerv2(player1);
                    Playerv2 cplayer2 = new Playerv2(player2);
                    int heroidx = inputData.getGames().get(i).getActions().get(j).getPlayerIdx();
                    if (heroidx == 1) {
                        output.addObject().put("command", "getPlayerHero")
                                .put("playerIdx", heroidx)
                                .putPOJO("output", cplayer1.getHero());
                    } else {
                        output.addObject().put("command", "getPlayerHero")
                                .put("playerIdx", heroidx)
                                .putPOJO("output", cplayer2.getHero());
                    }
                }

                if (command.equals("getPlayerTurn")) {
                    output.addObject().put("command", "getPlayerTurn")
                            .put("output", startingplayer);
                }

                if (command.equals("endPlayerTurn")) {
                    nrrounds++;
                    if (nrrounds % 2 == 0) {
                        nrturs++;
                        //Adding cards in hand
                        if (player1.getDeck().size() != 0) {
                            player1.getHand().add(player1.getDeck().get(0));
                            player1.getDeck().remove(0);
                        }
                        if (player2.getDeck().size() != 0) {
                            player2.getHand().add(player2.getDeck().get(0));
                            player2.getDeck().remove(0);
                        }
                        //Setting mana for players
                        if (nrturs >= int10) {
                            player1.setMana(player1.getMana() + int10);
                            player2.setMana(player2.getMana() + int10);
                        } else {
                            player1.setMana(player1.getMana() + nrturs);
                            player2.setMana(player2.getMana() + nrturs);
                        }
                    }
                    //Resetting frozen cards and attacked tur for both players
                    if (startingplayer == 1) {
                        for (int l = 2; l < int4; l++) {
                            for (int c = 0; c < arena.getMap().get(l).size(); c++) {
                                arena.getMap().get(l).get(c).setFrozen(0);
                                arena.getMap().get(l).get(c).setAttackedtur(0);
                                player1.getHero().setAttackedtur(0);
                            }
                        }
                        startingplayer = 2;
                    } else {
                        for (int l = 0; l < 2; l++) {
                            for (int c = 0; c < arena.getMap().get(l).size(); c++) {
                                arena.getMap().get(l).get(c).setFrozen(0);
                                arena.getMap().get(l).get(c).setAttackedtur(0);
                                player2.getHero().setAttackedtur(0);
                            }
                        }
                        startingplayer = 1;
                    }

                }

                if (command.equals("placeCard")) {
                    int handidx = inputData.getGames().get(i).getActions().get(j).getHandIdx();
                    if (startingplayer == 1) {
                        if (player1.getHand().get(handidx).getName().equals("Firestorm")
                                || player1.getHand().get(handidx).getName().equals("Winterfell")
                                || player1.getHand().get(handidx).getName().equals("Heart Hound")) {
                            output.addObject().put("command", "placeCard")
                                    .put("error",
                                            "Cannot place environment card on table.")
                                    .put("handIdx", handidx);
                        } else {
                            //Testing if player has enough mana
                            if (player1.getMana() >= player1.getHand().get(handidx).getMana()) {
                                //Testing cards by name to know where must be placed
                                if (player1.getHand().get(handidx).getName().equals("The Ripper")
                                        || player1.getHand().get(handidx).getName().equals("Miraj")
                                        || player1.getHand().get(handidx).getName()
                                        .equals("Goliath")
                                        || player1.getHand().get(handidx).getName()
                                        .equals("Warden")) {
                                    //If it is enough space on table
                                    if (arena.getMap().get(2).size() < int5) {
                                        arena.getMap().get(2).add((Minion) player1.getHand()
                                                .get(handidx));
                                        player1.setMana(player1.getMana() - player1.getHand()
                                                .get(handidx).getMana());
                                        player1.getHand().remove(handidx);
                                    } else {
                                        output.addObject().put("command", "placeCard")
                                                .put("error", "Cannot place card on "
                                                        + "table since row is full.")
                                                .put("handIdx", handidx);
                                    }
                                } else {
                                    //If it is enough space on table
                                    if (arena.getMap().get(int3).size() < int5) {
                                        arena.getMap().get(int3).add((Minion) player1.getHand()
                                                .get(handidx));
                                        player1.setMana(player1.getMana() - player1.getHand()
                                                .get(handidx).getMana());
                                        player1.getHand().remove(handidx);
                                    } else {
                                        output.addObject().put("command", "placeCard")
                                                .put("error", "Cannot place card on "
                                                        + "table since row is full.")
                                                .put("handIdx", handidx);
                                    }
                                }
                            } else {
                                output.addObject().put("command", "placeCard")
                                        .put("error", "Not enough mana to place card"
                                                + " on table.")
                                        .put("handIdx", handidx);
                            }
                        }
                    }
                    //Same thing for player 2
                    if (startingplayer == 2) {
                        if (player2.getHand().get(handidx).getName().equals("Firestorm")
                                || player2.getHand().get(handidx).getName().equals("Winterfell")
                                || player2.getHand().get(handidx).getName().equals("Heart Hound")) {
                            output.addObject().put("command", "placeCard")
                                    .put("error",
                                            "Cannot place environment card on table.")
                                    .put("handIdx", handidx);
                        } else {
                            if (player2.getMana() >= player2.getHand().get(handidx).getMana()) {
                                if (player2.getHand().get(handidx).getName().equals("The Ripper")
                                        || player2.getHand().get(handidx).getName().equals("Miraj")
                                        || player2.getHand().get(handidx).getName()
                                        .equals("Goliath")
                                        || player2.getHand().get(handidx).getName()
                                        .equals("Warden")) {
                                    if (arena.getMap().get(1).size() < int5) {
                                        arena.getMap().get(1).add((Minion) player2.getHand()
                                                .get(handidx));
                                        player2.setMana(player2.getMana() - player2.getHand()
                                                .get(handidx).getMana());
                                        player2.getHand().remove(handidx);
                                    } else {
                                        output.addObject().put("command", "placeCard")
                                                .put("error", "Cannot place card on "
                                                        + "table since row is full.")
                                                .put("handIdx", handidx);
                                    }
                                } else {
                                    if (arena.getMap().get(0).size() < int5) {
                                        arena.getMap().get(0).add((Minion) player2.getHand()
                                                .get(handidx));
                                        player2.setMana(player2.getMana() - player2.getHand()
                                                .get(handidx).getMana());
                                        player2.getHand().remove(handidx);
                                    } else {
                                        output.addObject().put("command", "placeCard")
                                                .put("error", "Cannot place card on "
                                                        + "table since row is full.")
                                                .put("handIdx", handidx);
                                    }
                                }
                            } else {
                                output.addObject().put("command", "placeCard")
                                        .put("error", "Not enough mana to place "
                                                + "card on table.")
                                        .put("handIdx", handidx);
                            }
                        }
                    }
                }

                if (command.equals("getCardsInHand")) {
                    ArrayList<Cards> newarray = new ArrayList<>();
                    int playeridx = inputData.getGames().get(i).getActions().get(j).getPlayerIdx();
                    if (playeridx == 1) {
                        //For each cards testing if it is environment or minion
                        for (int k = 0; k < player1.getHand().size(); k++) {
                            if (player1.getHand().get(k).getName().equals("Firestorm")
                                    || player1.getHand().get(k).getName().equals("Winterfell")
                                    || player1.getHand().get(k).getName().equals("Heart Hound")) {
                                //Creating depp copy for environment card
                                Environment newenv = new Environment(player1.getHand().get(k)
                                        .getMana(), player1.getHand().get(k).getName(),
                                        player1.getHand().get(k).getColors(),
                                        player1.getHand().get(k).getDescription());
                                newarray.add(newenv);
                            } else {
                                //Creating deep copy for minion card
                                Minion newmin = new Minion((Minion) player1.getHand().get(k));
                                newarray.add(newmin);
                            }
                        }
                    } else {
                        //Same thing but for player 2
                        for (int k = 0; k < player2.getHand().size(); k++) {
                            if (player2.getHand().get(k).getName().equals("Firestorm")
                                    || player2.getHand().get(k).getName().equals("Winterfell")
                                    || player2.getHand().get(k).getName().equals("Heart Hound")) {
                                Environment newenv = new Environment(player2.getHand().get(k)
                                        .getMana(), player2.getHand().get(k).getName(),
                                        player2.getHand().get(k).getColors(),
                                        player2.getHand().get(k).getDescription());
                                newarray.add(newenv);
                            } else {
                                Minion newmin = new Minion((Minion) player2.getHand().get(k));
                                newarray.add(newmin);
                            }
                        }
                    }
                    output.addObject().put("command", "getCardsInHand")
                            .put("playerIdx", playeridx)
                            .putPOJO("output", newarray);
                }

                if (command.equals("getPlayerMana")) {
                    int playeridx = inputData.getGames().get(i).getActions().get(j).getPlayerIdx();
                    Playerv2 cplayer1 = new Playerv2(player1);
                    Playerv2 cplayer2 = new Playerv2(player2);
                    if (playeridx == 1) {
                        output.addObject().put("command", "getPlayerMana")
                                .put("playerIdx", playeridx)
                                .putPOJO("output", cplayer1.getMana());
                    } else {
                        output.addObject().put("command", "getPlayerMana")
                                .put("playerIdx", playeridx)
                                .putPOJO("output", cplayer2.getMana());
                    }
                }

                if (command.equals("getCardsOnTable")) {
                    //Creating a deep copy for game arena
                    Arena newarena = new Arena();
                    newarena.setMap(new ArrayList<>());
                    for (int k = 0; k < arena.getMap().size(); k++) {
                        newarena.getMap().add(new ArrayList<>());
                    }
                    for (int k = 0; k < arena.getMap().size(); k++) {
                        for (int k2 = 0; k2 < arena.getMap().get(k).size(); k2++) {
                            newarena.getMap().get(k).add(new Minion(arena.getMap().get(k).get(k2)));
                        }
                    }
                    output.addObject().put("command", "getCardsOnTable")
                            .putPOJO("output", newarena.getMap());
                }

                if (command.equals("getEnvironmentCardsInHand")) {
                    int playeridx = inputData.getGames().get(i).getActions().get(j).getPlayerIdx();
                    ArrayList<Environment> newenvironment = new ArrayList<>();
                    if (playeridx == 1) {
                        for (int k = 0; k < player1.getHand().size(); k++) {
                            if (player1.getHand().get(k).getName().equals("Firestorm")
                                    || player1.getHand().get(k).getName().equals("Winterfell")
                                    || player1.getHand().get(k).getName().equals("Heart Hound")) {
                                newenvironment.add((Environment) player1.getHand().get(k));
                            }
                        }
                    } else {
                        for (int k = 0; k < player2.getHand().size(); k++) {
                            if (player2.getHand().get(k).getName().equals("Firestorm")
                                    || player2.getHand().get(k).getName().equals("Winterfell")
                                    || player2.getHand().get(k).getName().equals("Heart Hound")) {
                                newenvironment.add((Environment) player2.getHand().get(k));
                            }
                        }
                    }
                    output.addObject().put("command", "getEnvironmentCardsInHand")
                            .put("playerIdx", playeridx).putPOJO("output",
                                    newenvironment);
                }

                if (command.equals("useEnvironmentCard")) {
                    int handidx = inputData.getGames().get(i).getActions().get(j).getHandIdx();
                    int affectedrow = inputData.getGames().get(i).getActions().get(j)
                            .getAffectedRow();
                    if (startingplayer == 1) {
                        //Testing if it is an environment card
                        if (player1.getHand().get(handidx).getName().equals("Firestorm")
                                || player1.getHand().get(handidx).getName().equals("Winterfell")
                                || player1.getHand().get(handidx).getName().equals("Heart Hound")) {
                            //Testing if player 1 has enough mana to use it
                            if (player1.getMana() >= player1.getHand().get(handidx).getMana()) {
                                if (affectedrow == 0 || affectedrow == 1) {
                                    if (player1.getHand().get(handidx).getName()
                                            .equals("Heart Hound")
                                            && arena.getMap().get(int3 - affectedrow).size() == int5) {
                                        output.addObject().put("affectedRow", affectedrow)
                                                .put("command", "useEnvironmentCard")
                                                .put("error", "Cannot steal enemy card"
                                                        + " since the player's row is full.")
                                                .put("handIdx", handidx);
                                    } else {
                                        //Creating a deep copy
                                        Environment newenvironment = new Environment(player1
                                                .getHand().get(handidx).getMana(),
                                                player1.getHand().get(handidx).getName(),
                                                player1.getHand().get(handidx).getColors(),
                                                player1.getHand().get(handidx).getDescription());
                                        UseEnvironment.environment(arena, newenvironment,
                                                affectedrow, player1, player2);
                                        player1.getHand().remove(handidx);
                                    }
                                } else {
                                    output.addObject().put("affectedRow", affectedrow)
                                            .put("command", "useEnvironmentCard")
                                            .put("error", "Chosen row does not belong"
                                                    + " to the enemy.")
                                            .put("handIdx", handidx);
                                }
                            } else {
                                output.addObject().put("affectedRow", affectedrow)
                                        .put("command", "useEnvironmentCard")
                                        .put("error", "Not enough mana to use"
                                                + " environment card.")
                                        .put("handIdx", handidx);
                            }
                        } else {
                            output.addObject().put("affectedRow", affectedrow)
                                    .put("command", "useEnvironmentCard")
                                    .put("error", "Chosen card is not of type"
                                            + " environment.")
                                    .put("handIdx", handidx);
                        }
                    }
                    //Same thing for player 2
                    if (startingplayer == 2) {
                        if (player2.getHand().get(handidx).getName().equals("Firestorm")
                                || player2.getHand().get(handidx).getName().equals("Winterfell")
                                || player2.getHand().get(handidx).getName().equals("Heart Hound")) {
                            if (player2.getMana() >= player2.getHand().get(handidx).getMana()) {
                                if (affectedrow == 2 || affectedrow == int3) {
                                    if (player2.getHand().get(handidx).getName()
                                            .equals("Heart Hound")
                                            && arena.getMap().get(int3 - affectedrow).size() == int5) {
                                        output.addObject().put("affectedRow", affectedrow)
                                                .put("command", "useEnvironmentCard")
                                                .put("error", "Cannot steal enemy card"
                                                        + " since the player's row is full.")
                                                .put("handIdx", handidx);
                                    } else {
                                        Environment newenvironment = new Environment(player2
                                                .getHand().get(handidx).getMana(),
                                                player2.getHand().get(handidx).getName(), player2
                                                .getHand().get(handidx).getColors(),
                                                player2.getHand().get(handidx).getDescription());
                                        UseEnvironment.environment(arena, newenvironment,
                                                affectedrow, player1, player2);
                                        player2.getHand().remove(handidx);
                                    }
                                } else {
                                    output.addObject().put("affectedRow", affectedrow)
                                            .put("command", "useEnvironmentCard")
                                            .put("error", "Chosen row does not belong"
                                                    + " to the enemy.")
                                            .put("handIdx", handidx);
                                }
                            } else {
                                output.addObject().put("affectedRow", affectedrow)
                                        .put("command", "useEnvironmentCard")
                                        .put("error", "Not enough mana to use"
                                                + " environment card.")
                                        .put("handIdx", handidx);
                            }
                        } else {
                            output.addObject().put("affectedRow", affectedrow)
                                    .put("command", "useEnvironmentCard")
                                    .put("error", "Chosen card is not of type"
                                            + " environment.").put("handIdx", handidx);
                        }
                    }
                }

                if (command.equals("getCardAtPosition")) {
                    int row = inputData.getGames().get(i).getActions().get(j).getX();
                    int column = inputData.getGames().get(i).getActions().get(j).getY();
                    if (row > arena.getMap().size() || column > arena.getMap().get(row).size()) {
                        output.addObject().put("command", "getCardAtPosition")
                                .put("x", row)
                                .put("y", column).put("output",
                                        "No card available at that position.");
                    }
                    if (row < arena.getMap().size() && column < arena.getMap().get(row).size()) {
                        if (arena.getMap().get(row).get(column) != null) {
                            Minion newmin = new Minion(arena.getMap().get(row).get(column));
                            output.addObject().put("command", "getCardAtPosition")
                                    .put("x", row)
                                    .put("y", column).putPOJO("output", newmin);
                        } else {
                            output.addObject().put("command", "getCardAtPosition")
                                    .put("x", row)
                                    .put("y", column).put("output",
                                            "No card at that position.");
                        }
                    }
                }

                if (command.equals("getFrozenCardsOnTable")) {
                    ArrayList<Minion> newarray = new ArrayList<Minion>();
                    for (int l = 0; l < arena.getMap().size(); l++) {
                        for (int c = 0; c < arena.getMap().get(l).size(); c++) {
                            if (arena.getMap().get(l).get(c).getFrozen() == 1) {
                                newarray.add(arena.getMap().get(l).get(c));
                            }
                        }
                    }
                    output.addObject().put("command", "getFrozenCardsOnTable")
                            .putPOJO("output", newarray);
                }

                if (command.equals("cardUsesAttack")) {
                    int xattacker = inputData.getGames().get(i).getActions().get(j)
                            .getCardAttacker().getX();
                    int yattacker = inputData.getGames().get(i).getActions().get(j)
                            .getCardAttacker().getY();
                    int xattacked = inputData.getGames().get(i).getActions().get(j)
                            .getCardAttacked().getX();
                    int yattacked = inputData.getGames().get(i).getActions().get(j)
                            .getCardAttacked().getY();
                    Coord coordinatesattacked = new Coord(xattacked, yattacked);
                    Coord coordinatesattacker = new Coord(xattacker, yattacker);

                    if ((xattacker < 2 && xattacked >= 2) || (xattacker >= 2 && xattacked < 2)) {
                        if (arena.getMap().get(xattacker).get(yattacker).getAttackedtur() == 0) {
                            if (arena.getMap().get(xattacker).get(yattacker).getFrozen() == 0) {
                                int semtank = -1;
                                if (xattacker < 2) {
                                    for (int k = 0; k < arena.getMap().get(2).size(); k++) {
                                        if (arena.getMap().get(2).get(k).getName().equals("Goliath")
                                                || arena.getMap().get(2).get(k).getName()
                                                .equals("Warden")) {
                                            semtank = k;
                                            break;
                                        }
                                    }
                                    if (semtank != -1) {
                                        if (arena.getMap().get(xattacked).get(yattacked)
                                                .getName().equals("Goliath")
                                                || arena.getMap().get(xattacked).get(yattacked)
                                                .getName().equals("Warden")) {

                                            arena.getMap().get(xattacked).get(yattacked).setHealth(
                                                    arena.getMap().get(xattacked).get(yattacked)
                                                            .getHealth()
                                                            - arena.getMap().get(xattacker)
                                                            .get(yattacker).getAttackDamage());
                                            arena.getMap().get(xattacker).get(yattacker)
                                                    .setAttackedtur(1);

                                            if (arena.getMap().get(xattacked).get(yattacked)
                                                    .getHealth() <= 0) {
                                                arena.getMap().get(xattacked).remove(yattacked);
                                            }
                                        } else {
                                            AddPojocoordinates clas = new AddPojocoordinates(
                                                    "cardUsesAttack",
                                                    "Attacked card is not of type 'Tank'.",
                                                    coordinatesattacked, coordinatesattacker);
                                            output.addPOJO(clas);
                                        }
                                    } else {
                                        if (yattacked < arena.getMap().get(xattacked).size()) {
                                            arena.getMap().get(xattacked).get(yattacked).setHealth(
                                                    arena.getMap().get(xattacked).get(yattacked)
                                                            .getHealth()
                                                            - arena.getMap().get(xattacker)
                                                            .get(yattacker).getAttackDamage());
                                            arena.getMap().get(xattacker).get(yattacker)
                                                    .setAttackedtur(1);
                                            if (arena.getMap().get(xattacked).get(yattacked)
                                                    .getHealth() <= 0) {
                                                arena.getMap().get(xattacked).remove(yattacked);
                                            }
                                        }
                                    }
                                } else {
                                    for (int k = 0; k < arena.getMap().get(1).size(); k++) {
                                        if (arena.getMap().get(1).get(k).getName().equals("Goliath")
                                                || arena.getMap().get(1).get(k).getName()
                                                .equals("Warden")) {
                                            semtank = k;
                                            break;
                                        }
                                    }
                                    if (semtank != -1) {
                                        if (arena.getMap().get(xattacked).get(yattacked).getName()
                                                .equals("Goliath")
                                                || arena.getMap().get(xattacked).get(yattacked)
                                                .getName().equals("Warden")) {


                                            arena.getMap().get(xattacked).get(yattacked).setHealth(
                                                    arena.getMap().get(xattacked)
                                                            .get(yattacked).getHealth()
                                                            - arena.getMap().get(xattacker)
                                                            .get(yattacker).getAttackDamage());
                                            arena.getMap().get(xattacker).get(yattacker)
                                                    .setAttackedtur(1);
                                            if (arena.getMap().get(xattacked).get(yattacked)
                                                    .getHealth() <= 0) {
                                                arena.getMap().get(xattacked).remove(yattacked);
                                            }

                                        } else {
                                            AddPojocoordinates clas = new AddPojocoordinates(
                                                    "cardUsesAttack",
                                                    "Attacked card is not of type 'Tank'.",
                                                    coordinatesattacked, coordinatesattacker);
                                            output.addPOJO(clas);
                                        }
                                    } else {
                                        arena.getMap().get(xattacked).get(yattacked).setHealth(
                                                arena.getMap().get(xattacked)
                                                        .get(yattacked).getHealth()
                                                        - arena.getMap().get(xattacker)
                                                        .get(yattacker).getAttackDamage());
                                        arena.getMap().get(xattacker).get(yattacker)
                                                .setAttackedtur(1);
                                        if (arena.getMap().get(xattacked).get(yattacked)
                                                .getHealth() <= 0) {
                                            arena.getMap().get(xattacked).remove(yattacked);
                                        }
                                    }
                                }
                            } else {
                                AddPojocoordinates clas = new AddPojocoordinates(
                                        "cardUsesAttack", "Attacker card is frozen.",
                                        coordinatesattacked, coordinatesattacker);
                                output.addPOJO(clas);
                            }
                        } else {
                            AddPojocoordinates clas = new AddPojocoordinates(
                                    "cardUsesAttack", "Attacker card has already attacked"
                                    + " this turn.", coordinatesattacked, coordinatesattacker);
                            output.addPOJO(clas);
                        }
                    } else {
                        AddPojocoordinates clas = new AddPojocoordinates("cardUsesAttack",
                                "Attacked card does not belong to the enemy.",
                                coordinatesattacked, coordinatesattacker);
                        output.addPOJO(clas);
                    }

                }

                if (command.equals("cardUsesAbility")) {
                    cardUsesAbility(inputData, output, arena, i, j);
                }

                if (command.equals("useAttackHero")) {
                    int x = useAttackHero(inputData, output, arena, i, j, player1, player2);
                    if (x == 1) {
                        player1wins++;
                    }
                    if (x == -1) {
                        player2wins++;
                    }
                }

                if (command.equals("useHeroAbility")) {
                    int affectedrow = inputData.getGames().get(i).getActions().get(j)
                            .getAffectedRow();
                    if (startingplayer == 1) {
                        useHeroAbility(inputData, output, arena, affectedrow, player1, 1);
                    } else {
                        useHeroAbility(inputData, output, arena, affectedrow, player2, 2);
                    }
                }

                if (command.equals("getPlayerOneWins")) {
                    output.addObject().put("command", "getPlayerOneWins")
                            .put("output", player1wins);
                }

                if (command.equals("getPlayerTwoWins")) {
                    output.addObject().put("command", "getPlayerTwoWins")
                            .put("output", player2wins);
                }

                if (command.equals("getTotalGamesPlayed")) {
                    output.addObject().put("command", "getTotalGamesPlayed")
                            .put("output", player1wins + player2wins);
                }
            }
            for (int c = 0; c < int4; c++) {
                arena.getMap().get(c).clear();
            }
        }

        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        objectWriter.writeValue(new File(filePath2), output);
    }
}
