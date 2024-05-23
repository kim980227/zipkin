package com.example.springboot03;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
class Multiplication {
    int factorA, factorB;
}

@Service
class RandomGenServiceImpl {
    int getGetRandom() {
        return new Random().nextInt(10);
    }
}

@Service
class MultiplicationServiceImpl {
    @Autowired
    RandomGenServiceImpl randomGenService;

    Multiplication createRandomMultiplication() {
        return new Multiplication(
                randomGenService.getGetRandom(), randomGenService.getGetRandom());
    }

    // 답안 체점 서비스
    boolean checkAttempt(A a) {
        return a.getMultiplication().getFactorA() * a.getMultiplication().getFactorB() == a.getResultAttempt();
    }
}

@NoArgsConstructor
@AllArgsConstructor
class User {
    String alias;
}


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class A {
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
@RequestMapping("/app1")
class AppController {

    @Autowired
    RestTemplate rt;

    @GetMapping
    String f1() {
        System.out.println("app1");
//    RestTemplate rt = new RestTemplate();
        String result = rt.getForObject("http://localhost:8082/bpp1", String.class);

        return "app1";
    }
}

@RestController
@RequestMapping("/app2/{num}")
class AppController2 {

    @Autowired
    RestTemplate rt;

    @GetMapping
    String f1(@PathVariable String num) {
        System.out.println("app1");
        String result = rt.getForObject("http://localhost:8082/bpp1", String.class);

        return "app2 : " + num + result;
    }
}

@RestController
@RequestMapping("/app3")
class AppController3 {

    @Autowired
    RestTemplate rt;

    @GetMapping
    String f1() {
        System.out.println("app3");
        String result = rt.getForObject("http://localhost:8082/bpp3/3000", String.class);

        return "app3 : " + result;
    }
}

@RestController
@RequestMapping("/app4/{num}")
class AppController4 {

    @Autowired
    RestTemplate rt;

    @GetMapping
    String f1(@PathVariable String num) {
        System.out.println("app4");
        String result = rt.getForObject("http://localhost:8082/bpp4/" + num, String.class);

        return "app4 : " + num + " " + result;
    }
}

@RestController
@RequestMapping("/app5")
class AppController5 {

    @Autowired
    RestTemplate rt;

    @GetMapping
    String f1() {
        System.out.println("app5");
        Integer result = rt.getForObject("http://localhost:8082/bpp5", Integer.class);

        return "app5 : " + (result + 1);
    }
}

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
class Person {
    String name;
    Integer age;
}

@RestController
@RequestMapping("/app6")
class AppController6 {

    @Autowired
    RestTemplate rt;

    @GetMapping
    String f1() {
        System.out.println("app6");

//    Person result = rt.getForObject("http://localhost:8082/bpp6",Person.class);
//
//    return "app5 : "+ result.getName() +" "+result.getAge();

//    // 정수형 배열
//    Integer result = rt.getForObject("http://localhost:8082/bpp6",Integer[].class);
//
//    return ar[3];

        ResponseEntity<Person[]> result = rt.getForEntity("http://localhost:8082/bpp6", Person[].class); //{apple}에 num의 value가 들어감

        Map<String, Integer> li = new HashMap<String, Integer>();
        for (Person res : result.getBody()) {
            li.put(res.getName(), res.getAge());
        }
        System.out.println(result.getStatusCode());


//    LinkedList<Integer, String, Person> li =new
//            li.add(new Person(data))
//            li.add(10)li.add(10)
//          return li;

//    for (Person res:result) {
//      System.out.println(res.getName()+" "+res.getAge());
//    }

        return "app6 : " + li;
    }

    @GetMapping("/f5")
    String f5() {
        System.out.println("app6");
        Integer num1 = 100;
        Integer num2 = 200;

        ResponseEntity<Integer> result = rt.getForEntity("http://localhost:8082/bpp6/f5/{num1}/{num2}", Integer.class, num1, num2); //num1,num2의 url로 이동하는게 아니다.\
        // 값을 요청받는 api에 num1,mum2의 data를 넘겨주는 것이다
        // http://localhost:8082/bpp6/f5/{num1}/{num2} 로 매핑된 api를 호출, num1,num2의 data를 주고, 결과값을 받아온다.

        System.out.println(result.getStatusCode());

        return "num1 + num2 : " + result.getBody();
    }
}

@RestController
@RequestMapping("/app7")
class AppController7 {
    @Autowired
    RestTemplate rt;

    @GetMapping("")
    Mono<String> f1() {
        WebClient wc = WebClient.create();

        System.out.println(wc.toString()); // 객체가 살아 있는지 확인

        Mono<String> result = wc.get().
                uri("http://localhost:8082/bpp7").
                retrieve().
                bodyToMono(String.class); // 비동기 함수, 콘솔로 찍어도 바로 나오지않는다!!!

        String str = result.block(); // 비동기 함수가 return될때까지 기다림
        System.out.println(str);
        return result;
    }

}

@RestController
@RequestMapping("app8")
class AppController8{
    @Autowired
    RestTemplate rt;
    @GetMapping("")
    Mono<String> f1() {
        System.out.println("f1 call");
        Mono<String> r = f2();
        r.subscribe(v->{
            System.out.println(v);
        });
        return r;
    }

    public Mono<String> f2(){
        WebClient client = WebClient.create();
        return client.get()
                .uri("http://localhost:8082/bpp8")
                .retrieve()
                .bodyToMono(String.class);
    }

    //디버깅용
    @GetMapping("")
    Person f4() {
        WebClient wc = WebClient.create();

        System.out.println(wc.toString()); // 객체가 살아 있는지 확인

        Person result = wc.get()
                .uri("http://localhost:8082/bpp8")
                .retrieve()
                .bodyToMono(Person.class)
                .block(); // block을 받아오면서 해버림

        return result;
    }

    @GetMapping("/f3")
    LinkedList<Person> f3() {
        WebClient wc = WebClient.builder().build();
        LinkedList<Person> li = wc.get()
                .uri("http://localhost:8082/bpp8/f3")
                .retrieve()
                .bodyToMono(LinkedList.class)
                .block();
        return li;
    }
}

