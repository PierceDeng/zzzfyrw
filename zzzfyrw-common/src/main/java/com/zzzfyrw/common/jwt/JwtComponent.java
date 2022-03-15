package com.zzzfyrw.common.jwt;

import com.google.common.collect.Lists;
import com.zzzfyrw.common.constant.EncryptConstant;
import com.zzzfyrw.common.json.gson.GsonUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.Base64Codec;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.*;

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


//        int[] nums = {2,7,11};
//        int target = 9;
//        int[] calc = twoNum(nums, target);
//        System.out.println(Arrays.toString(calc));
//        String s = "pwwkew";
//        int i = lengthOfLongestSubstring(s);
//        System.out.println(i);

        int[] num1 = {2};
        int[] num2 = {};
        double medianSortedArrays = findMedianSortedArrays(num1, num2);
        System.out.println(medianSortedArrays);

    }

    public static int[] twoNum(int[] nums,int target){

        int[] result = new int[2];


        Map<Integer,Integer> hash = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            int value = target - nums[i];
            if(hash.containsKey(value)){
                result[1] = i;
                result[0] = hash.get(value);
                break;
            }
            hash.put(nums[i],i);
        }

        return result;
    }

    public static int lengthOfLongestSubstring(String s) {
        int result = 0;
        if(s.equals("")){
            return result;
        }
        int length = s.length();

        Map<Integer,String> hash = new HashMap<>();
        for (int i = 0; i < length; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = i; j < length; j++) {
                String next = s.substring(j, j + 1);
                if(sb.toString().contains(next)){
                    hash.put(sb.toString().length(),sb.toString());
                    break;
                }
                sb.append(next);
                if(length == j + 1){
                    hash.put(sb.toString().length(),sb.toString());
                    break;
                }

            }
        }
        Set<Map.Entry<Integer, String>> entries = hash.entrySet();

        for (Map.Entry<Integer, String> entry : entries) {
            if(entry.getKey() > result){
                result = entry.getKey();
            }
        }

        return result;
    }


    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {

        double result = 0.00000;
        List<Double> set = new ArrayList<>();
        for (int i : nums1) {
            set.add((double) i);
        }
        for (int i : nums2) {
            set.add((double) i);
        }
        Collections.sort(set);
        int size = set.size();
        int mid = size / 2;
        if(size % 2 == 0){ //整数
            result = (set.get(mid) + set.get(mid - 1)) / 2;
        }else { //单数
            result = set.get(mid);
        }
        return result;
    }



}
