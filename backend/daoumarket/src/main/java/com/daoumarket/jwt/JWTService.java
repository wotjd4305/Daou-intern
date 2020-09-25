package com.daoumarket.jwt;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.springframework.stereotype.Service;

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
    public String makeJwt(User res) throws Exception {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        Date expireTime = new Date();
        // ��ū ���� �ð� : 20��
        expireTime.setTime(expireTime.getTime() + 1000 * 60 * 20);
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secretKey);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        Map<String, Object> headerMap = new HashMap<String, Object>();

        // ����� alg�� typ�� ����
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
    public User checkJwt(String jwt) throws Exception {
    	// checkJwt�޼ҵ忡���� try������ �޾ƿ� Jwt�� �̿��Ͽ� �Ľ�
    	User dto = new User();
        try {
            Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(secretKey))
                    .parseClaimsJws(jwt).getBody(); // ���� ����ȴٸ� �ش� ��ū�� ������ū
//            System.out.println("expireTime :" + claims.getExpiration());
            
            dto.setId(Long.valueOf(claims.get("id") + ""));
            dto.setNum(Long.valueOf(claims.get("num") + ""));
            dto.setName((String) claims.get("name") + "");
            dto.setDepartment((String) claims.get("department") + "");
            dto.setImage((String) claims.get("image") + "");
            
            return dto;
        
        // �������� ��ū���� �����ϰ� ���⼭ �Ľ��� ���� �ʴ´ٸ� catch�� ����
        } catch (ExpiredJwtException exception) {
            return dto;
            
        } catch (JwtException exception) {
            return dto;
        }
    }
}
