
// ---------------------------------------------------------------------------------------
// A Move is used to store a target column, and a priority for the move. Winning moves
// have a higher priority than defensive moves. The category field is used as a sort key
// when deciding which move to choose.
// ---------------------------------------------------------------------------------------
record Move(Category category, int column) {

    enum Category {
        WINNER,
        PREVENT_LOSS,
    }

    public static Move makeWinner(int column) {
        return new Move(Category.WINNER, column);
    }

    public static Move makePreventLoss(int column) {
        return new Move(Category.PREVENT_LOSS, column);
    }
}
