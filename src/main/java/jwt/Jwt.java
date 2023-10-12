package jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.auth0.jwt.interfaces.Claim;

public class Jwt {
    public static String createJWT(boolean isAdmin, int userId) {
        Algorithm algorithm = Algorithm.HMAC256("64");
        return JWT.create()
                .withIssuer("auth0")
                .withClaim("isAdmin", isAdmin)
                .withClaim("userId", userId)
                .sign(algorithm);
    }

    public static DecodedJWT verify(String token) {
        Algorithm algorithm = Algorithm.HMAC256("64");
        JWTVerifier verifier = JWT.require(algorithm).build();
        return verifier.verify(token);
    }

    public static int getId(String token) throws JWTVerificationException {
        DecodedJWT jwt = JWT.decode(token);
        Claim id = jwt.getClaim("userId");
        return id.asInt();
    }

    public static boolean getAdminStatus(String token) {
        DecodedJWT jwt = JWT.decode(token);
        Claim id = jwt.getClaim("isAdmin");
        return id.asBoolean();
    }

    public static DecodedJWT decode(String token) {
        return JWT.decode(token);
    }
}
