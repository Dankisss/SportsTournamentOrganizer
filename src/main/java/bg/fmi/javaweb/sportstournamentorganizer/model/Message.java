package bg.fmi.javaweb.sportstournamentorganizer.model;


import lombok.Data;

@Data
public class Message {
    private Integer messageId;
    private MessageBox senderBox;
    private MessageBox receiverBox;
    private String messageText;

    public Message(Integer messageId, MessageBox senderBox, MessageBox receiverBox, String messageText) {
        this.messageId = messageId;
        this.senderBox = senderBox;
        this.receiverBox = receiverBox;
        this.messageText = messageText;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public MessageBox getSenderBox() {
        return senderBox;
    }

    public void setSenderBox(MessageBox senderBox) {
        this.senderBox = senderBox;
    }

    public MessageBox getReceiverBox() {
        return receiverBox;
    }

    public void setReceiverBox(MessageBox receiverBox) {
        this.receiverBox = receiverBox;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }




    @Override
    public int hashCode() {
        return messageId.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        return messageId.equals(((Message) o).messageId);
    }
}
