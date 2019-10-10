package kata.ex01.domain.rate;

import kata.ex01.domain.rule.EtcDiscountRule;
import kata.ex01.model.HighwayDrive;
import kata.ex01.model.RouteType;
import kata.ex01.model.VehicleFamily;
import kata.ex01.util.HolidayUtils;
import java.util.EnumSet;
import java.time.LocalDateTime;

import static kata.ex01.model.VehicleFamily.*;

public class HolidayDiscountRateImpl implements DiscountRate {
    /** ETC割引ルール **/
    private EtcDiscountRule _rule;
    /** 割引適用あり **/
    private final int DISCOUNT_RATE = 30;
    /** 割引適用なし **/
    private final int NO_DISCOUNT_RATE = 0;
    /** 割引対象の車種 **/
    private VehicleFamily[] vehicleFamilies = {STANDARD, MINI, MOTORCYCLE};

    public HolidayDiscountRateImpl(EtcDiscountRule rule)
    {
        this._rule = rule;
    }

    @Override
    public int Get(HighwayDrive drive) {
        var discountRule = _rule
                .getNewInstance()
                .setHoliday(drive.getEnteredAt().toLocalDate(), drive.getExitedAt().toLocalDate())
                .setVehicleFamily(drive.getVehicleFamily(), vehicleFamilies)
                .setRural(drive.getRouteType());

        if (discountRule.is()) return DISCOUNT_RATE;
        return NO_DISCOUNT_RATE;
    }
}
