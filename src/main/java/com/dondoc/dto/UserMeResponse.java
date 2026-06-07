// ApiResponse<T>에서 T 자리에 들어갈 클래스
// monthlyBudget이랑 dailyBudget은 DB에 없고 Service에서 계산해서 여기에 담을 예정

package com.dondoc.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserMeResponse {
    private String name;
    private Integer age;
    private Integer currentPigLevel;
    private Integer currentHouseLevel;
    private Integer currentCharacterLevel;
    private Long monthlyIncome;
    private Integer targetExpenseRatio;
    private Long monthlyBudget;
    private Long dailyBudget;
}
