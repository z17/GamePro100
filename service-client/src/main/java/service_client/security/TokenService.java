package service_client.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.impl.DefaultClaims;
import org.springframework.security.authentication.AuthenticationServiceException;

import java.util.Date;

public class TokenService {

    private String key;

    public void setKey(String key) {
        this.key = key;
    }


    public TokenAuthentication parseAndCheckToken(final String token) {
        DefaultClaims claims;
        try {
            claims = (DefaultClaims) Jwts.parser().setSigningKey(key).parse(token).getBody();
        } catch (Exception ex) {
            throw new AuthenticationServiceException("Token corrupted");
        }

        if (claims.get(TokenData.EXPIRATION_DATE.getValue(), Long.class) == null) {
            throw new AuthenticationServiceException("Invalid token");
        }

        Date expiredDate = new Date(claims.get(TokenData.EXPIRATION_DATE.getValue(), Long.class));
        if (!expiredDate.after(new Date())) {
            throw new AuthenticationServiceException("Token expired date error");
        }

        Long id = claims.get(TokenData.ID.getValue(), Number.class).longValue();
        String login = claims.get(TokenData.LOGIN.getValue(), String.class);
        String group = claims.get(TokenData.GROUP.getValue(), String.class);

        TokenUser user = new TokenUser(id, login, group);

        return new TokenAuthentication(token, true, user);
    }
}