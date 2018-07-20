package me.mancy.alphadrops.tokens;

public class DropCosts {

    private static int tierOneCost = -1, tierTwoCost = -1, tierThreeCost = -1, tierFourCost = -1;

    public static void setCost(int tier, int amount) {
        switch (tier) {
            case 1:
                tierOneCost = amount;
                break;
            case 2:
                tierTwoCost = amount;
                break;
            case 3:
                tierThreeCost = amount;
                break;
            case 4:
                tierFourCost = amount;
                break;
        }
    }

    public static int getCost(int tier) {
        switch (tier) {
            case 1:
                return tierOneCost;
            case 2:
                return tierTwoCost;
            case 3:
                return tierThreeCost;
            case 4:
                return tierFourCost;
        }
        return -1;
    }

}
