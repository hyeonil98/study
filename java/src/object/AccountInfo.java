package object;

public class AccountInfo {
    private long mileage;
    public AccountLevel getLevel() {
        if (mileage > 100_100) {
            return AccountLevel.DIAMOND;
        } else if (mileage > 50_000) {
            return AccountLevel.GOLD;
        } else if (mileage > 30_000) {
            return AccountLevel.SILVER;
        } else return AccountLevel.BRONZE;
    }

    public AccountInfo(long mileage) {
        this.mileage = mileage;
    }

    public AccountInfo withMileage(long mileage) {
        return new AccountInfo(mileage);
    }
}
