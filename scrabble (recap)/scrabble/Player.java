/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scrabble;

/**
 *
 * @author vladm
 */
public class Player {

    private Integer posV;
    private Integer posX;
    private Integer posY;

    public void setPosV(Integer posV) {
        this.posV = posV;
    }

    public void setPosX(Integer posX) {
        this.posX = posX;
    }

    public void setPosY(Integer posY) {
        this.posY = posY;
    }

    public Integer getPosV() {
        return posV;
    }

    public Integer getPosX() {
        return posX;
    }

    public Integer getPosY() {
        return posY;
    }

    public Player(int size) {
        try {
            if (size < 0) {
                throw new Exception("number of words is invalid");
            }
            posV = 0;
            posX = 0;
            posY = size - 1;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            posV = 0;
            posX = 0;
            posY = -1;
        }
    }
}
