package org.luisjulliana.bridalshower.database

import com.luisjulliana.bridalshower.domain.models.Item
import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.client.MongoClients
import com.mongodb.client.model.Filters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.bson.Document
import org.bson.conversions.Bson
import org.luisjulliana.bridalshower.BuildConfig
import org.luisjulliana.bridalshower.mapper.ItemMapper.mapToDomain

private const val CONNECTION_STRING = BuildConfig.MONDODBCONNECTIONSTRING
private const val DATABASE_NAME = "chadepanela"
private const val COLLECTION_NAME = "items"

object MongoDbDatabase {

    suspend fun getItems(
        status: Bson,
        category: Bson
    ): List<Item> = withContext(Dispatchers.IO) {
        val connectionString = ConnectionString(CONNECTION_STRING)
        val settings = MongoClientSettings.builder()
            .applyConnectionString(connectionString)
            .build()

        val client = MongoClients.create(settings)
        val database = client.getDatabase(DATABASE_NAME)
        val itemsCollection = database.getCollection(COLLECTION_NAME, Document::class.java)

        val combinedFilter = Filters.and(status, category)

        return@withContext itemsCollection.find(combinedFilter).toList().mapToDomain()
    }
}