package org.me.lc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author sangnk
 * @Created 04/10/2024 - 2:03 CH
 * @project = java_tool
 * @_ Mô tả:
 */
public class T2491 {
    //2491. Divide Players Into Teams of Equal Skill
    public static void main(String[] args) {
        T2491 t2491 = new T2491();
//        System.out.println(t2491.divinePlayers(new int[]{3,2,5,1,3,4}));
        System.out.println(t2491.divinePlayers(new int[]{2,2,2,2}));
    }

    public long divinePlayers(int[] skill) {
        Map<Long, Long> map = new java.util.HashMap<>();
        Map<Long, Map<Long, Long>> result = new java.util.HashMap<>();
        for (int i = 1; i <= skill.length ; i++) {
            for (int j = i + 1; j <= skill.length; j++) {
                long valueKey = Integer.valueOf(String.valueOf(i) + String.valueOf(j));
                long value = skill[i-1] + skill[j-1];
                map.put(valueKey, value);
                if(result.containsKey(value)){
                    Map<Long, Long> mapValue = result.get(value);
                    mapValue.put(valueKey, value);
                    result.put(value, mapValue);
                }
                else {
                    Map<Long, Long> mapValue = new java.util.HashMap<>();
                    mapValue.put(valueKey, value);
                    result.put(value, mapValue);
                }
            }
        }
        result.entrySet().removeIf(entry -> entry.getValue().size() != skill.length/2);
        for (Map.Entry<Long, Map<Long, Long>> entry : result.entrySet()) {
            List<String> checkDuplicate = new ArrayList<>();
            for (Map.Entry<Long, Long> entry1 : entry.getValue().entrySet()) {
                Long key = entry1.getKey();
                String keyString = String.valueOf(key);
                for (int i = 0; i < keyString.length(); i++) {
                    checkDuplicate.add(String.valueOf(keyString.charAt(i)));
                }
            }
            checkDuplicate.removeIf(entry2 -> checkDuplicate.stream().filter(entry3 -> entry3.equals(entry2)).count() > 1);
            if(checkDuplicate.size() == skill.length){
                Map<Long, Long> end = entry.getValue();
                Long valueResult = 0L;
                for (Map.Entry<Long, Long> entry1 : end.entrySet()) {
                    String keyString = String.valueOf(entry1.getKey());
                    Long kqphepnhan = 1L;
                    for (int i = 0; i < keyString.length(); i++) {
                        kqphepnhan *= Long.valueOf(skill[Integer.valueOf(String.valueOf(keyString.charAt(i))) - 1]);
                    }
                    valueResult += kqphepnhan;
                }
//                System.out.println(valueResult);
                return valueResult;
            }
        }

        return -1;
    }
}
