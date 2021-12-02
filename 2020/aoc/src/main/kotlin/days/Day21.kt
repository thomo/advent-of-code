package days

class Day21 {
    fun run(lines: List<String>) {
        val foods = foods(lines)

        val allergens = allergens(foods)

        val ingredients = ingredients(foods)

        var (cnt, iwa)  = analyze(foods, ingredients, allergens)

        // too high 2312
        println(cnt)

        println(analyze2(iwa))

        // println(iwa.map { i -> Pair(i, foods.filter{ f -> f.first.contains(i)}.flatMap{it.second}.toSet())})

    }

    fun analyze2(iwa: Map<String, List<String>>): String {
        var input = iwa.toMutableMap()
        var result = mutableMapOf<String, String>()

        while (input.isNotEmpty()) {
            var temp = input.filterValues { it.size == 1 }.mapValues { kv -> kv.value[0] }
            // println("$input -> $temp")
            temp.forEach{ it ->
                result.put(it.value, it.key)
                input = input.filterKeys { k -> k != it.key }.mapValues { kv -> kv.value.filterNot { v -> v == it.value } }.toMutableMap()
                // println(" -> reduced input: $input")
            }
        }

        println(result)
        return result.keys.sorted().map{ k -> result[k]}.joinToString(",")
    }

    fun analyze(
        foods: List<Pair<List<String>, List<String>>>,
        ingredients: Set<String>,
        allergens: Map<String, Int>
    ): Pair<Int, Map<String, List<String>>> {
        var iwa = mutableMapOf<String, List<String>>()
        var cnt = 0
        println(allergens)
        ingredients.forEach { i ->
            val fall = foods.filter { f -> f.first.contains(i) }.map { it.second }
            val fcnt = fall.count()
            // println("$i -> ${fall.toString()} / $fcnt / ${fall.flatten().toSet()}")
            val validAllergens = fall.flatten().toSet().filter { a -> fall.filter { it.contains(a) }.count() == allergens[a] }
            if (validAllergens.isEmpty()) {
                // println("$i +++")
                cnt += fcnt
            } else {
                iwa.put(i, validAllergens)
            }
        }
        return Pair(cnt, iwa)
    }

    fun ingredients(foods: List<Pair<List<String>, List<String>>>) =
        foods.flatMap { it.first }.toSet()

    fun allergens(foods: List<Pair<List<String>, List<String>>>) =
        foods.flatMap { it.second }.toSet().map { a -> Pair(a, foods.filter { f -> f.second.contains(a) }.count()) }
            .toMap()

    fun foods(lines: List<String>) =
        lines.map { it.dropLast(1).split(Regex(" \\(contains ")) }.map { Pair(it[0].split(" "), it[1].split(", ")) }

}
