package com.example.android.unscramble.ui.game

import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {

    private var _currentWordCount = 0
    private var wordsList: MutableList<String> = mutableListOf()
    private lateinit var currentWord: String
    lateinit var _currentScrambledWord: String
    private var _score = 0

    init {
        getNextWord()
    }

    val currentScrambledWord: String
        get() = _currentScrambledWord

    val score: Int
        get() = _score

    val currentWordCount: Int
        get() = _currentWordCount


    fun getNextWord() {
        currentWord = allWordsList.random()
        val tempWord = currentWord.toCharArray()
        tempWord.shuffle()

        while (String(tempWord).equals(currentWord, ignoreCase = false)) {
            tempWord.shuffle()
        }

        if (wordsList.contains(currentWord)) {
            getNextWord()
        } else {
            _currentScrambledWord = String(tempWord)
            _currentWordCount++
            wordsList.add(currentWord)
        }

    }

    fun nextWord(): Boolean {

        return if (_currentWordCount < MAX_NO_OF_WORDS) {
            getNextWord()
            true
        } else false
    }

    private fun increaseScore() {
        _score += SCORE_INCREASE
    }

    fun isUserWordCorrect(playerWord: String): Boolean {
        if (playerWord.equals(currentWord, true)) {
            increaseScore()
            return true
        }
        return false
    }

    fun reinitalizeData(){
        _score = 0
        _currentWordCount = 0
        wordsList.clear()
        getNextWord()
    }


}