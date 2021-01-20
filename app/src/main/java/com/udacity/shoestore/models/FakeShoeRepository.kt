package com.udacity.shoestore.models

class FakeShoeRepository {

    private fun getPredefinedShoes() =
        listOf<Shoe>(
            Shoe(
                "City Comfort",
                43.0,
                "Casual Shoes Inc.",
                "A nice shoe for all occasions"
            ),
            Shoe(
                "Marathon 3000",
                40.0,
                "Quasar",
                "Shoe for running and jogging"
            ),
            Shoe(
                "Elegance (2018 model)",
                39.0,
                "Leather & Shoes Ltd.",
                "City shoe"
            ),
            Shoe(
                "City Walk",
                39.0,
                "Casual Shoes Inc.",
                "A second pair of city shoes"
            ),
            Shoe(
                "K2",
                43.0,
                "Outdoor Ltd.",
                "Hiking shoe for walking"
            )
        )
}
