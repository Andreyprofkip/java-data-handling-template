package com.epam.izh.rd.online.service;

public class SimpleTextService implements TextService {

    /**
     * Реализовать функционал удаления строки из другой строки.
     *
     * Например для базовой строки "Hello, hello, hello, how low?" и строки для удаления ", he"
     * метод вернет "Hellollollo, how low?"
     *
     * @param base - базовая строка с текстом
     * @param remove - строка которую необходимо удалить
     */
    @Override
    public String removeString(String base, String remove) {
        StringBuilder sb1 = new StringBuilder(base);
        StringBuilder sb2 = new StringBuilder(remove);

        while (sb1.indexOf(remove)>0) {
            sb1 = sb1.delete(sb1.indexOf(remove),sb1.indexOf(remove)+sb2.length());

        }


        return sb1.toString();
    }

    /**
     * Реализовать функционал проверки на то, что строка заканчивается знаком вопроса.
     *
     * Например для строки "Hello, hello, hello, how low?" метод вернет true
     * Например для строки "Hello, hello, hello!" метод вернет false
     */
    @Override
    public boolean isQuestionString(String text) {
        return text.endsWith("?");
    }

    /**
     * Реализовать функционал соединения переданных строк.
     *
     * Например для параметров {"Smells", " ", "Like", " ", "Teen", " ", "Spirit"}
     * метод вернет "Smells Like Teen Spirit"
     */
    @Override
    public String concatenate(String... elements) {
        StringBuilder sb1 = new StringBuilder(elements[0]);
        for(int i = 1; i < elements.length; i++) {
            sb1.append(elements[i]);
        }
        return sb1.toString();
    }

    /**
     * Реализовать функционал изменения регистра в вид лесенки.
     * Возвращаемый текст должен начинаться с прописного регистра.
     *
     * Например для строки "Load Up On Guns And Bring Your Friends"
     * метод вернет "lOaD Up oN GuNs aNd bRiNg yOuR FrIeNdS".
     */
    @Override
    public String toJumpCase(String text) {
        StringBuilder sb1 = new StringBuilder(text);
        for(int i = 0; i < sb1.length(); i+=2) {
            sb1.setCharAt(i, Character.toLowerCase(sb1.charAt(i)));
        }
        for(int i = 1; i < sb1.length(); i+=2) {
            sb1.setCharAt(i, Character.toUpperCase(sb1.charAt(i)));
        }
        return sb1.toString();
    }

    /**
     * Метод определяет, является ли строка палиндромом.
     *
     * Палиндром - строка, которая одинаково читается слева направо и справа налево.
     *
     * Например для строки "а роза упала на лапу Азора" вернется true, а для "я не палиндром" false
     */
    @Override
    public boolean isPalindrome(String string) {
        String workCopy = string.replaceAll(" ","");
        StringBuffer reversCopy = new StringBuffer(new StringBuffer(workCopy).reverse());
        String str = reversCopy.toString();
        return str.equalsIgnoreCase(workCopy);

    }
}
