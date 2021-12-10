
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
