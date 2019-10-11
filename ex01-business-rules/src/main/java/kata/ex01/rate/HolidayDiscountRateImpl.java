package kata.ex01.rate;

import kata.ex01.model.HighwayDrive;
import kata.ex01.model.VehicleFamily;
import kata.ex01.rule.DiscountRule;

import java.util.EnumSet;

import static kata.ex01.model.VehicleFamily.*;

public class HolidayDiscountRateImpl implements DiscountRate {
    /** ETC割引ルール **/
    private DiscountRule discountRule;
    /** 割引適用あり **/
    private final int DISCOUNT_RATE = 30;
    /** 割引適用なし **/
    private final int NO_DISCOUNT_RATE = 0;
    /** 割引対象の車種 **/
    private EnumSet<VehicleFamily> vehicleFamilies = EnumSet.of(STANDARD, MINI, MOTORCYCLE);

    public HolidayDiscountRateImpl() { this.discountRule = new DiscountRule();}

    @Override
    public int Get(HighwayDrive drive) {
        var discountRule = this.discountRule.build(drive)
                .setVehicleFamilyRule(vehicleFamilies)
                .setHolidayRule()
                .setRuralRule()
                .getResult();

        if (discountRule.isDiscount()) return DISCOUNT_RATE;
        return NO_DISCOUNT_RATE;
    }
}
