package bg.fmi.javaweb.sportstournamentorganizer.security;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Base64;

public class GenerateKey {

    public static void main(String[] args) {
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

        String secretKey = Base64.getEncoder().encodeToString(key.getEncoded());

        System.out.println(secretKey);
    }
}
