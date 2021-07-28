package com.zzzfyrw.common.jwt;

import com.zzzfyrw.common.constant.EncryptConstant;
import com.zzzfyrw.common.json.gson.GsonUtil;
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
    private String tokenKey = EncryptConstant.JWT_KEY;
    private Long defaultExpirationTime = 60 * 60 * 1000L;
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
     * 过期时间为60分钟的jwt生成
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
        date = Optional.ofNullable(date).orElseGet(() -> new Date(now.getTime() + this.defaultExpirationTime));
        return Base64Codec.BASE64URL
                .encode(Jwts.builder().setClaims(map)
                        .setExpiration(date)
                        .setIssuedAt(now)
                        .setNotBefore(now)
                        .signWith(SignatureAlgorithm.HS256,secretKey)
                        .compact()
                        .getBytes());
    }

    public static void main(String[] args) {

//        int[] nums = {1,2,2,3,4,4};
//        int i = removeD(nums);
//        System.out.println("新长度"+i);
        int[] nums1 = {1,2,2,2,3,4,4};
        for (int i = 0; i < nums1.length -1 ; i++) {
            int index = i;
            for (int y = 0; y < i; y++) {
                if(nums1[i] - nums1[y] == 1){
                    index = y + 1;
                    break;
                }
            }
            nums1[i] = nums1[index];

        }
        for (int i : nums1) {
            System.out.println(i);
        }

    }

    public static int removeD(int[] nums){

        if(nums == null || nums.length == 0){
            return 0;
        }
        int length = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if(nums[i] == nums[i+1]){
                ++length;
            }
            int index = i;
            for (int y = 0; y < i + 1; y++) {
                if(nums[i] > nums[y]){
                    index = i + 1;
                }
            }
            nums[i] = nums[index];
            System.out.println(nums[i]);
        }
        length = nums.length - length;
        for (int num : nums) {
            System.out.println(num);
        }

        return length;

    }


}
