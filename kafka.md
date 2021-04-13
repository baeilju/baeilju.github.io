- 참고 : https://man-tae.tistory.com/5

- Server 실행
```
zookeeper-server-start.bat ../../config/zookeeper.properties
kafka-server-start.bat ../../config/server.properties
```

- Topic 생성/조회
```
kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic test21041301
kafka-topics.bat --list -zookeeper localhost:2181
```

- 컨슈머, 프로듀서 시작
```
kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic test21041301
kafka-console-producer.bat --broker-list localhost:9092 --topic test21041301
```
