# 🎮 Design: Tic Tac Toe - Java OOP LLD

## 🧩 What is Tic-Tac-Toe?

Tic Tac Toe is a 2-player game played on a board of size NxN. Each player takes alternate turns and places their symbol (X or O) on the board. The player who places N of their symbols consecutively in a row, column, or diagonal wins. If all cells are filled and no one wins, it’s a draw.

You can even play this within Google by searching **“tic tac toe”**!

![TicTacToe](https://www.tuitec.com/wp-content/uploads/2016/08/morpion-640x411.jpg)

---

## ❓ Functional Questions Considered

- Is the board always 3x3? ❌ → It’s NxN.
- Are only 2 players allowed? ✅ for now (but extendable).
- Can one be a bot? ✅ Yes.
- Can we undo a move? ✅ Yes.
- Can we support winning in different ways? ✅ Yes.
- Is it extensible and testable? ✅ Definitely.

---

## ✅ Requirements

- Configurable board size (NxN)
- 2 Players (Human/Bot)
- Symbols: 'X' and 'O'
- Alternate moves
- Winning: full row/column/diagonal with same symbol
- Draw condition supported
- Bot with 3 difficulty levels
- Undo last move (for human)

---

## 🧱 Core Entities

| Entity         | Attributes                                      |
|----------------|--------------------------------------------------|
| Game           | Board, Players, Moves, GameState, Strategies     |
| Player         | Name, Symbol, PlayerType                         |
| Bot            | DifficultyLevel, Strategy                        |
| Human          | Name, Email, Image                               |
| Board          | Cells, Size                                      |
| Cell           | Row, Col, Player, State                          |
| Move           | Player, Cell                                     |
| Strategy       | Row/Col/Diagonal, Easy/Hard bot strategies       |

---

## 🧩 Design Diagrams

### ✅ Use Case Diagram

```plantuml
@startuml
actor Human
actor Bot
rectangle Game {
  Human -- (Start Game)
  Human -- (Make Move)
  Bot -- (Make Move)
  (Make Move) .> (Check Winner) : includes
}
@enduml
```

---

### ✅ Class Diagram (Cleaned LLD)

```mermaid
classDiagram
  class Game {
    - Board board
    - List~Player~ players
    - List~WinningStrategy~ winningStrategies
    - List~Move~ moves
    + startGame()
    + makeMove()
    + undo()
  }

  class Player {
    <<abstract>>
    - Symbol symbol
    - PlayerType type
    + makeMove(Board) Move
  }

  class HumanPlayer {
    - String name
    - String email
    - Byte[] profileImage
    + makeMove(Board) Move
  }

  class Bot {
    - BotDifficultyLevel difficultyLevel
    - BotPlayingStrategy strategy
    + makeMove(Board) Move
  }

  class BotPlayingStrategy {
    <<interface>>
    + makeMove(Board) Move
  }

  class EasyBotPlayingStrategy
  class MediumBotPlayingStrategy
  class HardBotPlayingStrategy

  class WinningStrategy {
    <<interface>>
    + checkWinner(Board, Move): boolean
  }

  class RowWinningStrategy
  class ColWinningStrategy
  class DiagonalWinningStrategy

  class Board {
    - List~List~Cell~~ cells
    - int dimension
  }

  class Cell {
    - int row
    - int col
    - CellState state
    - Player player
  }

  class Move {
    - Player player
    - Cell cell
  }

  Game --> Board
  Game --> Player
  Game --> WinningStrategy
  Game --> Move
  Player <|-- HumanPlayer
  Player <|-- Bot
  Bot --> BotPlayingStrategy
  BotPlayingStrategy <|-- EasyBotPlayingStrategy
  BotPlayingStrategy <|-- MediumBotPlayingStrategy
  BotPlayingStrategy <|-- HardBotPlayingStrategy
  WinningStrategy <|-- RowWinningStrategy
  WinningStrategy <|-- ColWinningStrategy
  WinningStrategy <|-- DiagonalWinningStrategy
  Board --> Cell
  Move --> Player
  Move --> Cell
```

---

## 🚀 Features

- CLI-based game with undo support
- NxN board support
- Configurable winning strategies
- Bot difficulty control via Strategy pattern
- Proper abstraction via interfaces and factories

---

## 📁 Package Structure

```
src/
└── main/
    └── java/
        └── com/sarvesh/LLD3/TicTacToe/
            ├── controller/        # GameController
            ├── exception/         # InvalidMoveException
            ├── factory/           # Bot strategy factory
            ├── model/             # Game, Player, Board, Cell, Move
            ├── strategy/
            │   ├── botplayingstrategy/
            │   └── winningstrategy/
```

---

## 📌 Future Scope

- Convert CLI to REST API (Spring Boot)
- Add frontend for gameplay visualization
- Add game analytics and historical stats
- Tournament mode with multiple players

---

🧠 Designed using SOLID principles, Strategy pattern, and extensible game architecture.
