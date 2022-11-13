package com.fresco.springbootapp.dca;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class Dca1 {
    public static void main(String[] args) {
        new Dca1().findSecondMostCharacter("RAKSHITH");
    }

    public Character findSecondMostCharacter(String str) {
        Map<Character, Integer> characterMap = new LinkedHashMap<>();
        for (int i = 0; i < str.length(); i++) {
            Character x = str.charAt(i);
            if (characterMap.containsKey(x)) characterMap.put(x, characterMap.get(x) + 1);
            else characterMap.put(x, 1);
        }

//        var a = str.chars()
//                .mapToObj(c -> (char) c).collect(Collectors.groupingBy(Character::charValue, Collectors.counting()));
//
//        System.out.println(a);

        System.out.println(characterMap);

        Map.Entry<Character, Integer> max = Collections.max(characterMap.entrySet(), Map.Entry.comparingByValue());
        characterMap.remove(max.getKey());

        return 'R';
    }

}
