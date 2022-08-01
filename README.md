[![Gradle Build](https://github.com/hoho4190/upbit-api-helper/actions/workflows/gradle-build.yml/badge.svg)](https://github.com/hoho4190/upbit-api-helper/actions/workflows/gradle-build.yml)
[![Gradle Package](https://github.com/hoho4190/upbit-api-helper/actions/workflows/gradle-publish.yml/badge.svg)](https://github.com/hoho4190/upbit-api-helper/actions/workflows/gradle-publish.yml)
[![](https://jitpack.io/v/hoho4190/upbit-api-helper.svg)](https://jitpack.io/#hoho4190/upbit-api-helper)

# UPbit API Helper
UPbit API Helper using [Retrofit](https://square.github.io/retrofit/)

## API List
### Exchange API - `ExchangeApi`
- [x] 자산
  - [x] 전체 계좌 조회 - `getAccounts`
- [x] 주문
  - [x] 주문 가능 정보 - `getOrdersChance`
  - [x] 개별 주문 조회
    - by uuid - `getOrderByUuid`
    - by identifier - `getOrderByIdentifier`
  - [x] 주문 리스트 조회
    - by uuid - `getOrdersByUuid`
    - iby identifier - `getOrdersByIdentifier`
  - [x] 주문 취소 접수
    - by uuid - `deleteOrderByUuid`
    - by identifier - `deleteOrderByIdentifier`
  - [x] 주문하기
    - 지정가 매수 - `postOrdersBidLimit`
    - 지정가 매도 - `postOrdersAskLimit`
    - 시장가 매수 - `postOrdersBidPrice`
    - 시장가 매도 - `postOrdersAskMarket`
- [ ] ~~출금~~
- [ ] ~~입금~~
- [x] 서비스 정보
  - [x] 입출금 현황 - `getWalletStatus`
  - [x] API 키 리스트 조회 - `getApiKeys`

### Quotation API - `QuotationApi`
- [x] 시세 종목 조회
  - [x] 마켓 코드 조회 - `getMarketAll`
- [x] 시세 캔들 조회
  - [x] 분(Minute) 캔들 - `getCandlesMinutes`
  - [x] 일(Day) 캔들 - `getCandlesDays`
  - [x] 주(Week) 캔들 - `getCandlesWeeks`
  - [x] 월(Month) 캔들 - `getCandlesMonths`
- [x] 시세 체결 조회
  - [x] 최근 체결 내역 - `getTradesTicks`
- [x] 시세 Ticker 조회
  - [x] 현재가 정보 - `getTicker`
- [x] 시세 호가 정보(Orderbook) 조회
  - [x] 호가 정보 조회 - `getOrderbook`

### Board API
- [x] 공지사항 조회 - `getNoticeList`
- [x] 업비트 소식 조회 - `getNewsList`
- [x] 프로젝트 공시 조회 - `getDisclosureList`
> ★ Board API 사용 시 https://upbit.com/robots.txt 참고

## Usage
### [Dependency](https://jitpack.io/#hoho4190/upbit-api-helper)
#### Gradle
- Step 1. Add the JitPack repository to your build file
  ```kotlin
  allprojects {
      repositories {
          // ...
          maven {
              url = uri("https://jitpack.io")
          }
      }
  }
  ```
- Step 2. Add the dependency
  ```kotlin
  dependencies {
      implementation("com.github.hoho4190:upbit-api-helper:Tag")
  }
  ```

#### Maven
- Step 1. Add the JitPack repository to your build file
  ```xml
  <repositories>
      <repository>
          <id>jitpack.io</id>
          <url>https://jitpack.io</url>
      </repository>
  </repositories>
  ```
- Step 2. Add the dependency
  ```xml
  <dependency>
      <groupId>com.github.hoho4190</groupId>
      <artifactId>upbit-api-helper</artifactId>
      <version>Tag</version>
  </dependency>
  ```

### Example

#### ex) 최근 3건의 프로젝트 공시 조회
- Kotlin
  ```kotlin
  val call = BoardApi.getDisclosureList(3)
  val response = call.execute()
  
  if (response.isSuccessful) { 
      val data = response.body()
  } else {
      val errorResponse = ErrorResponse.from(response)
  }
  ```
- Java
  ```java
  Call<Board<DisclosureData>> call = BoardApi.getDisclosureList(3);
  Response<Board<DisclosureData>> response = call.execute();
  
  if (response.isSuccessful()) {
      Board<DisclosureData> data = response.body();
  } else {
      ErrorResponse errorResponse = ErrorResponse.from(response);
  }
  ```

#### ex) KRW 마켓에서 BTC를 10,000원어치 시장가 매수
- Kotlin
  ```kotlin
  val openApiKey = OpenApiKey(
    "OPEN_API_ACCESS_KEY", "OPEN_API_SECRET_KEY"
  )
  val market = "KRW-BTC"
  val price = "10000"
  
  val call = ExchangeApi.postOrdersBidPrice(openApiKey, market, price)
  val response = call.execute()
  
  if (response.isSuccessful) { 
      val data = response.body()
  } else {
      val errorResponse = ErrorResponse.from(response)
  }
  ```
- Java
  ```java
  OpenApiKey openApiKey = new OpenApiKey(
      "OPEN_API_ACCESS_KEY", "OPEN_API_SECRET_KEY"
  );
  String market = "KRW-BTC";
  String price = "10000";
  
  Call<Order> call = ExchangeApi.postOrdersBidPrice(openApiKey, market, price);
  Response<Order> response = call.execute();
  
  if (response.isSuccessful()) {
      Order data = response.body();
  } else {
      ErrorResponse errorResponse = ErrorResponse.from(response);
  }
  ```
  > [원화 마켓 주문 가격 단위 참고](https://docs.upbit.com/docs/market-info-trade-price-detail)

#### ex) Sync call
- Kotlin
  ```kotlin
  val call: Call<Board<DisclosureData>> = BoardApi.getDisclosureList(perPage, offset, region)
  val response: Response<Board<DisclosureData>> = call.execute()
  
  if (response.isSuccessful) {
      val data: Board<DisclosureData>? = response.body()
  } else {
      val errorResponse: ErrorResponse? = ErrorResponse.from(response)
  }
  ```
- Java
  ```java
  Call<Board<DisclosureData>> call = BoardApi.getDisclosureList(perPage, offset, region);
  Response<Board<DisclosureData>> response = call.execute();
  
  if (response.isSuccessful()) {
      Board<DisclosureData> data = response.body();
  } else {
      ErrorResponse errorResponse = ErrorResponse.from(response);
  }
  ```

#### ex) Async call
- Kotlin
  ```kotlin
  val call: Call<Board<DisclosureData>> = BoardApi.getDisclosureList(perPage, offset, region)
  call.enqueue(object: Callback<Board<DisclosureData>> {
      override fun onResponse(call: Call<Board<DisclosureData>>, response: Response<Board<DisclosureData>>) {
          if (response.isSuccessful) {
              val data: Board<DisclosureData>? = response.body()
          } else {
              val errorResponse: ErrorResponse? = ErrorResponse.from(response)
          }
      }
  
      override fun onFailure(call: Call<Board<DisclosureData>>, t: Throwable) {
          // failure handling...
      }
  })
  ```
- Java
  ```java
  Call<Board<DisclosureData>> call = BoardApi.getDisclosureList(perPage, offset, region);
  call.enqueue(new Callback<Board<DisclosureData>>() {
      
      @Override
      public void onResponse(Call<Board<DisclosureData>> call, Response<Board<DisclosureData>> response) {
          if (response.isSuccessful()) {
              Board<DisclosureData> data = response.body();
          } else {
              ErrorResponse errorResponse = ErrorResponse.from(response);
          }
          Assertions.assertTrue(response.isSuccessful());
      }
  
      @Override
      public void onFailure(Call<Board<DisclosureData>> call, Throwable t) {
          // failure handling...
      }
  });
  ```

## References
- [UPbit API reference v1.3.2](https://docs.upbit.com/v1.3.2/reference)
- [UPbit Guides](https://docs.upbit.com/docs)
  - 요청 수 제한
  - API 주요 에러 코드 목록
  - 원화 마켓 주문 가격 단위
- [Upbit robots.txt](https://upbit.com/robots.txt)
- [Retrofit](https://square.github.io/retrofit/)
