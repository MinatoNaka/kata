package kata.ex01.domain.rate;

import kata.ex01.domain.rule.EtcDiscountRule;
import kata.ex01.model.HighwayDrive;
import kata.ex01.model.VehicleFamily;

import java.time.LocalDateTime;
import java.time.LocalTime;

import static kata.ex01.model.VehicleFamily.*;

public class MidnightDiscountRateImpl implements DiscountRate {
    /** ETC割引ルール **/
    private EtcDiscountRule _rule;
    /** 割引適用あり **/
    private final int DISCOUNT_RATE = 30;
    /** 割引適用なし **/
    private final int NO_DISCOUNT_RATE = 0;
    /** 割引対象の車種 **/
    private VehicleFamily[] vehicleFamilies = {STANDARD, MINI, MOTORCYCLE};

    public MidnightDiscountRateImpl(EtcDiscountRule rule)
    {
        this._rule = rule;
    }

    @Override
    public int Get(HighwayDrive drive) {
        var discountRule = _rule
                .getNewInstance()
                .setTargetTimeSpan(LocalTime.of(0, 0), LocalTime.of(4, 0), drive.getEnteredAt(), drive.getExitedAt());

        if( discountRule.is() ) return  DISCOUNT_RATE;
        return NO_DISCOUNT_RATE;
    }
}
