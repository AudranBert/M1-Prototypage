package prototyopage;

public class Message {
    private int sender;
    private int receiver;
    private String content;
    private int numMessage;

    public Message(int sender, int receiver, String content, int numMessage) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.numMessage = numMessage;
    }

    public int getSender() {
        return sender;
    }

    public int getReceiver() {
        return receiver;
    }

    public String getContent() {
        return content;
    }

    public int getNumMessage() {
        return numMessage;
    }

    public void setNumMessage(int numMessage) {
        this.numMessage = numMessage;
    }
}
