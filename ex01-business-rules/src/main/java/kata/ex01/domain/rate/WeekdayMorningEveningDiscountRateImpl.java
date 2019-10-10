package kata.ex01.domain.rate;

import kata.ex01.domain.rule.EtcDiscountRule;
import kata.ex01.model.HighwayDrive;
import kata.ex01.model.RouteType;
import kata.ex01.model.VehicleFamily;
import kata.ex01.util.HolidayUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static kata.ex01.model.VehicleFamily.*;

public class WeekdayMorningEveningDiscountRateImpl implements DiscountRate {
    /** ETC割引ルール **/
    private EtcDiscountRule _rule;
    /** 割引適用あり **/
    private final int DISCOUNT_RATE_30 = 30;
    /** 割引適用あり **/
    private final int DISCOUNT_RATE_50 = 50;
    /** 割引適用なし **/
    private final int NO_DISCOUNT_RATE = 0;
    /** 割引対象の車種 **/
    private VehicleFamily[] vehicleFamilies = {STANDARD, MINI, MOTORCYCLE};

    public WeekdayMorningEveningDiscountRateImpl(EtcDiscountRule rule)
    {
        this._rule = rule;
    }
    @Override
    public int Get(HighwayDrive drive) {
        if(( isWeekdayMorning(drive) || isWeekdayEvening(drive) ) && isDrivePerCount(drive, 10, Integer.MAX_VALUE)) return DISCOUNT_RATE_50;
        if(( isWeekdayMorning(drive) || isWeekdayEvening(drive) ) && isDrivePerCount(drive,5, 9)) return DISCOUNT_RATE_30;
        return NO_DISCOUNT_RATE;
    }

    /** 平日朝ルール **/
    private boolean isWeekdayMorning(HighwayDrive drive)
    {
        var discountRule = _rule
                .getNewInstance()
                .setWeekDay(drive.getEnteredAt().toLocalDate(), drive.getExitedAt().toLocalDate())
                .setRural(drive.getRouteType())
                .setTargetTimeSpan(LocalTime.of(6, 0), LocalTime.of(9, 0), drive.getEnteredAt(), drive.getExitedAt());

        return discountRule.is();
    }

    /** 平日夕方ルール **/
    private boolean isWeekdayEvening(HighwayDrive drive)
    {
        var discountRule = _rule
                .getNewInstance()
                .setWeekDay(drive.getEnteredAt().toLocalDate(), drive.getExitedAt().toLocalDate())
                .setRural(drive.getRouteType())
                .setTargetTimeSpan(LocalTime.of(17, 0), LocalTime.of(20, 0), drive.getEnteredAt(), drive.getExitedAt());

        return discountRule.is();
    }

    /** 乗車回数ルール **/
    private boolean isDrivePerCount(HighwayDrive drive, int min, int max)
    {
        return false;
    }
}
