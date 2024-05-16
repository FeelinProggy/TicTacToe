package com.cpw253.tictactoe

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    // Establishing empty variables to be used in functions
    private var currentPlayer = ""
    private lateinit var gameInfo: TextView
    private lateinit var allGridButtons: Array<Button>
    private lateinit var winningCombos: Array<Array<Button>>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // Defining TextView after SetContentView
        gameInfo = findViewById(R.id.textGameInfo)
        //Setting player and message for X
        switchPlayer()

        // Creating local values for all buttons
        val btn1 = findViewById<Button>(R.id.buttonGrid_1)
        val bth2 = findViewById<Button>(R.id.buttonGrid_2)
        val btn3 = findViewById<Button>(R.id.buttonGrid_3)
        val btn4 = findViewById<Button>(R.id.buttonGrid_4)
        val btn5 = findViewById<Button>(R.id.buttonGrid_5)
        val btn6 = findViewById<Button>(R.id.buttonGrid_6)
        val btn7 = findViewById<Button>(R.id.buttonGrid_7)
        val btn8 = findViewById<Button>(R.id.buttonGrid_8)
        val btn9 = findViewById<Button>(R.id.buttonGrid_9)
        // Creating Array of all buttons
        allGridButtons = arrayOf(btn1, bth2, btn3, btn4, btn5, btn6, btn7, btn8, btn9)
        // Arrays to check for win
        val row1: Array<Button> = arrayOf(btn1, bth2, btn3)
        val row2: Array<Button> = arrayOf(btn4, btn5, btn6)
        val row3: Array<Button> = arrayOf(btn7, btn8, btn9)
        val col1: Array<Button> = arrayOf(btn1, btn4, btn7)
        val col2: Array<Button> = arrayOf(bth2, btn5, btn8)
        val col3: Array<Button> = arrayOf(btn3, btn6, btn9)
        val diagLeft: Array<Button> = arrayOf(btn1, btn5, btn9)
        val diagRight: Array<Button> = arrayOf(btn3, btn5, btn7)
        // Array for all possible wins
        winningCombos = arrayOf(row1, row2, row3, col1, col2, col3, diagLeft, diagRight)
    }

    // Place player's mark and call to switch
    fun markXO(view: View) {
        val button = view as Button
        // prevents turn from toggling if button is already marked
        if (button.text == currentPlayer)
        // if button is empty, mark it
        else if (button.text.isNullOrEmpty()) {
            button.text = currentPlayer
            // Check for win, if false switch player
            if (checkWin(button)){
                gameInfo.text = getString(R.string.player_wins, currentPlayer)
            }
            else {
                switchPlayer()
            }
        }
    }

    // Toggle's player and displayed message
    private fun switchPlayer() {
        currentPlayer = if (currentPlayer == "X") "O" else "X"

        gameInfo.text = getString(R.string.player_s_turn, currentPlayer)
    }

    private fun checkWin(selected: Button) : Boolean {
        for (line in winningCombos) {
            if (line.contains(selected)) {
                var threeInARow = true
                for (button in line) {
                    if (button.text.isNullOrEmpty()) threeInARow = false
                    else if (button.text !== currentPlayer) threeInARow = false
                }
                if (threeInARow) {
                    for (i in allGridButtons) {
                        i.isEnabled = false
                    }
                    return true
                }
            }
        }
        return false
    }

    // onClick sends to function
    fun startNewGame(view: View) {
        startNewGame(allGridButtons)
    }
    // New game resets all grid buttons and currentPlayer
    // SwitchPlayer sets up to start game with X again
    private fun startNewGame(gridButtons: Array<Button>) {
        for (i in gridButtons) {
            i.text = ""
            i.isEnabled = true
        }
        currentPlayer = ""
        switchPlayer()
    }

}
