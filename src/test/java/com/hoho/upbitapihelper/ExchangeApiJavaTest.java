package com.hoho.upbitapihelper;

import com.hoho.upbitapihelper.dto.ErrorResponse;
import com.hoho.upbitapihelper.dto.OpenApiKey;
import com.hoho.upbitapihelper.dto.exchange.Account;
import com.hoho.upbitapihelper.util.TestUtil;
import org.junit.jupiter.api.*;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

@Disabled
@DisplayName("E2E Test - Exchange API - Java")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ExchangeApiJavaTest {

    private OpenApiKey openApiKey;

    @BeforeAll
    void beforeAll() {
        openApiKey = TestUtil.getOpenApiKey();
    }

    @Disabled
    @Test
    @DisplayName("자산 - 전체 계좌 조회")
    public void getAccountsTest() throws IOException {
        // Given

        // When
        Call<List<Account>> call = ExchangeApi.getAccounts(openApiKey);
        Response<List<Account>> response = call.execute();

        // Then
        if (response.isSuccessful()) {
            List<Account> result = response.body();
            assert result != null;
            System.out.println("size: " + result.size());
            System.out.println(response.body());
        } else {
            ErrorResponse result = ErrorResponse.from(response);
            System.out.println(result);
        }
        Assertions.assertTrue(response.isSuccessful());
    }

    @Disabled
    @Test
    @DisplayName("자산 - 전체 계좌 조회 - async")
    public void getAccountsTest_async() throws InterruptedException {
        // Given

        // When
        Call<List<Account>> call = ExchangeApi.getAccounts(openApiKey);
        call.enqueue(new Callback<List<Account>>() {

            // Then
            @Override
            public void onResponse(Call<List<Account>> call, Response<List<Account>> response) {
                if (response.isSuccessful()) {
                    List<Account> result = response.body();
                    assert result != null;
                    System.out.println("size: " + result.size());
                    System.out.println(response.body());
                } else {
                    ErrorResponse result = ErrorResponse.from(response);
                    System.out.println(result);
                }
                Assertions.assertTrue(response.isSuccessful());
            }

            @Override
            public void onFailure(Call<List<Account>> call, Throwable t) {
                t.printStackTrace();
            }
        });

        Thread.sleep(1000L);
    }
}
