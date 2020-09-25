package com.daoumarket.jwt;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.springframework.stereotype.Service;

import com.daoumarket.dto.UserDto;

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
    public String makeJwt(UserDto res) throws Exception {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        Date expireTime = new Date();
        // 토큰 만료 시간 : 20분
        expireTime.setTime(expireTime.getTime() + 1000 * 60 * 20);
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secretKey);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        Map<String, Object> headerMap = new HashMap<String, Object>();

        // 헤더에 alg와 typ를 설정
        headerMap.put("typ","JWT");
        headerMap.put("alg","HS256");

        Map<String, Object> map= new HashMap<String, Object>();

        long id = res.getId();
        long num = res.getNum();
        String name = res.getName();
        String department = res.getDepartment();
        String image = res.getImage();

        map.put("id", id);
        map.put("num", num);
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
    public UserDto checkJwt(String jwt) throws Exception {
    	// checkJwt메소드에서는 try문에서 받아온 Jwt를 이용하여 파싱
    	UserDto dto = new UserDto();
        try {
            Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(secretKey))
                    .parseClaimsJws(jwt).getBody(); // 정상 수행된다면 해당 토큰은 정상토큰
//            System.out.println("expireTime :" + claims.getExpiration());
            
            dto.setId(Long.valueOf(claims.get("id") + ""));
            dto.setNum(Long.valueOf(claims.get("num") + ""));
            dto.setName((String) claims.get("name") + "");
            dto.setDepartment((String) claims.get("department") + "");
            dto.setImage((String) claims.get("image") + "");
            
            return dto;
        
        // 정상적인 토큰으로 간주하고 여기서 파싱이 되지 않는다면 catch문 잡힘
        } catch (ExpiredJwtException exception) {
            return dto;
            
        } catch (JwtException exception) {
            return dto;
        }
    }
}
