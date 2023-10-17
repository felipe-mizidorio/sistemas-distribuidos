package jwt.validation;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import jwt.Jwt;
import server.exceptions.AuthenticationException;
import server.exceptions.ServerResponseException;

public class ValidateToken {
    public static void validate(String token) throws ServerResponseException {
        if (token == null) {
            throw new AuthenticationException();
        }
        DecodedJWT jwt;
        try {
            jwt = Jwt.verify(token);
        } catch (JWTVerificationException ex) {
            throw new AuthenticationException();
        }

        Claim userId = jwt.getClaim("userId");
        Claim isAdmin = jwt.getClaim("isAdmin");

        if (userId.isMissing() || userId.isNull() || isAdmin.isMissing() || isAdmin.isNull()) {
            throw new AuthenticationException();
        }
    }
}
