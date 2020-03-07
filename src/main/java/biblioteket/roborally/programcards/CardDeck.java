package biblioteket.roborally.programcards;

import org.lwjgl.Sys;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class CardDeck implements ICardDeck {
    private ArrayList<ICard> cardDeck;
    private int topOfDrawPile = 0; //Will change as cards are drawn

    public CardDeck() {
        cardDeck = new ArrayList<>();
        String line = "";
        BufferedReader bufferedReader = null;

        try {
            bufferedReader = new BufferedReader(new FileReader("assets/ProgramCards.csv"));
            while ((line = bufferedReader.readLine()) != null) {
                line = line.replace("\uFEFF", ""); //Have to take away an invisible space.
                String[] cardInfo = line.split(",");
                CardType type = CardType.valueOf(cardInfo[0]);
                int priorityNum = Integer.parseInt(cardInfo[1]);
                ICard card = new Card(type, priorityNum);
                cardDeck.add(card);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        Collections.shuffle(cardDeck);
    }

    //TODO
    @Override
    public ArrayList<ICard> drawCards(int number) {
        if (topOfDrawPile > cardDeck.size() - 1) {
            topOfDrawPile = 0;
            Collections.shuffle(cardDeck);
        }

        ArrayList<ICard> drawnCards = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            drawnCards.add(cardDeck.get(topOfDrawPile++));
        }
        return drawnCards;

    }
}
