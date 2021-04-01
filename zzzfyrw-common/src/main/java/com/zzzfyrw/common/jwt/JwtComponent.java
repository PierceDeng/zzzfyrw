package com.zzzfyrw.common.jwt;

import com.zzzfyrw.common.codec.gson.GsonUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.Base64Codec;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

public class JwtComponent {
    private SecretKey secretKey;
    private String tokenKey = "264290ce387c9ebf5999750fe785921657aa59561bf50909af9dbf41d85d70ef";
    private Long defaultExpirationTime = 30 * 60 * 1000L;
    private Long defaultErrorTime = 5 * 1000L;
    private static final String AlgorithmStr ="HmacSHA256";
    private static final SignatureAlgorithm Algorithm = SignatureAlgorithm.HS256;
    public enum JwtHelper{
        INSTANCE;
        private JwtComponent jwtComponent;
        JwtHelper(){
           this.jwtComponent=new JwtComponent();
        }
        public JwtComponent getJwtComponent() {
            return jwtComponent;
        }
    }
    public JwtComponent(){
        byte[] encoded = Base64Codec.BASE64.decode(this.tokenKey);
        this.secretKey = new SecretKeySpec(encoded, 0, encoded.length, Algorithm.getValue());
    }
    public static JwtComponent getInstance(){
        return JwtHelper.INSTANCE.getJwtComponent();
    }
    public SecretKey getSecretKey() {
        return secretKey;
    }
    public void setSecretKey(SecretKey secretKey) {
        this.secretKey = secretKey;
    }
    /**
     * 过期时间为30分钟的jwt生成
     *
     * @param params 你的入参
     * @return jwt密文
     */
    public String getJws(Map<String, Object> params) {
        Date now = new Date();
        return Base64Codec.BASE64URL
                .encode(Jwts.builder().setClaims(params)
                        .setExpiration(new Date(now.getTime() + this.defaultExpirationTime))
                        .setIssuedAt(now)
                        .setNotBefore(now)
                        .signWith(SignatureAlgorithm.HS256,secretKey)
                        .compact()
                        .getBytes());
    }
    /**
     * 解析jwt
     *
     * @param jws 密文
     * @return jwt的解析对象
     */
    public Claims verifyJws(String jws) {
        try {
            jws = new String(Base64Codec.BASE64URL.decode(jws));
            return Jwts.parser()
                    .setSigningKey(secretKey)
                    .setAllowedClockSkewSeconds(this.defaultErrorTime)
                    .parseClaimsJws(jws)
                    .getBody();
        } catch (JwtException e) {
            e.printStackTrace();
        }
        return null;
    }
    public Map<String,Object> getJwt(String jwt){
        jwt = new String(Base64Codec.BASE64URL.decode(jwt));
        Map<String,Object> jwtClaims=Jwts.parser()
                .setSigningKey(secretKey)
                .setAllowedClockSkewSeconds(this.defaultErrorTime)
                .parseClaimsJws(jwt)
                .getBody();
        return jwtClaims;
    }
    /**
     * 生成一个自定义过期时间的jwt密文，如果你的过期时间为null，那么就是默认的过期时间30分钟。
     *
     * @param obj  自定义对象
     * @param date 过期时间
     * @return 密文
     */
    public String getJws(Object obj, Date date) {
        String json= GsonUtil.fromObjectToJson(obj);
        Map<String,Object> map=GsonUtil.fromJsonToMap(json);
        Date now = new Date();
        date = Optional.ofNullable(date).orElseGet(() -> {
            return new Date(this.defaultExpirationTime);
        });
        return Base64Codec.BASE64URL
                .encode(Jwts.builder().setClaims(map)
                        .setExpiration(date)
                        .setIssuedAt(now)
                        .setNotBefore(now)
                        .signWith(SignatureAlgorithm.HS256,secretKey)
                        .compact()
                        .getBytes());
    }

}
