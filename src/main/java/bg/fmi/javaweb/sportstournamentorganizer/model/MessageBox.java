package bg.fmi.javaweb.sportstournamentorganizer.model;

import java.util.List;

public class MessageBox {
    private Integer boxId;
    private User owner;
    private List<Message> messages = List.of();

    public MessageBox(Integer boxId, User owner, List<Message> messages) {
        this.boxId = boxId;
        this.owner = owner;
        this.messages = messages;
    }

    public Integer getBoxId() {
        return boxId;
    }

    public void setBoxId(Integer boxId) {
        this.boxId = boxId;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }



    @Override
    public int hashCode() {
        return boxId.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        return boxId.equals(((MessageBox) o).boxId);
    }
}
