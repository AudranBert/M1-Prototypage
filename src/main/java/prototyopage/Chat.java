package prototyopage;

import java.util.ArrayList;

public class Chat {
    private int idUser1;
    private int idUser2;
    private int idSejour;
    private int idChat;
    private static int nbrChat = 0;
    ArrayList<Message> messages = new ArrayList<Message>();

    public Chat(int id1Copy, int id2Copy, int idSejourCopy) {
        this.idUser1 = id1Copy;
        this.idUser2 = id2Copy;
        this.idSejour = idSejourCopy;
        idChat = nbrChat;
        nbrChat++;
    }

    public int getIdUser1() {
        return idUser1;
    }

    public int getIdUser2() {
        return idUser2;
    }

    public ArrayList<Message> getMessages() { return messages; }

    public int getIdSejour() {
        return idSejour;
    }

    public int getIdChat() {
        return idChat;
    }
}
