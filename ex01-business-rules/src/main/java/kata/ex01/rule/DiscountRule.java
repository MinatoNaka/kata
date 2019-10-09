package kata.ex01.rule;

import kata.ex01.model.HighwayDrive;

public interface DiscountRule {
    int getDiscountRate(HighwayDrive drive);
}
