# club-milege-service
사용자들이 장소에 리뷰를 작성할 때 포인트를 부여하고, 전체/개인에 대한 포인트 부여 히스토리와 개인별
누적 포인트를 관리하고자 합니다

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

빌드 하는 방법
- java -jar clubmileageservice.war   
-> article 폴더에 clubmileageservice.war 를 바로 실행 하면 됩니다.

### table 생성
- table 의 생성 sql -> [clubmilage.sql](https://github.com/Eco-Min/club-milege-service/blob/main/article/clubmilage.sql)
- .yml -> ddl-auto : update -> 자동으로 관리 됩니다.
