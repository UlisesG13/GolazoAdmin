package com.ulisesg.admingolazo.freatures.data.datasources.remote.mapper

import com.ulisesg.admingolazo.freatures.data.datasources.remote.model.ImageReponse
import com.ulisesg.admingolazo.freatures.domain.entities.Image

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
