package de.eventmodelers.catalog.addmissingdata.internal.adapter

import org.springframework.stereotype.Component

data class BookInfo(val title: String?, val author: String?, val description: String?)

@Component
class FetchExternalDataFromWarehouse {

  fun fetch(id: String): BookInfo {
    return BookInfo("Harry Potter", "j.k. rowling", "external information")
  }
}
