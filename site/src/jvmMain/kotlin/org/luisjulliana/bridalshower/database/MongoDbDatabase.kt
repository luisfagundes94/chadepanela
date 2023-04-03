package org.luisjulliana.bridalshower.database

import RoomType
import com.luisjulliana.bridalshower.domain.enums.ItemStatus
import com.luisjulliana.bridalshower.domain.models.Item
import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.client.MongoClients
import com.mongodb.client.MongoCollection
import org.bson.Document

private const val CONNECTION_STRING = "mongodb+srv://luisfelipecf94:hardstyle4ever@cluster0.qrmnt0q.mongodb.net/?retryWrites=true&w=majority"
private const val DATABASE_NAME = "chadepanela"

object MongoDbDatabase {

    suspend fun getItems(): List<Item> {
        val client = MongoClients.create(getSettings())
        val database = client.getDatabase(DATABASE_NAME)
        val itemsCollection: MongoCollection<Document> = database.getCollection("items")
        return itemsCollection.find().toList().map { document ->
            Item(
                id = document.getObjectId("_id").toString(),
                name = document.getString("name"),
                imageUrl = document.getString("imageUrl"),
                price = document.getDouble("price").toFloat(),
                url = document.getString("url"),
                roomType = RoomType.valueOf(document.getString("roomType")),
                status = ItemStatus.valueOf(document.getString("status")),
                quantity = document.getInteger("quantityNeeded")
            )
        }
    }

    private fun getSettings() = MongoClientSettings.builder()
        .applyConnectionString(ConnectionString(CONNECTION_STRING))
        .build()
}