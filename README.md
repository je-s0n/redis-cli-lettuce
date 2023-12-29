## Redis 설치 및 접속 방법
> 사용 PC : Macbook M2 Pro 13

<br/>
 
1) redis 설치
```script
$ brew install redis
```

2) 다운로드 받은 redis 버전 확인
```script
$ redis-server --version
```

3) Redis 서비스 시작
- Redis Default Port Number = 6379
```script
$ brew services start redis
```

4) 터미널로 접속하기
```script
$ redis-cli -h localhost -p 6379
```

5) Redis client tool을 이용하여 접속하기
- Apple 전용 Redis client tool인 [Medis](https://getmedis.com)를 이용해 접속 완료
![image](https://github.com/je-s0n/redis-cli-lettuce/assets/152856285/ed58d671-d821-47a1-b484-09b335252bab)

<br/>

## Spring Boot 프로젝트에서 lettuce를 이용하여 Redis를 사용하는 방법
1) repository에서 Entity를 사용
2) RedisTemplate를 활용하여 Service단에 RedisUtils 클래스를 만들어 사용


#### 2번 방법으로 진행해 RedisTemplete 활용하여 RedisUtils 클래스 생성
> RedisUtils 클래스는 com.project.lettuce.service 패키지 안에 있음

1) opsForValue	: Strings를 쉽게 Serialize/Deserialize 해주는 interface
2) opsForList	: List를 쉽게 Serialize/Deserialize 해주는 interface
3) opsForSet	: Set을 쉽게 Serialize / Deserialize 해주는 interface
4) opsForZSet	: Sorted Set을 쉽게 Serialize / Deserialize 해주는 interface
5) opsForHash	: Hash를 쉽게 Serialize / Deserialize 해주는 interface

<br/>

## Redis Commands
[링크](https://redis.io/commands/)

<br/>

## Redis Data Type에 따른 명령어
### Strings

### Lists

### Sets

### Sorted Sets

### Hashes
