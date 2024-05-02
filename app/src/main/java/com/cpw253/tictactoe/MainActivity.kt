package com.cpw253.tictactoe

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    // Player X takes the first turn
    private var currentPlayer = ""
    private var gameInfo: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        gameInfo = findViewById(R.id.textGameInfo)
        switchPlayer()

        val buttonGrid1 = findViewById<Button>(R.id.buttonGrid_1)
        buttonGrid1.setOnClickListener { selectXO(buttonGrid1) }
        val buttonGrid2 = findViewById<Button>(R.id.buttonGrid_2)
        buttonGrid2.setOnClickListener { selectXO(buttonGrid2) }
        val buttonGrid3 = findViewById<Button>(R.id.buttonGrid_3)
        buttonGrid3.setOnClickListener { selectXO(buttonGrid3) }
        val buttonGrid4 = findViewById<Button>(R.id.buttonGrid_4)
        buttonGrid4.setOnClickListener { selectXO(buttonGrid4) }
        val buttonGrid5 = findViewById<Button>(R.id.buttonGrid_5)
        buttonGrid5.setOnClickListener { selectXO(buttonGrid5) }
        val buttonGrid6 = findViewById<Button>(R.id.buttonGrid_6)
        buttonGrid6.setOnClickListener { selectXO(buttonGrid6) }
        val buttonGrid7 = findViewById<Button>(R.id.buttonGrid_7)
        buttonGrid7.setOnClickListener { selectXO(buttonGrid7) }
        val buttonGrid8 = findViewById<Button>(R.id.buttonGrid_8)
        buttonGrid8.setOnClickListener { selectXO(buttonGrid8) }
        val buttonGrid9 = findViewById<Button>(R.id.buttonGrid_9)
        buttonGrid9.setOnClickListener { selectXO(buttonGrid9) }

        val gridButtons: List<Button> = listOf(buttonGrid1, buttonGrid2, buttonGrid3,
            buttonGrid4, buttonGrid5, buttonGrid6, buttonGrid7, buttonGrid8, buttonGrid9)

        val newGame = findViewById<Button>(R.id.buttonNewGame)
        newGame.setOnClickListener { startNewGame(gridButtons) }
    }

    private fun selectXO(button: Button) {
        if (button.text.isNullOrEmpty()) {
            button.text = currentPlayer
            switchPlayer()
        }
    }

    private fun switchPlayer() {
        currentPlayer = if (currentPlayer == "X") "O" else "X"

        gameInfo!!.text = getString(R.string.player_s_turn, currentPlayer)
    }

    private fun startNewGame(gridButtons: List<Button>) {
        for (i in gridButtons) i.text = ""
        currentPlayer = ""
        switchPlayer()
    }
}
