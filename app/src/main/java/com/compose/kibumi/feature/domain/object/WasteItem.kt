package com.compose.kibumi.feature.domain.`object`

import com.compose.kibumi.R
import com.compose.kibumi.feature.domain.model.WasteItemModel

fun getListWasteItem(): MutableList<WasteItemModel>
{
    return mutableListOf(
        WasteItemModel(
            ID = 0,
            ItemName = "Botol Plastik PET",
            Price = 3850.0,
            R.drawable.image_pet
        ),
        WasteItemModel(
            ID = 1,
            ItemName = "Botol Plastik HDPE",
            Price = 4400.0,
            R.drawable.image_hdpe
        ),
        WasteItemModel(
            ID = 2,
            ItemName = "Kaleng Alumunium",
            Price = 11000.0,
            R.drawable.image_can
        ),
        WasteItemModel(
            ID = 3,
            ItemName = "Karton / Paper Board",
            Price = 2200.0,
            R.drawable.image_paper
        ),
        WasteItemModel(
            ID = 4,
            ItemName = "Campuran",
            Price = 2200.0,
            R.drawable.image_compound
        )
    )
}