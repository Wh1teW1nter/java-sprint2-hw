public enum MenuCommand {
    READ_MONTH_FILES(1),
    READ_YEAR_FILES(2),
    CHECK_REPORTS(3),
    PRINT_MONTH_INFO(4),
    PRINT_YEAR_INFO(5),
    EXIT(6),
    UNKNOWN(0);


    private final int code;


    private MenuCommand(int code) {
        this.code = code;
    }


    public int getCode() {
        return code;
    }

    public static MenuCommand findByCode(int code) {
        for (MenuCommand command : MenuCommand.values()) {
            if (command.code == code) {
                return command;
            }
        }
        return UNKNOWN;
    }
}
