package me.mancy.arandisdrops.parties;

public class DropPartyManager {

    public static boolean isActiveDropParty() {
        return activeDropParty;
    }

    static void setIsActiveDropParty(boolean isActiveDropParty) {
        DropPartyManager.activeDropParty = isActiveDropParty;
    }

    private static boolean activeDropParty = false;

}
