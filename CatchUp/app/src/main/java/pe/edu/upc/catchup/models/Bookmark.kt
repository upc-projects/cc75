package pe.edu.upc.catchup.models

import com.orm.SugarRecord

class Bookmark() : SugarRecord() {
    var sourceId: String? = null

    companion object {
        fun sourceIdsAsString(): String {
            return listAll()
                    .joinToString { bookmark ->
                        bookmark.sourceId!!
                    }
        }

        fun listAll(): List<Bookmark> {
            return SugarRecord.listAll(Bookmark::class.java)
        }

        fun listFor(sourceId: String): List<Bookmark> {
            return SugarRecord.find(Bookmark::class.java,
                    "source_id = ? ", sourceId)
        }
    }
}