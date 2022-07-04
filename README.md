# club-milege-service

### DB user 생성
mysql version 8.0.28
```sql
create user kjmin@'localhost' identified by '1234';   
create database club_mileage;   
grant all privileges on club_mileage.* to kjmin@localhost;
```   
### bootWar (내장 톰켓 생성 방법)
- [window] : ./gradlew.bat bootWar   
  [macOS / LINUX] : ./gradlew bootWar
- build/libs java -jar clubmileageservice.war   

-> article 폴더에 clubmileageservice.war 를 바로 실행 시켜도 됩니다.

### table 생성
- table 의 생성 sql -> [clubmilage.sql](https://github.com/Eco-Min/club-milege-service/blob/main/article/clubmilage.sql)
- .yml -> ddl-auto : update -> table 자동 생성
