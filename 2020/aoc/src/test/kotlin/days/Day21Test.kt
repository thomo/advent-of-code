package days

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day21Test {
    private lateinit var cut: Day21
    val input: List<String> = listOf(
        "mxmxvkd kfcds sqjhc nhms (contains dairy, fish)", "trh fvjkl sbzzf mxmxvkd (contains dairy)",
        "sqjhc fvjkl (contains soy)",
        "sqjhc mxmxvkd sbzzf (contains fish)"
    )

    @BeforeEach
    fun setup() {
        cut = Day21()

    }

    @Test
    fun readFoods() {
        val foods = cut.foods(input)
        assertEquals(4, foods.size)
    }

    @Test
    fun readIn() {
        val foods = cut.foods(input)
        val ingredients = cut.ingredients(foods)

        assertEquals(7, ingredients.size, ingredients.toString())
    }

    @Test
    fun readAll() {
        val foods = cut.foods(input)
        val allergens = cut.allergens(foods)

        assertEquals(3, allergens.size, allergens.toString())
        // println(allergens)
    }

    @Test
    fun analyse() {
        val foods = cut.foods(input)
        val ingredients = cut.ingredients(foods)
        val allergens = cut.allergens(foods)

        val (cnt, _) = cut.analyze(foods, ingredients, allergens)
        assertEquals(5, cnt)
    }

    @Test
    fun analyse2() {
        val foods = cut.foods(input)
        val ingredients = cut.ingredients(foods)
        val allergens = cut.allergens(foods)

        val (cnt, iwa) = cut.analyze(foods, ingredients, allergens)
        val answer = cut.analyze2(iwa)
        assertEquals("mxmxvkd,sqjhc,fvjkl", answer)
    }


}