package com.itheima;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {

    //生成JWT
    @Test
    public void testGenerateJwt() {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("username", "itheima");
        dataMap.put("id", 100);

        String jwt = Jwts.builder()
                .setClaims(dataMap) //指定jwt中的自定义数据
                .signWith(SignatureAlgorithm.HS256, "ITHEIMA") //指定签名算法及秘钥
                .setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 1000)) //令牌有效期
                .compact();//生成令牌

        System.out.println(jwt);
    }


    //校验JWT
    @Test
    public void testParserJwt() {
        Claims claims = Jwts.parser()
                .setSigningKey("ITHEIMA") //指定秘钥, 要与生成令牌时的秘钥相同
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MTAwLCJleHAiOjE2ODYyOTUwMjMsInVzZXJuYW1lIjoiaXRoZWltYSJ9.aSpbLfXKldmDaLb-esA8icj4Hv_5wj7S1U4imuiV4Bc")
                .getBody();
        System.out.println(claims);
    }

}
