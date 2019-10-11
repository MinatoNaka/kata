package kata.ex01.rate;

import kata.ex01.model.HighwayDrive;
import kata.ex01.model.VehicleFamily;
import kata.ex01.rule.DiscountRule;

import static kata.ex01.model.VehicleFamily.*;

public class WeekdayMorningEveningDiscountRateImpl implements DiscountRate {
    /** 割引ルール **/
    private DiscountRule discountRule;
    /** 割引適用あり **/
    private final int DISCOUNT_RATE_30 = 30;
    /** 割引適用あり **/
    private final int DISCOUNT_RATE_50 = 50;
    /** 割引適用なし **/
    private final int NO_DISCOUNT_RATE = 0;
    /** 割引対象の車種 **/
    private VehicleFamily[] vehicleFamilies = {STANDARD, MINI, MOTORCYCLE};

    public WeekdayMorningEveningDiscountRateImpl(DiscountRule discountRule)
    {

        this.discountRule = discountRule;
    }

    @Override
    public int Get(HighwayDrive drive) {
        /** 朝・夕割引で利用回数10回以上　**/
        if(( isWeekdayMorning(drive) || isWeekdayEvening(drive) ) && drive.getDriver().getCountPerMonth() > 10) return DISCOUNT_RATE_50;
        /** 朝・夕割引で利用回数5回以上　**/
        if(( isWeekdayMorning(drive) || isWeekdayEvening(drive) ) && drive.getDriver().getCountPerMonth() > 5) return DISCOUNT_RATE_30;
        return NO_DISCOUNT_RATE;
    }

    /** 平日・朝・地方ルール **/
    private boolean isWeekdayMorning(HighwayDrive drive)
    {
        int StartHour = 8;
        int EndHour = 10;
        var discountRule = this.discountRule.build(drive)
                .setWeekdayRule()
                .setRuralRule()
                .setDiscountTime(StartHour, EndHour)
                .getResult();

        return discountRule.isDiscount();
    }

    /** 平日・夕方・地方ルール **/
    private boolean isWeekdayEvening(HighwayDrive drive)
    {
        int StartHour = 17;
        int EndHour = 20;
        var discountRule = this.discountRule.build(drive)
                .setWeekdayRule()
                .setRuralRule()
                .setDiscountTime(StartHour, EndHour)
                .getResult();

        return discountRule.isDiscount();
    }
}
