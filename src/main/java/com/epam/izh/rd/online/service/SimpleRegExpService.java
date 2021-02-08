package com.epam.izh.rd.online.service;

import java.io.*;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleRegExpService implements RegExpService {

    /**
     * Метод должен читать файл sensitive_data.txt (из директории resources) и маскировать в нем конфиденциальную информацию.
     * Номер счета должен содержать только первые 4 и последние 4 цифры (1234 **** **** 5678). Метод должен содержать регулярное
     * выражение для поиска счета.
     *
     * @return обработанный текст
     */
    @Override
    public String maskSensitiveData() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("C:/java-data-handling-template/src/main/resources/sensitive_data.txt"));
        String content = br.readLine();
        br.close();

        Pattern pattern = Pattern.compile("\\d{4} \\d{4} \\d{4} \\d{4}");
        Matcher matcher = pattern.matcher(content);

        StringBuffer sbNewContent = new StringBuffer(content.length());
        String sNewContent = "";
        while (matcher.find()){
                sNewContent = matcher.group().substring(0,4) + " **** " + "**** " + matcher.group().substring(15,19);
                matcher.appendReplacement(sbNewContent, sNewContent);
        }
        matcher.appendTail(sbNewContent);
        return sbNewContent.toString();
    }

    /**
     * Метод должен считыввать файл sensitive_data.txt (из директории resources) и заменять плейсхолдер ${payment_amount} и ${balance} на заданные числа. Метод должен
     * содержать регулярное выражение для поиска плейсхолдеров
     *
     * @return обработанный текст
     */
    @Override
    public String replacePlaceholders(double paymentAmount, double balance) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("C:/java-data-handling-template/src/main/resources/sensitive_data.txt"));
        String content = bufferedReader.readLine();
        bufferedReader.close();
            String sPaymentAmount = DecimalFormat.getNumberInstance().format(paymentAmount);
            String sBalance =  DecimalFormat.getInstance().format(balance);

            Pattern pPayment = Pattern.compile("\\$\\{payment_amount\\}");
            Matcher mPayment = pPayment.matcher(content);
            content = mPayment.replaceAll(sPaymentAmount);

            Pattern pBalance = Pattern.compile("\\$\\{balance\\}");
            Matcher mBalance = pBalance.matcher(content);
            content = mBalance.replaceAll(sBalance);
        return content;
    }
}
