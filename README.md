[![Gradle Build](https://github.com/hoho4190/upbit-api-helper/actions/workflows/gradle-build.yml/badge.svg)](https://github.com/hoho4190/upbit-api-helper/actions/workflows/gradle-build.yml)

# UPbit API Helper

## 참고
- [UPbit API reference v1.3.2](https://docs.upbit.com/v1.3.2/reference)
- [UPbit Guides](https://docs.upbit.com/docs)
  - 요청 수 제한
  - API 주요 에러 코드 목록
  - 원화 마켓 주문 가격 단위

## 기능
### EXCHANGE API
- [x] 자산
  - [x] 전체 계좌 조회
- [x] 주문
  - [x] 주문 가능 정보
  - [x] 개별 주문 조회
  - [x] 주문 리스트 조회
  - [x] 주문 취소 접수
  - [x] 주문하기
- [ ] ~~출금~~
- [ ] ~~입금~~
- [x] 서비스 정보
  - [x] 입출금 현황
  - [x] API 키 리스트 조회

### QUOTATION API
- [x] 시세 종목 조회
  - [x] 마켓 코드 조회
- [x] 시세 캔들 조회
  - [x] 분(Minute) 캔들
  - [x] 일(Day) 캔들
  - [x] 주(Week) 캔들
  - [x] 월(Month) 캔들
- [x] 시세 체결 조회
  - [x] 최근 체결 내역
- [x] 시세 Ticker 조회
  - [x] 현재가 정보
- [x] 시세 호가 정보(Orderbook) 조회
  - [x] 호가 정보 조회

### Board API
- [x] 공지사항 조회
- [x] 업비트 소식 조회
- [x] 프로젝트 공시 조회

## 사용

- ex) 프로젝트 공시 조회
  ```kotlin
  val callSync = BoardApi.getDisclosureList(3)
  val response = callSync.execute()
  
  if (response.isSuccessful) { 
      val data = response.body()
  } else { 
      // error handling ...
      val error = RetrofitUtil.getErrorResponse(response)
  }
  ```

- ex) 주문하기 - 시장가 매수
  ```kotlin
  val openApiKey = OpenApiKey(
    "OPEN_API_ACCESS_KEY", "OPEN_API_SECRET_KEY"
  )
  val market = "KRW-BTC"
  val price = "10000"
  
  val callSync = ExchangeApi.postOrdersBidPrice(openApiKey, market, price)
  val response = callSync.execute()
  ```
