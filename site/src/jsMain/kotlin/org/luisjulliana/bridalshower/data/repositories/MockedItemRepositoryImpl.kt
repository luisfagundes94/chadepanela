package org.luisjulliana.bridalshower.data.repositories

import com.luisjulliana.bridalshower.core.DataState
import com.luisjulliana.bridalshower.domain.enums.ItemStatus
import com.luisjulliana.bridalshower.domain.enums.CategoryType
import com.luisjulliana.bridalshower.domain.models.Item
import com.luisjulliana.bridalshower.domain.repositories.ItemRepository

class MockedItemRepositoryImpl : ItemRepository {

    private val items =  listOf(
        Item(
            id = "1",
            name = "TV Samsung 55 NEO QLed",
            imageUrl = "https://cdn.leroymerlin.com.br/products/smart_tv_samsung_neo_qled_4k_55qn85a_desing_slim_mini_led_pro_1567494622_285c_600x600.jpg",
            price = 4200f.toString(),
            url = "https://www.samsung.com/br/tvs/qled-tv/qn85a-55-inch-neo-qled-4k-smart-tv-qn55qn85aagxzd/",
            categoryType = CategoryType.ALL,
            status = ItemStatus.AVAILABLE,
            quantity = 2
        ),
        Item(
            id = "1",
            name = "TV Samsung 55 NEO QLed",
            imageUrl = "https://cdn.leroymerlin.com.br/products/smart_tv_samsung_neo_qled_4k_55qn85a_desing_slim_mini_led_pro_1567494622_285c_600x600.jpg",
            price = 4200f.toString(),
            url = "https://www.samsung.com/br/tvs/qled-tv/qn85a-55-inch-neo-qled-4k-smart-tv-qn55qn85aagxzd/",
            categoryType = CategoryType.ALL,
            status = ItemStatus.AVAILABLE,
            quantity = 2
        ),
        Item(
            id = "1",
            name = "TV Samsung 55 NEO QLed",
            imageUrl = "https://cdn.leroymerlin.com.br/products/smart_tv_samsung_neo_qled_4k_55qn85a_desing_slim_mini_led_pro_1567494622_285c_600x600.jpg",
            price = 4200f.toString(),
            url = "https://www.samsung.com/br/tvs/qled-tv/qn85a-55-inch-neo-qled-4k-smart-tv-qn55qn85aagxzd/",
            categoryType = CategoryType.ALL,
            status = ItemStatus.AVAILABLE,
            quantity = 2
        ),
        Item(
            id = "1",
            name = "TV Samsung 55 NEO QLed",
            imageUrl = "https://cdn.leroymerlin.com.br/products/smart_tv_samsung_neo_qled_4k_55qn85a_desing_slim_mini_led_pro_1567494622_285c_600x600.jpg",
            price = 4200f.toString(),
            url = "https://www.samsung.com/br/tvs/qled-tv/qn85a-55-inch-neo-qled-4k-smart-tv-qn55qn85aagxzd/",
            categoryType = CategoryType.ALL,
            status = ItemStatus.AVAILABLE,
            quantity = 2
        ),
        Item(
            id = "1",
            name = "TV Samsung 55 NEO QLed",
            imageUrl = "https://cdn.leroymerlin.com.br/products/smart_tv_samsung_neo_qled_4k_55qn85a_desing_slim_mini_led_pro_1567494622_285c_600x600.jpg",
            price = 4200f.toString(),
            url = "https://www.samsung.com/br/tvs/qled-tv/qn85a-55-inch-neo-qled-4k-smart-tv-qn55qn85aagxzd/",
            categoryType = CategoryType.ALL,
            status = ItemStatus.AVAILABLE,
            quantity = 2
        ),
        Item(
            id = "1",
            name = "TV Samsung 55 NEO QLed",
            imageUrl = "https://cdn.leroymerlin.com.br/products/smart_tv_samsung_neo_qled_4k_55qn85a_desing_slim_mini_led_pro_1567494622_285c_600x600.jpg",
            price = 4200f.toString(),
            url = "https://www.samsung.com/br/tvs/qled-tv/qn85a-55-inch-neo-qled-4k-smart-tv-qn55qn85aagxzd/",
            categoryType = CategoryType.ALL,
            status = ItemStatus.AVAILABLE,
            quantity = 2
        )
    )
    override suspend fun getItems(
        status: ItemStatus?,
        categoryType: CategoryType?
    ) = DataState.Success(items)

    override suspend fun removeCartItem(id: String) {
        TODO("Not yet implemented")
    }

    override suspend fun removeAllCartItems() {
        TODO("Not yet implemented")
    }

    override suspend fun listCartItems() = DataState.Success(items)

    override suspend fun addCartItem(item: Item) {
        TODO("Not yet implemented")
    }

    override suspend fun updateItem(item: Item) {
        TODO("Not yet implemented")
    }
}