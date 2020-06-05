package com.example.yoweather.weather.adapter

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class GenericCollectionAdapterFactory<TCollection : MutableCollection<*>>(
    private val collectionClazz: Class<TCollection>,
    private val createEmptyCollection: () -> MutableCollection<Any>
) : JsonAdapter.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun create(
        type: Type,
        annotations: MutableSet<out Annotation>,
        moshi: Moshi
    ): JsonAdapter<*>? {
        val paramType = type as? ParameterizedType ?: return null
        if (paramType.rawType.typeName != collectionClazz.typeName) return null
        if (paramType.actualTypeArguments.size != 1) return null
        val argTypeName = paramType.actualTypeArguments[0].typeName
        val argType = Class.forName(argTypeName) as Class<Any>

        return GenericCollectionAdapter(argType, moshi, createEmptyCollection)
    }
}

