package com.example.kavyakanaja.backend.repository

import com.example.kavyakanaja.backend.models.*

interface PoetryRepository {
    suspend fun getAllPoems(): List<Poem>
    suspend fun getPoemById(id: String): Poem?
    suspend fun getPoemOfTheDay(): Poem?
    suspend fun getAllPoets(): List<Poet>
    suspend fun getPoetById(id: String): Poet?
}

class PoetryRepositoryImpl : PoetryRepository {
    // In-memory sample data for demonstration
    private val poets = listOf(
        Poet(
            id = "poet_ks_narasimhaswamy",
            name = "K. S. Narasimhaswamy",
            biography = "Prominent Indian poet in the Kannada language.",
            imageUrl = "https://upload.wikimedia.org/wikipedia/en/thumb/0/05/KSN_Swamy.jpg/220px-KSN_Swamy.jpg",
            birthYear = 1915,
            deathYear = 2003
        ),
        Poet(
            id = "poet_dvg",
            name = "D. V. Gundappa",
            biography = "Writer, poet, and philosopher. Famous for Mankuthimmana Kagga.",
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/e/ec/D_V_Gundappa.jpg",
            birthYear = 1887,
            deathYear = 1975
        )
    )

    private val poems = listOf(
        Poem(
            id = "poem_1",
            title = "Kurigalu Saar Kurigalu",
            poet = poets[0],
            content = "Kurigalu saar kurigalu\\nOnde samane saagidavu...",
            bhavartha = "This poem is a satirical take on the herd mentality of humans.",
            difficultWords = listOf(WordMeaning("Kurigalu", "Sheep")),
            isPoemOfTheDay = true
        ),
        Poem(
            id = "poem_2",
            title = "Mankuthimmana Kagga",
            poet = poets[1],
            content = "Hullagu bettada adi manege malligeyagu...",
            bhavartha = "A philosophical verse advising one to be humble.",
            difficultWords = listOf(WordMeaning("Hullagu", "Become grass")),
            isPoemOfTheDay = false
        )
    )

    override suspend fun getAllPoems(): List<Poem> = poems
    override suspend fun getPoemById(id: String): Poem? = poems.find { it.id == id }
    override suspend fun getPoemOfTheDay(): Poem? = poems.find { it.isPoemOfTheDay }
    override suspend fun getAllPoets(): List<Poet> = poets
    override suspend fun getPoetById(id: String): Poet? = poets.find { it.id == id }
}
