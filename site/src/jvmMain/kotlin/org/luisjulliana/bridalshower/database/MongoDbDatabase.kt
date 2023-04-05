package org.luisjulliana.bridalshower.database

import com.luisjulliana.bridalshower.domain.models.Item
import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.client.MongoClients
import com.mongodb.client.MongoCollection
import org.bson.Document
import org.luisjulliana.bridalshower.mapper.ItemMapper.mapToDomain

private const val CONNECTION_STRING = "mongodb+srv://luisfelipecf94:sEcret01@cluster0.qrmnt0q.mongodb.net/?retr yWrites=true&w=majority"
private const val DATABASE_NAME = "chadepanela"

object MongoDbDatabase {

    suspend fun getItems(): List<Item> {
        val client = MongoClients.create(setUpSettings())
        val database = client.getDatabase(DATABASE_NAME)
        val itemsCollection: MongoCollection<Document> = database.getCollection("items")

        return itemsCollection.find().toList().mapToDomain()
    }

    private fun setUpSettings() = MongoClientSettings.builder()
        .applyConnectionString(ConnectionString(CONNECTION_STRING))
        .build()
}