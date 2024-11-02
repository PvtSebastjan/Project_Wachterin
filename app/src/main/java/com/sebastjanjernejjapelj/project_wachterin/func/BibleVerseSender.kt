package com.sebastjanjernejjapelj.project_wachterin.func

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject

val bibleBooks = listOf(
    "Genesis", "Exodus", "Leviticus", "Numbers", "Deuteronomy", "Joshua", "Judges", "Ruth",
    "Samuel 1", "Samuel 2", "Kings 1", "Kings 2", "Chronicles 1", "Chronicles 2", "Ezra",
    "Nehemiah", "Esther", "Job", "Psalms", "Proverbs", "Ecclesiastes", "Song of Solomon",
    "Isaiah", "Jeremiah", "Lamentations", "Ezekiel", "Daniel", "Hosea", "Joel", "Amos",
    "Obadiah", "Jonah", "Micah", "Nahum", "Habakkuk", "Zephaniah", "Haggai", "Zechariah",
    "Malachi", "Matthew", "Mark", "Luke", "John", "Acts", "Romans", "Corinthians 1",
    "Corinthians 2", "Galatians", "Ephesians", "Philippians", "Colossians", "Thessalonians 1",
    "Thessalonians 2", "Timothy 1", "Timothy 2", "Titus", "Philemon", "Hebrews", "James",
    "Peter 1", "Peter 2", "John 1", "John 2", "John 3", "Jude", "Revelation"
)

data class BibleVerse(val book: String, val chapter: String, val verse: String, val text: String)

val client = OkHttpClient()

suspend fun fetchRandomBibleVerse(): BibleVerse? {
    return try {
        val request = Request.Builder()
            .url("https://bolls.life/get-random-verse/YLT/")
            .build()

        val response = withContext(Dispatchers.IO) {
            client.newCall(request).execute()
        }

        response.body?.string()?.let { json ->
            val jsonObject = JSONObject(json)
            val bookNumber = jsonObject.getString("book").toIntOrNull()
            val chapter = jsonObject.getString("chapter")
            val verse = jsonObject.getString("verse")
            val text = jsonObject.getString("text")

            // Map the book number to its corresponding book name
            val bookName = bookNumber?.let {
                bibleBooks.getOrNull(it - 1) ?: "Unknown Book"
            } ?: "Unknown Book"

            BibleVerse(bookName, chapter, verse, text)
        }
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}





