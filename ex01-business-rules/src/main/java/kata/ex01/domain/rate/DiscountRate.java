package kata.ex01.domain.rate;

import kata.ex01.model.HighwayDrive;

public interface DiscountRate {
    int Get(HighwayDrive drive);
}
