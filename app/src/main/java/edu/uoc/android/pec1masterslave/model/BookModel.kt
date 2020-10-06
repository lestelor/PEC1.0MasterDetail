package edu.uoc.android.pec1masterslave.model

import android.graphics.Bitmap
import java.util.*


object BookModel {

    var ITEM: MutableList<BookItem> = mutableListOf()

    val book1 = BookItem(0, "Alien", "Ridley Scott", Date(), "Description1", null)
    val book2 =BookItem(        1, "Terminator", "James Cameron", Date(), "Description2", null)
    val book3= BookItem(2, "Interestellar", "Christopher Nolan", Date(), "Description3", null)
    val book4 = BookItem(3, "Alien", "Ridley Scott", Date(), "Description4", null)
    val book5 =BookItem(        4, "Terminator", "James Cameron", Date(), "Description5", null)
    val book6= BookItem(5, "Interestellar", "Christopher Nolan", Date(), "Description6", null)
    val book7 = BookItem(6, "Alien", "Ridley Scott", Date(), "Description7", null)
    val book8 =BookItem(        7, "Terminator", "James Cameron", Date(), "Description8", null)
    val book9= BookItem(8, "Interestellar", "Christopher Nolan", Date(), "Description9", null)

    init {
        this.ITEM.add(0, book1)
        this.ITEM.add(book2)
        this.ITEM.add(book3)
        this.ITEM.add(book4)
        this.ITEM.add(book5)
        this.ITEM.add(book6)
        this.ITEM.add(book7)
        this.ITEM.add(book8)
        this.ITEM.add(book9)
    }

    class BookItem(
        val identificador: Int? = null,
        val titulo: String? = null,
        val autor: String? = null,
        val fechaPublicacion: Date? = null,
        val descripccion: String? = null,
        val urlPortada: String? = null
    ): java.io.Serializable


}