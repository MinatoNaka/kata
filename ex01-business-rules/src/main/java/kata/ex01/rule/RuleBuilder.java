package kata.ex01.rule;

import kata.ex01.model.RouteType;
import kata.ex01.model.Rule;
import kata.ex01.model.VehicleFamily;
import kata.ex01.util.HolidayUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

/**
 * @author kawasima
 */
public class RuleBuilder {
    private static Rule build(boolean value) {
        var rule = new Rule();
        rule.setIsRule(value);
        return rule;
    }

    /**
     * 休日判定ルール
     **/
    public static Rule holidayRule(LocalDate start, LocalDate end) {
        return build(HolidayUtils.isHoliday(start) || HolidayUtils.isHoliday(end));
    }

    /**
     * 車種判定ルール
     **/
    public static Rule vehicleFamilyRule(VehicleFamily target, Set<VehicleFamily> vehicleFamilies) {
        return build(vehicleFamilies.contains(target));
    }

    /**
     * 地方部判定ルール
     **/
    public static Rule ruralRule(RouteType routeType) {
        return build(routeType == RouteType.RURAL);
    }

    /**
     * 割引時間判定ルール
     **/
    public static Rule discountTimeRule(LocalTime startTime, LocalTime endTime, LocalDateTime enteredAt, LocalDateTime exitedAt) {
        LocalTime enteredTime = enteredAt.toLocalTime();
        LocalTime exitedTime = exitedAt.toLocalTime();

        // 日をまたがないドライブ
        if (enteredTime.isBefore(exitedTime)) {
            return build(
                    (enteredTime.equals(endTime) || enteredTime.isBefore(endTime))
                            || (exitedTime.equals(startTime) || exitedTime.isAfter(startTime))
            );
        }
        //日をまたぐドライブ
        return build(
                (enteredTime.equals(endTime) || enteredTime.isBefore(endTime))
                        && (exitedTime.equals(startTime) || exitedTime.isAfter(startTime))
        );
    }

    /**
     * 平日割引時間判定ルール
     **/
    public static Rule weekDayDiscountTimeRule(LocalTime startTime, LocalTime endTime, LocalDateTime enteredAt, LocalDateTime exitedAt) {
        LocalTime enteredTime = enteredAt.toLocalTime();
        LocalTime exitedTime = exitedAt.toLocalTime();

        // 日をまたがないドライブ
        if (enteredTime.isBefore(exitedTime)) {
            return build(
                    (!HolidayUtils.isHoliday(enteredAt.toLocalDate())
                            && (enteredTime.equals(endTime) || enteredTime.isBefore(endTime))
                    ) && (!HolidayUtils.isHoliday(exitedAt.toLocalDate())
                            && (exitedTime.equals(startTime) || exitedTime.isAfter(startTime)))
            );
        }
        //日をまたぐドライブ
        return build(
                (!HolidayUtils.isHoliday(enteredAt.toLocalDate())
                        && (enteredTime.equals(endTime) || enteredTime.isBefore(endTime))
                ) || (!HolidayUtils.isHoliday(exitedAt.toLocalDate())
                        && (exitedTime.equals(startTime) || exitedTime.isAfter(startTime)))
        );
    }
}
