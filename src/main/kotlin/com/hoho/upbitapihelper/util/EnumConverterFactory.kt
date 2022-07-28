package com.hoho.upbitapihelper.util

import kotlinx.serialization.SerialName
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

/**
 * enum 타입 - SerialName에 설정한 값으로 변환
 */
internal class EnumConverterFactory : Converter.Factory() {

    override fun stringConverter(
        type: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<Enum<*>, String>? =
        if (type is Class<*> && type.isEnum) {
            Converter { enum ->
                try {
                    enum.javaClass.getField(enum.name)
                        .getAnnotation(SerialName::class.java)?.value
                } catch (exception: Exception) {
                    null
                } ?: enum.toString()
            }
        } else {
            null
        }
}