package com.pratyusha.travelophile.data

import com.pratyusha.travelophile.R

data class Place(
    val placeImage: Int,
    val placeName: String,
    val location: String
)

val listOfPlaces = listOf<Place>(
    Place(
        placeImage = R.drawable.nusa_penida,
        placeName = "Nusa Penida",
        location = "Bali,Indonesia"
    ),
    Place(
        placeImage = R.drawable.new_delhi,
        placeName = "New Delhi",
        location = "India"
    )
)
val listOfBeaches = listOf<Place>()
val listOfMountains = listOf<Place>()
val listOfCities = listOf<Place>()
val listOfTemples = listOf<Place>()