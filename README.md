
## API 테스트

### 장소 검색
#### 예시
```bash
curl -G --data-urlencode "keyword=은행" http://localhost:8080/v1/locations 
```

#### 응답
```json
{
    "header": {
        "message": "SUCCESS"
    },
    "data": {
        "totalCount": 10,
        "list": [
            {
                "title": "별미곱창 본점"
            },
            {
                "title": "세광양대창 교대본점"
            },
            {
                "title": "해성막창집 본점"
            },
            {
                "title": "곱 마포점"
            },
            {
                "title": "백화양곱창 6호"
            },
            {
                "title": "우월소<b>곱창</b> 신도림본점"
            },
            {
                "title": "망원동맛집 초월양<b>곱창</b>"
            },
            {
                "title": "제일<b>곱창</b> 본점"
            },
            {
                "title": "오마이양대창 잠실점"
            },
            {
                "title": "불구멍"
            }
        ]
    }
}
```

### 검색 키워드 목록
#### 예시
```bash
curl -G 'http://localhost:8080/v1/keywords'
```

#### 응답
```json
{
    "header": {
        "message": "SUCCESS"
    },
    "data": {
        "totalCount": 4,
        "list": [
            {
                "name": "곱창",
                "count": 7
            },
            {
                "name": "은행",
                "count": 4
            },
            {
                "name": "이사",
                "count": 2
            },
            {
                "name": "용달",
                "count": 1
            }
        ]
    }
}
```