package bg.fmi.javaweb.sportstournamentorganizer.repository;

import bg.fmi.javaweb.sportstournamentorganizer.model.MessageBox;
import bg.fmi.javaweb.sportstournamentorganizer.repository.sequence.MessageBoxSequence;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class MessageBoxRepository {
    private static Map<Integer, MessageBox> messageBoxMap = new HashMap<>();

    public static void addMessageBox(MessageBox messageBox) {
        if(messageBox.getBoxId() != null) {
            throw new IllegalArgumentException("Cannot add messageBox with already given id.");
        }

        messageBox.setBoxId(MessageBoxSequence.getNext());
        messageBoxMap.put(messageBox.getBoxId(), messageBox);
    }

    public static boolean removeMessageBox(MessageBox messageBox) {
        return messageBoxMap.remove(messageBox.getBoxId()) != null;
    }

}

