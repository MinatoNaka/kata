package kata.ex01.rule;

import kata.ex01.model.HighwayDrive;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class MidnightDiscountImpl implements DiscountRule {
    @Override
    public int getDiscountRate(HighwayDrive drive) {
        if (this.isMidnight(drive.getEnteredAt(), drive.getExitedAt())) {
            return 30;
        }
        return 0;
    }

    private boolean isMidnight(LocalDateTime enteredAt, LocalDateTime exitedAt) {
        //todo ルールの時間範囲が日をまたぐ場合は考慮できてない
        LocalTime startTime = LocalTime.of(0, 0);
        LocalTime endTime = LocalTime.of(4, 0);

        LocalTime enteredTime = enteredAt.toLocalTime();
        LocalTime exitedTime = exitedAt.toLocalTime();

        if (enteredTime.isBefore(exitedTime)) {
            // 日をまたがないドライブ
            return (enteredTime.equals(endTime) || enteredTime.isBefore(endTime)) //todo 「<=」を書きたいだけ
                    && (exitedTime.equals(startTime) || exitedTime.isAfter(startTime));
        }

        // 日をまたぐドライブ
        return (enteredTime.equals(endTime) || enteredTime.isBefore(endTime))
                || (exitedTime.equals(startTime) || exitedTime.isAfter(startTime));
    }
}
