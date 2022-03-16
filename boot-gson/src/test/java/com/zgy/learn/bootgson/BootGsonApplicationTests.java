package com.zgy.learn.bootgson;

import com.google.gson.Gson;
import com.zgy.learn.bootgson.pojo.Monkey;
import com.zgy.learn.bootgson.pojo.PetHost;
import com.zgy.learn.bootgson.pojo.ShibaInu;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
class BootGsonApplicationTests {
    @Autowired
    @Qualifier(value = "gsonsettype")
    private Gson gson;

    @Test
    void contextLoads() {
    }

    // javaBean-->json
    @Test
    public void bean2Json() {
        Monkey monkey = new Monkey().setId(1).setGender(0).setAge(3).setName("lily").setKind("金丝猴");
        PetHost petHost1 = new PetHost().setId("18239fg1").setAge(22).setGender(1).setName("张璇")
                .setAddress("北京").setBirthday(new Date());
        PetHost petHost2 = new PetHost().setId("18fg39oe1").setAge(25).setGender(0).setName("张天")
                .setAddress("北京").setBirthday(new Date());
        ShibaInu shibaInu = new ShibaInu().setAge(1).setName("kaka").setGender(0).setId(1).setOrigin("日本");
        List<PetHost> list = new ArrayList<>();
        list.add(petHost1);
        list.add(petHost2);
        shibaInu.setPetHosts(list);
        String monkeyJsonStr = gson.toJson(monkey);
        String petHost1JsonStr = gson.toJson(petHost1);
        String shibaInuJsonStr = gson.toJson(shibaInu);
        System.out.println(monkeyJsonStr);
        System.out.println(petHost1JsonStr);
        System.out.println(shibaInuJsonStr);
    }

    // json-->javaBean
    @Test
    public void json2Bean() {
        String shibaInuJsonStr = "{\"id\":1,\"age\":1,\"gender\":0,\"name\":\"kaka\",\"origin\":\"日本\"," +
                "\"petHosts\":[{\"id\":\"18239fg1\",\"name\":\"张璇\",\"age\":22,\"gender\":1,\"address\":\"北京\",\"birthday\":\"2022-03-17 00:59:49\"},{\"id\":\"18fg39oe1\",\"name\":\"张天\",\"age\":25,\"gender\":0,\"address\":\"北京\",\"birthday\":\"2022-03-17 00:59:49\"}]}";

        ShibaInu shibaInu = gson.fromJson(shibaInuJsonStr, ShibaInu.class);
        System.out.println(shibaInu.toString());
    }

}
