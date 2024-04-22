package com.wcsm.dailygoals.util

import android.content.Context
import com.google.gson.Gson
import com.wcsm.dailygoals.data.model.Quote
import com.wcsm.dailygoals.data.model.Quotes
import kotlin.random.Random
import kotlin.random.nextInt

fun manageDurationMessage(minDuration: Int): String {
    var message = ""
    when {
        minDuration == 0 -> message = "Tarefa sem duração mínima."
        minDuration == 1 -> message = "Duração mínima de $minDuration hora."
        minDuration > 1 -> message = "Duração mínima de $minDuration horas."
    }

    return message
}

fun getMotivationalQuotes(context: Context): List<Quote> {
    val assetManager = context.assets
    val inputStream = assetManager.open("quotes.json")
    val json = inputStream.bufferedReader().use { it.readText() }

    val gson = Gson()
    val quotes = gson.fromJson(json, Quotes::class.java)

    return quotes.motivationalQuotes
}

fun getTwoQuotes(context: Context): List<Quote> {
    val quotes = getMotivationalQuotes(context)
    val getRandomNumber = Random.nextInt(0..quotes.size) // 0 a 75

    val quotesList = mutableListOf<Quote>()

    if(getRandomNumber == 0) {
        quotesList.add(quotes[getRandomNumber])
        quotesList.add(quotes[1])
    } else {
        quotesList.add(quotes[getRandomNumber])
        quotesList.add(quotes[getRandomNumber - 1])
    }

    return quotesList
}
