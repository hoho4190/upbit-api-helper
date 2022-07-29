package com.hoho.upbitapihelper

import com.hoho.upbitapihelper.dto.ErrorResponse
import com.hoho.upbitapihelper.dto.OpenApiKey
import com.hoho.upbitapihelper.util.RetrofitUtil
import com.hoho.upbitapihelper.util.TestUtil
import org.junit.jupiter.api.*

@Disabled
@DisplayName("E2E Test - Exchange API")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ExchangeApiTest {

    private lateinit var openApiKey: OpenApiKey

    @BeforeAll
    fun beforeAll() {
        openApiKey = TestUtil.getOpenApiKey()
    }

    @Disabled
    @Test
    @DisplayName("자산 - 전체 계좌 조회")
    fun getAccountsTest() {
        // Given

        // When
        val callSync = ExchangeApi.getAccounts(openApiKey)
        val response = callSync.execute()

        // Then
        println("url: ${response.raw().request().url()}")
        if (response.isSuccessful) {
            val result = response.body()
            println("size: ${result!!.size}")
            println(TestUtil.convertPrettyString(result))
        } else {
            val result: ErrorResponse? = RetrofitUtil.getErrorResponse(response)
            println(TestUtil.convertPrettyString(result))
        }
        Assertions.assertTrue(response.isSuccessful)
    }
}
