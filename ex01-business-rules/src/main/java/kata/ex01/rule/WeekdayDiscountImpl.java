package kata.ex01.rule;

import kata.ex01.model.HighwayDrive;
import kata.ex01.util.HolidayUtils;

import java.time.LocalDateTime;

public class WeekdayDiscountImpl implements DiscountRule {
    @Override
    public int getDiscountRate(HighwayDrive drive) {
        if (this.isWeekdayTerm(drive.getEnteredAt(), drive.getExitedAt())) {
        }
        return 0;
    }

    protected boolean isWeekdayTerm(LocalDateTime enteredAt, LocalDateTime exitedAt) {
        return (!HolidayUtils.isHoliday(enteredAt.toLocalDate()) && !HolidayUtils.isHoliday(exitedAt.toLocalDate()));
    }
}
