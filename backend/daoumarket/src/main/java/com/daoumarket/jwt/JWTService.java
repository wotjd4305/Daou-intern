package com.daoumarket.jwt;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.springframework.stereotype.Service;

import com.daoumarket.dto.TokenRequest;
import com.daoumarket.dto.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JWTService implements IJWTService {
    private String secretKey = "ThisisDaouMarketSecretKeyWelcomeJwt";
    
    @Override
    public String makeJwt(User user) throws Exception {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        Date expireTime = new Date();
        // 토큰 만료시간 : 20분
        expireTime.setTime(expireTime.getTime() + 1000 * 60 * 20);
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secretKey);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        Map<String, Object> headerMap = new HashMap<String, Object>();

        // 헤더에 alg와 typ을 설정
        headerMap.put("typ","JWT");
        headerMap.put("alg","HS256");

        Map<String, Object> map= new HashMap<String, Object>();

        long id = user.getId();
        long empNum = user.getEmpNum();
        String name = user.getName();
        String department = user.getDepartment();
        String image = user.getImage();

        map.put("id", id);
        map.put("empNum", empNum);
        map.put("name", name);
        map.put("department", department);
        map.put("image", image);
        
        JwtBuilder builder = Jwts.builder().setHeader(headerMap)
                .setClaims(map)
                .setExpiration(expireTime)
                .signWith(signatureAlgorithm, signingKey);

        return builder.compact();
    }

    @Override
    public User checkJwt(TokenRequest accessToken) throws Exception {
    	// checkJwt멧드에서는 try문에서 받아온 Jwt를 이용하여 파싱
    	User user = new User();
        try {
        	// 정상수생된다면 해당 토큰은 정상 토큰으로 간주하고 파싱되지 않는다면 catch문에서 잡히도록 수행
            Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(secretKey))
                    .parseClaimsJws(accessToken.getToken()).getBody(); 
//            System.out.println("expireTime :" + claims.getExpiration());
            
            user.setId(Long.valueOf(claims.get("id") + ""));
            user.setEmpNum(Long.valueOf(claims.get("empNum") + ""));
            user.setName((String) claims.get("name") + "");
            user.setDepartment((String) claims.get("department") + "");
            user.setImage((String) claims.get("image") + "");
            
            return user;
        
        } catch (ExpiredJwtException exception) {
            return user;
            
        } catch (JwtException exception) {
            return user;
        }
    }
}
