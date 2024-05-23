package com.example.springboot03;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
class Multiplication{int factorA, factorB;}

@Service
class RandomGenServiceImpl{
  int getGetRandom(){
    return new Random().nextInt(10);
  }
}

@Service
class MultiplicationServiceImpl{
  @Autowired
  RandomGenServiceImpl randomGenService;
  Multiplication createRandomMultiplication(){
    return new Multiplication(randomGenService.getGetRandom(), randomGenService.getGetRandom());
  }
  // 답안 체점 서비스
  boolean checkAttempt(A a){
    return a.getMultiplication().getFactorA() * a.getMultiplication().getFactorB() == a.getResultAttempt();
  }
}

@NoArgsConstructor
@AllArgsConstructor
class User{String alias;}


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class A{
  User user;
  Multiplication multiplication;
  Integer resultAttempt;
}
@RestController
public class Tiger {
  @Autowired
  MultiplicationServiceImpl multiplicationService;

  @RequestMapping("/t1")
  public Multiplication func01() {
    Multiplication multiplication = multiplicationService.createRandomMultiplication();
    System.out.println("func01 call");
    return multiplication;
  }
  @RequestMapping("/t2")
  public boolean func02(@RequestBody A a) {
    System.out.println("func02 call");
    return multiplicationService.checkAttempt(a);
  }
}


@RestController
@RequestMapping("/bpp1")
class BppController{
  @GetMapping
  String f1(){
    System.out.println("bpp1");

    return "bpp1";
  }
}
@RestController
@RequestMapping("/bpp2")
class BppController2{
  @GetMapping
  String f1(){
    System.out.println("bpp2");

    return "bpp1";
  }
}

@RestController
@RequestMapping("/bpp3/{num}")
class BppController3{
  @GetMapping
  String f1(@PathVariable String num){
    System.out.println("bpp3");

    return "bpp3"+num;
  }
}

@RestController
@RequestMapping("/bpp4/{num}")
class BppController4{
  @GetMapping
  String f1(@PathVariable String num){
    System.out.println("bpp4");

    return "bpp4 "+num;
  }
}

@RestController
@RequestMapping("/bpp5")
class BppController5{
  @GetMapping
  Integer f1(){
    System.out.println();
    return 9998;
  }
}

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
class Person{
  String name;
  Integer age;
}

@RestController
@RequestMapping("/bpp6")
class BppController6 {
  @GetMapping
  Person[] f1() {
//  ArrayList<Person> f1(){
    System.out.println("bpp6");
    Person[] data = {new Person("호랑이1", 10),
            new Person("호랑이2", 20),
            new Person("호랑이3", 30),
            new Person("호랑이4", 40)};
//    ArrayList<Person> data = new ArrayList<>();
//    data.add(new Person("호랑이1",10));
//    data.add(new Person("호랑이2",20));
//    data.add(new Person("호랑이3",30));
//    data.add(new Person("호랑이4",40));


//    return new Person("호랑이",10);
    return data;
  }

  @GetMapping("/f5/{num1}/{num2}")
  Integer f5(@PathVariable Integer num1, @PathVariable Integer num2) {
    System.out.println("bpp6");

    return num1 + num2;
  }

  @RestController
  @RequestMapping("/bpp7")
  class BppController7{
    @GetMapping
    String f1(){
      return "호랑이";
    }
  }

  @RestController
  @RequestMapping("/bpp8")
  class BppController8 {
    @GetMapping
    Person f1() {
      System.out.println("bpp 8");
      return new Person("앵무새", 99);
    }

    @GetMapping("/f3")
    LinkedList<Person>f3(){
      System.out.println("bpp 8 f3");
      LinkedList<Person> li = new LinkedList<>();
      li.add(new Person("라이언", 1));
      li.add(new Person("호랑이", 2));
      return li;
    }
  }
}