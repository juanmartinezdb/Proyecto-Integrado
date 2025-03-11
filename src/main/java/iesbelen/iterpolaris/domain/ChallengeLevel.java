package iesbelen.iterpolaris.domain;

public enum ChallengeLevel {
    MUY_FACIL(10, 1),
    FACIL(30, 5),
    NORMAL(60, 20),
    DIFICIL(200, 40),
    MUY_DIFICIL(500, 100);

    private final int xpValue;
    private final int coinsValue;

    ChallengeLevel(int xpValue, int coinsValue) {
        this.xpValue = xpValue;
        this.coinsValue = coinsValue;
    }

    public int getXpValue() {
        return xpValue;
    }

    public int getCoinsValue() {
        return coinsValue;
    }
}
