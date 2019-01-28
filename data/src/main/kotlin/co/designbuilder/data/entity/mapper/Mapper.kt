package co.designbuilder.data.entity.mapper


abstract class Mapper<Src, Dst> {

  abstract fun map(src: Src): Dst

  abstract fun mapBack(dst: Dst): Src

  fun map(collection: Collection<Src>): Collection<Dst> = collection.map {
    map(it)
  }

  fun mapBack(collection: Collection<Dst>): Collection<Src> = collection.map {
    mapBack(it)
  }

}
