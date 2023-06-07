package problems.practice

object HumanMonster {
  def main(args: Array[String]): Unit = {
    val gameBoard = initGameBoard()
    val humanGameBoard = putActor(0, 4, gameBoard, "H")
    val monsterGameBoard = putActor(1, 1, humanGameBoard, "M")
    println("initial board:")
    printGameBoard(monsterGameBoard)
    println("move monster towards to human board:")
    printGameBoard(moveMonsterTowardsHuman(monsterGameBoard))
  }

  def moveMonsterTowardsHuman(
    gameBoard: Array[Array[String]]
  ): Array[Array[String]] = {
    val (humanPosRow, humanPosCol): (Int, Int) = getActorPos(gameBoard, "H")
    val (monsterPosRow, monsterPosCol): (Int, Int) = getActorPos(gameBoard, "M")
    removeActor(monsterPosRow, monsterPosCol, gameBoard)
    val monsterNewRow =
      if (humanPosRow > monsterPosRow) monsterPosRow + 1 else monsterPosRow - 1
    val monsterNewCol =
      if (humanPosCol > monsterPosCol) monsterPosCol + 1 else monsterPosCol - 1
    putActor(monsterNewRow, monsterNewCol, gameBoard, "M")
  }

  def getActorPos(
    gameBoard: Array[Array[String]],
    actor: String
  ): (Int, Int) = {
    for (i <- 0 until 5; j <- 0 until 6) {
      if (gameBoard(i)(j) == actor)
        return (i, j)
    }
    (-1, -1)
  }

  def initGameBoard(): Array[Array[String]] = {
    val arr: Array[Array[String]] = Array.ofDim(5, 6)
    for (i <- 0 until 5; j <- 0 until 6) {
      arr(i)(j) = "."
    }
    arr
  }

  def printGameBoard(gameBoard: Array[Array[String]]): Unit = {
    for (i <- 0 until 5) {
      for (j <- 0 until 6) {
        print(gameBoard(i)(j) + " ")
      }
      println()
    }
  }

  def removeActor(
    row: Int,
    column: Int,
    gameBoard: Array[Array[String]],
  ): Array[Array[String]] = {
    gameBoard(row)(column) = "."
    gameBoard
  }

  def putActor(
    row: Int,
    column: Int,
    gameBoard: Array[Array[String]],
    actor: String
  ): Array[Array[String]] = {
    gameBoard(row)(column) = actor
    gameBoard
  }
}

