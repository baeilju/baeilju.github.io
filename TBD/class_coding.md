
### AbstractEvent.java
```java
public String toJson(){ // serialization, 직렬화 graph구조의 object를 json 문자열 형식으로 전환
        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;

        try {
            json = objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("JSON format exception", e);
        }

        return json;
    }
```

### DeliveryApplication.java
```java
@SpringBootApplication
@EnableBinding(KafkaProcessor.class)
@EnableFeignClients
public class DeliveryApplication {
    protected static ApplicationContext applicationContext;
    public static void main(String[] args) {
        applicationContext = SpringApplication.run(DeliveryApplication.class, args);
      //톰캣 서버를 띄우는 역할
    }
}
```


### Order.java
```java
    @PostPersist
    public void onPostPersist(){
        OrderPlaced orderPlaced = new OrderPlaced(); // 도메인 이벤트 한 건을 준비
        BeanUtils.copyProperties(this, orderPlaced); // 값을 설정, 동일한 필드명에 대하여 복사
        orderPlaced.publishAfterCommit();   // 발사 -> kafka로 이에 대해서는 abstract event에서 구현하여 biz로직을 간소화함
        //publishAfterCommin -> commit된 이후 publish하라는 명령


    }

    // setter, getter 접근자
    // 값 크기 최대, 최소 오류 등에 대한 검증 코드 작성을 위해
    // assert productId > 0;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
```

### PoicyHandler.java
```java
@Service
public class PolicyHandler{
    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverOrderPlaced_StartDelivery(@Payload OrderPlaced orderPlaced){

        if(orderPlaced.isMe()){
            System.out.println("##### listener StartDelivery : " + orderPlaced.toJson());
        }
    }

}
```
