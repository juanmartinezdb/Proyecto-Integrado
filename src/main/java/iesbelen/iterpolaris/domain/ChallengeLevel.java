package iesbelen.iterpolaris.domain;

public enum ChallengeLevel {
    MUY_FACIL(10),
    FACIL(30),
    NORMAL(60),
    DIFICIL(200),
    MUY_DIFICIL(500);

    private final int xpValue;

    ChallengeLevel(int xpValue) {
        this.xpValue = xpValue;
    }

    public int getXpValue() {
        return xpValue;
    }
}
