package kata.ex01.rule;

import kata.ex01.model.HighwayDrive;
import kata.ex01.model.RouteType;
import kata.ex01.util.HolidayUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class WeekdayMorningEveningDiscountImpl implements DiscountRule {
    @Override
    public int getDiscountRate(HighwayDrive drive) {
        if ((this.isWeekdayMorning(drive.getEnteredAt(), drive.getExitedAt()) || this.isWeekdayEvening(drive.getEnteredAt(), drive.getExitedAt()))
                && drive.getRouteType() == RouteType.RURAL) {
            if (drive.getDriver().getCountPerMonth() >= 5 && drive.getDriver().getCountPerMonth() <= 9) {
                return 30;
            }

            if (drive.getDriver().getCountPerMonth() >= 10) {
                return 50;
            }
        }

        return 0;
    }

    private boolean isWeekday(LocalDate targetDate) {
        return !HolidayUtils.isHoliday(targetDate);
    }

    private boolean isWeekdayMorning(LocalDateTime enteredAt, LocalDateTime exitedAt) {
        //WARNING: ルールの時間範囲が日をまたぐ場合は考慮できてない
        LocalTime startTime = LocalTime.of(6, 0);
        LocalTime endTime = LocalTime.of(9, 0);

        LocalTime enteredTime = enteredAt.toLocalTime();
        LocalTime exitedTime = exitedAt.toLocalTime();

        if (enteredTime.isBefore(exitedTime)) {
            // 日をまたがないドライブ
            return (this.isWeekday(enteredAt.toLocalDate()) && (enteredTime.equals(endTime) || enteredTime.isBefore(endTime)))
                    && (this.isWeekday(exitedAt.toLocalDate()) && (exitedTime.equals(startTime) || exitedTime.isAfter(startTime)));
        }

        // 日をまたぐドライブ
        return (this.isWeekday(enteredAt.toLocalDate()) && (enteredTime.equals(endTime) || enteredTime.isBefore(endTime)))
                || (this.isWeekday(exitedAt.toLocalDate()) && (exitedTime.equals(startTime) || exitedTime.isAfter(startTime)));
    }

    private boolean isWeekdayEvening(LocalDateTime enteredAt, LocalDateTime exitedAt) {
        //WARNING: ルールの時間範囲が日をまたぐ場合は考慮できてない
        LocalTime startTime = LocalTime.of(17, 0);
        LocalTime endTime = LocalTime.of(20, 0);

        LocalTime enteredTime = enteredAt.toLocalTime();
        LocalTime exitedTime = exitedAt.toLocalTime();

        if (enteredTime.isBefore(exitedTime)) {
            // 日をまたがないドライブ
            return (this.isWeekday(enteredAt.toLocalDate()) && (enteredTime.equals(endTime) || enteredTime.isBefore(endTime)))
                    && (this.isWeekday(exitedAt.toLocalDate()) && (exitedTime.equals(startTime) || exitedTime.isAfter(startTime)));
        }

        // 日をまたぐドライブ
        return (this.isWeekday(enteredAt.toLocalDate()) && (enteredTime.equals(endTime) || enteredTime.isBefore(endTime)))
                || (this.isWeekday(exitedAt.toLocalDate()) && (exitedTime.equals(startTime) || exitedTime.isAfter(startTime)));
    }
}
