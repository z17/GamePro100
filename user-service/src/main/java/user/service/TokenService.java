package user.service;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.stereotype.Service;
import user.TokenAuthentication;
import user.entity.UserEntity;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class TokenService {

    @Value("${token.key}")
    private String key;

    @Value("${token.alive}")
    private int tokenDaysAlive;

    public TokenAuthentication parseAndCheckToken(final String token) {
        DefaultClaims claims;
        try {
            claims = (DefaultClaims) Jwts.parser().setSigningKey(key).parse(token).getBody();
        } catch (Exception ex) {
            throw new AuthenticationServiceException("Token corrupted");
        }

        if (claims.get(TokenField.EXPIRATION_DATE.getValue(), Long.class) == null) {
            throw new AuthenticationServiceException("Invalid token");
        }

        Date expiredDate = new Date(claims.get(TokenField.EXPIRATION_DATE.getValue(), Long.class));
        if (!expiredDate.after(new Date())) {
            throw new AuthenticationServiceException("Token expired date error");
        }

        Long id = claims.get(TokenField.ID.getValue(), Number.class).longValue();
        String login = claims.get(TokenField.LOGIN.getValue(), String.class);
        String group = claims.get(TokenField.GROUP.getValue(), String.class);

        TokenUser user = new TokenUser(id, login, group);

        return new TokenAuthentication(token, true, user);
    }
}