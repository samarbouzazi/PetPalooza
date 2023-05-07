package petpalooza.Services.userServices;

public enum CustomHttpStatus {

    EMAIL_ALREADY_IN_USE(498),
    USERNAME_ALREADY_TAKEN(499);

    private final int value;

    CustomHttpStatus(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }
}
