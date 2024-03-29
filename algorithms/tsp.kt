// The TSP involves finding the shortest possible route that visits a given set of cities and returns to the original city.
// Genetic Algorithms mimic the process of natural selection to evolve a population of potential solutions toward an optimal or near-optimal solution.

// Consider a delivery person aiming to minimize travel distance while visiting a set of locations.
// The TSP with Genetic Algorithms can provide a near-optimal route for efficient deliveries.

import kotlin.random.Random

class TravelingSalesmanGeneticAlgorithm(
    private val cities: List<Pair<Double, Double>>,
    private val populationSize: Int,
    private val generations: Int
) {
    private val mutationRate = 0.02

    fun findOptimalRoute(): List<Int> {
        var population = generateInitialPopulation()

        repeat(generations) {
            population = evolvePopulation(population)
        }

        return getBestIndividual(population)
    }

    private fun generateInitialPopulation(): List<List<Int>> {
        return List(populationSize) {
            (0 until cities.size).shuffled()
        }
    }

    private fun evolvePopulation(currentPopulation: List<List<Int>>): List<List<Int>> {
        val matingPool = selectParents(currentPopulation)
        val newPopulation = crossover(matingPool)
        mutate(newPopulation)
        return newPopulation
    }

    private fun selectParents(population: List<List<Int>>): List<Pair<List<Int>, List<Int>>> {
        return List(populationSize) {
            val parent1 = population.random()
            val parent2 = population.random()
            parent1 to parent2
        }
    }

    private fun crossover(parents: List<Pair<List<Int>, List<Int>>>): List<List<Int>> {
        return parents.map { (parent1, parent2) ->
            val crossoverPoint = Random.nextInt(1, cities.size)
            val child = parent1.subList(0, crossoverPoint) + parent2.filterNot { parent1.contains(it) }
            child
        }
    }

    private fun mutate(population: List<List<Int>>) {
        population.forEach { individual ->
            if (Random.nextDouble() < mutationRate) {
                val indices = (0 until cities.size).shuffled().take(2)
                val temp = individual[indices[0]]
                individual[indices[0]] = individual[indices[1]]
                individual[indices[1]] = temp
            }
        }
    }

    private fun getBestIndividual(population: List<List<Int>>): List<Int> {
        return population.minByOrNull { calculateRouteDistance(it) } ?: emptyList()
    }

    private fun calculateRouteDistance(route: List<Int>): Double {
        return (0 until route.size - 1).sumByDouble { i ->
            val (x1, y1) = cities[route[i]]
            val (x2, y2) = cities[route[i + 1]]
            distance(x1, y1, x2, y2)
        }
    }

    private fun distance(x1: Double, y1: Double, x2: Double, y2: Double): Double {
        return kotlin.math.sqrt((x2 - x1).pow(2) + (y2 - y1).pow(2))
    }
}

fun main() {
    val cities = listOf(
        0.0 to 0.0,
        1.0 to 2.0,
        3.0 to 1.0,
        4.0 to 3.0
    )

    val populationSize = 100
    val generations = 1000

    val tspGeneticAlgorithm = TravelingSalesmanGeneticAlgorithm(cities, populationSize, generations)
    val optimalRoute = tspGeneticAlgorithm.findOptimalRoute()

    println("Optimal Route: $optimalRoute")
}
