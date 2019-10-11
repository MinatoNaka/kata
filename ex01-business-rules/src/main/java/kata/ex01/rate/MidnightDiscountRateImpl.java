package kata.ex01.rate;

import kata.ex01.model.HighwayDrive;
import kata.ex01.rule.DiscountRule;

public class MidnightDiscountRateImpl implements DiscountRate {
    /** ETC割引ルール **/
    private DiscountRule discountRule;
    /** 割引適用時の割引率 **/
    private final int DISCOUNT_RATE = 30;
    /** 割引適用なしの割引率 **/
    private final int NO_DISCOUNT_RATE = 0;

    public MidnightDiscountRateImpl()
    {
        this.discountRule = new DiscountRule();
    }

    @Override
    public int Get(HighwayDrive drive) {
        int StartHour = 0;
        int EndHour = 4;
        var discountRule = this.discountRule.build(drive)
                .setDiscountTime(StartHour, EndHour)
                .getResult();

        if (discountRule.isDiscount()) return DISCOUNT_RATE;
        return NO_DISCOUNT_RATE;
    }
}
