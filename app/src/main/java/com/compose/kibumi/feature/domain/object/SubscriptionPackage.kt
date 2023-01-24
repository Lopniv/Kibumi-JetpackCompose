package com.compose.kibumi.feature.domain.`object`

import com.compose.kibumi.R
import com.compose.kibumi.feature.domain.model.SubscriptionPackageModel

fun getListSubscriptionPackage(): MutableList<SubscriptionPackageModel>
{
    return mutableListOf(
        SubscriptionPackageModel(
            0,
            "Jemputin Hebat 2x",
            2,
            1000,
            129000,
            R.drawable.icon_bronze
        ),
        SubscriptionPackageModel(
            1,
            "Jemputin Hebat 3x",
            3,
            1500,
            159000,
            R.drawable.icon_silver
        ),
        SubscriptionPackageModel(
            2,
            "Jemputin Hebat 5x",
            6,
            1500,
            255000,
            R.drawable.icon_gold
        ),
    )
}