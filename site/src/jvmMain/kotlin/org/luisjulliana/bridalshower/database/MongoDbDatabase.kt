package org.luisjulliana.bridalshower.database

import com.luisjulliana.bridalshower.domain.models.Item
import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.bson.Document
import org.bson.conversions.Bson
import com.mongodb.client.MongoClients
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo
import org.luisjulliana.bridalshower.mapper.ItemMapper.mapToDomain

private const val CONNECTION_STRING =
    "mongodb+srv://luisfelipecf94:sEcret01@cluster0.qrmnt0q.mongodb.net/?retr yWrites=true&w=majority"
private const val DATABASE_NAME = "chadepanela"

object MongoDbDatabase {

    suspend fun getItems(status: Bson): List<Item> = withContext(Dispatchers.IO) {
        val connectionString = ConnectionString(CONNECTION_STRING)
        val settings = MongoClientSettings.builder()
            .applyConnectionString(connectionString)
            .build()

        val client = MongoClients.create(settings)
        val database = client.getDatabase(DATABASE_NAME)
        val itemsCollection = database.getCollection("items", Document::class.java)

        return@withContext itemsCollection.find(status).toList().mapToDomain()
    }
}