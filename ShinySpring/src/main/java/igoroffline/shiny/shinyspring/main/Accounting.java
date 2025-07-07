package igoroffline.shiny.shinyspring.main;

public class Accounting {

    public static AccountingResult<Gold> addGold(ShinyData data, String key, int add) {
        final var newInt = Data.getGold(data).value() + add;
        final var newGold = new Gold(newInt);
        Data.getData().put(key, new ShinyData(newGold, ShinyType.GOLD));

        return new AccountingResult<>(AccountingResultStatus.SUCCESS, newGold);
    }

    public static AccountingResult<Gold> reduceGold(ShinyData data, String key, int reduce) {
        final var newInt = Data.getGold(data).value() - reduce;
        if (newInt < Magic.LOWEST_POSSIBLE_GOLD_AMOUNT) {
            final var newGold = new Gold(Magic.LOWEST_POSSIBLE_GOLD_AMOUNT);
            Data.getData().put(key, new ShinyData(newGold, ShinyType.GOLD));
            return new AccountingResult<>(AccountingResultStatus.INSUFFICIENT_FUNDS, newGold);
        }
        final var newGold = new Gold(newInt);
        Data.getData().put(key, new ShinyData(newGold, ShinyType.GOLD));

        return new AccountingResult<>(AccountingResultStatus.SUCCESS, newGold);
    }
}
