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
> RedisUtils 클래스는 com.project.lettuce.service 패키지 안에 있으며, controller/service만 사용하여 프로젝트 개발

1) opsForValue	: Strings를 쉽게 Serialize/Deserialize 해주는 interface
2) opsForList	: List를 쉽게 Serialize/Deserialize 해주는 interface
3) opsForSet	: Set을 쉽게 Serialize / Deserialize 해주는 interface
4) opsForZSet	: Sorted Set을 쉽게 Serialize / Deserialize 해주는 interface
5) opsForHash	: Hash를 쉽게 Serialize / Deserialize 해주는 interface

<br/>

## Redis Commands
[링크](https://redis.io/commands/)

<br/>

## Redis Data Type에 따라 주로 사용하는 명령어
### Strings
1) SET : String 자료형의 데이터를 저장하는 method
2) SETEX : String 자료형의 데이터를 만료시간과 함께 저장하는 method
3) GET : String 자료형의 저장된 데이터를 가져오는 method
4) GETNX : String 자료형의 데이터를 저장할 때 값이 없으면 저장하는 method
5) GETDEL : 저장된 String 자료형의 데이터를 가져오면서 삭제하는 method
6) STRLEN : 저장된 String 자료형의 데이터의 길이를 가져오는 method
7) SUBSTR/GETRANGE : 저장된 String 자료형의 start-end 범위만큼 자른 문자열 데이터를 가져오는 method
   
<br/>

### Lists
1) LPUSH : List 자료형의 데이터를 왼쪽으로 원소를 삽입하는 method
2) LPOP : List 자료형의 데이터의 가장 왼쪽에 있는 원소를 가져오면서 제거하는 method
3) RPUSH : List 자료형의 데이터를 오른쪽으로 원소를 삽입하는 method
4) RPOP : List 자료형의 데이터의 가장 오른쪽에 있는 원소를 가져오면서 제거하는 method
5) RANGE : List 자료형의 데이터의 범위를 설정된 것과 같이 가져오는 method
6) TRIM : List 자료형의 데이터를 start-end 범위의 원소들을 보존한 후 나머지 원소들을 제거하여 List 자료형으로 저장하는 method
7) LINDEX : List 자료형의 key와 value에 대한 index값 리턴하는 method
8) LSIZE : List 자료형에 들어있는 원소들의 개수 리턴하는 method

<br/>

### Sets
1) SADD : Set 자료형의 데이터를 저장하는 method
2) SPOP : Set 자료형의 랜덤으로 하나 데이터를 삭제하는 method
3) SREM : key-value에 대한 Set 자료형의 데이터를 삭제하는 method
4) SISMEMBER : Set 자료형의 데이터 중 key-value에 해당하는 데이터가 있는지 확인하는 method
5) SMEMBERS : Set 자료형의 데이터의 key에 있는 데이터 리턴하는 method
6) SRANDMEMBER : Set 자료형의 데이터 중 랜덤으로 하나의 데이터를 리턴하는 method
7) SINTER : 두 개의 Set 자료형의 데이터 중 교집합으로 존재하는 데이터들을 리턴하는 method

<br/>

### Sorted Sets
1) ZADD : Sorted Set 자료형의 데이터(키-값-점수) 추가하는 method
2) ZREM : Sorted Set 자료형의 데이터(키-값) 제거하는 method
3) ZRANGE : Sorted Set 자료형의 키 범위에 대한 오름차순 데이터 검색하는 method
4) ZREVRANGE : Sorted Set 자료형의 키 범위에 대한 내림차순 데이터 검색하는 method
5) ZINCRBY : Sorted Set 자료형의 데이터(키-값)에 대한 점수값 증가 및 감소하는 method
6) ZCARD : Sorted Set 자료형의 키 값에 대한 저장된 value의 개수 리턴하는 method
7) ZCOUNT : Sorted Set 자료형에서 score값이 min과 max 사이에 있는 value의 개수 리턴하는 method
8) ZRANK : Sorted Set 자료형에서 score가 저장된 value의 오름차순으로 순위를 리턴하는 method
9) ZREVRANK : Sorted Set 자료형에서 score가 저장된 value의 내림차순으로 순위를 리턴하는 method
10) ZSCORE : Sorted Set 자료형에서 key-value에 대한 score값을 리턴하는 method
11) ZREVRANGEBYSCORE : Sorted Set 자료형에서 내림차순으로 min <= score <= max인 value값 Set형식으로 리턴하는 method
12) ZREVRANGEWITHSCORE : Sorted Set 자료형에서 내림차순으로 min <= score <= max인 value-score값을 Set형식으로 리턴하는 method

<br/>

### Hashes
1) HGET : Hash 자료형에서 key와 hashKey에 대한 값 리턴하는 method
2) HGETALL : Hash 자료형에서 key에 대한 hashKey 값들 모두 가져와서 리턴하는 method
3) HSET : Hash 자료형에서 key, hashKey, value 값을 저장하는 method
4) HSETNX : Hash 자료형에서 key, hashKey, value 값을 저장 시도할 때, 성공 시 true, 실패 시(=이미 value값이 있을 때) false를 리턴하는 method
5) HDEL : Hash 자료형에서 key, hashKey, value 값을 삭제 성공 시 true, 삭제 실패 시(=이미 value값이 없을 때) false를 리턴하는 method
6) HEXISTS : Hash 자료형에서 key, hashKey에 대한 value값 존재 여부 확인하는 method
7) HINCRBY : Hash 자료형에서 key, hashKey에 대한 value값에 incValue값만큼 증가시킨 값을 리턴하는 method
8) HKEYS : Hash 자료형에서 key에 대한 hashKey를 가져와서 리턴하는 method
9) HLEN : Hash 자료형에서 key에 저장된 hashKey의 개수를 모두 가져와서 리턴하는 method
10) HSTRLEN : Hash 자료형에서 key의 hashKey에 저장 데이터의 길이 리턴하는 method
11) HRANDFIELD : Hash 자료형에서 key에 저장된 hashKey 중에서 랜덤으로 한개 가져와서 리턴하는 method
