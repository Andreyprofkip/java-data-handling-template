package com.epam.izh.rd.online.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class SimpleBigNumbersService implements BigNumbersService {

    /**
     * Метод делит первое число на второе с заданной точностью
     * Например 1/3 с точностью 2 = 0.33
     * @param range точность
     * @return результат
     */
    @Override
    public BigDecimal getPrecisionNumber(int a, int b, int range) {
        BigDecimal firstNumber = new BigDecimal(a);
        BigDecimal secondNumber = new BigDecimal(b);
        return firstNumber.divide(secondNumber, range, RoundingMode.HALF_UP);
    }

    /**
     * Метод находит простое число по номеру
     *
     * @param range номер числа, считая с числа 2
     * @return простое число
     */
    @Override
    public BigInteger getPrimaryNumber(int range) {
        Integer[] arr = new Integer[range+1];
        int numberArr = 1;
        int i = 3;
        boolean isPrime = true;
        while (arr[range] == null){
            for (int j = 2; j <= i/j; j++){
                 if (i % j == 0 ) {
                    isPrime = false;
                }

            }
            if (isPrime){
                arr[numberArr] = i ;
                numberArr++;
            }
            i++;
            isPrime = true;
        }
        return BigInteger.valueOf(arr[range]);
    }
}
