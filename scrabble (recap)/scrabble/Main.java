/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scrabble;

import java.util.Random;

/**
 *
 * @author vladm
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Words words = new Words();
        words.addWords("aaa", "aab", "aba", "abb");
        Random generator = new Random();
        int N = 1 + generator.nextInt(3);
        Player[] players = new Player[N];
        for (int i = 0; i < N; ++i) {
            players[i] = new Player(words.size());
        }
        int count = 0;
        for (int i = 0; count < N; i = (i + 1) % N) {
            if (players[i].getPosX() <= players[i].getPosY()) {
                char c = (char) (generator.nextInt(2) + 'a');
                System.out.println(i + " received letter " + c);
                int x = players[i].getPosX();
                int y = players[i].getPosY();
                players[i].setPosX(words.lowerIndex(x, y,
                        players[i].getPosV(), c));
                players[i].setPosY(words.higherIndex(x, y,
                        players[i].getPosV(), c));
                if (players[i].getPosX() == players[i].getPosY()
                        && players[i].getPosV()
                        == words.wordAt(players[i].getPosX()).length() - 1) {
                    System.out.println(i + " won :) with word \""
                            + words.wordAt(players[i].getPosX()) + "\"");
                    break;
                } else if (players[i].getPosX() > players[i].getPosY()) {
                    ++count;
                }
                players[i].setPosV(1 + players[i].getPosV());
            }
        }
        if (count == N) {
            System.out.println("Nobody won :(");
        }
    }
}
