package com.josetorres.marvel.data

import com.jtorreal.superheromarvel.data.datasources.local.model.SuperHeroRoom
import com.jtorreal.superheromarvel.data.datasources.remote.model.response.SuperHeroServer
import com.jtorreal.superheromarvel.domain.model.SuperHeroDomain
import com.jtorreal.superheromarvel.domain.model.Thumbnail as ThumbnailDomain
import com.jtorreal.superheromarvel.data.datasources.remote.model.response.Thumbnail as ThumbnailServer
import com.jtorreal.superheromarvel.data.datasources.local.model.Thumbnail as ThumbnailRoom


fun SuperHeroServer.toDomainSuperHero(): SuperHeroDomain =
    SuperHeroDomain(
        id = id.toDefaultInteger(),
        name = name.toEmptyString(),
        description = description.toEmptyString(),
        thumbnail = ThumbnailDomain(
            thumbnail.toEmptyObjectServer().extension,
            thumbnail.toEmptyObjectServer().path
        )
    )

/**ROOM**/
fun SuperHeroDomain.toRoomSuperHero(): SuperHeroRoom =
    SuperHeroRoom(
        id = id.toDefaultInteger(),
        name = name.toEmptyString(),
        description = description.toEmptyString(),
        thumbnail = ThumbnailRoom(
            thumbnail.toEmptyObjectDomain().extension,
            thumbnail.toEmptyObjectDomain().path
        )
    )

fun SuperHeroRoom.toDomainSuperHero(): SuperHeroDomain =
    SuperHeroDomain(
        id = id.toDefaultInteger(),
        name = name.toEmptyString(),
        description = description.toEmptyString(),
        thumbnail = ThumbnailDomain(
            thumbnail.toEmptyObjectRoom().extension,
            thumbnail.toEmptyObjectRoom().path
        )
    )

fun ThumbnailServer?.toEmptyObjectServer(): ThumbnailServer {
    if (this == null) return ThumbnailServer("", "")
    return this
}

fun ThumbnailDomain?.toEmptyObjectDomain(): ThumbnailDomain {
    if (this == null) return ThumbnailDomain("", "")
    return this
}

fun ThumbnailRoom?.toEmptyObjectRoom(): ThumbnailRoom {
    if (this == null) return ThumbnailRoom("", "")
    return this
}

fun String?.toEmptyString(): String {
    if (this == null) {
        return ""
    }
    return toString()
}

fun Int?.toDefaultInteger(): Int {
    if (this == null) return 0
    return this
}









