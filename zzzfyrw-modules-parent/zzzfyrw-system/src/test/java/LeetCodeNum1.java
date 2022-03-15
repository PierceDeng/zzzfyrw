import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class LeetCodeNum1 {


    @Test
    public void test(){
//
//        int[] nums = {4,8,5,6};
//        int target = 10;
//        int[] ints = twoNum(nums, target);
//        System.out.println(Arrays.toString(ints));


        JSONObject jsonObject = new JSONObject();
        jsonObject.put("1",1);

        List<JSONObject> list = new ArrayList<>();
        list.add(jsonObject);


//        ImmutableList<JSONObject> list2 = ImmutableList.copyOf(list);


        List<Object> list2 = Arrays.stream(list.toArray()).collect(Collectors.toList());

        for (JSONObject object : list) {
            object.put("1",2);
        }


        System.out.println(Arrays.toString(list.toArray()));

        System.out.println(Arrays.toString(list2.toArray()));


    }

    public int[] twoNum(int[] nums,int target){
        int[] result = new int[2];
        Map<Integer,Integer> hash = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int value = target - nums[i];
            if(hash.containsKey(value)){
                result[1] = i;
                result[0] = hash.get(value);
                return result;
            }
            hash.put(nums[i],i);
        }
        return result;
    }



}
