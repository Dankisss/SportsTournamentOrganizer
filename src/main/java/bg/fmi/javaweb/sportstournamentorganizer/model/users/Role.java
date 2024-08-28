package bg.fmi.javaweb.sportstournamentorganizer.model.users;

import lombok.Getter;

@Getter
public enum Role {
    Manager("MANAGER"),
    Moderator("MODERATOR"),
    Player("PLAYER"),
    Follower("FOLLOWER");

    private final String role;

    Role(String role) {
        this.role = role;
    }

}
