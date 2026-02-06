package com.ulisesg.admingolazo.freatures.products.data.datasources.remote.mapper

import com.ulisesg.admingolazo.freatures.products.data.datasources.remote.model.ImageReponse
import com.ulisesg.admingolazo.freatures.products.domain.entities.Image

fun ImageReponse.toDomain(): Image {
    return Image(
        id = id,
        path = path,
        orden = orden
    )
}

fun List<ImageReponse>.toDomain(): List<Image> {
    return this.map { it.toDomain() }
}
